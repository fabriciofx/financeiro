package financeiro;

import java.util.Date;

import com.fabriciocabral.financeiro.dominio.AcordoServico;
import com.fabriciocabral.financeiro.dominio.Cliente;
import com.fabriciocabral.financeiro.dominio.Consumo;
import com.fabriciocabral.financeiro.dominio.Conta;
import com.fabriciocabral.financeiro.dominio.EventoContabil;
import com.fabriciocabral.financeiro.dominio.EventoMonetario;
import com.fabriciocabral.financeiro.dominio.Lancamento;
import com.fabriciocabral.financeiro.dominio.Moeda;
import com.fabriciocabral.financeiro.dominio.RegraBaixaRenda;
import com.fabriciocabral.financeiro.dominio.RegraFormulaSimples;
import com.fabriciocabral.financeiro.dominio.RegraMultiplicaPorTaxa;
import com.fabriciocabral.financeiro.dominio.TipoEvento;
import com.fabriciocabral.financeiro.dominio.TipoLancamento;

public class TesteDominio {
	public static Cliente configuraClienteNormal() {
		Cliente cam = new Cliente("Cafe A Margot");

		AcordoServico padrao = new AcordoServico();
		padrao.setTaxa(10);
		padrao.addRegraLancamento(TipoEvento.CONSUMO,
				new RegraMultiplicaPorTaxa(TipoLancamento.CONSUMO_BASICO),
				new Date());
		padrao.addRegraLancamento(TipoEvento.CHAMADA, new RegraFormulaSimples(
				0.5, 10.0, TipoLancamento.SERVICO), new Date());
		padrao.addRegraLancamento(TipoEvento.IMPOSTO, new RegraFormulaSimples(
				0.055, 0, TipoLancamento.IMPOSTO), new Date());
		cam.setAcordoServico(padrao);

		return cam;
	}

	public static Cliente configuraClienteBaixaRenda() {
		Cliente zé = new Cliente("José Severino da Silva");

		AcordoServico baixaRenda = new AcordoServico();
		baixaRenda.setTaxa(10);
		baixaRenda.addRegraLancamento(TipoEvento.CONSUMO, new RegraBaixaRenda(
				TipoLancamento.CONSUMO_BASICO, 5, 50), new Date());
		baixaRenda.addRegraLancamento(TipoEvento.CHAMADA,
				new RegraFormulaSimples(0, 10.0, TipoLancamento.SERVICO),
				new Date());
		zé.setAcordoServico(baixaRenda);

		return zé;
	}

	public static void testConsumo() {
		Cliente cam = configuraClienteNormal();

		Consumo evento = new Consumo(50, new Date(), new Date(), cam);
		evento.processa();

		Lancamento lancamentoResultante = evento.getLancamento(cam, 0);
		System.out.println(lancamentoResultante.getValor());
	}

	public void testConsumo2() {
		Cliente cam = configuraClienteNormal();
		Consumo evento = new Consumo(50, new Date(), new Date(), cam);
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

		Consumo evento = new Consumo(50, new Date(), new Date(), zé);
		evento.processa();

		Consumo evento2 = new Consumo(51, new Date(), new Date(), zé);
		evento2.processa();

		Lancamento lancamentoResultante1 = zé.getLancamentos().get(0);
		System.out.println(lancamentoResultante1.getValor());

		Lancamento lancamentoResultante2 = zé.getLancamentos().get(1);
		System.out.println(lancamentoResultante2.getValor());
	}

	public static void testServicoDepoisDaMudanca() {
		Cliente cam = configuraClienteNormal();

		EventoContabil evento = new EventoMonetario(40.0, TipoEvento.CHAMADA,
				new Date(), new Date(), cam);
		evento.processa();

		Lancamento lancamentoResultante = cam.getLancamentos().get(0);
		System.out.println(lancamentoResultante.getValor());
	}

	public static void testBalancoUsandoTransacoes() {
		Conta receitas = new Conta(Moeda.BRA);
		Conta contasProteladas = new Conta(Moeda.BRA);
		Conta contasAReceber = new Conta(Moeda.BRA);
		
		receitas.saque(500, contasAReceber, new Date());
		receitas.saque(200, contasProteladas, new Date());

		// assertEquals(500, contasAReceber.saldo());
		// assertEquals(200, contasProteladas.saldo());
		// assertEquals(-700, receitas.saldo());
	}

	public static void main(String[] args) {
		configuraClienteNormal();
	}
}
