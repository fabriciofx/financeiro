package com.fabriciocabral.financeiro.dominio;

import java.util.Date;
import java.util.List;

public interface Evento {
	public Evento novoEvento(TipoEvento tipo, NumeroConta conta,
			Date quandoOcorreu, Date quandoObservado);

	public TipoEvento getTipo();

	public NumeroConta getConta();

	public Date getQuandoOcorreu();

	public Date getQuandoObservado();

	public boolean jaFoiProcessado();

	public List<Lancamento> getLancamentosResultantes();

	public void addLancamentoResultante(Lancamento lancamento);
}
