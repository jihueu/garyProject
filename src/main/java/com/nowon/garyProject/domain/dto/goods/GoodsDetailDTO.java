package com.nowon.garyProject.domain.dto.goods;

import java.util.List;
import java.util.stream.Collectors;

import com.nowon.garyProject.domain.entity.item.Goods;

import lombok.Getter;

@Getter
public class GoodsDetailDTO {
	
	private long goodsNo;
	private String goodsName;
	private int price;//판매가, 정가
	private int sale;//할인가 or 할인율
	private int stock;//재고
	private String content;
	private List<GoodsFileDTO> files;
	
	public GoodsDetailDTO(Goods e) {
		
		this.goodsNo = e.getGoodsNo();
		this.goodsName = e.getGoodsName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		this.content=e.getContent();
		//파일정보: List<GoodsFile>->List<GoodsFileDTO>
		this.files=e.getGfiles().stream()
				.map(GoodsFileDTO::new)
				.collect(Collectors.toList());
	}
}
