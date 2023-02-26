package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.HTMLDocument.Iterator;

public class JpaMain {

	public static void main(String[] args) {
		// EntityManagerFactory를 통해 DB를 연결 ( hello는 persistence.xml의 persistence-unit name ) 
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello"); // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
		
		// createEntityManager() 메소드를 통해 생성된 EntityManger로 SQL 작업.
		EntityManager em = emf.createEntityManager(); // 엔티티 매니저는 쓰레드간에 공유하지 않음. 해당 트랜잭션에만 사용하고 close
		
		// 데이터를 변경하는 작업에는 꼭 Transaction이 필요하다.
		EntityTransaction tx = em.getTransaction();	// JPA 모든 데이터 변경은 트랜잭션 안에서 실행됨.
		
		try {
			tx.begin(); // SQL 실행 코드 전에 트랜잭션 begin 선언
			
			// 1) 등록
			/*
			Member member = new Member();
			member.setId(2L);
			member.setName("HelloB");
			em.persist(member); // Member 테이블에 데이터 Insert
			*/
			
			// 2) 조회
			Member findMember = em.find(Member.class, 1L); // EntityClass, PK ID로 Member 조회
			System.out.println("findMember.id = " + findMember.getId());
			System.out.println("findMember.Name = " + findMember.getUsername());
			
			// 3) 수정
			// 자바 컬렉션과 같은 개념으로 setName으로 데이터를 변경하면 JPA에서 커밋 시점 전에 체크하고 DB에 업데이트 쿼리를 날림.
			findMember.setUsername("HelloJPA"); 
			
			// JPQL를 사용하여 Custom query test
			// 객체를 대상으로 하는 객체지향 쿼리이므로 쿼리문의 Member는 테이블 Member가 아닌 엔티티 객체 Member를 가르키고 있다.
			// JPA에서 해당 객체 쿼리를 설정한 RDBMS의 방언에 맞춰서 대신 작성해준다. JPQL은 실행시 기본적으로 EntityManager.flush()를 실행하도록 설계되어있음.
			List<Member> result = em.createQuery("select m from Member as m", Member.class) // 쿼리, EntityClass로 Member 조회
					.setFirstResult(1) // offset
					.setMaxResults(5) // limit 
					.getResultList(); 
			
			for (Member member : result) {
				System.out.println("member.name= " + member.getUsername());
			}
			
			em.clear(); // 현재 시점에 영속성 컨텍스트에 담긴 엔티티 객체를 모두 삭제
			em.flush(); // 현재 시점에 영속성 컨텍스트의 쓰기 지연 SQL 저장소에 저장 되어있는 SQL 쿼리를 실행함
			tx.commit(); // SQL 실행 코드 이후에 트랜잭션 commit
		} catch (Exception e) {
			tx.rollback(); // 오류 발생시 트랜잭션 롤백
		} finally {
			em.close();	// 작업 종료 후 entityManager close
		}
		
		emf.close();
	}

}
