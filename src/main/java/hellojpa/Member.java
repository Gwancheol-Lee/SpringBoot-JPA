package hellojpa;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Member {
	
	@Id
	private Long id;
	
	@Column(name = "username", length = 10, nullable = false) // DB 컬럼 매핑 
	private String name;
	
	private int age;
	
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
	
	public Member() {
	}
	
	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
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
