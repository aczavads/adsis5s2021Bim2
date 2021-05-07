package adsis5s2021Bim2.aula20210505;

import java.io.File;
import java.io.IOException;

public class AppArquivos {
	
	public static void main(String[] args) throws IOException {
		File diretório = new File("c:/qualquer");
		
		listarArquivosDoDiretório(diretório);
		long tamanhoEmBytesDiretório = calcularTamanhoEmBytesDoDiretório(diretório);
	    System.out.println("Tamanho do diretório = " + tamanhoEmBytesDiretório);
	}
	
	private static long calcularTamanhoEmBytesDoDiretório(File diretório) {
		long tamanho = 0;
		File[] arquivosDoDiretório = diretório.listFiles();
		for (File arquivo : arquivosDoDiretório) {
			if (arquivo.isDirectory()) {
				tamanho += calcularTamanhoEmBytesDoDiretório(arquivo);
			} else {
				tamanho += arquivo.length();				
			}
		}		
		return tamanho;
	}

	public static void listarArquivosDoDiretório(File diretório) throws IOException {
		File[] arquivosDoDiretório = diretório.listFiles();
		for (File arquivo : arquivosDoDiretório) {
			if (arquivo.isDirectory()) {
				listarArquivosDoDiretório(arquivo);
			} else {
				System.out.println(arquivo.getCanonicalPath() + " #tamanho = " + arquivo.length() + "bytes");
			}
		}		
	}

}
