package adsis5s2021Bim2.aula20210617.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AppChatServer {
	private List<AppChatServerWorker> workers = new ArrayList<>();
	private ConcurrentLinkedQueue<Message> messagesToSend = new ConcurrentLinkedQueue<>();

	public static void main(String[] args) {
		AppChatServer server = new AppChatServer();
		server.listen();
	}

	public synchronized void addMessageToSend(Message message) {
		System.out.println("before>> " + messagesToSend.size());
		this.messagesToSend.add(message);
		System.out.println("server new message to send>>> " + message.text);
		System.out.println("after>> " + messagesToSend.size());

	}

	private void listen() {
		try {
			Runnable r = () -> {
				try {
					while (true) {
						synchronized (this) {
							while (!messagesToSend.isEmpty()) {
								boolean sent = false;
								for (AppChatServerWorker worker : workers) {
									if (messagesToSend.peek().sender != worker) {
										worker.sendMessage(messagesToSend.peek());
										sent = true;
									}
									if (sent) {
										messagesToSend.remove();
									}
								}
							}
							Thread.sleep(500);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			new Thread(r).start();

			ServerSocket socket = new ServerSocket(9091);
			System.out.println("Server listening...");
			while (true) {
				Socket clientSocket = socket.accept();
				System.out.println("Client connected!");
				AppChatServerWorker worker = new AppChatServerWorker(clientSocket, this);
				workers.add(worker);
				worker.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
