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

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class TesteConta {
    @Test
    public void balancoUsandoTransacoes() {
        Conta receitas = new Conta();
        Conta contasProteladas = new Conta();
        Conta contasAReceber = new Conta();

        receitas.saque(LocalDate.now(), contasAReceber, new Dinheiro("500.00"));
        receitas.saque(LocalDate.now(), contasProteladas,
                new Dinheiro("200.00"));

        assertEquals(new Dinheiro("500.00"), contasAReceber.saldo());
        assertEquals(new Dinheiro("200.00"), contasProteladas.saldo());
        assertEquals(new Dinheiro("700.00").multiplica(-1), receitas.saldo());
    }
}
