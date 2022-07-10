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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Periodo {
	private LocalDate inicio;
	private LocalDate termino;

	public Periodo() {
		this.inicio = LocalDate.now();
		this.termino = LocalDate.now();
	}

	public Periodo(final LocalDate inicio, final LocalDate termino) {
		this.inicio = inicio;
		this.termino = termino;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public static Periodo de(final String dataInicio) {
		LocalDate data = LocalDate.parse(dataInicio,
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return new Periodo(data, data);
	}

	public Periodo ate(final String dataTermino) {
		this.termino = LocalDate.parse(dataTermino,
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return this;
	}

	public long emHoras() {
		return ChronoUnit.HOURS.between(inicio, termino);
	}

	@Override
	public String toString() {
		final DateTimeFormatter formato = DateTimeFormatter
				.ofPattern("dd/MM/yyyy");

		return inicio.format(formato) + " a " + termino.format(formato);
	}

	public boolean contem(LocalDate data) {
		return false;
	}
}
