package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transacao {
	private List<Lancamento> lancamentos = new ArrayList<>();

	public Transacao(LocalDate data, Conta de, Conta para, Dinheiro quantia) {
		// A transação é composta de dois lançamentos, cada um de valor
		// oposto ao outro, para que as somas destes lançamentos sejam iguais
		// a zero.
		Lancamento lancamentoDe = new Lancamento(TipoLancamento.TRANSACAO,
				data, quantia.multiplica(-1));
		de.addLancamento(lancamentoDe);
		lancamentos.add(lancamentoDe);

		Lancamento lancamentoPara = new Lancamento(TipoLancamento.TRANSACAO,
				data, quantia);
		para.addLancamento(lancamentoPara);
		lancamentos.add(lancamentoPara);
	}
}