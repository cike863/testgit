package com.xsbweb.mapper;

import java.util.List;

import com.xsbweb.vo.zt.FemaleLeader;
import com.xsbweb.vo.zt.SubsConsume;
import com.xsbweb.vo.zt.TrsVote;
import com.xsbweb.vo.zt.WxSubscriber;

public interface ZtMapper {

	public int addFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public int editFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public int deleteFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public List<FemaleLeader> getFemaleLeaderList(FemaleLeader femaleLeader) throws Exception;
	
	public List<FemaleLeader> getSpecialFemaleLeaderList() throws Exception;
	
	public FemaleLeader getFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public void addTrsVote(TrsVote trsVote) throws Exception;
	
	/**
	 * 获取一个微信号账户当天的投票集合
	 * @param trsVote
	 * @return
	 * @throws Exception
	 */
	public List<TrsVote> getTrsVoteListByDay(TrsVote trsVote) throws Exception;
	
	public int getAllVoteCounts() throws Exception;
	
	
	
	/**
	 * 获取投票总数
	 * @param leaderName
	 * @return
	 * @throws Exception
	 */
	public int getVoteCountsByFemaleLeader(TrsVote trsVote) throws Exception;
	
	public int addWxSubscriber(WxSubscriber wxSubscriber) throws Exception;
	
	public WxSubscriber getWxSubscriber(String wxSubsId) throws Exception;
	
	public int editWxSubscriber(WxSubscriber wxSubscriber) throws Exception;
	
	public int addSubsConsume(SubsConsume subsConsume) throws Exception;
	
	public int editSubsConsume(SubsConsume subsConsume) throws Exception;
	
	public SubsConsume getSubsConsumeByCnsumNo(String cnsumNo) throws Exception;
	
	/**
	 * 初始化关注我们公众号的微信用户
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int batchInsertWx(List<WxSubscriber> list) throws Exception;

	public int getFemaleLeaderListCount(FemaleLeader femaleLeader) throws Exception;
	/**
	 * 获取订单列表
	 * @param subsConsume
	 * @return
	 * @throws Exception
	 */
	public List<SubsConsume> getSubsConsumeList(SubsConsume subsConsume) throws Exception;
	/**
	 * 订单数量
	 * @param subsConsume
	 * @return
	 * @throws Exception
	 */
	public int getSubsConsumeCount(SubsConsume subsConsume) throws Exception;

	public int delFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
}
