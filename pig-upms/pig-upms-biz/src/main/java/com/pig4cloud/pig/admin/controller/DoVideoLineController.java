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

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.DoVideo;
import com.pig4cloud.pig.admin.api.entity.DoVideoDetail;
import com.pig4cloud.pig.admin.api.entity.DoVideoLine;
import com.pig4cloud.pig.admin.api.vo.VideoDetailVOByJson;
import com.pig4cloud.pig.admin.api.vo.VideoLineVOByJson;
import com.pig4cloud.pig.admin.api.vo.VideoVOByJson;
import com.pig4cloud.pig.admin.service.DoVideoLineService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import com.pig4cloud.pig.common.security.annotation.Inner;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 设备管理（视频线路）
 *
 * @author pig code generator
 * @date 2022-10-19 12:42:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dovideoline")
@Tag(name = "设备管理（视频线路）管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DoVideoLineController {

	private final DoVideoLineService doVideoLineService;

	/**
	 * 分页查询
	 *
	 * @param page        分页对象
	 * @param doVideoLine 设备管理（视频线路）
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_get')")
	public R getDoVideoLinePage(Page page, DoVideoLine doVideoLine) {
		System.out.println(doVideoLine);
		QueryWrapper wrapper = new QueryWrapper();
		if (!doVideoLine.getVlKey().equals("")) {
			wrapper.like("vl_key", doVideoLine.getVlKey());
		}
		if (!doVideoLine.getVlName().equals("")) {
			wrapper.like("vl_name", doVideoLine.getVlName());
		}
		if (!doVideoLine.getVlUser().equals("")) {
			wrapper.like("vl_user", doVideoLine.getVlUser());
		}
		wrapper.orderByDesc("create_time");
		return R.ok(doVideoLineService.page(page, wrapper));
	}


	/**
	 * 通过id查询设备管理（视频线路）
	 *
	 * @param vlid id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{vlid}")
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_get')")
	public R getById(@PathVariable("vlid") String vlid) {
		return R.ok(doVideoLineService.getById(vlid));
	}

	/**
	 * 新增设备管理（视频线路）
	 *
	 * @param doVideoLine 设备管理（视频线路）
	 * @return R
	 */
	@Operation(summary = "新增设备管理（视频线路）", description = "新增设备管理（视频线路）")
	@SysLog("新增设备管理（视频线路）")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_add')")
	public R save(@RequestBody DoVideoLine doVideoLine) {
		doVideoLine.setCreateTime(LocalDateTime.now());
		return R.ok(doVideoLineService.save(doVideoLine));
	}

	/**
	 * 修改设备管理（视频线路）
	 *
	 * @param doVideoLine 设备管理（视频线路）
	 * @return R
	 */
	@Operation(summary = "修改设备管理（视频线路）", description = "修改设备管理（视频线路）")
	@SysLog("修改设备管理（视频线路）")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_edit')")
	public R updateById(@RequestBody DoVideoLine doVideoLine) {
		return R.ok(doVideoLineService.updateById(doVideoLine));
	}

	/**
	 * 通过id删除设备管理（视频线路）
	 *
	 * @param vlid id
	 * @return R
	 */
	@Operation(summary = "通过id删除设备管理（视频线路）", description = "通过id删除设备管理（视频线路）")
	@SysLog("通过id删除设备管理（视频线路）")
	@DeleteMapping("/{vlid}")
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_del')")
	public R removeById(@PathVariable String vlid) {
		return R.ok(doVideoLineService.removeById(vlid));
	}

	/**
	 * 通过ids删除设备管理
	 *
	 * @param ids ids
	 * @return R
	 */
	@Operation(summary = "通过ids删除设备管理", description = "通过ids删除设备管理")
	@SysLog("通过ids删除设备管理")
	@DeleteMapping("/batchRemove")
	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_del')")
	public R removeByIds(@RequestBody String[] ids) {
		System.out.println(ids);
		List idList = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		return R.ok(doVideoLineService.removeByIds(idList));
	}

//	/**
//	 * 通过ids启用设备
//	 *
//	 * @param ids ids
//	 * @return R
//	 */
//	@Operation(summary = "通过ids启用设备", description = "通过ids启用设备")
//	@SysLog("通过ids启用设备")
//	@PutMapping("/startVideoLineByIds")
//	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_edit')")
//	public R startVideoLineByIds(@RequestBody String[] ids) {
//		System.out.println(ids);
//		List idList = new ArrayList();
//		for (int i = 0; i < ids.length; i++) {
//			System.out.println(ids[i]);
//			DoVideoLine doVideoLine = doVideoLineService.getById(ids[i]);
//			doVideoLineService.updateById(doVideoLine);
//		}
//		return R.ok();
//	}
//
//	/**
//	 * 通过ids停止设备
//	 *
//	 * @param ids ids
//	 * @return R
//	 */
//	@Operation(summary = "通过ids停止设备", description = "通过ids停止设备")
//	@SysLog("通过ids停止设备")
//	@PutMapping("/stopVideoLineByIds")
//	@PreAuthorize("@pms.hasPermission('videoline_dovideoline_edit')")
//	public R stopVideoLineByIds(@RequestBody String[] ids) {
//		System.out.println(ids);
//		List idList = new ArrayList();
//		for (int i = 0; i < ids.length; i++) {
//			System.out.println(ids[i]);
//			DoVideoLine doVideoLine = doVideoLineService.getById(ids[i]);
//			doVideoLineService.updateById(doVideoLine);
//		}
//		return R.ok();
//	}

	@Inner(value = false)
	@Operation(summary = "调用别人接口获取json来新增设备管理", description = "调用别人接口获取json来新增设备管理")
	@SysLog("调用别人接口获取json来新增设备管理")
	@PostMapping("/saveByJson")
	public R saveByJson(@RequestBody List<VideoLineVOByJson> videoLineVOList) {
		System.out.println(videoLineVOList);
		for (VideoLineVOByJson o : videoLineVOList) {
			System.out.println(o);
			System.out.println("updateTime=" + o.getUpdateTime()
					+ ",vlKey=" + o.getVlKey()
					+ ",vlName=" + o.getVlName()
					+ ",vlUser=" + o.getVlUser()
					+ ",vlRtsp=" + o.getVlRtsp());
			QueryWrapper wrapper = new QueryWrapper();
			wrapper.eq("vl_key", o.getVlKey());
			wrapper.eq("vl_name", o.getVlName());

			DoVideoLine videoLine = doVideoLineService.getOne(wrapper);
			if (ObjectUtil.isNotEmpty(videoLine)) {
				videoLine.setUpdateTime(o.getUpdateTime());
				videoLine.setVlUser(o.getVlUser());
				videoLine.setVlRtsp(o.getVlRtsp());
				doVideoLineService.updateById(videoLine);
			} else {
				DoVideoLine line = new DoVideoLine();
				line.setVlid(IdUtil.getSnowflake(1, 1).nextIdStr());
				line.setCreateTime(LocalDateTime.now());
				line.setUpdateTime(o.getUpdateTime());
				line.setVlUser(o.getVlUser());
				line.setVlKey(o.getVlKey());
				line.setVlName(o.getVlName());
				line.setVlRtsp(o.getVlRtsp());
				doVideoLineService.save(line);
			}
		}
		return R.ok("设备更新成功");
//		DoVideo doVideo = new DoVideo();
//		String vid= IdUtil.getSnowflake(1,1).nextIdStr();
//		doVideo.setVid(vid);
//		doVideo.setCreateTime(LocalDateTime.now());
//		doVideo.setFileName(videoVO.getFileName());
//		doVideo.setFilePath(videoVO.getFilePath());
//		doVideo.setEventTime(videoVO.getEventTime());
//		doVideo.setFileStatus(videoVO.getFileStatus());
//		doVideoService.save(doVideo);
////		DoVideo query = getByFileName(doVideo.getFileName());
//		DoVideo query = (DoVideo) getById(vid).getData();
//		System.out.println(query);
//		if (videoVO.getVideoDetailList().size() > 0) {
//			List<VideoDetailVOByJson> videoDetailVOList = videoVO.getVideoDetailList();
//			for (VideoDetailVOByJson videoDetailVO : videoDetailVOList) {
//				DoVideoDetail videoDetail = new DoVideoDetail();
//				videoDetail.setVdid(IdUtil.getSnowflake(1,1).nextIdStr());
//				videoDetail.setVid(query.getVid());
//				videoDetail.setVdType(videoDetailVO.getVdType());
//				videoDetail.setVdTime1(videoDetailVO.getVdTime1());
//				videoDetail.setVdTime2(videoDetailVO.getVdTime2());
//				videoDetail.setVdPicture(videoDetailVO.getVdPicture());
//				videoDetail.setCreateTime(LocalDateTime.now());
//				doVideoDetailService.save(videoDetail);
//			}
//		}

	}
}
