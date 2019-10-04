package edu.up.bsi.conv.rest;

import java.net.URI;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.datatype.XMLGregorianCalendar;

import edu.up.bsi.conv.dao.TicketDAO;
import edu.up.bsi.conv.entidade.Ticket;
import edu.up.bsi.conv.entidade.Ticketao;

@Path(value = "tickets")
public class TicketREST {

	@Inject
	private TicketDAO dao;

	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarTarifa(Ticket ticket) throws Exception {

		XMLGregorianCalendar xmlDate = ticket.getDataHora();
		Date dateObj = xmlDate.toGregorianCalendar().getTime();

		Ticketao t = new Ticketao(ticket.getPraca(), ticket.getCancela(), dateObj, ticket.getTarifa(),
				ticket.getCategoria().value());
		Ticketao cadastrado = dao.cadastrarTicket(t);

		UriBuilder baseUriBuilder = uriInfo.getBaseUriBuilder();
		URI tarifaCadastrada = baseUriBuilder.path(TicketREST.class).path(cadastrado.toPath()).build();
		return Response.created(tarifaCadastrada).entity(cadastrado).build();

	}

}
