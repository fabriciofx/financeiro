package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.fabriciofx.financeiro.dominio.regras.TipoLancamento;

public class Transacao {
	private List<Lancamento> lancamentos = new ArrayList<>();

	public Transacao(double valor, Conta de, Conta para, LocalDate data) {
		// A transação é composta de dois lançamentos, cada um de valor
		// oposto ao outro, para que as somas destes lançamentos sejam iguais
		// a zero.
		Lancamento lancamentoDe = new Lancamento(TipoLancamento.TRANSACAO,
				data, -valor);
		de.addLancamento(lancamentoDe);
		lancamentos.add(lancamentoDe);

		Lancamento lancamentoPara = new Lancamento(TipoLancamento.TRANSACAO,
				data, valor);
		para.addLancamento(lancamentoPara);
		lancamentos.add(lancamentoPara);
	}
}