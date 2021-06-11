package adsis5s2021Bim2.aula20210610.listFilesServer;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ListFilesServer {
	
	public static void main(String[] args) {
		ListFilesServer server  = new ListFilesServer();
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
					String command = fromClient.nextLine();
					//ls d:/arthur/workspace
					//split(" ") ==> ["ls", "d:/arthur/workspace"]
					if (command.startsWith("ls")) {
						String dirName = command.split(" ")[1];
						File diretórioOrigem = new File(dirName);
						File[] arquivosOrigem = diretórioOrigem.listFiles();
						for (File arquivo : arquivosOrigem) {
							toClient.println(arquivo.getCanonicalPath());
							toClient.flush();
						}								
					}
					
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
