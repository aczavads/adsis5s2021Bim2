package adsis5s2021Bim2.aula20210616.ping;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServerMultiThread {

	public static void main(String[] args) {
		PingServerMultiThread server = new PingServerMultiThread();
		server.listen();
	}
	
//	public void removerWorker(PingServerMultiThreadWorker worker) {
//		//remover da lista de workers ativos.
//	}

	private void listen() {
		try {
			ServerSocket socket = new ServerSocket(9091);
			System.out.println("Server listening.");
			while (true) {
				System.out.println("Waiting for client to connect...");
				Socket clientSocket = socket.accept();
				//adionar a esta mensagem:
				//1) o número de clientes já atendidos por este servidor e
				//2) o número de clientes atualmente sendo atendidos por este servidor
				System.out.println("Client connected!"); 

				Runnable r = () -> {
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
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
				new Thread(r).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
