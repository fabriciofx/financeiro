package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.Evento;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;
import com.github.fabriciofx.financeiro.dominio.eventos.TipoEvento;
import com.github.fabriciofx.financeiro.dominio.regras.RegraBaixaRenda;
import com.github.fabriciofx.financeiro.dominio.regras.RegraFormulaSimples;
import com.github.fabriciofx.financeiro.dominio.regras.RegraMultiplicaPorTaxa;
import com.github.fabriciofx.financeiro.dominio.regras.TipoLancamento;

public class TesteDominio {
	public static Cliente configuraClienteNormal() {
		Cliente cam = new Cliente("Cafe A Margot");

		AcordoServico padrao = new AcordoServico();
		padrao.setTaxa(10);
		padrao.addRegraLancamento(TipoEvento.CONSUMO,
				new RegraMultiplicaPorTaxa(TipoLancamento.CONSUMO_BASICO),
				LocalDate.now());
		padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
				0.5, 10.0, TipoLancamento.SERVICO), LocalDate.now());
		padrao.addRegraLancamento(TipoEvento.IMPOSTO, new RegraFormulaSimples(
				0.055, 0, TipoLancamento.IMPOSTO), LocalDate.now());
		cam.setAcordoServico(padrao);

		return cam;
	}

	public static Cliente configuraClienteBaixaRenda() {
		Cliente zé = new Cliente("José Severino da Silva");

		AcordoServico baixaRenda = new AcordoServico();
		baixaRenda.setTaxa(10);
		baixaRenda.addRegraLancamento(TipoEvento.CONSUMO, new RegraBaixaRenda(
				TipoLancamento.CONSUMO_BASICO, 5, 50), LocalDate.now());
		baixaRenda.addRegraLancamento(TipoEvento.CHAMADA,
				new RegraFormulaSimples(0, 10.0, TipoLancamento.SERVICO),
				LocalDate.now());
		zé.setAcordoServico(baixaRenda);

		return zé;
	}

	public static void testConsumo() {
		Cliente cam = configuraClienteNormal();

		Consumo evento = new Consumo(LocalDate.now(), LocalDate.now(), cam, 50);
		evento.processa();

		Lancamento lancamentoResultante = evento.getLancamento(cam, 0);
		System.out.println(lancamentoResultante.getValor());
	}

	public void testConsumo2() {
		Cliente cam = configuraClienteNormal();
		Consumo evento = new Consumo(LocalDate.now(), LocalDate.now(), cam, 50);
		evento.processa();
		Lancamento lancamentoConsumo = evento.getLancamento(cam, 0);
		Lancamento lancamentoImposto = evento.getLancamento(cam, 1);

		// assertEquals(Money.reais(500), lancamentoConsumo.getValor());
		// assertEquals(TipoLancamento.CONSUMO_BASICO,
		// lancamentoConsumo.getTipo());
		// assertEquals(Money.reais(27.5), lancamentoImposto.getValor());
		// assertEquals(TipoLancamento.IMPOSTO, lancamentoImposto.getTipo());
		// assert(evento.getlancamentosResultantes().contains(lancamentoConsumo));
		// assert(evento.getTodosLancamentosResultantes().contains(lancamentoImposto));
	}

	public static void testConsumoBaixaRenda() {
		Cliente zé = configuraClienteBaixaRenda();

		Consumo evento = new Consumo(LocalDate.now(), LocalDate.now(), zé, 50);
		evento.processa();

		Consumo evento2 = new Consumo(LocalDate.now(), LocalDate.now(), zé, 51);
		evento2.processa();

		Lancamento lancamentoResultante1 = zé.getLancamentos().get(0);
		System.out.println(lancamentoResultante1.getValor());

		Lancamento lancamentoResultante2 = zé.getLancamentos().get(1);
		System.out.println(lancamentoResultante2.getValor());
	}

	public static void testServicoDepoisDaMudanca() {
		Cliente cam = configuraClienteNormal();

		Evento evento = new EventoMonetario(TipoEvento.CHAMADA,
				LocalDate.now(), LocalDate.now(), cam, 40.0);
		evento.processa();

		Lancamento lancamentoResultante = cam.getLancamentos().get(0);
		System.out.println(lancamentoResultante.getValor());
	}

	public static void testBalancoUsandoTransacoes() {
		Conta receitas = new Conta(Moeda.BRA);
		Conta contasProteladas = new Conta(Moeda.BRA);
		Conta contasAReceber = new Conta(Moeda.BRA);

		receitas.saque(LocalDate.now(), contasAReceber, 500);
		receitas.saque(LocalDate.now(), contasProteladas, 200);

		// assertEquals(500, contasAReceber.saldo());
		// assertEquals(200, contasProteladas.saldo());
		// assertEquals(-700, receitas.saldo());
	}

	public static void main(String[] args) {
		configuraClienteNormal();
	}
}
