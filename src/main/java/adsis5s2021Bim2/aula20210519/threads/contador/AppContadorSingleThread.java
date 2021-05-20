package adsis5s2021Bim2.aula20210519.threads.contador;

public class AppContadorSingleThread {
	
	public static void main(String[] args) {
		AppContadorSingleThread contador = new AppContadorSingleThread();
		contador.contar(50);
		
	}
	private void contar(int contarAté) {
		for (int i = 0; i <= contarAté; i++) {
			System.out.println( i + " => " + this.toString());
		}
		
	}

}
