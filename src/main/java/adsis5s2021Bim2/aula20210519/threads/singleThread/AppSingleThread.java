package adsis5s2021Bim2.aula20210519.threads.singleThread;

public class AppSingleThread {
	
	public static void main(String[] args) {
		
		for (long i = 0; i < 1_000_000_000; i++) {
			String[] teste = new String[1000];
			for (int j = 0; j < teste.length; j++) {
				teste[j] = "" + System.nanoTime();
			}
		}
		System.out.println("Foi.");
		
	}

}
