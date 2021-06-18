package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server_RThread SR;

		try {
			ServerSocket serversocket = new ServerSocket(44444);
			Socket socket = new Socket();
			while (true) {
				socket = serversocket.accept();
				SR = new Server_RThread(socket);
				SR.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}
