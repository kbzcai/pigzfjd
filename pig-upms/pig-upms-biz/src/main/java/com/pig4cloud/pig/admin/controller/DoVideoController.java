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

package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.VideoDTO;
import com.pig4cloud.pig.admin.api.entity.DoVideo;
import com.pig4cloud.pig.admin.mapper.DoVideoMapper;
import com.pig4cloud.pig.admin.service.DoVideoService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * 执法监督（视频分析结果）
 *
 * @author pig code generator
 * @date 2022-10-19 12:04:50
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dovideo" )
@Tag(name = "执法监督（视频分析结果）管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DoVideoController {

    private final DoVideoService doVideoService;

    @Autowired
	private final DoVideoMapper doVideoMapper;

    /**
     * 分页查询
     * @param page 分页对象
     * @param videoDTO 执法监督（视频分析结果）
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('video_dovideo_get')" )
    public R getDoVideoPage(Page page, VideoDTO videoDTO) {
		System.out.println(videoDTO.getFileName());
		System.out.println("--------------------");
		System.out.println(videoDTO.getVdStatus());
		System.out.println("--------------------");
		System.out.println(videoDTO.getVdType());
		if (ObjectUtil.isNull(videoDTO.getVdType())){
			videoDTO.setVdType(new ArrayList<String>());
		}
        return R.ok(doVideoService.getListByFileNameAndVdTypeAndVdStatus(page,videoDTO));
    }


    /**
     * 通过id查询执法监督（视频分析结果）
     * @param vid id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{vid}" )
//    @PreAuthorize("@pms.hasPermission('video_dovideo_get')" )
    public R getById(@PathVariable("vid" ) String vid) {
        return R.ok(doVideoService.getById(vid));
    }

    /**
     * 新增执法监督（视频分析结果）
     * @param doVideo 执法监督（视频分析结果）
     * @return R
     */
    @Operation(summary = "新增执法监督（视频分析结果）", description = "新增执法监督（视频分析结果）")
    @SysLog("新增执法监督（视频分析结果）" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('video_dovideo_add')" )
    public R save(@RequestBody DoVideo doVideo) {
        return R.ok(doVideoService.save(doVideo));
    }

    /**
     * 修改执法监督（视频分析结果）
     * @param doVideo 执法监督（视频分析结果）
     * @return R
     */
    @Operation(summary = "修改执法监督（视频分析结果）", description = "修改执法监督（视频分析结果）")
    @SysLog("修改执法监督（视频分析结果）" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('video_dovideo_edit')" )
    public R updateById(@RequestBody DoVideo doVideo) {
        return R.ok(doVideoService.updateById(doVideo));
    }

    /**
     * 通过id删除执法监督（视频分析结果）
     * @param vid id
     * @return R
     */
    @Operation(summary = "通过id删除执法监督（视频分析结果）", description = "通过id删除执法监督（视频分析结果）")
    @SysLog("通过id删除执法监督（视频分析结果）" )
    @DeleteMapping("/{vid}" )
    @PreAuthorize("@pms.hasPermission('video_dovideo_del')" )
    public R removeById(@PathVariable String vid) {
        return R.ok(doVideoService.removeById(vid));
    }

}
