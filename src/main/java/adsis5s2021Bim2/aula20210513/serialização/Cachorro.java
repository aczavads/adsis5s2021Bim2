package adsis5s2021Bim2.aula20210513.serialização;

import java.io.Serializable;

public class Cachorro implements Serializable {
	private static final long serialVersionUID = 4621016558952724932L;
	private String nome;
	private Raça raça;

	public Cachorro(String nome, Raça raça) {
		this.nome = nome;
		this.raça = raça;
	}

}
