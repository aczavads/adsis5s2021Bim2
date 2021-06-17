package adsis5s2021Bim2.aula20210616.pingRefatorado;

import java.io.PrintWriter;
import java.net.Socket;

public class PingServerMultiThreadWorker extends Thread {
	private Socket clientSocket;
	private PingServerMultiThread server;

	public PingServerMultiThreadWorker(Socket clientSocket, PingServerMultiThread server) {
		this.clientSocket = clientSocket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
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
			//remover aqui...
			server.removerWorker(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
