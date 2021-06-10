package adsis5s2021Bim2.aula20210609.echo;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	
	public static void main(String[] args) {
		EchoServer server  = new EchoServer();
		server.listen();
	}

	private void listen() {
		
		try {
			ServerSocket serverSocket = new ServerSocket(9091);
			System.out.println("Server listening...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected!");
				PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
				Scanner fromClient = new Scanner(clientSocket.getInputStream());
				
				if (fromClient.hasNextLine()) {
					toClient.println("ECHO: " + fromClient.nextLine());
					toClient.flush();
				}
				toClient.close();
				fromClient.close();
				clientSocket.close();
				System.out.println("Client disconnected.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
