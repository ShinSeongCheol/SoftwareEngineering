package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Login extends Join{
	private String ID;
	private String PASSWD;
	public int LOGIN_O;
	Socket socket;
	
	BufferedWriter s_bw;
	//ID를 텍스트필드에서 받아와서 저장하는 메서드
	public String getID(String ID) {
		this.ID = ID;
		return this.ID;
	}
	//비밀번호를 텍스트필드에서 받아와서 저장하는 메서드
	public String getPASSWD(String PASSWD) {
		this.PASSWD = PASSWD;
		return this.PASSWD;
	}
	//로그인 시도하는 메서드
	public void tryLogin(Socket socket) {
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			s_bw.write("Login," + this.ID + "," + this.PASSWD + "\n");
			s_bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//로그인 성공 여부를 확인하는 메서드
	public boolean acceptLogin(Socket socket) {
		try {
			String str;
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str = s_br.readLine();
			if(str.equals("login 성공")) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
