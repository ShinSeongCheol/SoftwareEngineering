package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server_RThread extends Thread {

	Socket R_socket;
	BufferedReader s_br;
	BufferedWriter s_bw;

	String str;

	public Server_RThread(Socket socket) {
		R_socket = socket;
	}

	public void run() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Database_4 db = new Database_4();
		try {
			s_br = new BufferedReader(new InputStreamReader(R_socket.getInputStream()));
			s_bw = new BufferedWriter(new OutputStreamWriter(R_socket.getOutputStream()));
			while (true) {
				str = s_br.readLine();
				System.out.println(str + "\n");
				StringTokenizer st = new StringTokenizer(str, ",");
				switch (st.nextToken()) {
				case "Login": {
					if(db.login(st.nextToken(), st.nextToken())) {
						s_bw.write("login 성공\n");
						s_bw.flush();
					}
					else {
						s_bw.write("login 실패\n");
						s_bw.flush();
					}
					break;
				}
				case "Join": {
					if(db.registerUser(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
							st.nextToken())) {
						s_bw.write("회원가입 성공\n");
						s_bw.flush();
					}
					else {
						s_bw.write("회원가입 실패\n");
						s_bw.flush();
					}
					break;
				}
				case "addProduct" : {
					db.addProduct(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
					break;
				}
				case "getProductInfo" : {
					s_bw.write(db.getProductInfo() + "\n");
					s_bw.flush();
					break;
				}
				case "deleteProduct" :{
					if(db.deleteProduct(st.nextToken(), st.nextToken())) {
						s_bw.write("true\n");
						s_bw.flush();
					}
					else {
						s_bw.write("false\n");
						s_bw.flush();
					}
					break;
				}
				case "searchProduct" : {
					s_bw.write(db.getProductInfo(st.nextToken()) + "\n");
					s_bw.flush();
					break;
				}
				case "getProduct" : {
					s_bw.write(db.getProductInfo() + "\n");
					s_bw.flush();
					break;
				}
				case "getPost" : {
					s_bw.write(db.getPost(st.nextToken()) + "\n");
					s_bw.flush();
					break;
				}
				case "getSellerInfo" : {
				s_bw.write(db.getSellerInfo(st.nextToken(), st.nextToken(), st.nextToken()) + "\n");
				s_bw.flush();
				break;
				}
				
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}finally {
			try {
				s_bw.close();
				s_br.close();
				R_socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
