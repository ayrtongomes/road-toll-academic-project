//package edu.up.bsi.conv.jwt;
//
///**
// * Tipos de Claims (Reinvidica��es de acesso) padr�es reservados para uso.
// * 
// * 
// *
// */
//public enum ClaimType {
//
//	ISSUER("iss", "Entidade geradora do TOKEN."),
//	SUBJECT("sub", "Identifica��o de quem est� recebendo o TOKEN para acesso."),
//	AUDIENCE("aud", "Identifica��o do p�blico que est� apto a receber o TOKEN para o servi�o."),
//	EXPIRATION_TIME("exp", "Data e hora de expira��o do TOKEN."),
//	NOT_BEFORE("nbf", "Data e hora de in�cio para aceitar o TOKEN, n�o aceitar antes dessa data."),
//	ISSUED_AT("iat", "Date e hora de cria��o do TOKEN."),
//	JWT_ID("jti", "Identifica��o �nica do TOKEN JWT para situa��es de necessidade de uso �nico, ou algo semelhante.");
//
//	private String shortName;
//	private String description;
//
//	private ClaimType(String shortName, String description) {
//		this.shortName = shortName;
//		this.description = description;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public String shortName() {
//		return shortName;
//	}
//
//}
