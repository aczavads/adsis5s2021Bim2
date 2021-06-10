package adsis5s2021Bim2.aula20210609.echo;

import java.awt.GridLayout;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EchoClient extends JDialog {
	private JTextField fieldMessage = new JTextField();
	private JButton btnSend = new JButton("Enviar");

	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.setVisible(true);
	}

	public EchoClient() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLayout(new GridLayout(3,1));
		this.add(new JLabel("Mensagem"));
		this.add(fieldMessage);
		this.add(btnSend);
		btnSend.addActionListener(event -> sendMessageToServer(fieldMessage.getText()));
	}

	private void sendMessageToServer(String message) {
		try {
			Socket socket = new Socket("localhost", 9091);
			
			PrintWriter toServer = new PrintWriter(socket.getOutputStream());
			Scanner fromServer = new Scanner(socket.getInputStream());
			
			toServer.println(message);
			toServer.flush();
			
			if (fromServer.hasNextLine()) {
				JOptionPane.showMessageDialog(this, fromServer.nextLine());
			}
			
			toServer.close();
			fromServer.close();
 			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



