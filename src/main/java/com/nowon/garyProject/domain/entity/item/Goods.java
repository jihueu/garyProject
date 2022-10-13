package com.nowon.garyProject.domain.entity.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.nowon.garyProject.domain.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Goods {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long goodsNo;
	String goodsName;
	int price;//판매가, 정가
	int sale;//할인가 or 할인율
	int stock;//재고
	@Column(columnDefinition = "text", nullable = false)
	String content;
	//색상
			
	@Builder.Default
	@JoinColumn(name = "gno")//주엔터티로 설정함
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	List<GoodsFile> gfiles=new ArrayList<>();
	
	public Goods addFile(GoodsFile file) {
		gfiles.add(file);
		return this;
	}
	
	public Goods removeFile(GoodsFile file) {
		gfiles.remove(file);
		return this;
	}
	
	@Builder.Default	
	@JoinColumn//categorys_ca_no
	@ManyToMany(cascade = CascadeType.DETACH)
	Set<Category> categorys=new HashSet<>();
	
	public Goods addCategory(Category category) {
		categorys.add(category);
		return this;
	}
	
	public Goods removeCategory(Category category) {
		categorys.remove(category);
		return this;
	}
	
	public Goods removeCategoryAll() {
		categorys.clear();
		return this;
	}
	
	@Builder.Default
	@OneToMany(mappedBy = "goods")
	List<Cart> carts=new ArrayList<>();
	

}
