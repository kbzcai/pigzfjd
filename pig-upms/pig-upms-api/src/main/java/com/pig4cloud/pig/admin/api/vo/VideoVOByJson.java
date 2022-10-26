package com.pig4cloud.pig.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VideoVOByJson implements Serializable {
	/**
	 * 视频名称
	 */
	@Schema(description = "视频名称")
	private String fileName;

	/**
	 * 视频路径
	 */
	@Schema(description = "视频路径")
	private String filePath;

	/**
	 * 识别状态
	 */
	@Schema(description = "识别状态")
	private Integer discriminateStatus;

	/**
	 * 是否存在违法
	 * 状态（0：未确认，1：确认，2：不是）
	 */
	@Schema(description = "状态（0：未确认，1：确认，2：不是）")
	private Integer fileStatus;

	/**
	 * 事件日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Schema(description = "事件日期")
	private LocalDateTime eventTime;

	/**
	 * 分析开始日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Schema(description = "分析开始日期")
	private LocalDateTime analysisStartTime;

	/**
	 * 分析结束日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Schema(description = "分析结束日期")
	private LocalDateTime analysisEndTime;

	@Schema(description = "执法分析明细")
	private List<VideoDetailVOByJson> videoDetailList;
}
