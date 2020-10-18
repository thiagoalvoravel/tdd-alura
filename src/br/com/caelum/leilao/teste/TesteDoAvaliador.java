package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("José");
	}

	@Test
	public void deveEntenderLeilaoEmOrdemCrescente() {
		//cenário
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(joao, 250.0)
				.lance(maria, 300.0)
				.lance(jose, 400.0)
				.constroi();
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		//ação
		leiloeiro.avalia(leilao);
		
		//validação
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//cenário
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(joao, 1000.0)
				.constroi();
		
		double maiorEsperado = 1000;
		double menorEsperado = 1000;
		
		//ação
		leiloeiro.avalia(leilao);
		
		//validação
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		
		
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		//cenário
		Usuario pedro = new Usuario("Pedro");
		Usuario paulo = new Usuario("Paulo");
		
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(joao, 250.0)
				.lance(maria, 300.0)
				.lance(jose, 400.0)
				.lance(pedro, 500.0)
				.lance(paulo, 750.0)
				.constroi();

		//ação
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		//validação
		assertEquals(3, tresMaiores.size());
		assertEquals(750.0, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(500.0, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(400.0, tresMaiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoEmOrdemAleatoria() {
		//cenário
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(maria, 300.0)
				.lance(joao, 250.0)
				.lance(joao, 1250.0)
				.lance(joao, 250.0)
				.lance(maria, 3300.0)
				.lance(maria, 2300.0)
				.constroi();
		
		double maiorEsperado = 3300;
		double menorEsperado = 250;
		
		//ação
		leiloeiro.avalia(leilao);
		
		//validação
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
	}
	
	@Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimoTres() {
		//cenário
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(maria, 100.0)
				.lance(joao, 200.0)
				.constroi();

        //ação
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        //validação
        assertEquals(2, maiores.size());
        assertEquals(maiores.get(0).getValor(), leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(maiores.get(1).getValor(), leiloeiro.getMenorDeTodos(), 0.00001);
    }
	
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLance() {
		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo").constroi();
		leiloeiro.avalia(leilao);
	}
	
}
