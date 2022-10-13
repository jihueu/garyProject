package com.nowon.garyProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileData {
	
	protected String url;
	protected String orgName;
	protected String newName;
	protected long size;

}
