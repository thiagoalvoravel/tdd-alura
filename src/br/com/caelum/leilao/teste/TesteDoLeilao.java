package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoLeilao {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("MacBook Air");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Paula"), 2000));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("MacBook Air");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Paula"), 2000));
		leilao.propoe(new Lance(new Usuario("Vitor"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Air");
		Usuario joao = new Usuario("João");
		
		leilao.propoe(new Lance(joao, 2000));
		leilao.propoe(new Lance(joao, 3000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	
	
}
