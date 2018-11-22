package br.com.mariojp.loja.negocio;

import java.math.BigDecimal;

public abstract class FormaDePagamento {

	
	public abstract BigDecimal pagamento(BigDecimal valor, Venda venda);
	
}
