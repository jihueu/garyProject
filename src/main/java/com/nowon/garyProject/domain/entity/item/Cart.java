package com.nowon.garyProject.domain.entity.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Cart {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long cartNo;
	//int는 널값X 0으로 들어갈거야
	int orderPrice;
	int count;
	@JoinColumn(name = "orderNo", nullable = false)
	@ManyToOne(cascade = CascadeType.PERSIST)//cascade(주엔터티), fetch
	private OrderGoods orders;
	//name은 물리DB의 이름이라 _사용해서 넣어도 됨
	@JoinColumn(name = "goodsNo", nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH)
	private Goods goods;
}
