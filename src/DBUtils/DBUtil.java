package DBUtils;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBUtil {
	private static Connection Connect() {

		java.sql.Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdemotest?useUnicode=true&characterEncoding=utf-8&useSSL=true";
			String user = "root";
			String password = "123456";
			Connection con = (Connection) DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常");
			return null;
		}
	}

	public static boolean SingUp(String username, String password) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = Connect();
		if (con != null) {
			// String sql = "insert into users(username,password) values ('" + username +
			// "','" + password + "')";
			String sql = "insert into users(username,password) values (?,?)";
			try {
				// stmt = (Statement) con.createStatement();
				PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				if (Occupy(username)) {
					return false;
				} else {
					int flag = pstmt.executeUpdate();
					if (flag > 0) {
						System.out.println("用户注册成功");
						return true;
					} else {
						System.out.println("注册失败");
						return false;
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("注册异常");
				return false;
			}

		} else {
			System.out.println("注册连接异常");
			return false;
		}
	}

	public static boolean Occupy(String username) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = Connect();
		if (con != null) {
			// String sql = "select * from users where username = '" + username + "'";
			String sql = "select * from users where username = ?";
			try {
				// stmt = (Statement) con.createStatement();
				PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("用户重复");
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库查找用户是否重复出错");
				return false;
			}
		} else {
			System.out.println("数据库查找用户是否重复连接异常");
			return false;
		}
	}

}
