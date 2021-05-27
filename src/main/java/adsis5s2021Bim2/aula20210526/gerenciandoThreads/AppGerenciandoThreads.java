package adsis5s2021Bim2.aula20210526.gerenciandoThreads;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class AppGerenciandoThreads extends JDialog {
	private JButton btnAddThreadProgressUI = new JButton("+");
	
	public static void main(String[] args) {
		AppGerenciandoThreads app = new AppGerenciandoThreads();
		app.setVisible(true);
	}
	
	public AppGerenciandoThreads() {
		this.setPreferredSize(new Dimension(400, 500));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(getPreferredSize());
		this.setLayout(new GridLayout(10, 1));
		this.add(btnAddThreadProgressUI);
		btnAddThreadProgressUI.addActionListener((event) -> {
			this.add(new ThreadProgressUI(new NovoContadorThread(0,100)));
			this.revalidate();
			//this.add(new JLabel("rteste"));
			//this.revalidate();
		});		
	}
		
	
	private class ThreadProgressUI extends JPanel {
		private JProgressBar progressBar = new JProgressBar(0,100);
		private JButton btnManage = new JButton("Start");
		private ThreadProgressUIState state = ThreadProgressUIState.NOT_RUNNING;
		private NovoContadorThread novoContadorThread;
		
		public ThreadProgressUI(NovoContadorThread novoContadorThread) {
			novoContadorThread.setListener(novoValor -> {
				this.progressBar.setValue(novoValor);
				this.progressBar.revalidate();
				if (novoValor == 100) {
					this.btnManage.setText("Finished");
					this.btnManage.setEnabled(false);
				}
					
			});
			this.novoContadorThread = novoContadorThread;
			this.setLayout(new GridLayout(1, 2));
			this.add(progressBar);
			this.add(btnManage);
			btnManage.addActionListener(event -> {
				if (state == ThreadProgressUIState.NOT_RUNNING) {
					state = ThreadProgressUIState.RUNNING;
					btnManage.setText("Stop");
					novoContadorThread.start();
				} else if (state == ThreadProgressUIState.RUNNING){
					state = ThreadProgressUIState.PAUSED;
					btnManage.setText("Restart");
					novoContadorThread.parar();
				} else if (state ==ThreadProgressUIState.PAUSED) {
					state = ThreadProgressUIState.RUNNING;					
					btnManage.setText("Stop");
					novoContadorThread.reiniciar();
				}
			});
		}

		
	}
	

}
