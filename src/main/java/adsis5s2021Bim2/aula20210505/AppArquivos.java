package adsis5s2021Bim2.aula20210505;

import java.io.File;
import java.io.IOException;

public class AppArquivos {
	
	public static void main(String[] args) throws IOException {
		File diretório = new File("c:/qualquer");
		
		listarArquivosDodiretório(diretório);
		long tamanhoEmBytesdiretório = calcularTamanhoEmBytesDodiretório(diretório);
	    System.out.println("Tamanho do diretório = " + tamanhoEmBytesdiretório);
	}
	
	private static long calcularTamanhoEmBytesDodiretório(File diretório) {
		long tamanho = 0;
		File[] arquivosDodiretório = diretório.listFiles();
		for (File arquivo : arquivosDodiretório) {
			if (arquivo.isDirectory()) {
				tamanho += calcularTamanhoEmBytesDodiretório(arquivo);
			} else {
				tamanho += arquivo.length();				
			}
		}		
		return tamanho;
	}

	public static void listarArquivosDodiretório(File diretório) throws IOException {
		File[] arquivosDodiretório = diretório.listFiles();
		for (File arquivo : arquivosDodiretório) {
			if (arquivo.isDirectory()) {
			} else {
				System.out.println(arquivo.getCanonicalPath() + " #tamanho = " + arquivo.length() + "bytes");
			}
		}		
	}

}
