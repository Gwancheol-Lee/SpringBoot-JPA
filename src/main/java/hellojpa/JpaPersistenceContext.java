package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.HTMLDocument.Iterator;

public class JpaPersistenceContext {

	public static void main(String[] args) {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			// 비영속 상태 (Entity 객체 선언만 하고 EntityManager 관련 액션은 없는 상태)
			Member member = new Member();
			member.setId(101L);
			member.setName("HelloJPA");
			
			em.find(Member.class, 101L); 
			
			// 영속 상태 (Entity 객체를 EntityManager에 저장하면서 영속성 컨텍스트(Persistence Context)에 의해 관리가 된다. 현재 시점 DB 저장x )
			em.persist(member);
			
			// 준영속 상태 (EntityManager에 저장되어 있는 Entity 객체를 영속성 컨텍스트에서 분리(삭제) 한다. )
			em.detach(member);
			
			// 삭제 (DB 테이블의 해당 Entity 객체를 삭제)
			em.remove(member);
			
			// Transaction commit 시점에 JPA에서 DB로 쿼리를 날림.
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
