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

import com.pig4cloud.pig.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 执法监督明细（视频分析结果明细）
 *
 * @author pig code generator
 * @date 2022-10-19 12:18:04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dovideodetail")
@Tag(name = "执法监督明细（视频分析结果明细）管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DoVideoDetailController {

	private final DoVideoDetailService doVideoDetailService;

	private final DoVideoService doVideoService;

	/**
	 * 分页查询
	 *
	 * @param page          分页对象
	 * @param doVideoDetail 执法监督明细（视频分析结果明细）
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
	public R getDoVideoDetailPage(Page page, DoVideoDetail doVideoDetail) {
		return R.ok(doVideoDetailService.page(page, Wrappers.query(doVideoDetail)));
	}


	/**
	 * 通过id查询执法监督明细（视频分析结果明细）
	 *
	 * @param vdid id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{vdid}")
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
	public R getById(@PathVariable("vdid") String vdid) {
		return R.ok(doVideoDetailService.getById(vdid));
	}


	/**
	 * 根据vid分页查询
	 *
	 * @param page          分页对象
	 * @param doVideoDetail 执法监督明细（视频分析结果明细）
	 * @return
	 */
	@Operation(summary = "根据vid分页查询，查询结果排序先名称升序，后创建时间倒序", description = "根据vid分页查询，查询结果排序先名称升序，后创建时间倒序")
	@SysLog("根据vid分页查询，查询结果排序先名称升序，后创建时间倒序")
	@GetMapping("/pageByVid")
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_get')" )
	public R getDoVideoDetailPageByVid(Page page, DoVideoDetail doVideoDetail) {
		System.out.println(doVideoDetail.getVid());
		//根据名称升序，时间倒序
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("vid", doVideoDetail.getVid());
		wrapper.orderByDesc("create_time");
		return R.ok(doVideoDetailService.page(page, wrapper));
	}

	/**
	 * 新增执法监督明细（视频分析结果明细）
	 *
	 * @param doVideoDetail 执法监督明细（视频分析结果明细）
	 * @return R
	 */
	@Operation(summary = "新增执法监督明细（视频分析结果明细）", description = "新增执法监督明细（视频分析结果明细）")
	@SysLog("新增执法监督明细（视频分析结果明细）")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_add')")
	public R save(@RequestBody DoVideoDetail doVideoDetail) {
		return R.ok(doVideoDetailService.save(doVideoDetail));
	}

	/**
	 * 修改执法监督明细（视频分析结果明细）
	 *
	 * @param doVideoDetail 执法监督明细（视频分析结果明细）
	 * @return R
	 */
	@Operation(summary = "修改执法监督明细（视频分析结果明细）", description = "修改执法监督明细（视频分析结果明细）")
	@SysLog("修改执法监督明细（视频分析结果明细）")
	@PutMapping
//    @PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_edit')" )
	public R updateById(@RequestBody DoVideoDetail doVideoDetail) {

		DoVideoDetail videoDetail = doVideoDetailService.getById(doVideoDetail.getVdid());
		//通过外键获取对应主表的数据
		DoVideo doVideo = doVideoService.getById(videoDetail.getVid());
		//只要副表有一条通过，主表就通过
		if (doVideoDetail.getVdStatus().equals(1)) {
			doVideo.setFileStatus(1);
		}
		doVideo.setConfirmTime(LocalDateTime.now());
		doVideo.setConfirmUser(SecurityUtils.getUser().getName());
		doVideoDetail.setConfirmTime(LocalDateTime.now());
		doVideoDetail.setConfirmUser(SecurityUtils.getUser().getName());
		boolean b = doVideoDetailService.updateById(doVideoDetail);
		if (doVideoDetail.getVdStatus().equals(2)) {
			//每次更新完毕后都判断附表数据状态是不是全都为不通过，如果全部不通过，主表也不通过
			QueryWrapper wrapper = new QueryWrapper();
			wrapper.eq("vid", doVideo.getVid());
			List<DoVideoDetail> list = doVideoDetailService.list(wrapper);
			System.out.println("输出的list是" + list);
			int unPass = 0;
			for (DoVideoDetail detail : list) {
				if (detail.getVdStatus().equals(2)) {
					unPass++;
//				System.out.println(unPass + "个未通过");
				}
			}
			//如果全都是未通过，则主表设置未通过
			if (unPass == list.size()) {
//			System.out.println("全部都未通过");
				doVideo.setFileStatus(2);
			}
		}
		doVideoService.updateById(doVideo);
		return R.ok(b);
	}

	/**
	 * 通过id删除执法监督明细（视频分析结果明细）
	 *
	 * @param vdid id
	 * @return R
	 */
	@Operation(summary = "通过id删除执法监督明细（视频分析结果明细）", description = "通过id删除执法监督明细（视频分析结果明细）")
	@SysLog("通过id删除执法监督明细（视频分析结果明细）")
	@DeleteMapping("/{vdid}")
	@PreAuthorize("@pms.hasPermission('videodetail_dovideodetail_del')")
	public R removeById(@PathVariable String vdid) {
		return R.ok(doVideoDetailService.removeById(vdid));
	}

}
