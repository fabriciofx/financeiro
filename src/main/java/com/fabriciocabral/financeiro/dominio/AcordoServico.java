package com.fabriciocabral.financeiro.dominio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AcordoServico {
	private double taxa;
	private Map<TipoEvento, Map<Date, RegraLancamento>> regrasLancamento = new HashMap<TipoEvento, Map<Date, RegraLancamento>>();

	public void addRegraLancamento(TipoEvento tipoEvento,
			RegraLancamento regra, Date vigencia) {
		if (regrasLancamento.get(tipoEvento) == null) {
			regrasLancamento.put(tipoEvento,
					new HashMap<Date, RegraLancamento>());
		}

		regrasLancamento.get(tipoEvento).put(vigencia, regra);
	}

	public RegraLancamento getRegraLancamento(TipoEvento tipoEvento, Date quando) {
		return regrasLancamento.get(tipoEvento).get(quando);
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double novaTaxa) {
		taxa = novaTaxa;
	}
}