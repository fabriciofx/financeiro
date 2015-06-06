package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.github.fabriciofx.financeiro.dominio.eventos.TipoEvento;

public class AcordoServico {
	private double taxa;
	private Map<TipoEvento, Map<LocalDate, RegraLancamento>> regrasLancamento = new HashMap<TipoEvento, Map<LocalDate, RegraLancamento>>();

	public void addRegraLancamento(TipoEvento tipoEvento,
			RegraLancamento regra, LocalDate vigencia) {
		if (regrasLancamento.get(tipoEvento) == null) {
			regrasLancamento.put(tipoEvento,
					new HashMap<LocalDate, RegraLancamento>());
		}

		regrasLancamento.get(tipoEvento).put(vigencia, regra);
	}

	public RegraLancamento getRegraLancamento(TipoEvento tipoEvento,
			LocalDate quando) {
		return regrasLancamento.get(tipoEvento).get(quando);
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double novaTaxa) {
		taxa = novaTaxa;
	}
}