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
package com.github.fabriciofx.financeiro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventoEnvelope implements Evento {
    private TipoEvento tipo;
    private LocalDate ocorrido;
    private LocalDate observado;
    private Cliente cliente;
    private List<Lancamento> lancamentos = new ArrayList<>();
    private List<Evento> secundarios = new ArrayList<>();

    public EventoEnvelope(
        TipoEvento tipo,
        LocalDate ocorrido,
        LocalDate observado,
        Cliente cliente
    ) {
        this.tipo = tipo;
        this.ocorrido = ocorrido;
        this.observado = observado;
        this.cliente = cliente;
    }

    @Override
    public TipoEvento tipoEvento() {
        return this.tipo;
    }

    @Override
    public LocalDate ocorrido() {
        return this.ocorrido;
    }

    @Override
    public LocalDate observado() {
        return this.observado;
    }

    @Override
    public Cliente cliente() {
        return this.cliente;
    }

    @Override
    public void faz(Lancamento lancamento) {
        this.lancamentos.add(lancamento);
    }

    @Override
    public void processa() {
        RegraLancamento regra = this.cliente.acordo()
                .getRegraLancamento(this.tipoEvento(), this.ocorrido);
        if (regra == null) {
            throw new RuntimeException("regra não encontrada!");
        }
        regra.processa(this);
    }

    @Override
    public List<Lancamento> lancamentos() {
        List<Lancamento> resultado = new ArrayList<>();
        resultado.addAll(this.lancamentos);
        for (Evento evento : this.secundarios) {
            resultado.addAll(evento.lancamentos());
        }
        return resultado;
    }

    public Lancamento lancamento(Cliente cliente, int index) {
        return this.lancamentos.get(index);
    }

    public void addEventoSecundario(Evento evento) {
        // so deve ser chamado pelo metodo set do evento secundario
        this.secundarios.add(evento);
    }

}
