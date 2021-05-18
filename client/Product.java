package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Product {
	private String P_NAME;
	private String P_Quantity;
	private String POST;
	private String date;
	public int P_OUT;
	
	//물건 이름을 저장하는 메서드
	public void getP_NAME(String name) {
		P_NAME = name;
	}
	//수량을 저장하는 메서드
	public void getP_Quantity(String Quantity) {
		P_Quantity = Quantity;
	}
	//게시물 내용을 저장하는 메서드
	public void getPost(String post) {
		POST = post;
	}
	//현재 날짜를 저장하는 메서드
	public void setDATE() {
		date = new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(new Date(System.currentTimeMillis()));
	}
	//저장된 정보를 서버에 보내 물품을 추가하는 메서드
	public void addProduct(Socket socket,String id) {
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			s_bw.write("addProduct," + P_NAME  + "," + P_Quantity + "," + POST + "," + date + "," + id + "\n");
			s_bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//물품등록 여부를 확인하는 메서드
	public boolean acceptProduct(Socket socket) {
		try {
			String str;
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str = s_br.readLine();
			if(str.equals("게시물 등록 성공")) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
