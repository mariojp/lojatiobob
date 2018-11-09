package br.com.mariojp.loja.userinterface;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	
	String titulo;
	Set<ItemMenu> itens = new TreeSet();
	
	public Menu(String titulo) {
		this.titulo = titulo;
	}
	
	public void addItem(ItemMenu e) {
		this.itens.add(e);
	}
	
	public void setItens(Set<ItemMenu> itens) {
		this.itens = itens;
	}
	
	public String obterOpcao() {
		System.out.println(this.titulo);
		for (ItemMenu itemMenu : itens) {
			System.out.println(itemMenu);
		}
		System.out.print("Digite a Opção desejada: ");
		return scanner.nextLine();
	}
}
