package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.HTMLDocument.Iterator;

public class JpaInheritanceMapping {

	public static void main(String[] args) {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin(); 
			
			Movie movie = new Movie();
			movie.setDirector("aaaa");
			movie.setActor("bbbb");
			movie.setName("바람과 함께 사라지다");
			movie.setPrice(10000);
			em.persist(movie);
			
			em.flush();
			em.clear();
			
			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("findMovie= " + findMovie);
			
			Item item = em.find(Item.class, movie.getId());
			System.out.println("item= " + item);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
