package com.github.fabriciofx.financeiro.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoContabil {
	private TipoEvento tipo;
	private Date quandoOcorreu;
	private Date quandoObservado;
	private Cliente cliente;
	private List<Lancamento> lancamentosResultantes = new ArrayList<>();
	private List<EventoContabil> eventosSecundarios = new ArrayList<>();

	public EventoContabil(TipoEvento tipo, Date quandoOcorreu,
			Date quandoObservado, Cliente cliente) {
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

	public Date getQuandoObservado() {
		return quandoObservado;
	}

	public Date getQuandoOcorreu() {
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

		return regra;
	}

	public void addEventoSecundario(EventoContabil evento) {
		// so deve ser chamado pelo metodo set do evento secundario
		eventosSecundarios.add(evento);
	}

	public List<Lancamento> getTodosLancamentosResultantes() {
		List<Lancamento> resultado = new ArrayList<>();
		resultado.addAll(lancamentosResultantes);

		for (EventoContabil evento : eventosSecundarios) {
			resultado.addAll(evento.getTodosLancamentosResultantes());
		}

		return resultado;
	}
}