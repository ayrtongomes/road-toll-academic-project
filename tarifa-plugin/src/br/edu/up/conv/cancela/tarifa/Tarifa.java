package br.edu.up.conv.cancela.tarifa;

/**
 * Interface para recuperar o valor da tarifa de uma Categoria de Ve�culo para o
 * Ped�gio.
 * 
 * 
 *
 */
public interface Tarifa {

	/**
	 * Busca o valor em R$ do pre�o da tarifa para uma Categoria e Tipo de Pra�a.
	 * 
	 * @param tipoPraca
	 * @param categoria
	 * @return
	 */
	public Double buscarTarifa(String tipoPraca, String categoria);

}
