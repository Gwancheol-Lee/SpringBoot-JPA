package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {
	
	@Id @GeneratedValue()
	@Column(name = "LOCKER_ID")
	private Long id;
	
	private String name;
	
	@OneToOne(mappedBy = "locker")
	private Member member;
	
	public Locker() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
