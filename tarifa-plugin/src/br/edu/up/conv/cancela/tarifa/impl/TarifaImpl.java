package br.edu.up.conv.cancela.tarifa.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.up.conv.cancela.tarifa.Tarifa;

public class TarifaImpl implements Tarifa {

	private static Client client;
	private static String URI_TARIFA_CALCULO = "http://localhost:8080/pedagio/rest/tarifas/calculoTarifa";

	@Override
	public Double buscarTarifa(String tipoPraca, String categoria) {
		double valor;
		URI_TARIFA_CALCULO += "?nomePraca=" + tipoPraca;
		URI_TARIFA_CALCULO += "&categoria=" + categoria.replace("C", "");

		client = ClientBuilder.newClient();

		Response response = client.target(URI_TARIFA_CALCULO).request(MediaType.APPLICATION_JSON).get();
		valor = response.readEntity(Double.class);

		return valor;
	}
}
