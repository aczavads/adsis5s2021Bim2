package adsis5s2021Bim2.aula20210519.threads.contador;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class AppContadorMultiThreadGUI extends JDialog {
	private long tempoTotal = 0;
	private JButton btnTempoTotal = new JButton("Mostrar Tempo Total");
	
	public static void main(String[] args) {
		AppContadorMultiThreadGUI app = new AppContadorMultiThreadGUI();
		app.setVisible(true);
	}
	
	public AppContadorMultiThreadGUI() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(getPreferredSize());
		this.setLayout(new GridLayout(10, 1));
		this.add(btnTempoTotal);
		this.add(new ContadorGUI());
		this.add(new ContadorGUI());
		this.add(new ContadorGUI());
		
		btnTempoTotal.addActionListener(event -> {
			JOptionPane.showMessageDialog(this, "Tempo total das threads: " + tempoTotal);
		});
	}
	
	private synchronized void aumentarTempoTotal(long tempo) {
		tempoTotal += tempo;
	}
	
	
	
	private  class ContadorGUI extends JPanel {
		private JProgressBar progressBar = new JProgressBar(0,100);
		private JButton btnStart = new JButton("Start");
		
		public ContadorGUI() {
			this.setLayout(new GridLayout(1, 2));
			this.add(progressBar);
			this.add(btnStart);
			btnStart.addActionListener(event -> {
				Runnable r = new Runnable() {
					public void run() {
						long início = System.currentTimeMillis();
						for (int i = 1; i <= 100; i++) {
							try {
								Thread.sleep(200);
							} catch (Exception e) {
							}
							progressBar.setValue(i);
						}
						long término = System.currentTimeMillis();
						aumentarTempoTotal(término-início);
					}
				};
				new Thread(r).start();
				this.invalidate();
			});
		}
		
	}
	

}
