package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Conta {
	private List<Lancamento> lancamentos;
	private Currency moeda;

	public Conta() {
		this("BRL");
	}

	public Conta(final String isoMoeda) {
		this(Currency.getInstance(isoMoeda));
	}

	public Conta(Currency moeda) {
		this.lancamentos = new ArrayList<>();
		this.moeda = moeda;
	}

	public Currency getMoeda() {
		return moeda;
	}

	public void addLancamento(LocalDate data, Dinheiro valor) {
		lancamentos.add(new Lancamento(TipoLancamento.TRANSACAO, data, valor));
	}

	public void addLancamento(Lancamento lancamento) {
		lancamentos.add(lancamento);
	}

	public Dinheiro saldo(Periodo periodo) {
		Dinheiro resultado = Dinheiro.ZERO_REAL;

		for (Lancamento lancamento : lancamentos) {
			if (periodo.contem(lancamento.getData())) {
				resultado = resultado.soma(lancamento.getValor());
			}
		}

		return resultado;
	}

	public Dinheiro saldo() {
		Dinheiro resultado = Dinheiro.ZERO_REAL;

		for (Lancamento lancamento : lancamentos) {
			resultado = resultado.soma(lancamento.getValor());
		}

		return resultado;
	}

	public Dinheiro depositos(Periodo periodo) {
		Dinheiro resultado = Dinheiro.ZERO_REAL;

		for (Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor positivo
			if (periodo.contem(lancamento.getData())
					&& lancamento.getValor().compareTo(Dinheiro.ZERO_REAL) > 0) {
				resultado = resultado.soma(lancamento.getValor());
			}
		}

		return resultado;
	}

	public Dinheiro saques(Periodo periodo) {
		Dinheiro resultado = Dinheiro.ZERO_REAL;

		for (Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor negativo
			if (periodo.contem(lancamento.getData())
					&& lancamento.getValor().compareTo(Dinheiro.ZERO_REAL) < 0) {
				resultado = resultado.soma(lancamento.getValor());
			}
		}

		return resultado;
	}

	public void saque(LocalDate data, Conta para, Dinheiro valor) {
		new Transacao(data, this, para, valor);
	}

	public void deposito(LocalDate data, Conta de, Dinheiro valor) {
		new Transacao(data, de, this, valor);
	}
}