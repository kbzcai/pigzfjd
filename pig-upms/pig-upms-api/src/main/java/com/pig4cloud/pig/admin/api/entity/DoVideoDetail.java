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
 * 执法监督明细（视频分析结果明细）
 *
 * @author pig code generator
 * @date 2022-10-19 12:18:04
 */
@Data
@TableName("do_video_detail")
//@EqualsAndHashCode(callSuper = true)
@Schema(description = "执法监督明细（视频分析结果明细）")
public class DoVideoDetail{

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="主键")
    private String vdid;

	@Schema(description ="创建日期")
	private LocalDateTime createTime;

    /**
     * 关联 do_video.vid
     */
    @Schema(description ="关联 do_video.vid")
    private String vid;

    /**
     * 违法类型
     */
    @Schema(description ="违法类型")
    private String vdType;

    /**
     * 截图，多个逗号分割
     */
    @Schema(description ="截图，多个逗号分割")
    private String vdPicture;

    /**
     * 违法开始时间
     */
    @Schema(description ="违法开始时间")
    private LocalDateTime vdTime1;

    /**
     * 违法结束时间
     */
    @Schema(description ="违法结束时间")
    private LocalDateTime vdTime2;

    /**
     * 确认时间（注意回填主表）
     */
    @Schema(description ="确认时间（注意回填主表）")
    private LocalDateTime confirmTime;

    /**
     * 确认人
     */
    @Schema(description ="确认人")
    private String confirmUser;

    /**
     * 状态（0：未确认，1：确认，2：不是）
     */
    @Schema(description ="状态（0：未确认，1：确认，2：不是）")
    private Integer vdStatus;

    /**
     * 备注
     */
    @Schema(description ="备注")
    private String remark;


}
