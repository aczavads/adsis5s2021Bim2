package adsis5s2021Bim2.aula20210610.listFilesServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ListFilesClient {
	
	public static void main(String[] args) {
		ListFilesClient client = new ListFilesClient();
		client.doIt();
	}
	
	private static void doIt() {
		try {
			Scanner fromConsole = new Scanner(System.in);
			String command = "";
			while (!command.equalsIgnoreCase("quit")) {
				System.out.println("Digite o comando: ");
				command = fromConsole.nextLine();
				if (command.startsWith("ls")) {
					//"ls c:/windows"
					//"ls d:/arthur/workspace"
					sendCommand(command);
				} else if (command.startsWith("size")) {
					//"size c:/windows"
					//"size d:/arthur/workspace"
					sendCommand(command);
				} else if (command.startsWith("copy")) {
					//"copy c:/windows/notepad.exe"
					//"size d:/arthur/workspace/teste/txt"
					copyFile(command);					
				}
			}							
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void copyFile(String command) throws Exception {
		Socket socket = new Socket("localhost",9091);
		InputStream fromServer = socket.getInputStream();
		PrintWriter toServer = new PrintWriter(socket.getOutputStream());
		String fileName = command.split(" ")[1];
		fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		FileOutputStream toFile = new FileOutputStream("d:/temp/" + fileName);
		toServer.println(command);
		toServer.flush();
		
		int data = fromServer.read();
		while (data != -1) {
			toFile.write(data);
			data = fromServer.read();
		}
		toFile.close();
		socket.close();
		
		
	}

	private static void sendCommand(String command) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",9091);
		PrintWriter toServer = new PrintWriter(socket.getOutputStream());
		Scanner fromServer = new Scanner(socket.getInputStream());
		toServer.println(command);
		toServer.flush();
		while (fromServer.hasNextLine()) {
			System.out.println(fromServer.nextLine());
		}
		socket.close();
	}

}
