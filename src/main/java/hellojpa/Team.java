package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team") // mappedBy -> 외래키(FK)가 있는 Member 객체의 team을 연관관계 주인으로 지정. 연관관계 주인의 데이터를 읽기만 가능.
	private List<Member> members = new ArrayList<>();
	
	public Team() {
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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
