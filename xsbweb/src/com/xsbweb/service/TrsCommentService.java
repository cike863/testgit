package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.TrsComment;

public interface TrsCommentService {

	public int insertTrsComment(TrsComment trsComment)throws Exception;
	
	public int deleteTrsComment(TrsComment trsComment)throws Exception;
	
	public List<TrsComment> getTrsCommentByObjectId(String objectId)throws Exception;
	
	public List<TrsComment> getTrsCommentByAddress(TrsComment trsComment)throws Exception;
	
	public int getCommentListCount(TrsComment trsComment)throws Exception;

	public int bathDeleteProject(String[] trsCommentNoArrs)throws Exception;
}
