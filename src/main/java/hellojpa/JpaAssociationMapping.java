package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.HTMLDocument.Iterator;

public class JpaAssociationMapping {

	public static void main(String[] args) {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			member.changeTeam(team); //**
			em.persist(member);
			
			em.flush();
			em.clear();
			
			Member findMember = em.find(Member.class, member.getId());
			/*
			 * 최대한 단방향 매핑으로 설계 및 개발을 끝내고 꼭 필요한 경우에만 양방향 매핑을 추가할 것. 
			 * 연관관계의 주인은 외래 키의 위치를 기준으로 정할 것.  
			 */
			
			// 단방향 매핑 ( Member 객체에서 Team 객체를 참조. ManyToOne )
			Team findTeam = findMember.getTeam();
			// 양방향 매핑 ( Member 객체 Team 객체 참조 + Team 객체에서 Member 객체 List Collection으로 참조. ManyToOne + OneToMany )
			List<Member> members = findMember.getTeam().getMembers();
			for (Member m : members) {
				System.out.println("m = " + m.getUsername());
				
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
