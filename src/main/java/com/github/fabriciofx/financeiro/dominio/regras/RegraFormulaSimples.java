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
package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;

public class RegraFormulaSimples extends RegraLancamento {
	private double multiplicador;
	private Dinheiro valorFixo;

	public RegraFormulaSimples(TipoLancamento tipo, double multiplicador,
			Dinheiro valorFixo) {
		super(tipo);
		this.multiplicador = multiplicador;
		this.valorFixo = valorFixo;
	}

	protected Dinheiro calculaValor(Evento evento) {
		Dinheiro valorDoEvento = ((EventoMonetario) evento).getValor();

		return valorDoEvento.multiplica(multiplicador).soma(valorFixo);
	}
}