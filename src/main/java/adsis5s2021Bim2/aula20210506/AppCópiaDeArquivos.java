package adsis5s2021Bim2.aula20210506;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AppCópiaDeArquivos {
	
	public static void main(String[] args) throws Exception {
		String origem = "c:/qualquer/primeiro.txt";
		String destino = "c:/qualquer/primeiro.txt.bkp";
		copiarArquivo(origem, destino);
		
	} 

	private static void copiarArquivo(String origem, String destino) throws Exception {
		File arquivoOrigem = new File(origem);
		File arquivoDestino = new File(destino);
		
		//IO: Input/Output
		
		FileInputStream input = new FileInputStream(arquivoOrigem);
		FileOutputStream output = new FileOutputStream(arquivoDestino);
		int dado = input.read();
		while (dado != -1) {
			System.out.print("=");
			output.write(dado);
			dado = input.read();
		}
		input.close();
		output.close();
	}

}
