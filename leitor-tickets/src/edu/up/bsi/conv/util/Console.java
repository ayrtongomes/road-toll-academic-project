package edu.up.bsi.conv.util;

import java.util.Scanner;

/**
 * Classe espec�fica para realizar opera��es de entrada e sa�da de teclado.
 * 
 * 
 * 
 */
public class Console {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * M�todo para montar menu para sele��o de op��es
	 * 
	 * @param opcoes
	 * @param titulo
	 * @return a op��o escolhida pelo usu�rio
	 */
	public static int mostrarMenu(String[] opcoes, String titulo, Boolean opcaoSair) {
		final String MENSAGEM = "Por favor, escolha uma op��o:";
		int opcaoEscolhida = 0;
		if (titulo == null) {
			System.out.println(MENSAGEM);
		} else {
			System.out.println(titulo);
		}

		for (int i = 0; i < opcoes.length; i++) {
			System.out.println(i + 1 + ") " + opcoes[i]);
		}
		int opcaoFinal = opcoes.length;
		if (opcaoSair == true) {
			System.out.println(opcoes.length + 1 + ") Para sair.");
			opcaoFinal = opcoes.length + 1;
		}
		try {
			opcaoEscolhida = lerInteiroPositivo(MENSAGEM);
			if (opcaoEscolhida == opcaoFinal) {
				return -1;
			}
		} catch (Exception e) {
			opcaoEscolhida = mostrarMenu(opcoes, "Voc� selecionou um op��o inv�lida, por favor tente novamente.",
					opcaoSair);
		}
		return opcaoEscolhida;
	}

	/**
	 * M�todo espec�fico para realizar a captura de dados decimais no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usu�rio.
	 * @return valor decimal informado pelo usu�rio.
	 */
	public static Double lerDecimal(String descricao) {
		Double valor;
		try {
			valor = Double.parseDouble(lerTexto(descricao));
		} catch (Exception e) {
			valor = lerDecimal(descricao);
		}
		return valor;
	}

	/**
	 * M�todo espec�fico para realizar a captura de dados inteiros no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usu�rio.
	 * @return valor inteiro informado pelo usu�rio.
	 */
	public static Integer lerInteiro(String descricao) {
		Integer valor;
		try {
			valor = Integer.parseInt(lerTexto(descricao));
		} catch (Exception e) {
			valor = lerInteiro(descricao);
		}
		return valor;
	}

	/**
	 * 
	 * M�todo espec�fico para realizar a captura de dados inteiros positivos no
	 * console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usu�rio.
	 * @return valor inteiro positivo informado pelo usu�rio.
	 */
	public static Integer lerInteiroPositivo(String descricao) {
		Integer valor = null;
		do {
			valor = lerInteiro(descricao);
			if (valor < 0) {
				System.out.println("Digite um valor positivo.");
			}
		} while (valor < 0);
		return valor;
	}

	/**
	 * M�todo espec�fico para realizar a captura de dados texto no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usu�rio.
	 * @return um texto informado pelo usu�rio.
	 */
	public static String lerTexto(String descricao) {
		String valor;
		if (descricao == null) {
			System.out.println("Entre com valor:");
		} else {
			System.out.println(descricao);
		}
		try {
			valor = scanner.nextLine();
		} catch (Exception e) {
			valor = lerTexto(descricao);
		}
		return valor;
	}

}
