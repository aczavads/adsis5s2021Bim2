package adsis5s2021Bim2.aula20210513.serialização;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class AppSerialização {
	
	public static void main(String[] args) throws IOException {
		
		Cachorro fiel = new Cachorro("Fiel",  new PastorAlemão());
		
		ObjectOutputStream output = new ObjectOutputStream(System.out);
		output.writeObject(fiel);
	}

}
