package adsis5s2021Bim2.aula20210520.threadCópiaDiretório;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CópiaDeDiretórioThread extends Thread {
	private String origem;
	private String destino;

	public CópiaDeDiretórioThread(String origem, String destino) {
		this.origem = origem;
		this.destino = destino;
	}

	@Override
	public void run() {
		try {
			copiarDiretório(origem, destino);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void copiarDiretório(String origem, String destino) throws Exception {
		File diretórioOrigem = new File(origem);
		File[] arquivosOrigem = diretórioOrigem.listFiles();
		for (File arquivo : arquivosOrigem) {
			copiarArquivo(arquivo.getCanonicalPath(), destino + "/" + arquivo.getName());
		}		
	}
	
	private static void copiarArquivo(String origem, String destino) throws Exception {
		File arquivoOrigem = new File(origem);
		File arquivoDestino = new File(destino);
		
		//IO: Input/Output
		
		FileInputStream input = new FileInputStream(arquivoOrigem);
		FileOutputStream output = new FileOutputStream(arquivoDestino);
		int dado = input.read();
		while (dado != -1) {
			output.write(dado);
			dado = input.read();
		}
		input.close();
		output.close();
	}
}
