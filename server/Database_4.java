package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_4 {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//서버 연결 메서드
	private void connect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zero", "shinseongcheol", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//회원가입한 내용을 DB에 저장하는 메서드 
	public boolean registerUser(String ID, String PASSWORD, String ADDRESS, String NIN, String NAME, String NUMBER) {
		connect();

		try {
			pstmt = con.prepareStatement(
					"insert into userinfo(ID, PASSWORD, ADDRESS, P_NUM, JS_NAME, JS_NUM) values(?,?,?,?,?,?)");
			pstmt.setString(1, ID);
			pstmt.setString(2, PASSWORD);
			pstmt.setString(3, ADDRESS);
			pstmt.setString(4, NIN);
			pstmt.setString(5, NAME);
			pstmt.setString(6, NUMBER);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
		}
		
		finish();
		return false;
	}

	//ID와 PASSWORD를 가지고 로그인을 하는 메서드
	public boolean login(String ID, String PASSWORD) {
		connect();
		try {
			pstmt = con.prepareStatement("select id,password from userinfo where ID=? and password=?");
			pstmt.setString(1, ID);
			pstmt.setString(2, PASSWORD);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if ((rs.getString(1).equals(ID)) && (rs.getString(2).equals(PASSWORD))) {
					return true;
				}
			}
			
		} catch (SQLException e) {
		}
		finish();
		return false;
	}
	
	//물건을 DB에 저장하는 메서드
	public boolean addProduct(String P_NAME, String P_Quantity, String POST, String DATE, String ID) {
		connect();
		try {
			pstmt = con.prepareStatement("insert into products(P_NAME, P_Quantity, POST, Date, ID) values(?,?,?,?,?)");
			pstmt.setString(1, P_NAME);
			pstmt.setString(2, P_Quantity);
			pstmt.setString(3, POST);
			pstmt.setString(4, DATE);
			pstmt.setString(5, ID);
			pstmt.execute();
			return true;
			
		}catch(SQLException e) {
		}
		
		finish();
		return false;
	}
	
	//물건 정보를 DB에서 받아오는 메서드
	public String getProductInfo() {
		connect();
		try {
			pstmt = con.prepareStatement("select P_NAME,P_Quantity,Date from products");
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()) {
				str += rs.getString("P_NAME") + "," + rs.getString("P_Quantity") + "," + rs.getString("Date") + "/";
			}
			return str;
		}catch(SQLException e) {
		}
		finish();
		return "false";
	}
	//물건 이름을 가지고 물건 정보를 DB에서 받아오는 메서드
	public String getProductInfo(String P_NAME) {
		connect();
		try {
			pstmt = con.prepareStatement("select P_NAME,P_Quantity,Date from products where P_NAME=?");
			pstmt.setString(1, P_NAME);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()) {
				str += rs.getString("P_NAME") + "," + rs.getString("P_Quantity") + "," + rs.getString("Date") + "/";
			}
			return str;
		}catch(SQLException e) {
		}
		finish();
		return "false";
	}
	
	//판매자 정보를 DB에서 가져오는 메서드
	public String getSellerInfo(String P_NAME,String P_Quantity, String Date) {
		connect();
		String str = null;
		try {
			pstmt = con.prepareStatement("select ID from products where P_NAME=? and P_Quantity=? and Date =?");
			pstmt.setString(1, P_NAME);
			pstmt.setString(2, P_Quantity);
			pstmt.setString(3, Date);
			rs = pstmt.executeQuery();
			rs.next();
			String ID = rs.getString("ID");
			pstmt = con.prepareStatement("select JS_NAME, JS_NUM, ADDRESS from userinfo where id=?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				str ="이름 : " + rs.getString("JS_NAME") + " 연락처 : " + rs.getString("JS_NUM") + " 주소: " + rs.getString("ADDRESS");
				return str;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		finish();
		return str;
	}
	
	//게시물 내용을 DB에서 가져오는 메서드
	public String getPost(String P_NAME) {
		connect();
		String str = null;
		try {
			pstmt = con.prepareStatement("select POST from products where P_NAME=?");
			pstmt.setString(1, P_NAME);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				str = rs.getString("POST");
				return str;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		finish();
		return str;
	}
	//DB에 있는 물품을 삭제하는 메서드
	public boolean deleteProduct(String str,String id) {
		connect();
		try {
			pstmt = con.prepareStatement("select ID from products where P_NAME = ?");
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("ID").equals(id)) {
					pstmt = con.prepareStatement("delete from products where P_NAME = ?");
					pstmt.setString(1, str);
					pstmt.execute();
					return true;
				}
			}
			
		}catch(SQLException e) {
			return false;
		}
		finish();
		return false;
	}

	//DB연결 해제하는 메서드
	private void finish() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
