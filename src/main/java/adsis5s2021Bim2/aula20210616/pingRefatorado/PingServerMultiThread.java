package adsis5s2021Bim2.aula20210616.pingRefatorado;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PingServerMultiThread {
	private long clientesJáAtendidos = 0;
	private List<PingServerMultiThreadWorker> workers = new ArrayList<>();
	
	public static void main(String[] args) {
		PingServerMultiThread server = new PingServerMultiThread();
		server.listen();
	}
	
	public void removerWorker(PingServerMultiThreadWorker worker) {
		clientesJáAtendidos++;
		workers.remove(worker);
	}

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
				PingServerMultiThreadWorker worker = new PingServerMultiThreadWorker(clientSocket, this);
				workers.add(worker);
				worker.start();
				System.out.println("Client connected! Já atendidos: " + clientesJáAtendidos + ", ativos=" + workers.size()); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
