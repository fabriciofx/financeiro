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

public class Transacao {
	private List<Lancamento> lancamentos = new ArrayList<>();

	public Transacao(LocalDate data, Conta de, Conta para, Dinheiro quantia) {
		// A transação é composta de dois lançamentos, cada um de valor
		// oposto ao outro, para que as somas destes lançamentos sejam iguais
		// a zero.
		Lancamento lancamentoDe = new Lancamento(TipoLancamento.TRANSACAO,
				data, quantia.negativa());
		de.addLancamento(lancamentoDe);
		lancamentos.add(lancamentoDe);

		Lancamento lancamentoPara = new Lancamento(TipoLancamento.TRANSACAO,
				data, quantia);
		para.addLancamento(lancamentoPara);
		lancamentos.add(lancamentoPara);
	}
}