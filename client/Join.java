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
	
	//사용자가 입력한 이름을 받아오는 메서드
	public String getJS_NAME(String name) {
		JS_NAME = name;
		return JS_NAME;
	}
	//사용자가 입력한 전화번호를 받아오는 메서드
	public String getJS_NUM(String call) {
		JS_NUM = call;
		return JS_NUM;
	}
	//사용자가 입력한 주민번호를 받아오는 메서드
	public String getP_NUM(String resident) {
		P_NUM = resident;
		return P_NUM;
	}
	//사용자가 입력한 주소을 받아오는 메서드
	public String getADRESS(String address) {
		ADDRESS = address;
		return ADDRESS;
	}
	//사용자가 입력한 아이디를 받아오는 메서드
	public String getID(String id) {
		ID = id;
		return ID;
	}
	//사용자가 입력한 비밀번호를 받아오는 메서드
	public String getPASSWD(String passwd) {
		PASSWD = passwd;
		return PASSWD;
	}
	//서버에 로그인을 요청하는 메서드
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
	//로그인 성공여부를 서버에서 받아오는 메서드
	public boolean acceptJoin(Socket socket) {
		try {
			String str;
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str = s_br.readLine();
			if(str.equals("회원가입 성공")) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
