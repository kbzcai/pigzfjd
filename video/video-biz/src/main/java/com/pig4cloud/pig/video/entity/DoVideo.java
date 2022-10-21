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
package com.pig4cloud.pig.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 执法监督（视频分析结果）
 *
 * @author pig code generator
 * @date 2022-10-19 12:04:50
 */
@Data
@TableName("do_video")
//@EqualsAndHashCode(callSuper = true)
@Schema(description = "执法监督（视频分析结果）")
public class DoVideo{

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="主键")
    private String vid;

    /**
     * 线路来源
     */
    @Schema(description ="线路来源")
    private String lineSource;

    /**
     * 音视频名称
     */
    @Schema(description ="音视频名称")
    private String fileName;

    /**
     * 音视频路径
     */
    @Schema(description ="音视频路径")
    private String filePath;

    /**
     * 音视频开始日期
     */
    @Schema(description ="音视频开始日期")
    private LocalDateTime fileDate1;

    /**
     * 音视频结束日期
     */
    @Schema(description ="音视频结束日期")
    private LocalDateTime fileDate2;

    /**
     * 确认时间
     */
    @Schema(description ="确认时间")
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
    private Integer fileStatus;

    /**
     * 备注
     */
    @Schema(description ="备注")
    private String remark;


}
