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
