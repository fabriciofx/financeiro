package com.github.fabriciofx.financeiro.dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class TesteConta {
	@Test
	public void balancoUsandoTransacoes() {
		Conta receitas = new Conta(Moeda.BRA);
		Conta contasProteladas = new Conta(Moeda.BRA);
		Conta contasAReceber = new Conta(Moeda.BRA);

		receitas.saque(LocalDate.now(), contasAReceber, 500.00);
		receitas.saque(LocalDate.now(), contasProteladas, 200.00);

		assertEquals(500.00, contasAReceber.saldo(), 0.01);
		assertEquals(200.00, contasProteladas.saldo(), 0.01);
		assertEquals(-700.00, receitas.saldo(), 0.01);
	}
}
