package com.nowon.garyProject.domain.dto.goods;

import com.nowon.garyProject.domain.entity.item.Goods;

import lombok.Getter;

@Getter
public class GoodsListDTO {
	
	private long goodsNo;
	private String goodsName;
	private int price;//판매가, 정가
	private int sale;//할인가 or 할인율
	private int stock;//재고
	private String defImgUrl;//url
	
	public GoodsListDTO(Goods e) {
		
		this.goodsNo = e.getGoodsNo();
		this.goodsName = e.getGoodsName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		//파일정보: List<goodsFile>->List<goodsFileDTO>
		
		e.getGfiles().forEach(fe->{
			if(fe.isDefImg())defImgUrl=fe.getUrl()+fe.getOrgName();
		});
	}
}
