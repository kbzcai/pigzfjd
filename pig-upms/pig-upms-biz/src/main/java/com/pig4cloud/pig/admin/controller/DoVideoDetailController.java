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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.DoVideo;
import com.pig4cloud.pig.admin.api.entity.DoVideoDetail;
import com.pig4cloud.pig.admin.service.DoVideoDetailService;
import com.pig4cloud.pig.admin.service.DoVideoService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 执法监督明细（视频分析结果明细）
 *
 * @author pig code generator
 * @date 2022-10-19 12:18:04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dovideodetail" )
@Tag(name = "执法监督明细（视频分析结果明细）管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DoVideoDetailController {

    private final DoVideoDetailService doVideoDetailService;

	private final DoVideoService doVideoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doVideoDetail 执法监督明细（视频分析结果明细）
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
    public R getDoVideoDetailPage(Page page, DoVideoDetail doVideoDetail) {
        return R.ok(doVideoDetailService.page(page, Wrappers.query(doVideoDetail)));
    }


    /**
     * 通过id查询执法监督明细（视频分析结果明细）
     * @param vdid id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{vdid}" )
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
    public R getById(@PathVariable("vdid" ) String vdid) {
        return R.ok(doVideoDetailService.getById(vdid));
    }


	/**
	 * 根据vid分页查询
	 * @param page 分页对象
	 * @param doVideoDetail 执法监督明细（视频分析结果明细）
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/pageByVid" )
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
	public R getDoVideoDetailPageByVid(Page page, DoVideoDetail doVideoDetail) {
		System.out.println(doVideoDetail.getVid());
		QueryWrapper wrapper=new QueryWrapper();
//		wrapper.eq("vid",vid);
		wrapper.eq("vid",doVideoDetail.getVid());
		wrapper.orderByDesc("create_time");
		return R.ok(doVideoDetailService.page(page,wrapper));
	}

    /**
     * 新增执法监督明细（视频分析结果明细）
     * @param doVideoDetail 执法监督明细（视频分析结果明细）
     * @return R
     */
    @Operation(summary = "新增执法监督明细（视频分析结果明细）", description = "新增执法监督明细（视频分析结果明细）")
    @SysLog("新增执法监督明细（视频分析结果明细）" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_add')" )
    public R save(@RequestBody DoVideoDetail doVideoDetail) {
        return R.ok(doVideoDetailService.save(doVideoDetail));
    }

    /**
     * 修改执法监督明细（视频分析结果明细）
     * @param doVideoDetail 执法监督明细（视频分析结果明细）
     * @return R
     */
    @Operation(summary = "修改执法监督明细（视频分析结果明细）", description = "修改执法监督明细（视频分析结果明细）")
    @SysLog("修改执法监督明细（视频分析结果明细）" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_edit')" )
    public R updateById(@RequestBody DoVideoDetail doVideoDetail) {
    	if (doVideoDetail.getVdStatus().equals(1)){
			DoVideoDetail videoDetail = doVideoDetailService.getById(doVideoDetail.getVdid());
			DoVideo doVideo = doVideoService.getById(videoDetail.getVid());
			doVideo.setFileStatus(1);
			doVideoService.updateById(doVideo);
		}
        return R.ok(doVideoDetailService.updateById(doVideoDetail));
    }

    /**
     * 通过id删除执法监督明细（视频分析结果明细）
     * @param vdid id
     * @return R
     */
    @Operation(summary = "通过id删除执法监督明细（视频分析结果明细）", description = "通过id删除执法监督明细（视频分析结果明细）")
    @SysLog("通过id删除执法监督明细（视频分析结果明细）" )
    @DeleteMapping("/{vdid}" )
    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_del')" )
    public R removeById(@PathVariable String vdid) {
        return R.ok(doVideoDetailService.removeById(vdid));
    }

}
