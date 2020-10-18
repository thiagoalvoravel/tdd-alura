package br.com.caelum.leilao.teste;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoLance {
	
	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLanceComValorZerado() {
		new Lance(new Usuario("Maria"), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLanceComValorNegativo() {
		new Lance(new Usuario("Maria"), 0.0);
	}

}
