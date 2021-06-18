package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Join {
	private String JS_NAME;
	private String JS_NUM;
	private String P_NUM;
	private String ADDRESS;
	private String ID;
	private String PASSWD;
	
	//����ڰ� �Է��� �̸��� �޾ƿ��� �޼���
	public String getJS_NAME(String name) {
		JS_NAME = name;
		return JS_NAME;
	}
	//����ڰ� �Է��� ��ȭ��ȣ�� �޾ƿ��� �޼���
	public String getJS_NUM(String call) {
		JS_NUM = call;
		return JS_NUM;
	}
	//����ڰ� �Է��� �ֹι�ȣ�� �޾ƿ��� �޼���
	public String getP_NUM(String resident) {
		P_NUM = resident;
		return P_NUM;
	}
	//����ڰ� �Է��� �ּ��� �޾ƿ��� �޼���
	public String getADRESS(String address) {
		ADDRESS = address;
		return ADDRESS;
	}
	//����ڰ� �Է��� ���̵� �޾ƿ��� �޼���
	public String getID(String id) {
		ID = id;
		return ID;
	}
	//����ڰ� �Է��� ��й�ȣ�� �޾ƿ��� �޼���
	public String getPASSWD(String passwd) {
		PASSWD = passwd;
		return PASSWD;
	}
	//������ �α����� ��û�ϴ� �޼���
	public void tryJoin(Socket socket) {
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			s_bw.write("Join," + ID + "," + PASSWD + "," + ADDRESS + "," + P_NUM + "," + JS_NAME + "," + JS_NUM + "\n");
			s_bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//�α��� �������θ� �������� �޾ƿ��� �޼���
	public boolean acceptJoin(Socket socket) {
		try {
			String str;
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str = s_br.readLine();
			if(str.equals("ȸ������ ����")) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
