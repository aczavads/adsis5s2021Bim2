package adsis5s2021Bim2.aula20210610.listFilesServer;

import java.io.PrintWriter;
import java.net.Socket;
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
				//"ls c:/windows"
				//"ls d:/arthur/workspace"
				if (command.startsWith("ls")) {
					Socket socket = new Socket("localhost",9091);
					PrintWriter toServer = new PrintWriter(socket.getOutputStream());
					Scanner fromServer = new Scanner(socket.getInputStream());
					toServer.println(command);
					toServer.flush();
					while (fromServer.hasNextLine()) {
						System.out.println(fromServer.nextLine());
					}
				} 
				//"size c:/windows"
				//"size d:/arthur/workspace"
				
			}							
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
