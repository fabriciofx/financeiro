package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.fabriciofx.financeiro.dominio.regras.TipoLancamento;

public class Conta {
	private List<Lancamento> lancamentos;
	private Moeda moeda;

	public Conta(Moeda moeda) {
		this.lancamentos = new ArrayList<>();
		this.moeda = moeda;
	}

	public Moeda getMoeda() {
		return moeda;
	}

	public void addLancamento(double valor, LocalDate data) {
		// assert(moeda.equals(valor.Moeda()));
		lancamentos.add(new Lancamento(TipoLancamento.TRANSACAO, data, valor));
	}

	public void addLancamento(Lancamento lancamento) {
		lancamentos.add(lancamento);
	}

	public double saldo(Periodo periodo) {
		double resultado = 0.0;

		for (Lancamento lancamento : lancamentos) {
			if (periodo.contem(lancamento.getData())) {
				resultado += lancamento.getValor();
			}
		}

		return resultado;
	}

	public double saldo() {
		return saldo(new Periodo());
	}

	public double depositos(Periodo periodo) {
		double resultado = 0.0;

		for (Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor positivo
			if (periodo.contem(lancamento.getData())
					&& lancamento.getValor() > 0) {
				resultado += lancamento.getValor();
			}
		}

		return resultado;
	}

	public double saques(Periodo periodo) {
		double resultado = 0.0;

		for (Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor negativo
			if (periodo.contem(lancamento.getData())
					&& lancamento.getValor() < 0) {
				resultado = resultado + lancamento.getValor();
			}
		}

		return resultado;
	}

	public void saque(double valor, Conta alvo, LocalDate data) {
		new Transacao(valor, this, alvo, data);
	}

	public void deposito(double valor, Conta de, LocalDate data) {
		new Transacao(valor, de, this, data);
	}
}