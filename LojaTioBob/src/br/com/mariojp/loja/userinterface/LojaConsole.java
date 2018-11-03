package br.com.mariojp.loja.userinterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.mariojp.loja.Util.ConsoleUtil;
import br.com.mariojp.loja.negocio.Cliente;
import br.com.mariojp.loja.negocio.Item;
import br.com.mariojp.loja.negocio.Loja;
import br.com.mariojp.loja.negocio.Produto;
import br.com.mariojp.loja.negocio.Venda;

public class LojaConsole {

	Scanner scanner = new Scanner(System.in);

	private Loja loja;

	public LojaConsole(Loja loja) {
		this.loja = loja;
	}

	public void iniciar() {
		this.menu();
	}

	public void menu() {
		String op = "";
		while (!op.equalsIgnoreCase("s")) {
			System.out.println("MENU PRINCIPAL");
			System.out.println("1 - Produtos");
			System.out.println("2 - Clientes");
			System.out.println("3 - Vendas");
			System.out.println("S - Sair");
			System.out.print("Digite a Opção desejada: ");
			op = scanner.nextLine();
			System.out.println("");
			switch (op) {
			case "1":
				produtos();
				break;
			case "2":
				clientes();
				break;
			case "3":
				vendas();
				break;
			case "S":
			case "s":
				System.out.println("Bye bye!\n");
			    System.exit(0);
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
			ConsoleUtil.limparTela();
		}
	}

	public void produtos() {
		String op = "";
		while (!op.equalsIgnoreCase("v")) {
			System.out.println("MENU PRODUTOS");
			System.out.println("1 - Inserir");
			System.out.println("2 - Alterar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Listar");
			System.out.println("V - Voltar");
			System.out.print("Digite a Opção desejada: ");
			op = scanner.nextLine();
			System.out.println("");
			switch (op) {
			case "1":
				inserirProduto();
				break;
			case "2":
				alterarProduto();
				break;
			case "3":
				excluirProduto();
				break;
			case "4":
				listar(loja.getProdutos());
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
			ConsoleUtil.limparTela();		
		}
		//return "0";
	}

	private void listar(List<?> lista) {
		for (Object object : lista) {
			System.out.println(object);
		}
		scanner.nextLine();

	}

	private void excluirProduto() {
		System.out.println("Excluir Produto: ");
		System.out.print("Codigo: ");
		String codigo = scanner.nextLine();

		Produto produto = loja.obterProduto(codigo);
		if (produto != null) {
			System.out.println(produto);
			System.out.print("Confirma Excluir [S] ? ");
			String sim = scanner.nextLine();
			if (sim.equalsIgnoreCase("s")) {
				loja.excluir(produto);
				System.out.println("Produto Excluido com Sucesso");
			} else {
				System.out.println("Exclusão cancelada");
			}
		} else {
			System.out.println("Não existe produto com esse codigo");
		}
		scanner.nextLine();
	}

	private void alterarProduto() {
		List<String> erros = new ArrayList<>();
		
		System.out.println("Alterar Produto: ");
		System.out.print("Codigo: ");
		String codigo = scanner.nextLine();
		Produto produto = loja.obterProduto(codigo);

		String nome = null;
		String valorString = null;
		BigDecimal valor = null;

		
		if (produto != null) {
			System.out.print("Nome [" + produto.getNome() + "]: ");
			nome = scanner.nextLine();
			if (nome.length() == 0) {
				nome = produto.getNome();
			}
			System.out.print("Valor [" + produto.getValor() + "]:");
			valorString = scanner.nextLine();

			if (valorString.length() == 0) {
				valorString = produto.getValor().toPlainString();
			}

			if (nome.length() < 3) {
				erros.add("Nome Invalida: O nome deve ter mais que 3 caracteres");
			}
			try {
				valor = new BigDecimal(valorString);
			} catch (NumberFormatException e) {
				erros.add("Valor Invalida:  (use o formato 0000.00) ");
			}

		}else {
			erros.add("Não existe produto com esse Codigo");
		}
		
		if (erros.size() == 0) {
			produto.setNome(nome);
			produto.setValor(valor);
			this.loja.salvarProduto(produto);
			System.out.println("Salvo com Sucesso");
		} else {
			listar(erros);
		}
		scanner.nextLine();
	}

	private void inserirProduto() {
		System.out.println("Inserir Produto: ");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		System.out.print("Valor: ");
		String valorString = scanner.nextLine();

		List<String> erros = new ArrayList<>();
		if (nome.length() < 3) {
			erros.add("Nome Invalida: O nome deve ter mais que 3 caracteres");
		}
		BigDecimal valor = null;
		try {
			valor = new BigDecimal(valorString);
		} catch (NumberFormatException e) {
			erros.add("Valor Invalida:  (use o formato 0000.00) ");
		}

		if (erros.size() == 0) {
			Produto produto = new Produto(nome, valor);
			this.loja.salvarProduto(produto);
			System.out.println("Salvo com Sucesso");
		} else {
			listar(erros);
		}
		scanner.nextLine();
	}

	public void clientes() {
		String op = "";
		while (!op.equalsIgnoreCase("v")) {
			System.out.println("MENU CLIENTES");
			System.out.println("1 - Inserir");
			System.out.println("2 - Alterar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Listar");
			System.out.println("V - Voltar");
			System.out.print("Digite a Opção desejada: ");
			op = scanner.nextLine();
			System.out.println("");
			switch (op) {
			case "1":
				inserirCliente();
				break;
			case "2":
				alterarCliente();
				break;
			case "3":
				excluirCliente();
				break;
			case "4":
				listar(loja.getClientes());
				break;

			default:
				System.out.println("Opção invalida");
				break;
			}
			ConsoleUtil.limparTela();		
		}
		
	}
	
	private void inserirCliente() {
		System.out.println("Inserir Cliente: ");
		System.out.print("CPF ou CNPJ: ");
		String cpfcnpj = scanner.nextLine();
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		List<String> erros = new ArrayList<>();
		if ( !(cpfcnpj.length() ==11 || cpfcnpj.length() == 14) ) {
			erros.add("CPF ou CNPJ Invalidos: Digite apenas numeros para o CFP (11) ou CNPJ (14) ");
		}
		
		if (nome.length() < 3) {
			erros.add("Nome Invalida: O nome deve ter mais que 3 caracteres");
		}

		if (erros.size() == 0) {
			Cliente cliente = new Cliente(cpfcnpj,nome);
			this.loja.salvarCliente(cliente);
			System.out.println("Salvo com Sucesso");
		} else {
			listar(erros);
		}
		scanner.nextLine();
	}
	
	private void alterarCliente() {
		List<String> erros = new ArrayList<>();
		String nome = null;
		System.out.println("Alterar Cliente: ");
		System.out.print("CPF ou CNPJ: ");
		String cpfCnpj = scanner.nextLine();
		Cliente cliente = loja.obterCliente(cpfCnpj);

		if (cliente != null) {
			
			System.out.print("Nome [" + cliente.getNome() + "]: ");
			nome = scanner.nextLine();
			if (nome.length() == 0) {
				nome = cliente.getNome();
			}

			if (nome.length() < 3) {
				erros.add("Nome Invalida: O nome deve ter mais que 3 caracteres");
			}

		}else {
			erros.add("Não existe cliente com esse CPF ou CNPJ");
		}
		
		if (erros.size() == 0) {
			cliente.setNome(nome);
			this.loja.salvarCliente(cliente);
			System.out.println("Salvo com Sucesso");
		} else {
			listar(erros);
		}
		scanner.nextLine();
		
	}
	


	private void excluirCliente() {
		System.out.println("Excluir Cliente: ");
		System.out.print("CPF ou CNPJ: ");
		String cpfCnpj = scanner.nextLine();
		Cliente cliente = loja.obterCliente(cpfCnpj);
		
		if (cliente != null) {
			System.out.println(cliente);
			System.out.print("Confirma Excluir [S] ? ");
			String sim = scanner.nextLine();
			if (sim.equalsIgnoreCase("s")) {
				loja.excluir(cliente);
				System.out.println("Cliente Excluido com Sucesso");
			} else {
				System.out.println("Exclusão cancelada");
			}
		} else {
			System.out.println("Não existe cliente com esse cpf ou cnpj");
		}
		scanner.nextLine();
		ConsoleUtil.limparTela();		
	}





	public void vendas() {
		String op = "";
		while (!op.equalsIgnoreCase("v")) {
			System.out.println("MENU VENDAS");
			System.out.println("1 - Inserir");
			System.out.println("2 - Excluir");
			System.out.println("3 - Listar");
			System.out.println("V - Voltar");
			System.out.print("Digite a Opção desejada: ");
			op = scanner.nextLine();
			System.out.println("");
			switch (op) {
			case "1":
				inserirVenda();
				break;
			case "2":
				excluirVenda();
				break;
			case "3":
				listar(loja.getVendas());
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
			ConsoleUtil.limparTela();		

		}
	}

	private void excluirVenda() {
		System.out.println("Excluir Venda: ");
		System.out.print("Codigo da Venda: ");
		String codigo = scanner.nextLine();
		Venda venda = loja.obterVenda(codigo);
		
		if (venda != null) {
			System.out.println(venda.vendaComItensToString());
			System.out.print("Confirma Excluir [S] ? ");
			String sim = scanner.nextLine();
			if (sim.equalsIgnoreCase("s")) {
				loja.excluir(venda);
				System.out.println("Cliente Excluido com Sucesso");
			} else {
				System.out.println("Exclusão cancelada");
			}
		} else {
			System.out.println("Não existe cliente com esse cpf ou cnpj");
		}
		scanner.nextLine();
		ConsoleUtil.limparTela();			
	}

	private void inserirVenda() {
		System.out.println("Inserir Venda: ");
		System.out.print("Digite o CPF ou CNPJ do Cliente: ");
		String cpfCnpj = scanner.nextLine();
		
		Cliente cliente = loja.obterCliente(cpfCnpj);


		if (cliente != null) {
			Venda venda = new Venda(cliente);
			menuVenda(venda);
			this.loja.salvarVenda(venda);
			System.out.println("Salvo com Sucesso");
			
		}else {
			System.out.println("Não existe cliente com esse CPF ou CNPJ insira o cliente antes");
		}
			

		scanner.nextLine();
		ConsoleUtil.limparTela();		
	}

	private void menuVenda(Venda venda) {
		String op = "";
		while (!op.equalsIgnoreCase("3")) {
			System.out.println("VENDA ");
			System.out.println(venda.vendaComItensToString());
			System.out.println("1 - Inserir Item");
			System.out.println("2 - Excluir Item");
			System.out.println("3 - Fechar");
			System.out.print("Digite a Opção desejada: ");
			op = scanner.nextLine();
			System.out.println("");
			switch (op) {
			case "1":
				inserirItem(venda);
				break;
			case "2":
				excluirItem(venda);
				break;
			case "3":
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
		}		
	}



	private void excluirItem(Venda venda) {
		System.out.println("Excluir Item da Venda: ");
		System.out.print("Codigo do Item: ");
		String codigo = scanner.nextLine();
		Item item = venda.obterItem(codigo);
		
		if (item != null) {
			System.out.println(item);
			System.out.print("Confirma Excluir [S] ? ");
			String sim = scanner.nextLine();
			if (sim.equalsIgnoreCase("s")) {
				venda.excluir(item);
				System.out.println("Item Excluido com Sucesso");
			} else {
				System.out.println("Exclusão cancelada");
			}
		} else {
			System.out.println("Não existe item com esse codigo");
		}
		scanner.nextLine();
		ConsoleUtil.limparTela();	
	}

	private void inserirItem(Venda venda) {
		System.out.println("Inserir Item da Venda: ");
		System.out.print("Codigo do Produto: ");
		String codigo = scanner.nextLine();
		Produto produto = loja.obterProduto(codigo);
		if(produto != null) {
			venda.adicionaItem(produto);
		}else {
			System.out.println("Produto Não existe");
		}
		
	}
	
	

}
