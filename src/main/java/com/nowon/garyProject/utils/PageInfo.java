package com.nowon.garyProject.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageInfo {
	
	private int start;
	private int end;
	private int tot;
	
	
	private void calcPage(int pageNo) {
			
		int pageBlock=5;//페이지번호 개수
		
		int blockNo=pageNo/pageBlock;
		if(pageNo%pageBlock!=0) { blockNo++;}
		end=pageBlock*blockNo;
		start=end-pageBlock+1;
		if(end>tot)end=tot;
	}
	
	public static PageInfo getInstance(int pageNo, int rowTotal, int limit) {
		return new PageInfo(pageNo, rowTotal, limit);
	}
	private PageInfo(int pageNo, int rowTotal, int limit) {
		
		tot=rowTotal/limit;
		if(rowTotal%limit>0) tot++;
		calcPage(pageNo);
	}
	
	public static PageInfo getInstance(int pageNo,int pageTotal) {
		return new PageInfo(pageNo, pageTotal);
	}
	private PageInfo(int pageNo, int pageTotal) {
		tot=pageTotal;
		calcPage(pageNo);
	}
}
