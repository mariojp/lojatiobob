package br.com.mariojp.loja.teste;

import java.math.BigDecimal;

import br.com.mariojp.loja.negocio.Cliente;
import br.com.mariojp.loja.negocio.Item;
import br.com.mariojp.loja.negocio.Produto;
import br.com.mariojp.loja.negocio.Venda;

public class VendaTeste {

	
	public static void main(String[] args) {
		
		//Cria produtos
		Produto xbox = new Produto("XBOX","1500.00");
		Produto bola = new Produto("Bola Adidas",new BigDecimal("59.00"));
		Produto bicicleta = new Produto("Bicicleta Caloi","500.00");
		
		//CLiente
		Cliente cliente = new Cliente("12312312399", "Pedro ");
		
		//Cria nota fiscal
		Venda notafiscal = new Venda(cliente);
		
		//Adiciona produto com quantidade 1 
		notafiscal.adicionaItem(xbox);
		
		//Adiciona produto com quantidade maior que 1 
		notafiscal.adicionaItem(bola,4);

		//Adiciona produto com quantidade maior que 1 
		notafiscal.adicionaItem(bicicleta,2);

		notafiscal.fechar(new BigDecimal("9.99"));
		
		System.out.println(notafiscal);
		
		for (Item item : notafiscal.getItens()) {
			System.out.println(item);
		}
		
	}
}
