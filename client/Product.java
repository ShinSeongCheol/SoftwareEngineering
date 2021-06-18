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
	
	//���� �̸��� �����ϴ� �޼���
	public void getP_NAME(String name) {
		P_NAME = name;
	}
	//������ �����ϴ� �޼���
	public void getP_Quantity(String Quantity) {
		P_Quantity = Quantity;
	}
	//�Խù� ������ �����ϴ� �޼���
	public void getPost(String post) {
		POST = post;
	}
	//���� ��¥�� �����ϴ� �޼���
	public void setDATE() {
		date = new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(new Date(System.currentTimeMillis()));
	}
	//����� ������ ������ ���� ��ǰ�� �߰��ϴ� �޼���
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
	//��ǰ��� ���θ� Ȯ���ϴ� �޼���
	public boolean acceptProduct(Socket socket) {
		try {
			String str;
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str = s_br.readLine();
			if(str.equals("�Խù� ��� ����")) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
