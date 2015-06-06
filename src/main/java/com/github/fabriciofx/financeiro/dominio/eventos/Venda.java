package com.github.fabriciofx.financeiro.dominio.eventos;

import java.util.Date;

import com.github.fabriciofx.financeiro.dominio.Fornecedor;
import com.github.fabriciofx.financeiro.dominio.NumeroConta;

public interface Venda extends Evento {
	public Venda novaVenda(NumeroConta conta, Date quandoOcorreu,
			Date quandoObservado, Fornecedor fornecedor, double valor);

	public Fornecedor getFornecedor();

	public double getValor();
}
