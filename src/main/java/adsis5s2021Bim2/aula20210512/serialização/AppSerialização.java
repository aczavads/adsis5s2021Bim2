package adsis5s2021Bim2.aula20210512.serialização;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppSerialização {
	
	public static void main(String[] args) throws Exception {
		
		gravarCarro();
		Carro lido = lerCarro();
		System.out.println(lido);
		 
	}

	private static Carro lerCarro() throws Exception {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File("d:/carro.ser")));
		Carro lido = (Carro) input.readObject();
		return lido;
	}

	private static void gravarCarro() throws Exception {
		Carro hrvTestDrive = new Carro("Honda", "HRV", 2021);		
		
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("d:/carro.ser")));
		
		output.writeObject(hrvTestDrive);
	}

}
