package adsis5s2021Bim2.aula20210610.listFilesServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.tools.ToolProvider;

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
						File[] arquivosOrigem = listDirFiles(command);
						for (File arquivo : arquivosOrigem) {
							toClient.println(arquivo.getCanonicalPath());
							toClient.flush();
						}								
					} else if (command.startsWith("size")) {
						File[] arquivosOrigem = listDirFiles(command);
						long totalSize = 0;
						for (File arquivo : arquivosOrigem) {
							totalSize += arquivo.length();
						}								
						toClient.println(totalSize);
						toClient.flush();
					} else if (command.startsWith("copy")) {
						FileInputStream inputFromFile = new FileInputStream(command.split(" ")[1]);
						OutputStream toClientFileData = clientSocket.getOutputStream();
						
						int data = -1;
						while (data != -1) {
							toClientFileData.write(data);
							data = inputFromFile.read();
						}
						toClientFileData.write(-1);
						toClientFileData.flush();
						toClientFileData.close();
						inputFromFile.close();
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

	private File[] listDirFiles(String command) {
		String dirName = command.split(" ")[1];
		File diretórioOrigem = new File(dirName);
		File[] arquivosOrigem = diretórioOrigem.listFiles();
		return arquivosOrigem;
	}

}
