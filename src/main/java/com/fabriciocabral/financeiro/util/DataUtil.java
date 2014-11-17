package com.fabriciocabral.financeiro.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DataUtil {
	// Calcula a quantidade de milisegundos em um dia
	// 24 * 60 * 60 * 1000 = 86400000L (horas * minutos * segundos *
	// milisegundos)
	private static final long DIA_EM_MILISEGUNDOS = 86400000L;
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final String FORMATO_DATA_HORA = "dd/MM/yyyy hh:mm";

	private static DateFormat dfData = new SimpleDateFormat(FORMATO_DATA);
	private static DateFormat dfDataHora = new SimpleDateFormat(
			FORMATO_DATA_HORA);

	private DataUtil() {
	}

	public static Date convertaData(final String data) {
		try {
			dfData.setLenient(false);
			return dfData.parse(data);
		} catch (Exception e) {
			throw new IllegalArgumentException("data inválida!");
		}
	}

	public static String convertaData(final Date data) {
		try {
			dfData.setLenient(false);
			return dfData.format(data);
		} catch (Exception e) {
			throw new IllegalArgumentException("data inválida!");
		}
	}

	public static Date convertaDataHora(final String data, final String hora) {
		try {
			dfDataHora.setLenient(false);
			return dfDataHora.parse(data + " " + hora);
		} catch (Exception e) {
			throw new IllegalArgumentException("data ou hora inválida!");
		}
	}

	public static Date hoje() {
		return new Date();
	}

	public static Date amanha() {
		return new Date(hoje().getTime() + DIA_EM_MILISEGUNDOS);
	}
}
