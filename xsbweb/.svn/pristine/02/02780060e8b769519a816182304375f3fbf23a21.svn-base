package com.xsbweb.util;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.xsbweb.vo.extend.MeetCallVO;

public class RedisCommnUtils {

	private static Logger log = Logger.getLogger(RedisCommnUtils.class);
	/**
	 * 获取redis中语音会议的成员信息
	 * @param confid
	 * @return
	 */
	public static List<MeetCallVO> getMeetCallListFotRedis(String confid){
		RedisUtil redisUtil = new RedisUtil();
		log.info("confid："+confid);
		List<MeetCallVO> meetCallVOList = redisUtil.getToListJedis("meetconf:"+confid);
		log.info("meetCallVOList："+meetCallVOList);
		if(meetCallVOList==null){
			log.info("##############语音会议："+confid+",redis中会议成员信息集合不存在！");
		}
		return meetCallVOList;
	}
	/**
	 * 存储redis中语音会议的成员信息
	 * @param confid
	 * @return
	 */
	public static void setMeetCallListFotRedis(List<MeetCallVO> meetCallVOList,String confid){
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.addToListJedis("meetconf:"+confid,meetCallVOList);
		//日志记录
		return;
	}
}
