package br.com.mariojp.loja.negocio;

import java.math.BigDecimal;

import br.com.mariojp.loja.negocio.FormaDePagamento;
import br.com.mariojp.loja.negocio.Venda;

public class Cartao extends FormaDePagamento {

	@Override
	public BigDecimal pagamento(BigDecimal valor, Venda venda) {
		//Juros
		//Conectar no site do cartao
		return BigDecimal.ZERO;
	}

}
