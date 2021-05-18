package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//tcp 소켓 연결
			Socket socket = new Socket("220.69.240.175",44444);
			//SwingUi 객체 생성
			SwingUi tt = new SwingUi(socket);
			//changeUiToLogin()메서드 실행
			tt.changeUiToLogin();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
