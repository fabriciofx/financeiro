package com.github.fabriciofx.financeiro.dominio;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public final class Dinheiro implements Comparable<Dinheiro> {
	public static final Dinheiro ZERO_REAL = new Dinheiro("0.00");
	public static final Dinheiro UM_REAL = new Dinheiro("1.00");

	private final long quantia;
	private final Currency moeda;

	private static long deBigDecimalParaLong(final BigDecimal quantia,
			final Currency moeda) {
		return quantia.movePointRight(moeda.getDefaultFractionDigits())
				.longValue();
	}

	private static BigDecimal deLongParaBigDecimal(final long quantia,
			final Currency moeda) {
		return new BigDecimal(quantia).movePointLeft(moeda
				.getDefaultFractionDigits());
	}

	private void verificaMoeda(final Dinheiro dinheiro) {
		if (!Objects.nonNull(dinheiro) || !moeda.equals(dinheiro.moeda)) {
			throw new IllegalArgumentException("quantia ou moeda inválida!");
		}
	}

	public Dinheiro(final String quantia) {
		this(quantia, "BRL");
	}

	public Dinheiro(final String quantia, final String isoMoeda) {
		this(new BigDecimal(quantia), isoMoeda);
	}

	public Dinheiro(final BigDecimal quantia, final String isoMoeda) {
		this(quantia, Currency.getInstance(isoMoeda));
	}

	public Dinheiro(final BigDecimal quantia, final Currency moeda) {
		this(deBigDecimalParaLong(quantia, moeda), moeda);
	}

	public Dinheiro(final long quantia, final Currency moeda) {
		this.quantia = quantia;
		this.moeda = Objects.requireNonNull(moeda);
	}

	public Currency moeda() {
		return moeda;
	}

	public BigDecimal quantia() {
		return deLongParaBigDecimal(quantia, moeda);
	}

	public Dinheiro negativa() {
		return multiplica(-1);
	}

	public boolean estaNegativo() {
		return quantia < 0L ? true : false;
	}

	public Dinheiro soma(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);

		return new Dinheiro(quantia + dinheiro.quantia, moeda);
	}

	public Dinheiro subtraia(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);

		return new Dinheiro(quantia - dinheiro.quantia, moeda);
	}

	public Dinheiro multiplica(final BigDecimal numero) {
		return new Dinheiro(numero.multiply(new BigDecimal(quantia))
				.longValue(), moeda);
	}

	public Dinheiro multiplica(final Number numero) {
		return multiplica(new BigDecimal(numero.toString()));
	}

	public Dinheiro[] parcela(final int numero) {
		final long quociente = quantia / numero;
		final long resto = quantia % numero;
		final Dinheiro[] parcelas = new Dinheiro[numero];

		for (int i = 0; i < parcelas.length - 1; i++) {
			parcelas[i] = new Dinheiro(quociente, moeda);
		}

		parcelas[parcelas.length - 1] = new Dinheiro(quociente + resto, moeda);

		return parcelas;
	}

	public String toString(final Locale localizacao) {
		if (localizacao == null) {
			throw new IllegalArgumentException("localização inválida!");
		}

		final NumberFormat formatoMoeda = NumberFormat.getInstance(localizacao);
		formatoMoeda.setMinimumFractionDigits(moeda.getDefaultFractionDigits());

		return moeda.getSymbol() + " " + formatoMoeda.format(quantia());
	}

	@Override
	public String toString() {
		return toString(new Locale("pt", "BR"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((moeda == null) ? 0 : moeda.getCurrencyCode().hashCode());
		result = prime * result + (int) (quantia ^ (quantia >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (getClass() != o.getClass())
			return false;

		Dinheiro d = (Dinheiro) o;

		if (quantia != d.quantia)
			return false;

		if (moeda == null && d.moeda != null)
			return false;

		if (!moeda.getCurrencyCode().equals(d.moeda.getCurrencyCode()))
			return false;

		return true;
	}

	@Override
	public int compareTo(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);

		if (quantia > dinheiro.quantia)
			return 1;
		else if (quantia < dinheiro.quantia) {
			return -1;
		}

		return 0;
	}
}
