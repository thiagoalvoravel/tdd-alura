package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLeilaoEmOrdemCrescente() {
		//cenário
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("PS3 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		//ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//validação
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//cenário
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("PS3 Novo");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		double maiorEsperado = 1000;
		double menorEsperado = 1000;
		
		//ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//validação
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		
		
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		//cenário
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		Usuario pedro = new Usuario("Pedro");
		Usuario paulo = new Usuario("Paulo");
		
		Leilao leilao = new Leilao("PS3 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(pedro, 500.0));
		leilao.propoe(new Lance(paulo, 750.0));

		//ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		//validação
		assertEquals(3, tresMaiores.size());
		assertEquals(750.0, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(500.0, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(400.0, tresMaiores.get(2).getValor(), 0.00001);
		
	}
	
}
