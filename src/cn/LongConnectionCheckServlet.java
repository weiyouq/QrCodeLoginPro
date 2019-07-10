package cn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.vo.LoginUserVo;
import cn.vo.UserVo;

/**
 * 用长连接，检查登录状态
 *
 */
public class LongConnectionCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uuid = request.getParameter("uuid");
    	String jsonStr = "";
		System.out.println("in");
		System.out.println("uuid:" + uuid);
		long inTime = new Date().getTime();
		Boolean bool = true;
		while (bool) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//检测登录
			UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
			System.out.println("userVo:" + userVo);
			if(userVo != null){
				bool = false;
				jsonStr = "{\"uname\":\""+userVo.getUname()+"\"}";
				LoginUserVo.getLoginUserMap().remove(uuid);
			}else{
				if(new Date().getTime() - inTime > 5000){
					bool = false;
				}
			}
		}
		System.out.println("login ok : " + jsonStr);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}
}
