package WXDemo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class jsonDemo {

	public static void main(String[] args) {
		// System.out.println(DBJson().toString());
		demo();
	}

	public static JSONObject demo() {
		JSONArray j = new JSONArray();
		String name = "lili";
		String sex = "man";
		for (int i = 0; i < 3; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("name", name);
			map.put("sex", sex);
			j.put(new JSONObject(map));
			name = name + i + "next";
		}
		Map<Object, Object> map2 = new HashMap<>();
		map2.put("na", j);
		map2.put("ss", "dsx ");
		JSONObject json = new JSONObject(map2);
		System.out.println(json.toString());
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("JSON1", json);
		map3.put("JSON2", json);
		return new JSONObject(map3);

	}

	public static JSONArray DBJson() {

		JSONArray j = new JSONArray();
		String driver = "com.mysql.jdbc.Driver";

		String url = "jdbc:mysql://127.0.0.1:3306/webdemotest?useUnicode=true&characterEncoding=utf-8&useSSL=true";

		String user = "root";

		String password = "123456";
		try {

			Class.forName(driver);

			Connection conn = (Connection) DriverManager.getConnection(url, user, password);

			Statement statement = (Statement) conn.createStatement();

			String sql = "select * from users";
			ResultSet rs = statement.executeQuery(sql);
			String name = null;
			String pwd = null;
			String sql2 = "select * from users where name = ?";
			while (rs.next()) {
				name = rs.getString("userName");
				pwd = rs.getString("passWord");

				Map<String, Object> map = new HashMap<>();
				map.put("name", name);
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, name);
				ResultSet prs = pstmt.executeQuery();
				j.put(new JSONObject(map));

			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}