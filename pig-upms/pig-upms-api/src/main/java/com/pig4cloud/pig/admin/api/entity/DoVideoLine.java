/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 设备管理（视频线路）
 *
 * @author pig code generator
 * @date 2022-10-19 12:42:16
 */
@Data
@TableName("do_video_line")
//@EqualsAndHashCode(callSuper = true)
@Schema(description = "设备管理（视频线路）")
public class DoVideoLine {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="主键")
    private String vlid;


	@Schema(description ="创建日期")
	private LocalDateTime createTime;

	@Schema(description ="修改日期")
	private LocalDateTime updateTime;



	/**
     * 视频线路标识
     */
    @Schema(description ="视频线路标识")
    private String vlKey;

    /**
     * 视频线路名称
     */
    @Schema(description ="视频线路名称")
    private String vlName;

    /**
     * 视频线路所属人
     */
    @Schema(description ="视频线路所属人")
    private String vlUser;

    /**
     * 视频线路 RTSP 流
     */
    @Schema(description ="视频线路 RTSP 流")
    private String vlRtsp;

    /**
     * 备注
     */
    @Schema(description ="备注")
    private String remark;


}
