package com.github.fabriciofx.financeiro.dominio.eventos;

import java.util.Date;
import java.util.List;

import com.github.fabriciofx.financeiro.dominio.Lancamento;
import com.github.fabriciofx.financeiro.dominio.NumeroConta;

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
