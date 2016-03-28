package com.xsbweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.controller.manage.AppMeetRoomController;
import com.xsbweb.mapper.APPCommonMapper;
import com.xsbweb.mapper.CommonMapper;
import com.xsbweb.mapper.MeetRoomMapper;
import com.xsbweb.mapper.TrsMediaMapper;
import com.xsbweb.mapper.TrsProjectMapper;
import com.xsbweb.service.MeetRoomService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.XsbBusinessUtil;
import com.xsbweb.vo.ImSubAccount;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.MeetRoomVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;

public class MeetRoomServiceImpl implements MeetRoomService {

	private static Logger log = Logger.getLogger(MeetRoomServiceImpl.class);

	@Autowired
	private MeetRoomMapper meetRoomMapper;
	
	@Autowired
	private APPCommonMapper appCommonMapper;
	
	@Autowired
	private TrsProjectMapper trsProjectMapper;
	
	@Autowired
	private TrsMediaMapper trsMediaMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<MeetRoomVO> getMeetRoomList(MeetRoomVO meetRoomVO)
			throws Exception {
		// TODO Auto-generated method stub
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getMeetRoomList(meetRoomVO);
	}
	@Override
	public int getMeetRoomListCount(MeetRoomVO meetRoomVO)
			throws Exception {
		// TODO Auto-generated method stub
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getMeetRoomListCount(meetRoomVO);
	}

	@Override
	public MeetRoomVO getMeetRoomByConfid(String confid) throws Exception {
		// TODO Auto-generated method stub
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getMeetRoomByConfid(confid);
	}

	@Override
	public int deleteMeetRoomByConfid(String confid) throws Exception {
		// TODO Auto-generated method stub
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.deleteMeetRoomByConfid(confid);
	}

	@Override
	public int addMeetRoom(MeetRoomVO meetRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("meetNo", meetRoomVO.getMeetNo());
		param.put("confid", meetRoomVO.getConfid());
		param.put("voiptoconfid", meetRoomVO.getVoiptoconfid());
		param.put("maxmember", meetRoomVO.getMaxmember());
		param.put("passwd", meetRoomVO.getPasswd());
		param.put("compere", meetRoomVO.getCompere());
		param.put("prcFlag", null);
		meetRoomMapper.addMeetRoom(param);
		return (Integer)param.get("prcFlag");
	}

	@Override
	public List<ImSubAccount> getSubAccountList(ImSubAccount subAccount)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getSubAccountList(subAccount);
	}

	@Override
	public ImSubAccount getImSubAccount() throws Exception{
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getImSubAccount();
	}

	@Override
	public int updateImSubAccount(ImSubAccount imSubAccount) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.updateImSubAccount(imSubAccount);
	}

	@Override
	public int insertImSubAccount(ImSubAccount imSubAccount) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.insertImSubAccount(imSubAccount);
	}

	@Override
	public int cancelImSubAccount(String voipAccount) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.cancelImSubAccount(voipAccount);
	}

	@Override
	public int updateMeetRoomInfo(MeetRoomVO meetRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.updateMeetRoomInfo(meetRoomVO);
	}

	@Override
	public ImSubAccount getCustomerInfoByVoip(String voipAccount)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getCustomerInfoByVoip(voipAccount);
	}

	@Override
	public List<ImSubAccount> getCustomersByVoips(ImSubAccount imSubAccount)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getCustomersByVoips(imSubAccount);
	}

	@Override
	public List<ImSubAccount> getImSubAccountList(int no) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getImSubAccountList(no);
	}

	@Override
	public int updateBatchImSubAccount(Map<String, String> map)
			throws Exception {
		if(map==null){
			return 0;
		}
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		for (String key : map.keySet()) {
			ImSubAccount isa = new ImSubAccount();
			isa.setCustomerPhoneNo(key);
			isa.setVoipAccount(map.get(key));
			log.info("#################给容联voip账号关联customerId:"+key+"------"+map.get(key));
			meetRoomMapper.updateImSubAccount(isa);
		}
		return 1;
	}

	@Override
	public List<ImSubAccount> getImSubAccountByPhones(ImSubAccount imSubAccount)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getImSubAccountByPhones(imSubAccount);
	}

	@Override
	public int cancelBatchImSubAccount(ImSubAccount imSubAccount)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.cancelBatchImSubAccount(imSubAccount);
	}

	/*@Override
	public int addForeMeetRoom(MeetRoomVO meetRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("meetMediaNo", meetRoomVO.getMeetMediaNo());
		param.put("shareMediaNo", meetRoomVO.getShareMediaNo());
		param.put("showDate", meetRoomVO.getShowDate());
		param.put("onlineDate", meetRoomVO.getOnlineDate());
		param.put("confName", meetRoomVO.getConfName());
		param.put("status", "1");
		param.put("isShow", meetRoomVO.getIsShow());
		param.put("prcFlag", null);
		param.put("meetNo", null);
		meetRoomMapper.addForeMeetRoom(param);
		return (Integer)param.get("prcFlag");
	}*/
	
	@Override
	public int addForeMeetRoom(MeetRoomVO meetRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		/*param.put("meetMediaNo", meetRoomVO.getMeetMediaNo());
		param.put("shareMediaNo", meetRoomVO.getShareMediaNo());
		param.put("showDate", meetRoomVO.getShowDate());
		param.put("onlineDate", meetRoomVO.getOnlineDate());
		param.put("confName", meetRoomVO.getConfName());
		param.put("status", "1");
		param.put("isShow", meetRoomVO.getIsShow());
		param.put("prcFlag", null);
		param.put("meetNo", null);*/
				
		meetRoomVO.setStatus("1");
		meetRoomVO.setMeetRole("1024");
		meetRoomMapper.addForeMeetRoom(meetRoomVO);
		int prcFlag=meetRoomVO.getPrcFlag();
		/*if(prcFlag>0){//prcFlag获取到的是一个空值;
			//存储过程执行成功会返回meetNo
			String meetNo = meetRoomVO.getMeetNo();
			List<ProjectItem> itemList = meetRoomVO.getVoiceItemList();
			if(itemList!=null && !itemList.isEmpty()){
				for (int i = 0; i < itemList.size(); i++) {
					itemList.get(i).setObjectNo(meetNo);
					itemList.get(i).setItemLocationOrder(String.valueOf(i));
				}
				//批量插入item
				commonMapper.addItemsBatch(itemList);
			}*/
			/*if(meetRoomVO.getTrsMedias()!=null&&!meetRoomVO.getTrsMedias().isEmpty()){
				prcFlag*=trsMediaMapper.batchInsert(meetRoomVO.getTrsMedias());
			for(TrsMedia trsMedia:meetRoomVO.getTrsMedias()){
				if(trsMedia!=null){
					trsMedia.setObjectId(meetNo);
					trsMediaMapper.insert(trsMedia);
				}				
			}	
		}*/
		return prcFlag;
	}

	@Override
	public List<MeetRoomVO> getForeMeetRoomList(MeetRoomVO meetRoomVO)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getForeMeetRoomList(meetRoomVO);
	}

	@Override
	public int deleteMeetRoomByMeetNo(String meetNo) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.deleteForeMeetRoomByMeetNo(meetNo);
	}

	@Override
	public List<MeetVideoRoomVO> getVideoMeetRoomList(MeetVideoRoomVO meetVideoRoomVO)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getVideoMeetRoomList(meetVideoRoomVO);
	}

	@Override	
	public int addForeVideoMeetRoom(MeetVideoRoomVO meetVideoRoomVO){//更改
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		meetVideoRoomVO.setStatus("1");
		meetVideoRoomVO.setMeetRole("128");
		//调用存储过程插入直播预告信息
		try {
			meetRoomMapper.addForeVideoMeetRoom(meetVideoRoomVO);
			//存储过程执行结果
			/*int prcFlag = meetVideoRoomVO.getPrcFlag();	*/
			//String meetMediaNo = meetVideoRoomVO.getMeetMediaNo();
			//String shareMediaNo = meetVideoRoomVO.getShareMediaNo();
			/*if(prcFlag>0){//prcFlag获取到的是一个空值;
				//存储过程执行成功会返回meetNo
				String meetNo = meetVideoRoomVO.getMeetNo();
				List<ProjectItem> itemList = meetVideoRoomVO.getVideoItemList();
				if(itemList!=null && !itemList.isEmpty()){
					for (int i = 0; i < itemList.size(); i++) {
						if(itemList.get(i).getItemKey()!=null&&itemList.get(i).getItemKey()!=""){
							itemList.get(i).setObjectNo(meetNo);
							itemList.get(i).setItemLocationOrder(String.valueOf(i));
						}
					}
					//批量插入item
					commonMapper.addItemsBatch(itemList);
				}
			}*/
			/*if(meetVideoRoomVO.getOldMediaList()!=null&&!meetVideoRoomVO.getOldMediaList().isEmpty()){
				trsMediaMapper.batchInsert(meetVideoRoomVO.getOldMediaList());
				for(TrsMedia trsMedia:trsMedias){
					trsMedia.setObjectId(objectNo);
					trsMediaMapper.insert(trsMedia);
				}
			}*/
		} catch (Exception e) {
			log.info("##################addForeVideoMeetRoom(MeetVideoRoomVO meetVideoRoomVO)执行失败");
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	@Override
	public int deleteVideoMeetRoomBymeetNo(String meetNo) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		log.info("meetNo service:"+meetNo);
		return meetRoomMapper.deleteVideoMeetRoomBymeetNo(meetNo);
	}

	@Override
	public List<RObject> getShowingMeetRoomList(RObject rObject,String meetType) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<RObject> mList = new ArrayList<RObject>();
		//获取语音直播列表
		Map<String, Object> param1 = XsbBusinessUtil.initRObjectParam(XsbBusinessConstant.MEETING_DOING, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> meetingList = appCommonMapper.getRObjectListByGroup(param1);
		//获取视频直播列表
		Map<String, Object> param2 = XsbBusinessUtil.initRObjectParam(XsbBusinessConstant.MEETING_VIDEO_DOING, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> videoMeetingList = appCommonMapper.getRObjectListByGroup(param2);
		if(XsbBusinessConstant.MEET_VOICE.equals(meetType)){
			for (RObject rObject3 : meetingList) {
				rObject3.setMeetType("voice");
				mList.add(rObject3);
			}
		}else if(XsbBusinessConstant.MEET_VIDEO.equals(meetType)){
			for (RObject rObject2 : videoMeetingList) {
				rObject2.setMeetType("video");
				mList.add(rObject2);
			}
		}else{
			for (RObject rObject3 : meetingList) {
				rObject3.setMeetType("voice");
				mList.add(rObject3);
			}
			for (RObject rObject2 : videoMeetingList) {
				rObject2.setMeetType("video");
				mList.add(rObject2);
			}
		}
		return mList;
	}

	@Override
	public List<RObject> getMeetRoomListByType(RObject rObject,String type,String meetType)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<RObject> mList = new ArrayList<RObject>();
		String meetVoiceType = "";
		String meetVideoType = "";
		//1：直播预告，2：往期回放
		if("1".equals(type)){
			meetVoiceType = XsbBusinessConstant.MEETING_TODO;
			meetVideoType = XsbBusinessConstant.MEETING_VIDEO_TODO;
		}else{
			meetVoiceType = XsbBusinessConstant.MEETING_DONE;
			meetVideoType = XsbBusinessConstant.MEETING_VIDEO_DONE;
		}
		//获取语音直播列表
		Map<String, Object> param1 = XsbBusinessUtil.initRObjectParam(meetVoiceType, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> meetingList = appCommonMapper.getRObjectListByGroup(param1);
		//获取视频直播列表
		Map<String, Object> param2 = XsbBusinessUtil.initRObjectParam(meetVideoType, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> videoMeetingList = appCommonMapper.getRObjectListByGroup(param2);
		if(XsbBusinessConstant.MEET_VOICE.equals(meetType)){
			for (RObject rObject3 : meetingList) {
				rObject3.setMeetType("voice");
				mList.add(rObject3);
			}
		}else if(XsbBusinessConstant.MEET_VIDEO.equals(meetType)){
			for (RObject rObject2 : videoMeetingList) {
				rObject2.setMeetType("video");
				mList.add(rObject2);
			}
		}else{
			for (RObject rObject3 : meetingList) {
				rObject3.setMeetType("voice");
				mList.add(rObject3);
			}
			for (RObject rObject2 : videoMeetingList) {
				rObject2.setMeetType("video");
				mList.add(rObject2);
			}
		}
		return mList;
	}

	@Override
	public MeetVideoRoomVO getVideoMeetRoomByMeetNo(String meetNo)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		//获取视频直播详情
		MeetVideoRoomVO meetVideoRoomVO = meetRoomMapper.getVideoMeetRoomByMeetNo(meetNo);
		//视频role处理，便于前台数据处理
		/*int role = Integer.parseInt(meetVideoRoomVO.getMeetRole());
		if(role>=128&&role<256){
			meetVideoRoomVO.setMeetRole(role-128+"");
		}else if(role>=256&&role<512){
			meetVideoRoomVO.setMeetRole(role-256+"");
		}else if(role>=512){
			meetVideoRoomVO.setMeetRole(role-512+"");
		}*/
		//获取视频直播对应的项目详情
		if(CommonUtils.isNotBlank(meetVideoRoomVO.getMeetProjectNo())){
			TrsProject trsProject = trsProjectMapper.getProjectByNo(meetVideoRoomVO.getMeetProjectNo());
			if(trsProject!=null){
				//获取媒体资源
				List<TrsMedia> meidaList = trsMediaMapper.getVideoListByObjectId(meetVideoRoomVO.getMeetProjectNo());
				if(meidaList!=null){
					trsProject.setTrsMediaList(meidaList);
				}
				meetVideoRoomVO.setTrsProject(trsProject);
			}else{
				meetVideoRoomVO.setTrsProject(null);
			}
		}else{
			meetVideoRoomVO.setTrsProject(null);
		}
		//获取往期回顾的视频资源
		List<TrsMedia> oldMediaList = trsMediaMapper.getVideoListByObjectId(meetNo);
		if(oldMediaList!=null && !oldMediaList.isEmpty()){
			for (TrsMedia trsMedia : oldMediaList) {
				//往期视频资源名称用资源描述
				trsMedia.setMediaName(trsMedia.getMediaDesc());
			}
			meetVideoRoomVO.setOldMediaList(oldMediaList);
		}
		/*List<ProjectItem> items = trsProjectMapper.getProjectItemListByNo(meetNo);
		if(items!=null){
			meetVideoRoomVO.setVideoItemList(items);
		}*/
		return meetVideoRoomVO;
	}
	/**
	 * 通过ID更改状态(实现)
	 * @param meetRoomVO
	 * @return
	 * @throws Exception
	 * 
	 */
	@Override
	public int updateMeetIsShowByMeetNo(MeetRoomVO meetRoomVO) throws Exception {
		//设置数据源，OLTP常量指定某个库
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		//Map<String,Object> param = new HashMap<String, Object>();
		//meetRoomVO.setIsShow("1");
		//return  meetRoomMapper.updateMeetIsShowByMeetNo(meetRoomVO);
		int flag;
		try {
			flag = meetRoomMapper.updateMeetIsShowByMeetNo(meetRoomVO);
		} catch (Exception e) {
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}   
/**
 * 	public int addForeVideoMeetRoom(MeetVideoRoomVO meetVideoRoomVO)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		/*param.put("meetMediaNo", meetVideoRoomVO.getMeetMediaNo());
		param.put("shareMediaNo", meetVideoRoomVO.getShareMediaNo());
		param.put("status", "1");
		param.put("prcFlag", null);
		param.put("meetNo", null);
		
		
		meetVideoRoomVO.setStatus("1");
		meetVideoRoomVO.setMeetRole("128");
		meetRoomMapper.addForeVideoMeetRoom(meetVideoRoomVO);
		return meetVideoRoomVO.getPrcFlag();
	}
 */
	
	@Override
	public List<RObject> getShowingMeetToWap(RObject rObject) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<RObject> mList = new ArrayList<RObject>();
		//获取视频直播列表
		Map<String, Object> param2 = XsbBusinessUtil.initRObjectParam(XsbBusinessConstant.MEETING_VIDEO_DOING, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> videoMeetingList = appCommonMapper.getRObjectListByGroup(param2);
		for (RObject rObject2 : videoMeetingList) {
			rObject2.setMeetType("video");
			mList.add(rObject2);
		}
		return mList;
	}

	@Override
	public List<RObject> getMeetRoomListByTypeToWap(RObject rObject,String type)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<RObject> mList = new ArrayList<RObject>();
		String meetVideoType = "";
		//1：直播预告，2：往期回放
		if("1".equals(type)){
			meetVideoType = XsbBusinessConstant.MEETING_VIDEO_TODO;
		}else{
			meetVideoType = XsbBusinessConstant.MEETING_VIDEO_DONE;
		}
		//获取视频直播列表
		Map<String, Object> param2 = XsbBusinessUtil.initRObjectParam(meetVideoType, rObject.getPageNo(), rObject.getPageSize());
		List<RObject> videoMeetingList = appCommonMapper.getRObjectListByGroup(param2);
		for (RObject rObject2 : videoMeetingList) {
			rObject2.setMeetType("video");
			mList.add(rObject2);
		}
		return mList;
	}
	
	/**
	 * 获取视频直播模块总数
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getVideoMeetRoomCounts(MeetVideoRoomVO meetVideoRoomVO)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return meetRoomMapper.getVideoMeetRoomCounts(meetVideoRoomVO);
	}
	
	/**
	 * 开始直播或者结束直播
	 * @param meetNo
	 * @param baseUrl
	 * @param showStatus
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateMeetToShowStatus(String meetNo, String baseUrl,
			String meetRole){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		MeetVideoRoomVO meetVideoRoomVO = new MeetVideoRoomVO();
		meetVideoRoomVO.setMeetNo(meetNo);
		meetVideoRoomVO.setBaseUrl(baseUrl);
		if(Integer.valueOf(meetRole)>=128 && Integer.valueOf(meetRole)<256){
			meetVideoRoomVO.setMeetRole((Integer.valueOf(meetRole)+128)+"");
		}else{
			meetVideoRoomVO.setMeetRole((Integer.valueOf(meetRole)+256)+"");
		}
		int flag;
		try {
			flag = meetRoomMapper.updateMeetVideoRoom(meetVideoRoomVO);
		} catch (Exception e) {
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}
	@Override
	public void updateVideoMeetRoomByMeetNo(MeetVideoRoomVO meetVideoRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		//更新时，无添加itemKey以及itemValue情况执行update动作
		/*if(projectItems!=null&&!projectItems.isEmpty()){
			trsProjectMapper.updateObjectRecodeByObjectNo(projectItems);
			for(ProjectItem projectItem:projectItems){
				Map<String,Object>param = new HashMap<String,Object>();
				param.put("objectNo", projectItem.getObjectNo());
				param.put("oldItemKey", projectItem.getOldItemKey());
				param.put("itemKey", projectItem.getItemKey());
				param.put("objectNo", projectItem.getObjectNo());
				param.put("itemValue",projectItem.getItemValue());
				trsProjectMapper.updateObjectRecodeByObjectNo(param);
			}
		}
		//更新时，添加itemKey以及itemValue情况执行insert动作
		if(addObjectItems!=null&&!addObjectItems.isEmpty()){
			for(int i=0;i<addObjectItems.size();i++){
				addObjectItems.get(i).setObjectNo(meetVideoRoomVO.getMeetNo());
			}
			trsProjectMapper.addObjectRecode(addObjectItems);
			for(ProjectItem projectItem:addObjectItems){
				trsProjectMapper.addObjectRecode(projectItem);
			}
		}*/
		//trsMedias更新
		/*if(meetVideoRoomVO.getOldMediaList()!=null&&!meetVideoRoomVO.getOldMediaList().isEmpty()){
			trsMediaMapper.batchInsert(meetVideoRoomVO.getOldMediaList());
			for(TrsMedia trsMedia:trsMedias){
				trsMedia.setObjectId(meetVideoRoomVO.getMeetProjectNo());
				trsMediaMapper.insert(trsMedia);
			}
		}*/
		//meetVideoRoomVO更新
		meetRoomMapper.updateMeetVideoRoom(meetVideoRoomVO);
	}
	@Override
	public MeetRoomVO getForeMeetRoomByMeetNo(String meetNo) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		MeetRoomVO meetRoomVO=meetRoomMapper.getForeMeetRoomByMeetNo(meetNo);
		/*List<TrsMedia> meidaList = trsMediaMapper.getTrsMediaListByObjectId(meetRoomVO.getMeetNo());
		if(meidaList!=null){
			meetRoomVO.setTrsMedias(meidaList);
		}*/
		/*List<ProjectItem> items =trsProjectMapper.getObjectRecordByObjectNo(meetRoomVO.getMeetNo());
		if(items!=null){
			meetRoomVO.setVoiceItemList(items);
		}*/
		/*TrsProject trsProject = trsProjectMapper.getProjectListByProjectNo(meetRoomVO.getProjectNo());
		if(trsProject!=null){
			meetRoomVO.setProjName(trsProject.getProjName());
		}*/
		
		return meetRoomVO;
	}
	@Override
	public void editMeetRoomByMeetNo(MeetRoomVO meetRoomVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int result = meetRoomMapper.updateMeetRoomByMeetNo(meetRoomVO);
		if(result>0){
			/*if(meetRoomVO.getTrsMedias()!=null&& !meetRoomVO.getTrsMedias().isEmpty()){
				for(int i=0;i<meetRoomVO.getTrsMedias().size();i++){
					if(meetRoomVO.getTrsMedias().get(i)!=null){
						meetRoomVO.getTrsMedias().get(i).setObjectId(meetRoomVO.getMeetNo());
						trsMediaMapper.insert(meetRoomVO.getTrsMedias().get(i));
					}
				}
			}*/
			/*if(meetRoomVO.getOldVoiceItemList()!=null&& !meetRoomVO.getOldVoiceItemList().isEmpty()){
				trsProjectMapper.updateObjectRecodeByObjectNo(meetRoomVO.getOldVoiceItemList());	
				for(ProjectItem projectItem:meetRoomVO.getOldVoiceItemList()){
					if(projectItem!=null){
						Map<String,Object>param = new HashMap<String,Object>();
						param.put("objectNo", projectItem.getObjectNo());
						param.put("oldItemKey", projectItem.getOldItemKey());
						param.put("itemKey", projectItem.getItemKey());
						param.put("objectNo", meetRoomVO.getMeetNo());
						param.put("itemValue",projectItem.getItemValue());
						trsProjectMapper.updateObjectRecodeByObjectNo(param);	
					}		
				}
			}*/
			/*if(meetRoomVO.getVoiceItemList()!=null&& !meetRoomVO.getVoiceItemList().isEmpty()){
				for(int i=0;i<meetRoomVO.getVoiceItemList().size();i++){
					meetRoomVO.getVoiceItemList().get(i).setObjectNo(meetRoomVO.getMeetNo());	
				}
				trsProjectMapper.addObjectRecode(meetRoomVO.getVoiceItemList());
				for(ProjectItem projectItem:meetRoomVO.getVoiceItemList()){					
					if(projectItem!=null){
						projectItem.setObjectNo(meetRoomVO.getMeetNo());
						trsProjectMapper.addObjectRecode(projectItem);
					}					
				}
			}*/
		}		
	}
	@Override
	public int batchDeleteVideoMeetRoomByMeetNos(String[] meetNoArrs)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag = 0;
		try{
			meetRoomMapper.batchDeleteVideoMeetRoomByMeetNos(meetNoArrs);
			flag =1;
		}catch(Exception e){
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	@Override
	public int batchDeleteMeetRoomByMeetNos(String[] meetNoArrs)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag = 0;
		try{
			meetRoomMapper.batchDeleteMeetRoomByMeetNos(meetNoArrs);
			flag =1;
		}catch(Exception e){
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
}
