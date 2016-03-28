package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.service.SqlService;
import com.xsbweb.vo.SqlVO;


/**
 * 
 * @author Administrator
 *
 */
@Controller
public class SqlController {
	private Logger log = Logger.getLogger(SqlController.class);
	@Autowired
	private SqlService sqlService;
	
	@RequestMapping(value="/admin/sql",method= RequestMethod.GET)
	public ModelAndView toSqlSelect() throws Exception {
		ModelAndView mav = new ModelAndView("manage/sqlSelect/sqlSelecttList");
		return mav;
	}

	@RequestMapping(value = "/admin/sql/selectBySql", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getResult(SqlVO sqlVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!sqlVo.getSql().toLowerCase().contains("count".toLowerCase())){
			String sql = "select count(1) as count "+sqlVo.getSql().substring(sqlVo.getSql().toLowerCase().indexOf("from"));
			SqlVO sqlVo1 = new  SqlVO();
			sqlVo1.setSql(sql);
			int count = sqlService.findResultCount(sqlVo1);
			sqlVo.setTotalRecord(count);
		}
		List<Map<String, Object>> mapList = sqlService.findResult(sqlVo);
		map.put("columnName", mapList.get(mapList.size()-1).get("columnName"));
		mapList.remove(mapList.get(mapList.size() - 1));
		map.put("resultList", mapList);
		map.put("sqlVo", sqlVo);
		return map;
	}
}
