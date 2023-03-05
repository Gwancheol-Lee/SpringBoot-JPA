package hellojpa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
/*
 * @Inheritance(strategy = InheritanceType.XXX)
 * JOINED : 조인 전략(상속받은 자식 엔티티 클래스가 각 테이블로 생성되고 조인하여 저장,조회,수정됨)
 * SINGLE_TABLE : 단일 테이블 전략(부모 엔티티 클래스 테이블 하나에 자식 엔티티 클래스의 컬럼명이 추가되고 단일 테이블로 저장,조회,수정됨)
 * TABLE_PER_CLASS : 구현 클래스마다 테이블 전략
 * 
 * @DiscriminatorColumn(name="DTYPE") : 부모 엔티티 클래스에 선언. 부모 테이블에 저장되는 데이터가 어떤 자식 테이블 관련 데이터인지 구분하는 컬럼 추가. Default 컬럼명 DTYPE
 * @DiscriminatorValue("XXX") : 자식 엔티티 클래스에 선언. 저장되는 DTYPE의 Value값을 지정. Default값은 해당 자식 테이블명
 */
@Inheritance(strategy = InheritanceType.JOINED)
// 조인 엔티티 클래스명이 입력됨. Default 컬럼명=DTYPE
@DiscriminatorColumn
public class Item {
	
	@Id @GeneratedValue()
	private Long id;
	
	private String name;
	private int price;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
