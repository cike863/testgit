package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.ImSubAccount;
import com.xsbweb.vo.extend.*;

public interface MeetRoomMapper {

	public List<MeetRoomVO> getMeetRoomList(MeetRoomVO meetRoomVO) throws Exception;
	
	public int getMeetRoomListCount(MeetRoomVO meetRoomVO) throws Exception;
	
	public MeetRoomVO getMeetRoomByConfid(String confid) throws Exception;
	
	public int deleteMeetRoomByConfid(String confid) throws Exception;
	
	public int deleteForeMeetRoomByMeetNo(String meetNo) throws Exception;
	
	public MeetRoomVO getForeMeetRoomByMeetNo(String meetNo) throws Exception;
	
	public void addMeetRoom(Map<String, Object> param) throws Exception;
	
	public List<ImSubAccount> getSubAccountList(ImSubAccount subAccount) throws Exception;
	
	public int updateMeetRoomInfo(MeetRoomVO meetRoomVO) throws Exception;
	
	public int updateMeetRoomByMeetNo(MeetRoomVO meetRoomVO) throws Exception;
	/**
	 * 获取一个容联云账户
	 * @return
	 * @throws Exception
	 */
	public ImSubAccount getImSubAccount() throws Exception;
	
	/**
	 * 获取指定个数容联云账户
	 * @param no
	 * @return
	 * @throws Exception
	 */
	public List<ImSubAccount> getImSubAccountList(int no) throws Exception;
	
	public int updateImSubAccount(ImSubAccount imSubAccount) throws Exception;
	
	public int insertImSubAccount(ImSubAccount imSubAccount) throws Exception;
	
	public int cancelImSubAccount(String voipAccount) throws Exception;
	
	public ImSubAccount getCustomerInfoByVoip(String voipAccount)throws Exception;
	
	public List<ImSubAccount> getCustomersByVoips(ImSubAccount imSubAccount)throws Exception;
	
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
	//public int addForeMeetRoom(Map<String, Object> param)throws Exception;
	public void addForeMeetRoom(MeetRoomVO meetRoomVO)throws Exception;
	/**
	 * 获取
	 * @param meetRoomVO
	 * @return
	 * @throws Exception
	 */
	public List<MeetRoomVO> getForeMeetRoomList(MeetRoomVO meetRoomVO)throws Exception;
	/**
	 * 批量删除语音
	 * @param meetNoArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteMeetRoomByMeetNos(String[] meetNoArrs)throws Exception;
	//------------------------------视频直播模块 begin-----------------------------
	public List<MeetVideoRoomVO> getVideoMeetRoomList(MeetVideoRoomVO meetVideoRoomVO)throws Exception;
	
	/**
	 * 获取视频直播模块总数
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	public int getVideoMeetRoomCounts(MeetVideoRoomVO meetVideoRoomVO) throws Exception;
	
	//public int addForeVideoMeetRoom(Map<String, Object> param)throws Exception;
	public void addForeVideoMeetRoom(MeetVideoRoomVO meetVideoRoomVO)throws Exception;
	
	public int deleteVideoMeetRoomBymeetNo(String meetNo) throws Exception;
	
	/**
	 * 根据视频直播编号获取信息
	 * @param meetNo
	 * @return
	 * @throws Exception
	 */
	public MeetVideoRoomVO getVideoMeetRoomByMeetNo(String meetNo) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	public int updateMeetVideoRoom(MeetVideoRoomVO meetVideoRoomVO) throws Exception;
	/**
	 * 批量删除视频直播
	 * @param meetNoArrs
	 * @throws Exception
	 */
	public int batchDeleteVideoMeetRoomByMeetNos(String[] meetNoArrs)throws Exception;
	//------------------------------视频直播模块 end-------------------------------
	
	/**
	 * 点击更改是否展示状态	
	 */
	public int updateMeetIsShowByMeetNo(MeetRoomVO meetRoomVO) throws Exception;

	
}

