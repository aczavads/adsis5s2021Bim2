package adsis5s2021Bim2.aula20210513.serialização;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class AppMilSerializadosGzipados {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//Cachorro[] cachorrada = new Cachorro[1000];
		List<Cachorro> cachorrada = new ArrayList<Cachorro>();
		
		for (int i = 0; i < 1000; i++) {
			Raça r = i < 400 ? new Poodle() : new SemRaçaDefinida();
			Cachorro c = new Cachorro("C�ozinho " + i, r);
			cachorrada.add(c);
		}
		
		//ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(new File("d:/cachorrada.ser.gz"))));
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("d:/cachorrada.ser")));
		output.writeObject(cachorrada);
		
		System.out.println(cachorrada.size());
	}

}
