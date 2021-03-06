package com.xsbweb.vo;

import java.io.Serializable;

import com.xsbweb.common.bean.BasePo;

public class Comment extends BasePo implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String commentNo;

    private String commentTime;

    private String commenter;

    private String commentAddress;

    private String commentPlatform;

    private String commentStatus;

    private String commentTarget;

    private String commentContent;
    //评论人名称
    private String commenterName;
    //头像图片地址
    private String picPath;
    //点赞数
    private String commentPraise;
    
	public String getCommentPraise() {
		return commentPraise;
	}

	public void setCommentPraise(String commentPraise) {
		this.commentPraise = commentPraise;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(String commentNo) {
        this.commentNo = commentNo == null ? null : commentNo.trim();
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter == null ? null : commenter.trim();
    }

    public String getCommentAddress() {
        return commentAddress;
    }

    public void setCommentAddress(String commentAddress) {
        this.commentAddress = commentAddress == null ? null : commentAddress.trim();
    }

    public String getCommentPlatform() {
        return commentPlatform;
    }

    public void setCommentPlatform(String commentPlatform) {
        this.commentPlatform = commentPlatform == null ? null : commentPlatform.trim();
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus == null ? null : commentStatus.trim();
    }

    public String getCommentTarget() {
        return commentTarget;
    }

    public void setCommentTarget(String commentTarget) {
        this.commentTarget = commentTarget == null ? null : commentTarget.trim();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}