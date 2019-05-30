package WXDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class WXDemo
 */
@WebServlet("/WXDemo")
public class WXDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String passsword = request.getParameter("password");
		// String sex = request.getParameter("avaurl");
		System.out.println("username:" + username + "passsword:" + passsword);
		// System.out.println(sex);
		// 返回值给微信小程序
		Writer out = response.getWriter();
		Map<String, Object> map = new HashMap<>();
		map.put("login", true);
		out.write(new JSONObject(map).toString());
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("code");
		String passsword = request.getParameter("nick");
		String sex = request.getParameter("avaurl");
		System.out.println("用户临时标识:" + username + "昵称:" + passsword);
		System.out.println("用户头像:" + sex);
		// 返回值给微信小程序
		Writer out = response.getWriter();

		out.write("进入后台了POST");
		out.flush();
		run(username);
	}

	// 获取用户唯一id标识
	public static void run(String code) {// run方法，里面包含需要执行的任务
		try {// try代码块，当发生异常时会转到catch代码块中
				// URL url= new URL(null, url, new sun.net.www.protocol.https.Handler());
			String u = "https://api.weixin.qq.com/sns/jscode2session?appid=wxacb7f76b6356b11b&secret=6a1ce9276b1dc74fd197ae7ab0e3a908&js_code="
					+ code + "&grant_type=authorization_code";
			URL url = new URL(null, u, new sun.net.www.protocol.https.Handler());// 创建一个URL类的实例，并指定网址
			java.net.URLConnection connection = url.openConnection();// 创建实例连接指定URL上的内容
			InputStream is = (InputStream) connection.getInputStream();// 获取内容的字节流
			InputStreamReader isr = new InputStreamReader(is, "utf-8");// 将字节流包装为字符串流，并制定编码格式为utf-8
			BufferedReader br = new BufferedReader(isr);// 创建一个实例用来存放转换后的字符
			String line;// 定义一个字符串类型变量
			StringBuilder builder = new StringBuilder();// 创建实例
			while ((line = br.readLine()) != null) {// 读取信息，并且当信息不为空时
				builder.append(line + "\n");// append方法使builder包含line中的所有信息
			}
			br.close();// 关闭流
			isr.close();// 关闭流
			is.close();// 关闭流
			System.out.println(builder);// 输出读取的信息
		} catch (MalformedURLException e) {// 当try代码块有异常时转到catch代码块
			e.printStackTrace();// 打印异常所在位置及原因
		} catch (IOException e) {// 当try代码块有异常时转到catch代码块
			e.printStackTrace();// 打印异常所在位置及原因
		}
	}
}
