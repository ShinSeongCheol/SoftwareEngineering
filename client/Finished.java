package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Finished extends Product{
	Socket socket;
	//Finished Ŭ���� ������
	public Finished(Socket socket) {
		this.socket = socket;
	}
	//�ǸſϷ� ���� ���θ� Ȯ���ϴ� �޼���
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
	//�ø� �Խù��� �����ϴ� ��û�� ������ ������ �޼���
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
