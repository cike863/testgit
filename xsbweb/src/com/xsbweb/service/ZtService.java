package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.zt.FemaleLeader;
import com.xsbweb.vo.zt.SubsConsume;
import com.xsbweb.vo.zt.TrsVote;
import com.xsbweb.vo.zt.WxSubscriber;

public interface ZtService {

	public int addFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public int editFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public int deleteFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public List<FemaleLeader> getFemaleLeaderList(FemaleLeader femaleLeader) throws Exception;
	
	public FemaleLeader getFemaleLeader(FemaleLeader femaleLeader) throws Exception;
	
	public int addTrsVote(TrsVote trsVote) throws Exception;
	
	/**
	 * 是否投票
	 * @param trsVote
	 * @return
	 * @throws Exception
	 */
	public int isVote(TrsVote trsVote) throws Exception;
	
	/**
	 * 获取投票总数
	 * @param leaderName
	 * @return
	 * @throws Exception
	 */
	public int getFemaleLeaderVoteTotal(String leaderName,String companyName) throws Exception;
	
	public int addWxSubscriber(WxSubscriber wxSubscriber) throws Exception;
	
	public int editWxSubscriber(WxSubscriber wxSubscriber) throws Exception;
	
	public WxSubscriber getWxSubscriber(String wxSubsId) throws Exception;
	
	public int addSubsConsume(SubsConsume subsConsume) throws Exception;
	
	public int editSubsConsume(SubsConsume subsConsume) throws Exception;
	
	public SubsConsume getSubsConsumeByCnsumNo(String cnsumNo) throws Exception;

	public int getFemaleLeaderListCount(FemaleLeader femaleLeader) throws Exception;
	
	/**
	 * 初始化关注我们公众号的微信用户
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int batchInsertWx(List<WxSubscriber> list) throws Exception;
	/**
	 * 订单li's't
	 * @param subsConsumethrows
	 * @return
	 */
	public List<SubsConsume> getSubsConsumeList(SubsConsume subsConsume) throws Exception;
	/**
	 * 订单数量
	 * @param subsConsume
	 * @return
	 * @throws Exception
	 */
	public int getSubsConsumeCount(SubsConsume subsConsume) throws Exception;
	
	public int getAllVoteCounts() throws Exception;
	
	public List<FemaleLeader> getSpecialFemaleLeaderList() throws Exception;
	/**
	 * 删除FemaleLeader
	 * @param femaleLeader
	 * @return
	 * @throws Exception
	 */
	public int delFemaleLeader(FemaleLeader femaleLeader) throws Exception;
}

