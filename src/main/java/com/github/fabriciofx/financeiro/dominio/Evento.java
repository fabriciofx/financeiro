/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2015-2022 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private TipoEvento tipo;
    private LocalDate quandoOcorreu;
    private LocalDate quandoObservado;
    private Cliente cliente;
    private List<Lancamento> lancamentosResultantes = new ArrayList<>();
    private List<Evento> eventosSecundarios = new ArrayList<>();

    public Evento(TipoEvento tipo, LocalDate quandoOcorreu,
            LocalDate quandoObservado, Cliente cliente) {
        this.tipo = tipo;
        this.quandoOcorreu = quandoOcorreu;
        this.quandoObservado = quandoObservado;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoEvento getTipoEvento() {
        return tipo;
    }

    public LocalDate getQuandoObservado() {
        return quandoObservado;
    }

    public LocalDate getQuandoOcorreu() {
        return quandoOcorreu;
    }

    public void addLancamentoResultante(Lancamento lancamento) {
        lancamentosResultantes.add(lancamento);
    }

    public Lancamento getLancamento(Cliente cliente, int index) {
        return lancamentosResultantes.get(index);
    }

    public void processa() {
        achaRegra().processa(this);
    }

    public RegraLancamento achaRegra() {
        RegraLancamento regra = cliente.getAcordoServico().getRegraLancamento(
                this.getTipoEvento(), this.quandoOcorreu);

        if (regra == null) {
            throw new RuntimeException("regra não encontrada!");
        }

        return regra;
    }

    public void addEventoSecundario(Evento evento) {
        // so deve ser chamado pelo metodo set do evento secundario
        eventosSecundarios.add(evento);
    }

    public List<Lancamento> getTodosLancamentosResultantes() {
        List<Lancamento> resultado = new ArrayList<>();
        resultado.addAll(lancamentosResultantes);

        for (Evento evento : eventosSecundarios) {
            resultado.addAll(evento.getTodosLancamentosResultantes());
        }

        return resultado;
    }
}