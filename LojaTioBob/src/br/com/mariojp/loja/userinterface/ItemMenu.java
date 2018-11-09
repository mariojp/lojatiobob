package br.com.mariojp.loja.userinterface;

public class ItemMenu implements Comparable<ItemMenu>{

	String identificador;
	String texto;
	
	public ItemMenu(String identificador, String texto) {
		this.identificador = identificador;
		this.texto = texto;
	}
	
	
	public String getIdentificador() {
		return identificador;
	}
	
	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return identificador + " - "+texto;
	}


	@Override
	public int compareTo(ItemMenu o) {
		return identificador.compareTo(o.identificador);
	}
	
}
