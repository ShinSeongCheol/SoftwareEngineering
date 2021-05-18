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
	
	//Search������
	public Search(Socket socket) {
		this.socket = socket;
	}
	
	//P_NAME�� ���ڷ� ������ ���ϴ� ���������� ��û�ϴ� �޼���
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
	
	//��û�� ������ �޴� �޼���
	public String getSEARCH(String P_NAME) {
		SEARCH = P_NAME;
		return SEARCH;
	}
	
	public String getProduct() {
		PRODUCT = "��ǰ�� �����ϴ�.";
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
