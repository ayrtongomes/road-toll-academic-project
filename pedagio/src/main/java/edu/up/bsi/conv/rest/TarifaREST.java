package edu.up.bsi.conv.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import edu.up.bsi.conv.dao.TarifaDAO;
import edu.up.bsi.conv.entidade.Tarifa;

@Path(value = "tarifas")
public class TarifaREST {

	@Inject
	private TarifaDAO dao;

	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarTarifa(Tarifa tarifa) throws Exception {
		Tarifa existente = dao.listarPorPracaCategoria(tarifa.getNomePraca(), tarifa.getCategoria());
		if (existente == null) {
			Tarifa cadastrado = dao.cadastrarTarifa(tarifa);
			UriBuilder baseUriBuilder = uriInfo.getBaseUriBuilder();
			URI tarifaCadastrada = baseUriBuilder.path(TarifaREST.class).path(cadastrado.toPath()).build();
			return Response.created(tarifaCadastrada).entity(cadastrado).build();
		} else {
			Mensagem mensagem = new Mensagem(Status.CONFLICT.toString(), "Tarifa j� cadastrada.");
			return Response.status(Status.CONFLICT).entity(mensagem).build();
		}
	}

	@DELETE
	@Path(value = "{id}")
	public Response removerTarifa(@PathParam(value = "id") Integer codigo) {
		try {
			dao.removerTarifaPorCodigo(codigo);
			Mensagem mensagem = new Mensagem();
			mensagem.setStatus(Status.ACCEPTED.toString());
			mensagem.setDescricao("A tarifa com c�digo " + codigo + " foi removido com sucesso!");
			return Response.accepted().entity(mensagem).build();
		} catch (Exception e) {
			Mensagem mensagem = new Mensagem();
			mensagem.setStatus(Status.CONFLICT.toString());
			mensagem.setDescricao("A tarifa com c�digo " + codigo + " n�o p�de ser removido!");
			return Response.status(Status.CONFLICT).entity(mensagem).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarTarifa(Tarifa tarifa) {
		try {
			Tarifa atualizado = dao.atualizarTarifa(tarifa);
			UriBuilder baseUriBuilder = uriInfo.getBaseUriBuilder();
			URI tarifaAtualizada = baseUriBuilder.path(TarifaREST.class).path(atualizado.toPath()).build();
			return Response.accepted(tarifaAtualizada).entity(atualizado).build();
		} catch (Exception e) {
			Mensagem mensagem = new Mensagem();
			mensagem.setStatus(Status.CONFLICT.toString());
			mensagem.setDescricao("A tarifa " + tarifa.getId() + " j� est� cadastrada!");
			return Response.status(Status.CONFLICT).entity(mensagem).build();
		}
	}

	@GET
	@Path(value = "{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarTarifaoPorId(@PathParam(value = "codigo") Integer codigo) {
		Tarifa pesquisado = dao.buscarTarifaPorCodigo(codigo);
		if (pesquisado != null) {
			UriBuilder baseUriBuilder = uriInfo.getBaseUriBuilder();
			URI tarifaPesquisada = baseUriBuilder.path(TarifaREST.class).path(pesquisado.toPath()).build();
			return Response.created(tarifaPesquisada).entity(pesquisado).build();
		} else {
			Mensagem mensagem = new Mensagem(Status.NOT_FOUND.toString(),
					"A tarifa com c�digo " + codigo + " n�o foi cadastrada!");
			return Response.status(Status.NOT_FOUND).entity(mensagem).build();
		}
	}

	@GET
	@Path(value = "search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarTarifaPorNomePracaCategoria(@QueryParam(value = "nomePraca") String nome,
			@QueryParam(value = "categoria") Integer categoria) {
		Tarifa tarifa = dao.buscarTarifaPorCodigo(categoria);
		if (tarifa != null) {
			return Response.ok().entity(tarifa).build();
		} else {
			Mensagem mensagem = new Mensagem(Status.NOT_FOUND.toString(),
					"O tarifa com o nome " + nome + " n�o foi cadastrada!");
			return Response.status(Status.NOT_FOUND).entity(mensagem).build();
		}
	}

	@GET
	@Path(value = "calculoTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calcularTarifaPorNomePracaCategoria(@QueryParam(value = "nomePraca") String praca,
			@QueryParam(value = "categoria") Integer categoria) {
		Tarifa tarifa = dao.listarPorPracaCategoria(praca, categoria);

		double valorTarifa = 0;
		switch (tarifa.getCategoria()) {
		case 1:
			valorTarifa = tarifa.getValorPraticado() * 1;
			break;
		case 2:
			valorTarifa = tarifa.getValorPraticado() * 2;
			break;
		case 3:
			valorTarifa = tarifa.getValorPraticado() * 1.5;
			break;
		case 4:
			valorTarifa = tarifa.getValorPraticado() * 3;
			break;
		case 5:
			valorTarifa = tarifa.getValorPraticado() * 2;
			break;
		case 6:
			valorTarifa = tarifa.getValorPraticado() * 4;
			break;
		case 7:
			valorTarifa = tarifa.getValorPraticado() * 5;
			break;
		case 8:
			valorTarifa = tarifa.getValorPraticado() * 6;
			break;
		case 9:
			valorTarifa = tarifa.getValorPraticado() * 0.5;
			break;
		default:
			break;
		}

		if (valorTarifa > 0) {
			return Response.ok(valorTarifa).build();
		} else {
			Mensagem mensagem = new Mensagem(Status.NOT_FOUND.toString(), "O tarifa com o nome " + tarifa.getNomePraca()
					+ "e categoria " + tarifa.getCategoria() + " n�o foi cadastrada!");
			return Response.status(Status.NOT_FOUND).entity(mensagem).build();
		}
	}

	@GET
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		try {
			List<Tarifa> tarifas = dao.listarTodos();
			return Response.ok().entity(tarifas).build();
		} catch (Exception e) {
			Mensagem mensagem = new Mensagem(Status.INTERNAL_SERVER_ERROR.toString(),
					"Infelizmente n�o conseguimos conectar com o gabrielzinho de dados!");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
		}
	}

}
