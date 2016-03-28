package com.xsbweb.common.bean;

public class XsbBusinessConstant {

	//-----------各种appkey、appsecret---------
	//七牛资源第三方服务器
	//appkey
	public static final String QINIU_APP_KEY = "p0tMQL52alFIkq0kCm1lhxWODR-b3b06CDOF8tzn";
	//appsecret
	public static final String QINIU_APP_SECRET = "Eefm3b78pFa1Dc3ZH28iMGuyrnwIVGibpU6kgBDG";
	//资源文件路径前缀
	public static final String QINIU_URL = "http://7xromj.com1.z0.glb.clouddn.com/"; 
	 //要上传的空间
	public static final String QINIU_BUCKETNAME = "xsb-dyly"; 
	//---------------------------------------
	
	//短信发送url接口
	//public static final String SMSURL="http://121.199.50.122:8888/smsGBK.aspx?action=send&userid=956&account=SZXGCM&password=12345678&mobile=";
	public static final String SMSURL="http://121.199.50.122:8888/sms.aspx?action=send&userid=956&account=SZXGCM&password=a12345678&mobile=";
	
	public static final String NEWS_TYPE_ENUM="oltp.t_rl_trs_news_idx.news_status";
	
	public static final String GZ_INDUSTRY ="oltp.t_rl_snap_customer.gz_industry";
	
	
	public static final String CAPITAL_SCALE ="oltp.t_rl_snap_customer.capital_scale";
	
	public static final String CUSTOMER_TYPE ="oltp.t_rl_snap_customer.customer_type";
	
	public static final String DYLY_URL = "http://120.55.188.47:8080/dyly";
	
	
	public static final String APP_SHARE_URL= "http://m.dyly.com/p.html";
	//路演大厅分享链接
	public static String PROJ_SHARE_URL="http://m.diyiluyan.com:8080/roaddetail.do?";
	public static String PROJ_D_HTML=".html";
	
	public static String WAP_BASE_URL="http://m.diyiluyan.com:8080";
	
	public static final String OBJ_SCROLL_1 ="obj_scroll_1";
	public static final String OBJ_SCROLL_2 ="obj_scroll_2";
	public static final String OBJ_SCROLL_3 ="obj_scroll_3";
	public static final String OBJ_SCROLL_4 ="obj_scroll_4";
	public static final String OBJ_SCROLL_5 ="obj_scroll_5";
	public static final String PROJ_SHOWING ="proj_showing";
	public static final String PROJ_SHOWED ="proj_showed";
	public static final String MEETING_TODO ="meeting_audio_todo";
	public static final String MEETING_DOING ="meeting_audio_doing";
	public static final String MEETING_DONE ="meeting_audio_down";
	public static final String MEETING_VIDEO_TODO ="meeting_video_todo";
	public static final String MEETING_VIDEO_DOING ="meeting_video_doing";
	public static final String MEETING_VIDEO_DONE ="meeting_video_done";
	public static final String NEWS_SCROLL_1 ="news_scroll_1";
	public static final String NEWS_SCROLL_2 ="news_scroll_2";
	public static final String NEWS_SCROLL_3 ="news_scroll_3";
	public static final String NEWS_SCROLL_4 ="news_scroll_4";
	public static final String NEWS_SCROLL_5 ="news_scroll_5";
	public static final String NEWS_PRIMARY ="news_primary";
	public static final String NEWS_MARK ="news_mark";
	public static final String NEWS_COMPANY ="news_company";
	public static final String NEWS_INDUSTRY ="news_industry";
	public static final String NEWS_POLICY ="news_policy";
	
	public static final String OBJ_SCROLL_1_CODE ="1";
	public static final String OBJ_SCROLL_2_CODE ="2";
	public static final String OBJ_SCROLL_3_CODE ="4";
	public static final String OBJ_SCROLL_4_CODE ="8";
	public static final String OBJ_SCROLL_5_CODE ="16";
	public static final String PROJ_SHOWING_CODE ="32";
	public static final String PROJ_SHOWED_CODE ="64";
	public static final String MEETING_VIDEO_TODO_CODE ="128";
	public static final String MEETING_VIDEO_DOING_CODE ="256";
	public static final String MEETING_VIDEO_DONE_CODE ="512";
	public static final String MEETING_TODO_CODE ="1024";
	public static final String MEETING_DOING_CODE ="2048";
	public static final String MEETING_DONE_CODE ="4096";
	public static final String NEWS_SCROLL_1_CODE ="8192";
	public static final String NEWS_SCROLL_2_CODE ="16384";
	public static final String NEWS_SCROLL_3_CODE ="32768";
	public static final String NEWS_SCROLL_4_CODE ="65536";
	public static final String NEWS_SCROLL_5_CODE ="131072";
	public static final String NEWS_PRIMARY_CODE ="262144";
	public static final String NEWS_MARK_CODE ="524288";
	public static final String NEWS_COMPANY_CODE ="1048576";
	public static final String NEWS_INDUSTRY_CODE ="2097152";
	public static final String NEWS_POLICY_CODE ="4194304";
	//数据字典  oltp.t_rl_snap_customer_favor.favor_type
	public static final String FAVOR_TYPE_NEWS ="news";
	public static final String FAVOR_TYPE_PROJECT ="project";
	public static final String FAVOR_TYPE_STOCK ="stock";
	public static final String FAVOR_TYPE_REMIND ="remind";
	
	public static final String MEET_VOICE = "voice";
	public static final String MEET_VIDEO = "video";
	public static final String ALL = "all";
	
	//三板行情k线类型
	public static final String SBHQ_K_R = "realtime";//实时
	public static final String SBHQ_K_D = "day";//天
	public static final String SBHQ_K_W = "week";//周
	public static final String SBHQ_K_M = "month";//月
	public static final String SBHQ_K_Y = "year";//年
	
	//item分类
	public static final String ITEM_QZ = "qz";
	public static final String ITEM_GK = "gk";
	
	//敲钟固定ID标示
	public static final String OBJECT_ID_QZ = "prmtlist01";
	//PE风云标示
	public static final String OBJECT_ID_EXPR = "expr";
	//项目报名列表
	public static final String OBJECT_EXCEL="项目报名列表";
	//用户详情列表
	public static final String Customer_EXCEL="用户详情列表";
	
	public static final String[] CHECK_DEPT_NAMES={"项目部","市场部","品牌部","新媒体运营部","节目部","行政人事部","投研部","财务部","投研部","投资服务部","财经公关部"};
	
	public static final String[] PASS_DEPT_NAMES={"	信息技术部","产品部","总经办"};
	
	
}
