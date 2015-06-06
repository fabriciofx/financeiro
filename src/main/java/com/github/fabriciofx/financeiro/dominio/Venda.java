package com.github.fabriciofx.financeiro.dominio;

import java.util.Date;

interface Venda extends Evento {
	public Venda novaVenda(NumeroConta conta, Date quandoOcorreu,
			Date quandoObservado, Fornecedor fornecedor, double valor);

	public Fornecedor getFornecedor();

	public double getValor();
}
