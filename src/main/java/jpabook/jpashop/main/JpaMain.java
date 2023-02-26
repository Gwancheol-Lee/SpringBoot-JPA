package jpabook.jpashop.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.HTMLDocument.Iterator;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Order order = em.find(Order.class, 1L);
			em.persist(order);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			em.persist(orderItem);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
