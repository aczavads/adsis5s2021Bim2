package adsis5s2021Bim2.aula20210505;

import java.io.File;
import java.io.IOException;

public class AppArquivos {
	
	public static void main(String[] args) throws IOException {
		File diretório = new File("/qualquer");
		
		listarArquivosDoDiretório(diretório);
		long tamanhoEmBytesDiretório = calcularTamanhoEmBytesDoDiretório(diretório);
		System.out.println("Tamanho do diretório=" + tamanhoEmBytesDiretório);
	}
	
	private static long calcularTamanhoEmBytesDoDiretório(File diretório) {
		long tamanho = 0;
		File[] arquivosDoDiretório = diretório.listFiles();
		for (File arquivo : arquivosDoDiretório) {
			tamanho += arquivo.length();
		}		
		return tamanho;
	}

	public static void listarArquivosDoDiretório(File diretório) {
		File[] arquivosDoDiretório = diretório.listFiles();
		for (File arquivo : arquivosDoDiretório) {
			System.out.println(arquivo.getName() + " #tamanho=" + arquivo.length() + "bytes");
		}		
	}

}
