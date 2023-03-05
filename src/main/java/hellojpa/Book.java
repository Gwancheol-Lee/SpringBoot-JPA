package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//부모 테이블에 저장되는 DTYPE 데이터명을 지정. 기본값은 엔티티 클래스명
@DiscriminatorValue("B")
public class Book extends Item {
	
	private String author;
	private String isbn;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
