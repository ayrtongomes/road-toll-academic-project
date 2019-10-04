//package edu.up.bsi.conv.jwt;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.Key;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.util.Arrays;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.json.Json;
//import javax.json.JsonArrayBuilder;
//import javax.json.JsonObjectBuilder;
//
//import com.nimbusds.jose.JOSEObjectType;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.JWSHeader;
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.JWSSigner;
//import com.nimbusds.jose.Payload;
//import com.nimbusds.jose.crypto.RSASSASigner;
//
///**
// * Classe especializada em cria tokens Padr�o JWT
// * 
// * 
// *
// */
//@ApplicationScoped
//public class JWTTokenManager {
//
//	/**
//	 * Chave privada para assinatura do TOKEN
//	 */
//	private static PrivateKey privateKey;
//
//	/**
//	 * Constantes relacionadas a chave privada.
//	 * 
//	 * Essa � a senha utilizada para realizar a leitura do arquivo de chaves
//	 * privadas.
//	 */
//	private char[] password = "positivo".toCharArray();
//	/**
//	 * Constantes relacionadas a chave privada.
//	 * 
//	 * Esse � o apelido para a chave privada espec�fica que ser� utilizada para o
//	 * JWT.
//	 * 
//	 * Quando se cria a chave, um apelido � informado.
//	 */
//	private String alias = "convergentes";
//
//	public JWTTokenManager() {
//	}
//
//	@PostConstruct
//	private void init() {
//		/**
//		 * Representa o arquivo das chaves privadas.
//		 */
//		FileInputStream fis = null;
//		try {
//			/**
//			 * Inicializa o keyStore
//			 */
//			KeyStore ks = KeyStore.getInstance("JKS");
//			/**
//			 * Cria o InputStream de Arquivo para a leitura da chave privada.
//			 */
//			fis = new FileInputStream(recuperaPathParaKeyStore());
//			/**
//			 * Carrega o keyStore
//			 */
//			ks.load(fis, password);
//			/**
//			 * Recupera a chave espec�fica para assinar o JWT
//			 */
//			Key key = ks.getKey(alias, password);
//			if (key instanceof PrivateKey) {
//				privateKey = (PrivateKey) key;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//	}
//
//	/**
//	 * Recupera o PATH do sistema operacional para o arquivo de Key Store onde est�o
//	 * armazenadas as chaves privadas.
//	 * 
//	 * @return
//	 */
//	private String recuperaPathParaKeyStore() {
//		String configDir = System.getProperty("jboss.server.config.dir");
//		String keystorePath = configDir + File.separator + "jwt.keystore";
//		return keystorePath;
//	}
//
//	/**
//	 * Cria o TOKEN JWT Assinado
//	 * 
//	 * @param subject
//	 * @param roles
//	 * @return
//	 * @throws Exception
//	 */
//	public String criarJWTToken(final String subject, final String[] roles) throws Exception {
//		/**
//		 * Cria o Cabe�alho do JWT com algor�tmo RS256 e define o TOKEN como JWT. Essa �
//		 * a primeira parte do TOKEN.
//		 */
//		JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).type(new JOSEObjectType("jwt")).build();
//		/**
//		 * Cria o objeto de Claims, parte do TOKEN JWT que possui informa��es de
//		 * Payload. inclunido as Roles Essa � a segunda parte do TOKEN.
//		 */
//		JsonObjectBuilder claimsBuilder = criarPayload(subject, roles);
//		Payload payload = new Payload(claimsBuilder.build().toString());
//		/**
//		 * Cria o JWSObject com as duas partes: header e payload.
//		 */
//		JWSObject jwsObject = new JWSObject(jwsHeader, payload);
//		/**
//		 * Objeto especialista em realizar a assinatura di TOKEN.
//		 */
//		JWSSigner signer = new RSASSASigner(privateKey);
//		/**
//		 * Cria o terceiro bloco que � a assinatura do TOKEN.
//		 */
//		jwsObject.sign(signer);
//		/**
//		 * Serializa o TOKEN em String para ser enviada.
//		 */
//		return jwsObject.serialize();
//	}
//
//	/**
//	 * Tempo de validade do Token em segundos. 360 � igual a 5 minutos.
//	 */
//	private static final int TOKEN_EXPIRATION_TIME = 360;
//	/**
//	 * Informa��es que far�o parte do TOKEN. As roles representam o papel do
//	 * usu�rio, como por exemplo, o seu perfil de acesso. a palavra "groups" �
//	 * configurada no standalone.xml para que o wildfly saiba decoficar esse payload
//	 * no retorno do TOKEN e criar as roles do usu�rio.
//	 */
//	private static final String ROLES = "groups";
//	/**
//	 * Emissor do Token, representa a aplica��o que gera o Token.
//	 */
//	private static final String ISSUER = "convergentes-jwt-issuer";
//	/**
//	 * Destinat�rio do Token, representa o cliente que recebe o Token.
//	 */
//	private static final String AUDIENCE = "convergentes-jwt-audience";
//
//	/**
//	 * Cria a fra��o chamada de paylod do JWT
//	 * 
//	 * @param subject
//	 * @param rolesBuilder
//	 * @return
//	 */
//	private JsonObjectBuilder criarPayload(final String subject, final String[] roles) {
//		/**
//		 * Cria a lista de pap�is, ou perfis de acesso de acordo com a autentica��o
//		 * realizada.
//		 */
//		JsonArrayBuilder rolesBuilder = Json.createArrayBuilder(Arrays.asList(roles));
//		JsonObjectBuilder claimsBuilder = Json.createObjectBuilder();
//		claimsBuilder.add(ClaimType.SUBJECT.shortName(), subject);
//		claimsBuilder.add(ClaimType.ISSUER.shortName(), ISSUER);
//		claimsBuilder.add(ClaimType.AUDIENCE.shortName(), AUDIENCE);
//		claimsBuilder.add(ROLES, rolesBuilder.build());
//		Long expirationTime = System.currentTimeMillis() / 1000 + TOKEN_EXPIRATION_TIME;
//		claimsBuilder.add(ClaimType.EXPIRATION_TIME.shortName(), expirationTime);
//		return claimsBuilder;
//	}
//
//}
