package com.xsbweb.controller.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

@Controller
public class UeditorController {
	@RequestMapping("/ueditor1_4_3/servlet/UploadServlet")
	public void config(HttpServletRequest request,
			HttpServletResponse response, String action) {
		response.setContentType("application/json");
		// config.json配置文件和图片上传位置，都默认为“网站根目录”
		String rootPath = request.getSession().getServletContext()
				.getRealPath("/");

		// 自己设置的目录
		/*Object uploadRootPath = BasePropertyConfigurer
				.getContextProperty("setting.upload_root_path");
		if (uploadRootPath != null) {
			rootPath = uploadRootPath.toString();
		}*/

		try {
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
