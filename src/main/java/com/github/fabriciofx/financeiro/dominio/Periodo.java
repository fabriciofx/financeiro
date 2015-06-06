package com.github.fabriciofx.financeiro.dominio;

import java.util.Date;

import com.github.fabriciofx.financeiro.util.DataUtil;

public class Periodo {
	private Date inicio;
	private Date termino;

	public Periodo() {
		this.inicio = DataUtil.hoje();
		this.termino = DataUtil.hoje();
	}

	public Periodo(final Date inicio, final Date termino) {
		this.inicio = inicio;
		this.termino = termino;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public static Periodo de(final String dataInicio) {
		return new Periodo(DataUtil.convertaData(dataInicio),
				DataUtil.convertaData(dataInicio));
	}

	public Periodo ate(final String dataTermino) {
		this.termino = DataUtil.convertaData(dataTermino);

		return this;
	}

	public long emHoras() {
		long tempo = termino.getTime() - inicio.getTime();

		return tempo / (1000L * 60L * 60L);
	}

	@Override
	public String toString() {
		return DataUtil.convertaData(inicio) + " a "
				+ DataUtil.convertaData(termino);
	}

	public boolean contem(Date data) {
		return inicio.after(data) && termino.before(data);
	}
}
