package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// 부모 테이블에 저장되는 DTYPE 데이터명을 지정. 기본값은 엔티티 클래스명
@DiscriminatorValue("A")
public class Album extends Item {
	
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
