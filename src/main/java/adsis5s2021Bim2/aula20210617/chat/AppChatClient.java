package adsis5s2021Bim2.aula20210617.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppChatClient {
	
	public static void main(String[] args) {
		AppChatClient client = new AppChatClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Socket socket = new Socket("localhost", 9091);
			Runnable r = () -> {
				try {
					Scanner fromServer = new Scanner(socket.getInputStream());
					while (true) {
						while (fromServer.hasNextLine()) {
							System.out.println(fromServer.nextLine());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			new Thread(r).start();
			Scanner fromConsole = new Scanner(System.in);
			PrintWriter toServer = new PrintWriter(socket.getOutputStream());
			String message = "";
			while (true) {
				System.out.println("message>> ");
				message = fromConsole.nextLine();
				if (message.equals("<quit>")) {
					break;
				}
				toServer.println(message);		
				toServer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
