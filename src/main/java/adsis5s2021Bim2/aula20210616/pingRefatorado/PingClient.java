package adsis5s2021Bim2.aula20210616.pingRefatorado;

import java.net.Socket;
import java.util.Scanner;

public class PingClient {
	
	public static void main(String[] args) {
		PingClient client = new PingClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Socket socket = new Socket("localhost",9091);
			
			Scanner fromServer = new Scanner(socket.getInputStream());
			while (fromServer.hasNextLine()) {
				System.out.println("Response from server:" + fromServer.nextLine());
			}
			fromServer.close();
			
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
