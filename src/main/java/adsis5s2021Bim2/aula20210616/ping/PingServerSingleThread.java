package adsis5s2021Bim2.aula20210616.ping;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServerSingleThread {
	
	public static void main(String[] args) {
		PingServerSingleThread server = new PingServerSingleThread();
		server.listen();
	}

	private void listen() {
		try {
			ServerSocket socket = new ServerSocket(9091);
			System.out.println("Server listening.");
			while (true) {
				System.out.println("Waiting for client to connect...");
				Socket clientSocket = socket.accept();
				System.out.println("Client connected!");
				
				PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
				for (int i = 0; i < 50; i++) {
					toClient.println("[" + i + "] Ping!");
					toClient.flush();
					try {
						Thread.sleep(500);
					} catch (Exception e) {
					}
				}
				toClient.close();
				clientSocket.close();
				
				System.out.println("Client disconnected.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
