package br.com.alura.lojavirtual.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja-virtual");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
