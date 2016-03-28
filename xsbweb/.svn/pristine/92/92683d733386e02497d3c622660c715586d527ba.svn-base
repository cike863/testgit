package com.xsbweb.service;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.ImSubAccount;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.MeetRoomVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;

public interface MeetRoomService {
	
	//----------------app接口---
	public List<RObject> getShowingMeetRoomList(RObject rObject,String meetType) throws Exception;
	public List<RObject> getMeetRoomListByType(RObject rObject,String type,String meetType) throws Exception;
	//-------------------------

	public List<MeetRoomVO> getMeetRoomList(MeetRoomVO meetRoomVO) throws Exception;
	public int getMeetRoomListCount(MeetRoomVO meetRoomVO) throws Exception;
	
	public MeetRoomVO getMeetRoomByConfid(String confid) throws Exception;
	
	public int deleteMeetRoomByConfid(String confid) throws Exception;
	
	public int addMeetRoom(MeetRoomVO meetRoomVO) throws Exception;
	
	public List<ImSubAccount> getSubAccountList(ImSubAccount subAccount) throws Exception;
	
	public int updateMeetRoomInfo(MeetRoomVO meetRoomVO) throws Exception;
	
	/**
	 * 获取一个容联云账户
	 * @return
	 * @throws Exception
	 */
	public ImSubAccount getImSubAccount() throws Exception;
	
	public int updateImSubAccount(ImSubAccount imSubAccount) throws Exception;
	
	public int insertImSubAccount(ImSubAccount imSubAccount) throws Exception;
	
	public int cancelImSubAccount(String voipAccount) throws Exception;
	
	public ImSubAccount getCustomerInfoByVoip(String voipAccount)throws Exception;
	
	public List<ImSubAccount> getCustomersByVoips(ImSubAccount imSubAccount)throws Exception;
	
	public List<ImSubAccount> getImSubAccountList(int no) throws Exception;
	
	/**
	 * 批量给容联voip账号关联custmerId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBatchImSubAccount(Map<String,String> map)throws Exception;
	
	/**
	 * 根据手机号码数组查询容联voip账号
	 * @param imSubAccount
	 * @return
	 * @throws Exception
	 */
	public List<ImSubAccount> getImSubAccountByPhones(ImSubAccount imSubAccount)throws Exception;
	
	/**
	 * 批量释放voip账号
	 * @param imSubAccount
	 * @return
	 * @throws Exception
	 */
	public int cancelBatchImSubAccount(ImSubAccount imSubAccount)throws Exception;
	
	//------------------------------语音直播预告-----------------------------
	/**
	 * 新增语音会议直播预告
	 * @param meetRoomVO
	 * @return
	 * @throws Exception
	 */
	public int addForeMeetRoom(MeetRoomVO meetRoomVO)throws Exception;
	
	/**
	 * 获取
	 * @param meetRoomVO
	 * @return
	 * @throws Exception
	 */
	public List<MeetRoomVO> getForeMeetRoomList(MeetRoomVO meetRoomVO)throws Exception;
	
	public int deleteMeetRoomByMeetNo(String meetNo) throws Exception;
	/**
	 * 通过ID更改状态(声明)
	 * @param meetRoomVO
	 * @return
	 * @throws Exception
	 */
	public int updateMeetIsShowByMeetNo(MeetRoomVO meetRoomVO) throws Exception;
	
	//------------------------------视频直播模块 begin-----------------------------
	public List<MeetVideoRoomVO> getVideoMeetRoomList(MeetVideoRoomVO meetVideoRoomVO)throws Exception;

	/**
	 * 获取视频直播模块总数
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	public int getVideoMeetRoomCounts(MeetVideoRoomVO meetVideoRoomVO) throws Exception;
	
	public int addForeVideoMeetRoom(MeetVideoRoomVO meetVideoRoomVO)throws Exception;
	
	public int deleteVideoMeetRoomBymeetNo(String meetNo) throws Exception;
	
	/**
	 * 根据视频直播编号获取信息
	 * @param meetNo
	 * @return
	 * @throws Exception
	 */
	public MeetVideoRoomVO getVideoMeetRoomByMeetNo(String meetNo) throws Exception;
	
	/**
	 * 给wap提供路演直播中的数据
	 * @param rObject
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getShowingMeetToWap(RObject rObject) throws Exception;
	/**
	 * 给wap提供路演直播预告和往期
	 * @param rObject
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getMeetRoomListByTypeToWap(RObject rObject, String type)
			throws Exception;
	//------------------------------视频直播模块 end-------------------------------
	
	/**
	 * 开始直播或者结束直播
	 * @param meetNo
	 * @param baseUrl
	 * @param showStatus
	 * @return
	 * @throws Exception
	 */
	public int updateMeetToShowStatus(String meetNo, String baseUrl,String meetRole)throws Exception;
	public void updateVideoMeetRoomByMeetNo(MeetVideoRoomVO meetVideoRoomVO)throws Exception;
	
	/**
	 * 通过meetNo获取语音直播详情
	 * @param meetNo
	 * @return
	 * @throws Exception 
	 */
	public MeetRoomVO getForeMeetRoomByMeetNo(String meetNo) throws Exception;
	/**
	 * 通过meetNo更新语音直播详情
	 * @param meetRoomVO
	 * @throws Exception
	 */
	public void editMeetRoomByMeetNo(MeetRoomVO meetRoomVO)throws Exception;
	/**
	 * 批量删除视频直播
	 * @param meetNoArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteVideoMeetRoomByMeetNos(String[] meetNoArrs)throws Exception;
	/**
	 * 批量删除语音直播
	 * @param meetNoArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteMeetRoomByMeetNos(String[] meetNoArrs)throws Exception;
	
	
	
}
