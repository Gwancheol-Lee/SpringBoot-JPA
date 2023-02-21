package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		// EntityManagerFactory를 통해 DB를 연결 ( hello는 persistence.xml의 persistence-unit name ) 
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
		
		// createEntityManager() 메소드를 통해 생성된 EntityManger로 SQL 작업.
		EntityManager em = emf.createEntityManager();
		
		// 데이터를 변경하는 작업에는 꼭 Transaction이 필요하다.
		EntityTransaction tx = em.getTransaction();	
		
		try {
			tx.begin(); // SQL 실행 코드 전에 트랜잭션 begin 선언
			
			Member member = new Member();
			member.setId(2L);
			member.setName("HelloB");
			em.persist(member); // Member 테이블에 데이터 Insert
			
			tx.commit(); // SQL 실행 코드 이후에 트랜잭션 commit
		} catch (Exception e) {
			tx.rollback(); // 오류 발생시 트랜잭션 롤백
		} finally {
			em.close();	// 작업 종료 후 entityManager close
		}
		
		emf.close();
	}

}
