package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class VideoDTO {
	private String fileName;
	private List<String> vdType;
	private Integer vdStatus;
}
