package com.github.fabriciofx.financeiro.dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;
import com.github.fabriciofx.financeiro.dominio.regras.RegraBaixaRenda;
import com.github.fabriciofx.financeiro.dominio.regras.RegraFormulaSimples;
import com.github.fabriciofx.financeiro.dominio.regras.RegraMultiplicaPorTaxa;

public class TesteRegra {
	private Cliente clienteNormal, clienteBaixaRenda;

	private Cliente criaClienteNormal() {
		Cliente clienteNormal = new Cliente("Cafe A Margot");

		AcordoServico padrao = new AcordoServico();
		padrao.setTaxa(10);
		
		padrao.addRegraLancamento(TipoEvento.CONSUMO,
				new RegraMultiplicaPorTaxa(TipoLancamento.CONSUMO_BASICO),
				LocalDate.of(2015, 1, 1));
		
		padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
				TipoLancamento.SERVICO, 0.5, 10.00), LocalDate.of(2015, 1, 1));
		
		padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
				TipoLancamento.SERVICO, 0.5, 15.00), LocalDate.of(2015, 10, 1));
		
		padrao.addRegraLancamento(TipoEvento.IMPOSTO, new RegraFormulaSimples(
				TipoLancamento.IMPOSTO, 0.055, 0.00), LocalDate.of(2015, 1, 1));
		
		clienteNormal.setAcordoServico(padrao);

		return clienteNormal;
	}

	private Cliente criaClienteBaixaRenda() {
		Cliente clienteBaixaRenda = new Cliente("José Severino da Silva");

		AcordoServico baixaRenda = new AcordoServico();
		baixaRenda.setTaxa(10);
		
		baixaRenda.addRegraLancamento(TipoEvento.CONSUMO, new RegraBaixaRenda(
						TipoLancamento.CONSUMO_BASICO, 5, 50), LocalDate.of(
						2015, 1, 1));
		
		baixaRenda.addRegraLancamento(TipoEvento.CHAMADA,
				new RegraFormulaSimples(TipoLancamento.SERVICO, 0, 10.00),
				LocalDate.of(2015, 10, 1));

		baixaRenda.addRegraLancamento(TipoEvento.IMPOSTO, new RegraFormulaSimples(
				TipoLancamento.IMPOSTO, 0.055, 0.00), LocalDate.of(2015, 1, 1));

		clienteBaixaRenda.setAcordoServico(baixaRenda);

		return clienteBaixaRenda;
	}

	@Before
	public void inicializa() {
		clienteNormal = criaClienteNormal();
		clienteBaixaRenda = criaClienteBaixaRenda();
	}

	@Test
	public void consumo() {
		Consumo evento = new Consumo(LocalDate.of(2015, 10, 25), LocalDate.of(
				2015, 10, 25), clienteNormal, 50);
		evento.processa();
		Lancamento lancamentoResultante = evento
				.getLancamento(clienteNormal, 0);

		assertEquals(500.0, lancamentoResultante.getValor(), 0.01);
	}

	@Test
	public void servico() {
		Evento evento = new EventoMonetario(TipoEvento.CHAMADA, LocalDate.of(
				2015, 5, 25), LocalDate.of(2015, 5, 25), clienteNormal, 40.00);
		evento.processa();
		Lancamento lancamentoResultante = clienteNormal.getLancamentos().get(0);

		assertEquals(30.00, lancamentoResultante.getValor(), 0.01);
	}

	@Test
	public void servicoDepoisDaMudanca() {
		Evento evento = new EventoMonetario(TipoEvento.CHAMADA, LocalDate.of(
				2015, 10, 5), LocalDate.of(2015, 10, 15), clienteNormal, 40.00);
		evento.processa();
		Lancamento lancamentoResultante = clienteNormal.getLancamentos().get(0);

		assertEquals(35.00, lancamentoResultante.getValor(), 0.01);
	}

	@Test
	public void consumoBaixaRenda() {
		Consumo evento = new Consumo(LocalDate.of(2015, 10, 1), LocalDate.of(
				2015, 10, 1), clienteBaixaRenda, 50);
		evento.processa();

		Consumo evento2 = new Consumo(LocalDate.of(2015, 10, 2), LocalDate.of(
				2015, 10, 2), clienteBaixaRenda, 51);
		evento2.processa();

		Lancamento lancamentoResultante1 = clienteBaixaRenda.getLancamentos()
				.get(0);
		Lancamento lancamentoResultante2 = clienteBaixaRenda.getLancamentos()
				.get(1);

		assertEquals(250.0, lancamentoResultante1.getValor(), 0.01);
		assertEquals(13.75, lancamentoResultante2.getValor(), 0.01);
	}

//	@Test
//	public void consumoComImposto() {
//		Consumo evento = new Consumo(LocalDate.now(), LocalDate.now(),
//				clienteNormal, 50);
//		evento.processa();
//		Lancamento lancamentoConsumo = evento.getLancamento(clienteNormal, 0);
//		Lancamento lancamentoImposto = evento.getLancamento(clienteNormal, 1);
//
//		assertEquals(500.00, lancamentoConsumo.getValor(), 0.01);
//		assertEquals(TipoLancamento.CONSUMO_BASICO, lancamentoConsumo.getTipo());
//
//		
//		assertEquals(27.5, lancamentoImposto.getValor(), 0.01);
//		assertEquals(TipoLancamento.IMPOSTO, lancamentoImposto.getTipo());
//		assertTrue(evento.getTodosLancamentosResultantes().contains(
//				lancamentoConsumo));
//		assertTrue(evento.getTodosLancamentosResultantes().contains(
//				lancamentoImposto));
//	}
}
