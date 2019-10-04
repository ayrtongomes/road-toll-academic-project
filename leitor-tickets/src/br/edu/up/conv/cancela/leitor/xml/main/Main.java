package br.edu.up.conv.cancela.leitor.xml.main;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.edu.up.conv.cancela.leitor.xml.tickets.Ticket;
import edu.up.bsi.conv.util.Console;

public class Main {

	private static JAXBContext context;

	private static String URI_TICKET = "http://localhost:9090/ticket/rest/tickets";
	private static Client client;

	public static void main(String[] args) throws JAXBException {
		
		Boolean continuar = true;
		String path = Console.lerTexto("Informe o path dos ticketao:");

		do {		
			context = JAXBContext.newInstance(Ticket.class);
			client = ClientBuilder.newClient();
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			if(listOfFiles.length > 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					String filename = listOfFiles[i].getName();
					Ticket t = unmarshal(path + "\\" + filename);
					if(cadastrarTicket(t)) {
						listOfFiles[i].delete();						
					} else {
						continuar = false;
						System.out.println("Ticket não registrado na base, por motivos maiores que pude perceber");
					}
				}					
			} else {
				continuar = false;
			}
			
		} while (continuar);
		
	}
	
	public static Ticket unmarshal(String filename) throws JAXBException {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Ticket ticket = (Ticket) unmarshaller.unmarshal(new File(filename));
		return ticket;
	}

	private static Boolean cadastrarTicket(Ticket ticket) {
		Response response = client.target(URI_TICKET).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(ticket, MediaType.APPLICATION_XML));
		if (Status.CREATED.getStatusCode() == response.getStatus()) {
			System.out.println("Ticket registrado na base de dados");
			return true;
		} else {
			System.out.println("Erro ao salvar no banco");
			return false;
		}
	}
}
