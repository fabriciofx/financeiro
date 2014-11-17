package com.fabriciocabral.financeiro.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {
	private String nome;
	private AcordoServico acordoServico;
	private List<Lancamento> lancamentos = new ArrayList<>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addLancamento(Lancamento lancamento) {
		lancamentos.add(lancamento);
	}

	public List<Lancamento> getLancamentos() {
		return Collections.unmodifiableList(lancamentos);
	}

	public AcordoServico getAcordoServico() {
		return acordoServico;
	}

	public void setAcordoServico(AcordoServico acordo) {
		acordoServico = acordo;
	}
}
