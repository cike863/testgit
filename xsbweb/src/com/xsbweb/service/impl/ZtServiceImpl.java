package com.xsbweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.ZtMapper;
import com.xsbweb.service.ZtService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.zt.FemaleLeader;
import com.xsbweb.vo.zt.SubsConsume;
import com.xsbweb.vo.zt.TrsVote;
import com.xsbweb.vo.zt.WxSubscriber;

public class ZtServiceImpl implements ZtService{

	@Autowired
	private ZtMapper ztMapper;
	@Override
	public int addFemaleLeader(FemaleLeader femaleLeader) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.addFemaleLeader(femaleLeader);
	}

	@Override
	public int editFemaleLeader(FemaleLeader femaleLeader) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.editFemaleLeader(femaleLeader);
	}

	@Override
	public int deleteFemaleLeader(FemaleLeader femaleLeader) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.deleteFemaleLeader(femaleLeader);
	}

	@Override
	public List<FemaleLeader> getFemaleLeaderList(FemaleLeader femaleLeader)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getFemaleLeaderList(femaleLeader);
	}

	@Override
	public FemaleLeader getFemaleLeader(FemaleLeader femaleLeader)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getFemaleLeader(femaleLeader);
	}
	
	@Override
	public int addTrsVote(TrsVote trsVote) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		ztMapper.addTrsVote(trsVote);
		Integer prcFlag = trsVote.getPrcFlag();
		return prcFlag==null?0:prcFlag;
	}

	@Override
	public int isVote(TrsVote trsVote) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<TrsVote> trsVoteList = ztMapper.getTrsVoteListByDay(trsVote);
		int flag = 1;
		if(trsVoteList!=null && !trsVoteList.isEmpty()){
			if(trsVoteList.size()>15){
				flag = -1;
			}
			for (TrsVote trsVote2 : trsVoteList) {
				if(trsVote.getLeaderName().equals(trsVote2.getLeaderName())
						&& trsVote.getCompanyName().equals(trsVote2.getCompanyName())){
					flag = -1;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public int getFemaleLeaderVoteTotal(String leaderName,String companyName) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		TrsVote trsVote = new TrsVote();
		trsVote.setCompanyName(companyName);
		trsVote.setLeaderName(leaderName);
		return ztMapper.getVoteCountsByFemaleLeader(trsVote);
	}

	@Override
	public int addWxSubscriber(WxSubscriber wxSubscriber) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.addWxSubscriber(wxSubscriber);
	}

	@Override
	public int editWxSubscriber(WxSubscriber wxSubscriber) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.editWxSubscriber(wxSubscriber);
	}

	@Override
	public WxSubscriber getWxSubscriber(String wxSubsId) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getWxSubscriber(wxSubsId);
	}

	@Override
	public int addSubsConsume(SubsConsume subsConsume) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		ztMapper.addSubsConsume(subsConsume);
		return subsConsume.getCnsumNo();
	}

	@Override
	public int editSubsConsume(SubsConsume subsConsume) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.editSubsConsume(subsConsume);
	}

	@Override
	public SubsConsume getSubsConsumeByCnsumNo(String cnsumNo) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getSubsConsumeByCnsumNo(cnsumNo);
	}

	@Override
	public int getFemaleLeaderListCount(FemaleLeader femaleLeader)
			throws Exception {
		// TODO Auto-generated method stub
		return ztMapper.getFemaleLeaderListCount(femaleLeader);
	}

	@Override
	public int batchInsertWx(List<WxSubscriber> list) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.batchInsertWx(list);
	}

	@Override
	public List<SubsConsume> getSubsConsumeList(SubsConsume subsConsume)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getSubsConsumeList(subsConsume);
	}

	@Override
	public int getSubsConsumeCount(SubsConsume subsConsume) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getSubsConsumeCount(subsConsume);
	}

	@Override
	public int getAllVoteCounts() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getAllVoteCounts();
	}

	@Override
	public List<FemaleLeader> getSpecialFemaleLeaderList() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return ztMapper.getSpecialFemaleLeaderList();
	}

	@Override
	public int delFemaleLeader(FemaleLeader femaleLeader)
			throws Exception {
		return ztMapper.delFemaleLeader(femaleLeader);
	}
}
