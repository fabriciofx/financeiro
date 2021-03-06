package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
	private TipoEvento tipo;
	private LocalDate quandoOcorreu;
	private LocalDate quandoObservado;
	private Cliente cliente;
	private List<Lancamento> lancamentosResultantes = new ArrayList<>();
	private List<Evento> eventosSecundarios = new ArrayList<>();

	public Evento(TipoEvento tipo, LocalDate quandoOcorreu,
			LocalDate quandoObservado, Cliente cliente) {
		this.tipo = tipo;
		this.quandoOcorreu = quandoOcorreu;
		this.quandoObservado = quandoObservado;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public TipoEvento getTipoEvento() {
		return tipo;
	}

	public LocalDate getQuandoObservado() {
		return quandoObservado;
	}

	public LocalDate getQuandoOcorreu() {
		return quandoOcorreu;
	}

	public void addLancamentoResultante(Lancamento lancamento) {
		lancamentosResultantes.add(lancamento);
	}

	public Lancamento getLancamento(Cliente cliente, int index) {
		return lancamentosResultantes.get(index);
	}

	public void processa() {
		achaRegra().processa(this);
	}

	public RegraLancamento achaRegra() {
		RegraLancamento regra = cliente.getAcordoServico().getRegraLancamento(
				this.getTipoEvento(), this.quandoOcorreu);

		if (regra == null) {
			throw new RuntimeException("regra não encontrada!");
		}

		return regra;
	}

	public void addEventoSecundario(Evento evento) {
		// so deve ser chamado pelo metodo set do evento secundario
		eventosSecundarios.add(evento);
	}

	public List<Lancamento> getTodosLancamentosResultantes() {
		List<Lancamento> resultado = new ArrayList<>();
		resultado.addAll(lancamentosResultantes);

		for (Evento evento : eventosSecundarios) {
			resultado.addAll(evento.getTodosLancamentosResultantes());
		}

		return resultado;
	}
}