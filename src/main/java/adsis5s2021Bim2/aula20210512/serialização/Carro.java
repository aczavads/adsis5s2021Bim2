package adsis5s2021Bim2.aula20210512.serialização;

import java.io.Serializable;

public class Carro implements Serializable {
	private static final long serialVersionUID = 4136000513136319679L;
	private String marca;
	private String modelo;
	private int ano;

	public Carro(String marca, String modelo, int ano) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getAno() {
		return ano;
	}

	@Override
	public String toString() {
		return "Carro [marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + "]";
	}
	
	

}
