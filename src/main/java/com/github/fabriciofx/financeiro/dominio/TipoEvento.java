package com.github.fabriciofx.financeiro.dominio;

public enum TipoEvento {
	CONSUMO("Consumo"), CHAMADA("Chamada de Serviço"), IMPOSTO("Imposto");

	private final String nome;

	TipoEvento(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
