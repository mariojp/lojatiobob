package br.com.mariojp.loja.negocio;

import java.math.BigDecimal;

public class Dinheiro extends FormaDePagamento {

	@Override
	public BigDecimal pagamento(BigDecimal valor, Venda venda) {
		BigDecimal total = BigDecimal.ZERO;
		for ( Item item : venda.getItens()) {
			total = total.add(item.getValor());
		}
		System.out.println("VALOR TOTAL "+total);
		BigDecimal troco = valor.subtract(total);
		return troco;
	}

}
