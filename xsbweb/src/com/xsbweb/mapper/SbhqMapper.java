package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.CustomerFavor;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.FundVO;
import com.xsbweb.vo.extend.KlineVO;
import com.xsbweb.vo.extend.SbhqMarket;
import com.xsbweb.vo.extend.SbhqSencodVO;
import com.xsbweb.vo.extend.SbhqSituationVO;
import com.xsbweb.vo.extend.SbhqVO;
import com.xsbweb.vo.extend.StockPriceVO;

public interface SbhqMapper {

	public List<SbhqVO> getStockPriceRByStockId(String stockId)throws Exception;
	
	/**
	 * 获取自选股列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getFavorListByCustomerId(Map<String,Object> param)throws Exception;
	
	/**
	 * 行业板块
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getIndustryList(Map<String,Object> param)throws Exception;
	
	/**
	 * 地区板块
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getAreaList(Map<String,Object> param)throws Exception;
	
	/**
	 * 券商排行
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getBrokerList(Map<String,Object> param)throws Exception;
	
	/**
	 * 定向增发
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getDirectionalAdditionList(Map<String,Object> param)throws Exception;
	
	/**
	 * 拟挂牌信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getPreList(Map<String,Object> param)throws Exception;
	
	/**
	 * 三板基金
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<SbhqVO> getFundList()throws Exception;
	
	/**
	 * 根据stockId获取信息
	 * @return
	 * @throws Exception
	 */
	public StockPriceVO getStockPriceDByStockId(String stockId)throws Exception;
	
	/**
	 * 获取股票涨幅榜、跌幅榜、成交量榜
	 * @param stockPriceVO
	 * @return
	 * @throws Exception
	 */
	public List<StockPriceVO> getStockPriceList(StockPriceVO stockPriceVO)throws Exception;
	
	public List<KlineVO> getStockPriceKlineD(String stockId)throws Exception;
	
	public List<KlineVO> getStockPriceKlineM(String stockId)throws Exception;
	
	public List<KlineVO> getStockPriceKlineR(String stockId)throws Exception;
	
	public List<KlineVO> getStockPriceKlineW(String stockId)throws Exception;
	
	public List<KlineVO> getStockPriceKlineY(String stockId)throws Exception;
	
	/**
	 * 三板指数数据
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<KlineVO> getSbzsKlineR(String stockId)throws Exception;
	
	/**
	 * 获取股票信息
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public StockPriceVO getStockInfo(String stockId)throws Exception;
	
	/**
	 * 获取股票信息的最高和最低信息
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public StockPriceVO getMaxMinPriceByStockId(String stockId)throws Exception;
	
	/**
	 * 获取899002股票信息的最高和最低信息
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public StockPriceVO getMaxMinPriceBy899002(String stockId)throws Exception;
	
	/**
	 * 三板基金
	 * @param basePo
	 * @return
	 * @throws Exception
	 */
	public List<FundVO> getFundList(BasePo basePo)throws Exception;
	
	/**
	 * 三板行情二级列表接口
	 * @param sbhqSencodVO
	 * @return
	 * @throws Exception
	 */
	public List<SbhqSencodVO> getSbhqDataSecond(SbhqSencodVO sbhqSencodVO)throws Exception;
	
	/**
	 * 获取券商二级列表
	 * @param sbhqSencodVO
	 * @return
	 * @throws Exception
	 */
	public List<SbhqSencodVO> getStockListByBroker(SbhqSencodVO sbhqSencodVO)throws Exception;
	
	/**
	 * 获取拟挂牌信息，从media里面获取，object_id固定为pre
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<TrsMedia> getPreListByMedias(TrsMedia trsMedia)throws Exception;
	
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
	 * 获取日K的最高最低数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public StockPriceVO getDKlineHighLowData(Map<String,Object> param)throws Exception;
	
	/**
	 * 获取三板行情公司概况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public SbhqSituationVO getSbhqComapnySituationByStockId(String stockId)throws Exception;
}
