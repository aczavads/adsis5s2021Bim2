package adsis5s2021Bim2.aula20210602.concorrĂȘncia;

public class ContadorVisual {
	private long valor;
	
	public synchronized long incrementar() {
		valor++;
		System.out.println("Novo valor: " + valor);
		return valor;
	}

}
