package adsis5s2021Bim2.aula20210617.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AppChatServerWorker extends Thread {
	private Socket clientSocket;
	private ConcurrentLinkedQueue<Message> messagesToSend = new ConcurrentLinkedQueue<>();
	private Thread outThread = null;
	private AppChatServer appChatServer;

	public AppChatServerWorker(Socket clientSocket, AppChatServer appChatServer) {
		this.clientSocket = clientSocket;
		this.appChatServer = appChatServer;
		this.outThread = new Thread() {
			@Override
			public void run() {
				try {
					PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
					while (true) {
						synchronized (this) {
							while (messagesToSend.size() == 0) {
								this.wait();
							}
							while (!messagesToSend.isEmpty()) {								
								toClient.println(messagesToSend.remove());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		this.outThread.start();
	}

	@Override
	public void run() {
		try {
			Scanner fromClient = new Scanner(clientSocket.getInputStream());
			while (true) {
				while (fromClient.hasNextLine()) {
					String m = fromClient.nextLine();
					appChatServer.addMessageToSend(new Message(this, m));
					System.out.println("worker message received>> " + m);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void sendMessage(Message message) {
		messagesToSend.add(message);
		synchronized (this.outThread) {
			this.outThread.notify();			
		}
	}

}
