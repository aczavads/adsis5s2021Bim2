package adsis5s2021Bim2.aula20210506;

import java.io.File;

public class AppNewFile {
	
	public static void main(String[] args) throws Exception  {
		File novoDiretório = new File("c:/novoDiretório");
		novoDiretório.mkdir();
		
		File novoArquivo = new File(novoDiretório.getCanonicalPath() + "/" + "novo.txt");
		novoArquivo.createNewFile();
	}

}
