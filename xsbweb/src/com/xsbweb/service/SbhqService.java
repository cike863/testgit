package com.xsbweb.service;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.FundVO;
import com.xsbweb.vo.extend.KlineVO;
import com.xsbweb.vo.extend.SbhqMarket;
import com.xsbweb.vo.extend.SbhqSencodVO;
import com.xsbweb.vo.extend.SbhqSituationVO;
import com.xsbweb.vo.extend.SbhqVO;
import com.xsbweb.vo.extend.StockPriceVO;

public interface SbhqService {

	public List<SbhqVO> getStockPriceRByStockId(String stockId)throws Exception;

	public Map<String, Object> getSbhqDataFirst(String pageType,
			String transMethod, String customerId,
			Integer pageNo, Integer pageSize)throws Exception;

	public void getSbhqExpData(String stockId, Map<String, Object> map)throws Exception;

	public void getSbhqKlineByStockId(String stockId,String type,Map<String,Object> map,String common)throws Exception;

	public Map<String, Object> getSbhqStockInfo(String stockId,
			Map<String, Object> map,String customerId)throws Exception;

	public List<FundVO> getFundList(Integer pageNo, Integer pageSize)throws Exception;
	
	/**
	 * 三板行情二级列表接口
	 * @param sbhqSencodVO
	 * @return
	 * @throws Exception
	 */
	public List<SbhqSencodVO> getSbhqDataSecond(SbhqSencodVO sbhqSencodVO)throws Exception;
	
	/**
	 * 获取拟挂牌信息，从media里面获取，object_id固定为pre
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<TrsMedia> getPreListByMedias(TrsMedia trsMedia)throws Exception;
	
	/**
	 * 三板指数数据
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<KlineVO> getSbzsKlineR(String stockId)throws Exception;
	
	public List<StockPriceVO> getSbhqRankList(StockPriceVO stockPriceVO) throws Exception;
	
	/**
	 * 三板行情首页信息
	 * @return
	 * @throws Exception
	 */
	public List<SbhqMarket> getSbhqMarket()throws Exception;
	
	/**
	 * 主办同时做市的信息
	 * @param brokerName
	 * @return
	 * @throws Exception
	 */
	public SbhqVO getSbhqBrokerZSByName(String brokerName)throws Exception;
	
	/**
	 * 获取三板行情中我要看路演中的stock列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<StockPriceVO> getSbhqWantApplyRoadList(Map<String,Object> param)throws Exception;

	/**
	 * 获取三板行情公司概况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public SbhqSituationVO getSbhqComapnySituationByStockId(String stockId)throws Exception;
}
