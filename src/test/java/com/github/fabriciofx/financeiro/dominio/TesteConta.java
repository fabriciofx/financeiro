package com.github.fabriciofx.financeiro.dominio;

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
