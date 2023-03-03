package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR", 
		sequenceName = "MEMBER_SEQ",
		initialValue = 1, // 시퀀스 DDL 사용시 처음 시작하는 값
		allocationSize = 1) // 시퀀스 한 번 호출에 증가하는 수
public class Member {
	
	/*
	 * IDENTITY: 키본 키 생성을 DB에 위임 (AUTO_INCREMENT) 
	 * SEQUENCE: PRIMARY KEY로 생성. UNIQUE한 값.
	 */
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME", length = 10, nullable = false) // DB 컬럼 매핑 
	private String username;

//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	@ManyToOne // Member 입장에선 Many, Team이 One
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 조인하는 컬럼명 선언
	private Team team;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;
	
	@OneToMany(mappedBy = "member")
	private List<Order> memberProducts = new ArrayList<>();
	
	public Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Team getTeam() {
		return team;
	}

	public void changeTeam(Team team) { // 다른 비지니스 로직이 들어가는 경우 set말고 다른 메서드 명으로 변경해서 사용하는 것을 권장
		this.team = team;
		team.getMembers().add(this); // Team 객체의 Members도 세팅
	}
	
	/*
	
	@Enumerated(EnumType.STRING) // enum 타입 매핑. ORDINARY 사용X. ORDINARY: enum 순서를 DB에 저장. STRING: enum 이름을 DB에 저장
	private RoleType roleType;
 
	@Temporal(TemporalType.TIMESTAMP)  // 날짜 타입 매핑. 최신버전에서는 해당 어노테이션 선언하지 않아도 JPA에서 알아서 인식함.
	private Date createdDate; 
	
	@Temporal(TemporalType.TIMESTAMP)  
	private Date lastModifiedDate; 
	
	@Lob // BLOB(Boolean), CLOB(String) 매칭
	private String description;
	
	@Transient // DB에 생성 및 매핑 하지 않음. 캐시 메모리에만 저장해서 사용하고자 할 때 사용
	private int temp;
	
	*/
}
