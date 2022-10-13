package com.nowon.garyProject.domain.entity.item;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.nowon.garyProject.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class OrderGoods {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long orderNo;
	@Column(nullable = false)
	private String status;
	
	//멤버테이블과 조인 n:1관계
	@JoinColumn(name = "memNo", nullable = false)
	@ManyToOne // fetchType.EAGER
	private Member members;
	
	public OrderGoods addMember(Member members) {
		this.members=members;
		return this;
	}
	
	//배송테이블과 조인 1:1관계
	@JoinColumn(name = "deliveryNo")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Delivery delivery;
	
	public OrderGoods addDelivery(Delivery delivery) {
		this.delivery=delivery;
		return this;
	}
	
	//상품주문테이블과 조인 1:n관계
	//양방향은 연관테이블생성된다. 그래서 mappedBy속성을 이용하여 연관테이블이 생성되지않게한다. 
	@Builder.Default
	@OneToMany(mappedBy = "orders")//읽기전용
	List<Cart> carts=new Vector<Cart>();
	
	
	

}
