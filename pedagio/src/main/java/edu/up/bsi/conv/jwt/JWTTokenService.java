///*
// * JBoss, Home of Professional Open Source
// * Copyright 2018, Red Hat, Inc. and/or its affiliates, and individual
// * contributors by the @authors tag. See the copyright.txt in the
// * distribution for a full listing of individual contributors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package edu.up.bsi.conv.jwt;
//
//import java.text.ParseException;
//import java.util.logging.Logger;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.HeaderParam;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import com.nimbusds.jwt.JWT;
//import com.nimbusds.jwt.JWTParser;
//
//import edu.up.bsi.conv.rest.Mensagem;
//
//@Path("/")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class JWTTokenService {
//
//	private static final Logger log = Logger.getLogger(JWTTokenService.class.getName());
//
//	@Inject
//	JWTTokenManager jwtManager;
//
//	@GET
//	@Path("/claims")
//	public Response verificaClaims(@HeaderParam("Authorization") String authorization) {
//		final String INICIO_BEARER_TOKEN = "Bearer ";
//		final int POSICAO_INICIO_TOKEN = 7;
//		if (authorization != null && authorization.startsWith(INICIO_BEARER_TOKEN)) {
//			try {
//				JWT jwt = JWTParser.parse(authorization.substring(POSICAO_INICIO_TOKEN));
//				return Response.ok(jwt.getJWTClaimsSet().getClaims()).build();
//			} catch (ParseException e) {
//				log.warning(e.toString());
//				return Response.status(Status.BAD_REQUEST).build();
//			}
//		}
//		return Response.status(Status.NO_CONTENT).build();
//	}
//
//	/**
//	 * Realiza a Geração do TOKEN através de usuário e senha
//	 * 
//	 * @param username
//	 * @param password
//	 * @return
//	 */
//	@POST
//	@Path("/token")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response solicitacaoTokenJWT(@FormParam("username") String username,
//			@FormParam("password") String password) {
//		try {
//			log.info("Autenticando usuário: " + username);
//			String[] papeis = JWTTokenUserFake.recuperaPapeis(username, password);
//			String token = jwtManager.criarJWTToken(username, papeis);
//			log.info("Autenticação " + username + " realizada!");
//			return Response.ok(new JWTToken(token)).build();
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			Mensagem mensagem = new Mensagem();
//			mensagem.setStatus(Status.UNAUTHORIZED.toString());
//			mensagem.setDescricao(e.getMessage());
//			return Response.status(Response.Status.UNAUTHORIZED).entity(mensagem).build();
//		}
//	}
//
//}
