package adsis5s2021Bim2.aula20210527.gerenciandoThreadsParaCópiaDeArquivos;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class AppGerenciandoThreads extends JDialog {
	private JTextField fieldDiretórioDeOrigem = new JTextField();
	private JTextField fieldDiretórioDeDestino = new JTextField();
	private JTextField fieldArquivosPorThread = new JTextField();
	private JLabel info = new JLabel("0 arquivos na origem, necessárias 0 threads");
	private List<ThreadProgressUI> progressUIs = new ArrayList<>();
	
	public static void main(String[] args) {
		AppGerenciandoThreads app = new AppGerenciandoThreads();
		app.setVisible(true);
	}
	
	public AppGerenciandoThreads() {
		this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(getPreferredSize());
		this.setLayout(new GridLayout(30, 1));
		this.add(new JLabel("Diretório de origem"));
		this.add(fieldDiretórioDeOrigem);
		this.add(new JLabel("Diretório de destino"));
		this.add(fieldDiretórioDeDestino);
		this.add(new JLabel("Quantidade de arquivos por thread"));
		this.add(fieldArquivosPorThread);
		fieldArquivosPorThread.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
//				JOptionPane.showMessageDialog(rootPane, 
//								"Calculando aquivos a partir de " 
//								+ fieldDiretórioDeOrigem.getText() 
//								+ " com " 
//								+ fieldArquivosPorThread.getText() +
//								" arquivos por thread.");				
				for (ThreadProgressUI threadProgressUI : progressUIs) {
					remove(threadProgressUI);
					revalidate();
				}
				//seu código vem aqui! :D
				File dirOrigem = new File(fieldDiretórioDeOrigem.getText());
				File[] arquivosDoDirOrigem = dirOrigem.listFiles();
				int quantidadeArquivosDirOrigem = arquivosDoDirOrigem.length;
				int quantidadeArquivosPorThread = Integer.parseInt(fieldArquivosPorThread.getText());
				int quantidadeCalculadaDeThreads = quantidadeArquivosDirOrigem / quantidadeArquivosPorThread; //calcule aqui!
				if (quantidadeArquivosDirOrigem % quantidadeArquivosPorThread > 0) {
					quantidadeCalculadaDeThreads++;
				}				
				
				info.setText("Serão necessárias " 
						+ quantidadeCalculadaDeThreads + " threads, "
						+ " copiando " + quantidadeArquivosPorThread + " arquivos "
						+ " para copiar os " 
						+ quantidadeArquivosDirOrigem + " arquivos."); 
				
				for (int i = 0; i < quantidadeCalculadaDeThreads; i++) {
					File[] arquivosDaThread = new File[] {};
					ThreadProgressUI progressUI = new ThreadProgressUI(
							new FileCopyThread(
									arquivosDaThread, 
									fieldDiretórioDeOrigem.getText(), 
									fieldDiretórioDeDestino.getText()));
					progressUIs.add(progressUI);
					add(progressUI);										
				}
				revalidate();
			}
		});
		this.add(info);
		this.add(new JSeparator());
	}
		
	
	private class ThreadProgressUI extends JPanel {
		private JProgressBar progressBar = new JProgressBar(0,100);
		private JButton btnManage = new JButton("Start");
		private ThreadProgressUIState state = ThreadProgressUIState.NOT_RUNNING;
		private FileCopyThread novoContadorThread;
		
		public ThreadProgressUI(FileCopyThread novoContadorThread) {
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
