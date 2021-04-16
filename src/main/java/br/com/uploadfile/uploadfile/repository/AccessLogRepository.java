package br.com.uploadfile.uploadfile.repository;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uploadfile.uploadfile.models.AccessLog;

@Component
public class AccessLogRepository {

	@Autowired
	private EntityManager em;

	private static Integer BATCH_SIZE = 50;

	public AccessLog save(AccessLog accessLog) {
		try {
			em.getTransaction().begin();
			em.persist(accessLog);
			em.flush();
			em.clear();
			em.getTransaction().commit();
			return accessLog;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}

	}

	public Set<AccessLog> saveBatch(Set<AccessLog> accessLogs) {
		try {
			int index = 0;
			for (AccessLog accessLog : accessLogs) {
				if (!em.getTransaction().isActive()) {
					em.getTransaction().begin();
				}
				if (index >= 0 && index % BATCH_SIZE == 0) {
					em.flush();
					em.clear();
					em.getTransaction().commit();
				}
				em.persist(accessLog);
				index++;
			}
			em.flush();
			em.clear();
			em.getTransaction().commit();
			return accessLogs;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}

	}

}
