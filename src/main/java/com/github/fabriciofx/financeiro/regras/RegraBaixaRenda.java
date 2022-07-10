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
package com.github.fabriciofx.financeiro.regras;

import com.github.fabriciofx.financeiro.Dinheiro;
import com.github.fabriciofx.financeiro.KWH;
import com.github.fabriciofx.financeiro.RegraLancamento;
import com.github.fabriciofx.financeiro.TipoLancamento;
import com.github.fabriciofx.financeiro.Evento;
import com.github.fabriciofx.financeiro.eventos.Consumo;

public class RegraBaixaRenda extends RegraLancamento {
    private double taxa;
    private KWH limiteDeConsumo;

    public RegraBaixaRenda(TipoLancamento tipo, double taxa, KWH limiteDeConsumo) {
        super(tipo);
        this.taxa = taxa;
        this.limiteDeConsumo = limiteDeConsumo;
    }

    protected Dinheiro calculaValor(Evento evento) {
        Consumo eventoDeConsumo = (Consumo) evento;
        double consumoAtual = eventoDeConsumo.getValor();

        return consumoAtual > limiteDeConsumo.valor() ? new Dinheiro(
                Double.toString(consumoAtual * eventoDeConsumo.getTaxa()))
                : new Dinheiro(Double.toString(consumoAtual * this.taxa));
    }
}