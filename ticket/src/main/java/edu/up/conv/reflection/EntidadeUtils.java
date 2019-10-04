package edu.up.conv.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Remove espa�os em branco de todos os atributos {@link String} com getters e
 * setters padr�es.
 * 
 *
 */
public class EntidadeUtils {

	private EntidadeUtils() {
	}

	@PrePersist
	@PreUpdate
	public void removeWhiteSpaces(Object entidade) {
		System.out.println("Passou aqui...");
		Field[] atributos = entidade.getClass().getDeclaredFields();
		Method getMethod;
		Method setMethod;
		for (Field field : atributos) {
			if (field.getType() == String.class) {
				try {
					getMethod = entidade.getClass()
							.getDeclaredMethod("get".concat(field.getName().substring(0, 1).toUpperCase())
									.concat(field.getName().substring(1)));
					setMethod = entidade.getClass().getDeclaredMethod("set"
							.concat(field.getName().substring(0, 1).toUpperCase()).concat(field.getName().substring(1)),
							String.class);
					String value = (String) getMethod.invoke(entidade);
					setMethod.invoke(entidade, value.trim());
				} catch (Exception e) {
					// nada a fazer
				}
			}
		}
	}

}
