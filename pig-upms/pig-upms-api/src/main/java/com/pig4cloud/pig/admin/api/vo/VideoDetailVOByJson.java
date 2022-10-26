package com.pig4cloud.pig.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VideoDetailVOByJson implements Serializable {
//	/**
//	 * 视频名称
//	 */
//	@Schema(description ="视频名称")
//	private String fileName;
//
//	/**
//	 * 视频路径
//	 */
//	@Schema(description ="视频路径")
//	private String filePath;
//
//	/**
//	 * 违法信息
//	 */
//	@Schema(description ="违法信息")
//	private IllegalInfoVO illegalInfo;

	/**
	 * 违法类型
	 */
	@Schema(description ="违法类型")
	private String vdType;

	/**
	 * 图片路径
	 */
	@Schema(description ="图片路径")
	private String vdPicture;

	/**
	 * 音视频路径
	 */
	@Schema(description ="违法视频路径")
	private String vdFile;

	/**
	 * 违法开始时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Schema(description ="违法开始时间")
	private LocalDateTime vdTime1;

	/**
	 * 违法结束时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Schema(description ="违法结束时间")
	private LocalDateTime vdTime2;
}
