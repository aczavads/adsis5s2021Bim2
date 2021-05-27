package adsis5s2021Bim2.aula20210527.gerenciandoThreadsParaCópiaDeArquivos;

import java.io.File;

public class FileCopyThread extends Thread {
	private NovoContadorThreadListener listener = null;
	private boolean parada;
	private File[] arquivosDaThread;
	private String dirOrigem;
	private String dirDestino;


	public FileCopyThread(File[] arquivosDaThread, String dirOrigem, String dirDestino) {
		this.arquivosDaThread = arquivosDaThread;
		this.dirOrigem = dirOrigem;
		this.dirDestino = dirDestino;
	}

	public void setListener(NovoContadorThreadListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void run() {
		for (File arquivo : arquivosDaThread) {
			if (listener != null) {
				//calcular o percentual de 0 a 100 em função do total de arquivos que a thread copiará.
				listener.valorAlterado(0); //AJUSTAR ESTE ZERO!
			}
			try {
				synchronized (this) {
					while (this.parada) {
						this.wait();
						System.out.println("Opa, fui notificada!");
					}
				}
				this.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void parar() {
		this.parada = true;
	}
	public synchronized void reiniciar() {
		this.parada = false;
		this.notify();
	}

}
