package adsis5s2021Bim2.aula20210602.introduçãoSockets;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class AppServer {
	
	public static void main(String[] args) {
		
		try {
			System.out.println("Server listening...");
			ServerSocket serverSocket = new ServerSocket(9091);
			while (true) {
				System.out.println("Waiting for a client to connect...");
				Socket client = serverSocket.accept();
				System.out.println("Connection accepted!");
				
				PrintWriter outputToClient = new PrintWriter(client.getOutputStream());
				Scanner inputFromClient = new Scanner(client.getInputStream());
				
				System.out.println("-----------------------------------");
				while (inputFromClient.hasNextLine()) {
					System.out.println(inputFromClient.nextLine());
				}
				System.out.println("+++++++++++++++++++++++++++++++++++");
				
				outputToClient.println("Hello client! It is " + new Date() +". Bye!");
				outputToClient.flush();
				outputToClient.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server is down!");
	}

}
