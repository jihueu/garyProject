package com.nowon.garyProject.domain.dto.goods;

import com.nowon.garyProject.domain.entity.item.Goods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class GoodsInsertDTO {
	
	private long caNo;
	private String name;
	private int price;
	private int sale;
	private boolean isRate;
	private int stock;
	private String defImg;//대표이미지url
	private String gImg;//추가이미지url
	private String content;
	
	public Goods toEntity() {
		if(isRate) {
			sale=price*sale/100;
		}
		return Goods.builder()
				.goodsName(name).price(price)
				.sale(sale).stock(stock).content(content)
				.build();
	}

}
