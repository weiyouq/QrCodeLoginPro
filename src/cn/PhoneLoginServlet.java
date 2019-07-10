package cn;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.vo.LoginUserVo;
import cn.vo.UserVo;

/**
 * 二维码手机端登录
 *
 */
public class PhoneLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PhoneLoginServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uuid = request.getParameter("uuid");
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		System.out.println(uuid);
		System.out.println(uname);
		System.out.println(upwd);
		//验证登录
		boolean bool = true;
		if(bool){
			//将登陆信息存入map
			UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
			if(userVo == null){
				userVo = new UserVo();
				userVo.setUname(uname);
				userVo.setUpwd(upwd);
				LoginUserVo.getLoginUserMap().put(uuid, userVo);
			}
		}
		PrintWriter out = response.getWriter();
		out.print(bool);
		out.flush();
		out.close();
	}
}
