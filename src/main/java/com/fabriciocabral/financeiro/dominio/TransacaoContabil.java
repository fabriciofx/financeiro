package com.fabriciocabral.financeiro.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransacaoContabil {
	private List<Lancamento> lancamentos = new ArrayList<>();

	public TransacaoContabil(double valor, Conta de, Conta para, Date data) {
		// A transação é composta de dois lançamentos, cada um de valor
		// oposto ao outro, para que as somas destes lançamentos sejam iguais
		// a zero.
		Lancamento lancamentoDe = new Lancamento(-valor, data,
				TipoLancamento.TRANSACAO);
		de.addLancamento(lancamentoDe);
		lancamentos.add(lancamentoDe);

		Lancamento lancamentoPara = new Lancamento(valor, data,
				TipoLancamento.TRANSACAO);
		para.addLancamento(lancamentoPara);
		lancamentos.add(lancamentoPara);
	}
}