package adsis5s2021Bim2.aula20210602.gerenciandoThreadsParaCópiaDeArquivosRefatorado;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

public class FileCopyThread extends Thread {
	private FileCopyThreadListener listener = null;
	private boolean parada;
	private List<File> arquivosDaThread;
	private String dirOrigem;
	private String dirDestino;


	public FileCopyThread(List<File> arquivosDaThread, String dirOrigem, String dirDestino) {
		this.arquivosDaThread = arquivosDaThread;
		this.dirOrigem = dirOrigem;
		this.dirDestino = dirDestino;
	}

	public void setListener(FileCopyThreadListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void run() {
		int jáCopiados = 0;
		for (File arquivo : arquivosDaThread) {
			try {
				FileOutputStream output = new FileOutputStream(dirDestino + "/" + arquivo.getName());
				Files.copy(arquivo.toPath(), output);
				output.close();
				jáCopiados++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (listener != null) {
				//calcular o percentual de 0 a 100 em função do total de arquivos que a thread copiará.
				double percentual = ((jáCopiados*100) / arquivosDaThread.size());
				listener.valorAlterado( (int)percentual ); //AJUSTAR ESTE ZERO!
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
