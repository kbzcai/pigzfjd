package com.pig4cloud.pig.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VideoLineVOByJson implements Serializable {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Schema(description = "变更日期")
	private LocalDateTime updateTime;

	/**
	 * 视频线路标识
	 */
	@Schema(description = "视频线路标识")
	private String vlKey;

	/**
	 * 视频线路名称
	 */
	@Schema(description = "视频线路名称")
	private String vlName;

	/**
	 * 视频线路所属人
	 */
	@Schema(description = "视频线路所属人")
	private String vlUser;

	/**
	 * 视频线路 RTSP 流
	 */
	@Schema(description = "视频线路 RTSP 流")
	private String vlRtsp;

}
