package org.seven;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class PaytmService implements PaytmServices{
	@PersistenceUnit(name = "PayTmUnit")
	private EntityManagerFactory entityManagerFactory;
	@Override
	public List<PaytmTransaction> loadAllTransaction(int offset,int limit,String orderField) {
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<PaytmTransaction> criteria = criteriaBuilder.createQuery(PaytmTransaction.class);
		Root<PaytmTransaction> paytmTransaction = criteria.from(PaytmTransaction.class);  
		criteria.orderBy(criteriaBuilder.asc(paytmTransaction.get(orderField)));
		TypedQuery<PaytmTransaction> qry = em.createQuery(criteria);
		qry.setFirstResult(offset);
		qry.setMaxResults(limit);
		List<PaytmTransaction> trans = qry.getResultList();
		return trans;
	}

}
