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

import com.github.fabriciofx.financeiro.temporal.SingleTemporalCollection;
import com.github.fabriciofx.financeiro.temporal.TemporalCollection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AcordoServico {
    private double taxa;
    private Map<TipoEvento, TemporalCollection<RegraLancamento>> regrasLancamento;

    public AcordoServico(double taxa) {
        this.taxa = taxa;
        this.regrasLancamento = new HashMap<>();
    }

    public void addRegraLancamento(TipoEvento tipoEvento,
            RegraLancamento regra, LocalDate vigencia) {
        if (this.regrasLancamento.get(tipoEvento) == null) {
            this.regrasLancamento.put(tipoEvento, new SingleTemporalCollection<>());
        }
        this.regrasLancamento.get(tipoEvento).put(vigencia, regra);
    }

    public RegraLancamento getRegraLancamento(TipoEvento tipoEvento,
            LocalDate quando) {
        return this.regrasLancamento.get(tipoEvento).get(quando);
    }

    public double taxa() {
        return this.taxa;
    }
}
