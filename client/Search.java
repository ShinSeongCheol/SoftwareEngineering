package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Search extends Product{
	private String SEARCH;
	private String PRODUCT;
	Socket socket;
	
	//Search생성자
	public Search(Socket socket) {
		this.socket = socket;
	}
	
	//P_NAME을 인자로 가지고 원하는 물건정보를 요청하는 메서드
	public void searchProduct() {
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			s_bw.write("searchProduct," + SEARCH + "\n");
			s_bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//요청한 정보를 받는 메서드
	public String getSEARCH(String P_NAME) {
		SEARCH = P_NAME;
		return SEARCH;
	}
	
	public String getProduct() {
		PRODUCT = "물품이 없습니다.";
		try {
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PRODUCT = s_br.readLine();
			return PRODUCT;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PRODUCT;
	}
}
