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

public class Lancamento {
    private final TipoLancamento tipo;
    private final LocalDate data;
    private final Dinheiro valor;

    public Lancamento(TipoLancamento tipo, LocalDate data, Dinheiro valor) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }

    public Dinheiro getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }
}