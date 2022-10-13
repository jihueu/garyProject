package com.nowon.garyProject.domain.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Delivery {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long deliveryNo;
	@Column(nullable = false)
	private int zipcode;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String detail;
	@Column(nullable = false)
	private String status;
	
	@OneToOne(mappedBy = "delivery")
	private OrderGoods oGoods;

}
