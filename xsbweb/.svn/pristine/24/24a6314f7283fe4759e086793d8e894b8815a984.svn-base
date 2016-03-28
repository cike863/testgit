package com.xsbweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.mapper.APPCommonMapper;
import com.xsbweb.mapper.CommonMapper;
import com.xsbweb.mapper.EnumVOMapper;
import com.xsbweb.mapper.TrsMediaMapper;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.XsbBusinessUtil;
import com.xsbweb.vo.ApplyRoad;
import com.xsbweb.vo.Comment;
import com.xsbweb.vo.CustomerFavor;
import com.xsbweb.vo.IndustryBaike;
import com.xsbweb.vo.News;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.CustomerVO;
import com.xsbweb.vo.extend.EnumVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;
import com.xsbweb.vo.extend.XSBTotalVO;

public class XSBBaseServiceImpl implements XSBBaseService {
	
	private Logger log = Logger.getLogger(XSBBaseServiceImpl.class);

	@Autowired
	private EnumVOMapper enumVOMapper;
	
	@Autowired
	private APPCommonMapper appCommonMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private TrsMediaMapper trsMediaMapper;
	
	@Override
	public List<EnumVO> getEnumVOList(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enumVOMapper.getEnumVOList(enumVO);
	}

	@Override
	public List<EnumVO> getEnumVOListByColumnFullName(String columnFullName)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enumVOMapper.getEnumVOListByColumnFullName(columnFullName);
	}

	@Override
	public List<RObject> getRObjectListByGroup(String group) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("group", group);
		param.put("prcFlag", null);
		List<RObject> rObjectList = appCommonMapper.getRObjectListByGroup(param);
		return rObjectList;
	}

	@Override
	public XSBTotalVO getXSBTotalOfRoadIndex() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.getXSBTotalOfRoadIndex();
	}

	@Override
	public List<IndustryBaike> getIndustryBaikeList(IndustryBaike industryBaike)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.getIndustryBaikeList(industryBaike);
	}

	@Override
	public IndustryBaike getIndustryBaikeById(String baikeId) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.getIndustryBaikeById(baikeId);
	}

	@Override
	public int addComment(String objectId,String customerId,String commentContent,String type) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Comment comment = new Comment();
		comment.setCommentAddress(objectId);
		comment.setCommenter(customerId);
		comment.setCommentContent(commentContent);
		comment.setCommentPlatform(type);
		return commonMapper.addComment(comment);
	}
	
	@Override
	public int addComment(Comment comment) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.addComment(comment);
	}

	@Override
	public List<Comment> getCommentByAddress(Comment comment) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.getCommentByAddress(comment);
	}

	@Override
	public int addCustomerFavor(CustomerFavor customerFavor) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.addCustomerFavor(customerFavor);
	}

	
	
	@Override
	public Map<String, Object> getCustomerFavorList(CustomerFavor customerFavor)
			throws Exception {
		if(customerFavor==null){
			return new HashMap<String, Object>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(XsbBusinessConstant.FAVOR_TYPE_NEWS.equals(customerFavor.getFavorType())){
			//新闻收藏集合
			MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
			List<News> newsList = appCommonMapper.getNewsListByFavor(customerFavor);
			map.put("newsList", newsList);
		}else if (XsbBusinessConstant.FAVOR_TYPE_PROJECT.equals(customerFavor.getFavorType())) {
			//项目收藏集合
			MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
			List<TrsProject> peojectList = appCommonMapper.getProjectListByFavor(customerFavor);
			map.put("peojectList", peojectList);
		}else if (XsbBusinessConstant.FAVOR_TYPE_STOCK.equals(customerFavor.getFavorType())) {
			//自选股
			
		}
		return map;
	}

	@Override
	public List<News> getNewsListByFavor(CustomerFavor customerFavor)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.getNewsListByFavor(customerFavor);
	}

	@Override
	public List<TrsProject> getProjectListByFavor(CustomerFavor customerFavor)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.getProjectListByFavor(customerFavor);
	}

	@Override
	public List<MeetVideoRoomVO> getRemindListByFavor(CustomerFavor customerFavor) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.getRemindListByFavor(customerFavor);
	}
	
	@Override
	public int deleteFavor(CustomerFavor customerFavor) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.deleteFavor(customerFavor);
	}

	@Override
	public XSBTotalVO getSqlServerDataTotal() throws Exception {
		MultipleDataSource.setDataSourceKey("sqlserver");
		return appCommonMapper.getSqlServerDataTotal();
	}

	@Override
	public boolean isFavored(CustomerFavor customerFavor) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag = appCommonMapper.isFavored(customerFavor);
		if(flag>0){
			return true;
		}
		return false;
	}

	@Override
	public List<RObject> getSearchListByWord(String word,int pageNo, int pageSize,String searchType) {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", word);
		param.put("pageNo", pageNo);
		param.put("pageSize", pageSize);
		if(CommonUtils.isBlank(searchType)){
			param.put("searchType", "");
		}else{
			param.put("searchType", searchType);
		}
		param.put("prcFlag", null);
		List<RObject> rlist = null;
		try {
			rlist = appCommonMapper.getSearchListByWord(param);
			//初始化跳转
			XsbBusinessUtil.initScrollForward(rlist);
			log.info("###########################prcFlag:"+param.get("prcFlag"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("#########XSBBaseServiceImpl.getSearchListByWord查询报错,查询词条:"+word+",pageNo:"+pageNo+",pageSize:"+pageSize);
		}
		return rlist;
	}

	@Override
	public List<Comment> getCommentByTalk(Comment comment) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.getCommentByTalk(comment);
	}

	@Override
	public List<EnumVO> getEnumVOListByNameAndCode(EnumVO enumVO)
			throws Exception {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("enumFullName", enumVO.getEnumFullName());
		param.put("enumGroupCode", enumVO.getEnumGroupCode());
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enumVOMapper.getEnumVOListByNameAndCode(param);
	}
	
	/**
	 * 申请路演
	 * @param applyRoad
	 * @return
	 * @throws Exception
	 */
	@Override
	public int applyRoad(ApplyRoad applyRoad){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag = 0;
		try {
			flag = appCommonMapper.applyRoad(applyRoad);
		} catch (Exception e) {
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}

	/**
	 * 根据公司名称判断是否已报名路演
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	@Override
	public int isApplyRoad(String companyName) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return appCommonMapper.isApplyRoad(companyName);
	}

	@Override
	public List<RObject> getCustomerTalkList(String customerId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return commonMapper.getCustomerTalkList(customerId);
	}

	/**
	 * 批量更新资源
	 * @param trsMedias
	 * @return
	 * @throws Exception
	 */
	@Override
	public int batchInsertMedias(List<TrsMedia> trsMedias) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return trsMediaMapper.batchInsert(trsMedias);
	}

}
