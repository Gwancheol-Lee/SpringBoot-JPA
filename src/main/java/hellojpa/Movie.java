package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//부모 테이블에 저장되는 DTYPE 데이터명을 지정. 기본값은 엔티티 클래스명
@DiscriminatorValue("M")
public class Movie extends Item {
	
	private String director;
	private String actor;
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	
}
