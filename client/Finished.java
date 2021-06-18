package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Finished extends Product{
	Socket socket;
	//Finished 클래스 생성자
	public Finished(Socket socket) {
		this.socket = socket;
	}
	//판매완료 성공 여부를 확인하는 메서드
	public boolean finished() {
		try {
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = s_br.readLine();
			if(str.equals("true")) {
				return true;
			}else if (str.equals("false")) {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//올린 게시물을 삭제하는 요청을 서버에 보내는 메서드
	public void deleteProduct(String P_NAME, String ID) {
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			s_bw.write("deleteProduct," + P_NAME + "," + ID + "\n");
			s_bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
