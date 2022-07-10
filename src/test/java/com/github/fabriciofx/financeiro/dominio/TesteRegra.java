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

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;
import com.github.fabriciofx.financeiro.dominio.regras.RegraBaixaRenda;
import com.github.fabriciofx.financeiro.dominio.regras.RegraFormulaSimples;
import com.github.fabriciofx.financeiro.dominio.regras.RegraMultiplicaPorTaxa;

public class TesteRegra {
    private Cliente clienteNormal, clienteBaixaRenda;

    private Cliente criaClienteNormal() {
        Cliente clienteNormal = new Cliente("Cafe A Margot");

        AcordoServico padrao = new AcordoServico();
        padrao.setTaxa(10);

        padrao.addRegraLancamento(TipoEvento.CONSUMO,
                new RegraMultiplicaPorTaxa(TipoLancamento.CONSUMO_BASICO),
                LocalDate.of(2015, 1, 1));

        padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
                TipoLancamento.SERVICO, 0.5, new Dinheiro("10.00")), LocalDate
                .of(2015, 1, 1));

        padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
                TipoLancamento.SERVICO, 0.5, new Dinheiro("15.00")), LocalDate
                .of(2015, 10, 1));

        padrao.addRegraLancamento(TipoEvento.IMPOSTO, new RegraFormulaSimples(
                TipoLancamento.IMPOSTO, 0.055, Dinheiro.ZERO_REAL), LocalDate
                .of(2015, 1, 1));

        clienteNormal.setAcordoServico(padrao);

        return clienteNormal;
    }

    private Cliente criaClienteBaixaRenda() {
        Cliente clienteBaixaRenda = new Cliente("José Severino da Silva");

        AcordoServico baixaRenda = new AcordoServico();
        baixaRenda.setTaxa(10);

        baixaRenda.addRegraLancamento(TipoEvento.CONSUMO, new RegraBaixaRenda(
                TipoLancamento.CONSUMO_BASICO, 5, new KWH(50)), LocalDate.of(
                2015, 1, 1));

        baixaRenda.addRegraLancamento(TipoEvento.CHAMADA,
                new RegraFormulaSimples(TipoLancamento.SERVICO, 0,
                        new Dinheiro("10.00")), LocalDate.of(2015, 10, 1));

        baixaRenda.addRegraLancamento(TipoEvento.IMPOSTO,
                new RegraFormulaSimples(TipoLancamento.IMPOSTO, 0.055, Dinheiro.ZERO_REAL),
                LocalDate.of(2015, 1, 1));

        clienteBaixaRenda.setAcordoServico(baixaRenda);

        return clienteBaixaRenda;
    }

    @Before
    public void inicializa() {
        clienteNormal = criaClienteNormal();
        clienteBaixaRenda = criaClienteBaixaRenda();
    }

    @Test
    public void consumo() {
        Consumo evento = new Consumo(LocalDate.of(2015, 10, 25), LocalDate.of(
                2015, 10, 25), clienteNormal, new KWH(50));
        evento.processa();
        Lancamento lancamentoResultante = evento
                .getLancamento(clienteNormal, 0);

        assertEquals(new Dinheiro("500.00"), lancamentoResultante.getValor());
    }

    @Test
    public void servico() {
        Evento evento = new EventoMonetario(TipoEvento.CHAMADA, LocalDate.of(
                2015, 5, 25), LocalDate.of(2015, 5, 25), clienteNormal,
                new Dinheiro("40.00"));
        evento.processa();
        Lancamento lancamentoResultante = clienteNormal.getLancamentos().get(0);

        assertEquals(new Dinheiro("30.00"), lancamentoResultante.getValor());
    }

    @Test
    public void servicoDepoisDaMudanca() {
        Evento evento = new EventoMonetario(TipoEvento.CHAMADA, LocalDate.of(
                2015, 10, 5), LocalDate.of(2015, 10, 15), clienteNormal,
                new Dinheiro("40.00"));
        evento.processa();
        Lancamento lancamentoResultante = clienteNormal.getLancamentos().get(0);

        assertEquals(new Dinheiro("35.00"), lancamentoResultante.getValor());
    }

    @Test
    public void consumoBaixaRenda() {
        Consumo evento = new Consumo(LocalDate.of(2015, 10, 1), LocalDate.of(
                2015, 10, 1), clienteBaixaRenda, new KWH(50));
        evento.processa();

        Consumo evento2 = new Consumo(LocalDate.of(2015, 10, 2), LocalDate.of(
                2015, 10, 2), clienteBaixaRenda, new KWH(51));
        evento2.processa();

        Lancamento lancamentoResultante1 = clienteBaixaRenda.getLancamentos()
                .get(0);
        Lancamento lancamentoResultante2 = clienteBaixaRenda.getLancamentos()
                .get(1);

        assertEquals(new Dinheiro("250.00"), lancamentoResultante1.getValor());
        assertEquals(new Dinheiro("13.75"), lancamentoResultante2.getValor());
    }

    // @Test
    // public void consumoComImposto() {
    // Consumo evento = new Consumo(LocalDate.now(), LocalDate.now(),
    // clienteNormal, 50);
    // evento.processa();
    // Lancamento lancamentoConsumo = evento.getLancamento(clienteNormal, 0);
    // Lancamento lancamentoImposto = evento.getLancamento(clienteNormal, 1);
    //
    // assertEquals(500.00, lancamentoConsumo.getValor(), 0.01);
    // assertEquals(TipoLancamento.CONSUMO_BASICO, lancamentoConsumo.getTipo());
    //
    //
    // assertEquals(27.5, lancamentoImposto.getValor(), 0.01);
    // assertEquals(TipoLancamento.IMPOSTO, lancamentoImposto.getTipo());
    // assertTrue(evento.getTodosLancamentosResultantes().contains(
    // lancamentoConsumo));
    // assertTrue(evento.getTodosLancamentosResultantes().contains(
    // lancamentoImposto));
    // }
}
