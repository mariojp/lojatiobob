package br.com.mariojp.loja;

import br.com.mariojp.loja.negocio.Loja;
import br.com.mariojp.loja.userinterface.LojaConsole;

public class Principal {

	
	public static void main(String[] args) {
		Loja loja = new Loja();
		
		LojaConsole console = new LojaConsole(loja);
		console.iniciar();
	}
}
