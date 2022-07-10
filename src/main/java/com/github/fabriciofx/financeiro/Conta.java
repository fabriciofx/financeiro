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
import java.util.Currency;
import java.util.List;

public class Conta {
    private List<Lancamento> lancamentos;
    private Currency moeda;

    public Conta() {
        this("BRL");
    }

    public Conta(final String isoMoeda) {
        this(Currency.getInstance(isoMoeda));
    }

    public Conta(Currency moeda) {
        this.lancamentos = new ArrayList<>();
        this.moeda = moeda;
    }

    public Currency getMoeda() {
        return moeda;
    }

    public void addLancamento(LocalDate data, Dinheiro valor) {
        lancamentos.add(new Lancamento(TipoLancamento.TRANSACAO, data, valor));
    }

    public void addLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public Dinheiro saldo(Periodo periodo) {
        Dinheiro resultado = Dinheiro.ZERO_REAL;

        for (Lancamento lancamento : lancamentos) {
            if (periodo.contem(lancamento.getData())) {
                resultado = resultado.soma(lancamento.getValor());
            }
        }

        return resultado;
    }

    public Dinheiro saldo() {
        Dinheiro resultado = Dinheiro.ZERO_REAL;

        for (Lancamento lancamento : lancamentos) {
            resultado = resultado.soma(lancamento.getValor());
        }

        return resultado;
    }

    public Dinheiro depositos(Periodo periodo) {
        Dinheiro resultado = Dinheiro.ZERO_REAL;

        for (Lancamento lancamento : lancamentos) {
            // veririca também se o lancamento possui um valor positivo
            if (periodo.contem(lancamento.getData())
                    && lancamento.getValor().compareTo(Dinheiro.ZERO_REAL) > 0) {
                resultado = resultado.soma(lancamento.getValor());
            }
        }

        return resultado;
    }

    public Dinheiro saques(Periodo periodo) {
        Dinheiro resultado = Dinheiro.ZERO_REAL;

        for (Lancamento lancamento : lancamentos) {
            // veririca também se o lancamento possui um valor negativo
            if (periodo.contem(lancamento.getData())
                    && lancamento.getValor().compareTo(Dinheiro.ZERO_REAL) < 0) {
                resultado = resultado.soma(lancamento.getValor());
            }
        }

        return resultado;
    }

    public void saque(LocalDate data, Conta para, Dinheiro valor) {
        new Transacao(data, this, para, valor);
    }

    public void deposito(LocalDate data, Conta de, Dinheiro valor) {
        new Transacao(data, de, this, valor);
    }
}