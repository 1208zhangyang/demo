package WXDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class urlTest {
	public static void main(String[] args) {// 程序主函数
		run();// 调用新建的ReadByGet类
	}

	// static class ReadByGet extends Thread {// 定义一个静态的ReadByGet类继承于Thread类
	public static void run() {// run方法，里面包含需要执行的任务
		try {// try代码块，当发生异常时会转到catch代码块中
				// URL url= new URL(null, url, new sun.net.www.protocol.https.Handler());
			URL url = new URL(null,
					"https://api.weixin.qq.com/sns/jscode2session?appid=wxacb7f76b6356b11b&secret=6a1ce9276b1dc74fd197ae7ab0e3a908&js_code=071zO2D721OlDR0FJez72zRVC72zO2DO&grant_type=authorization_code",
					new sun.net.www.protocol.https.Handler());// 创建一个URL类的实例，并指定网址
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
	// }

}
