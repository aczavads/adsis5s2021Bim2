package adsis5s2021Bim2.aula20210520.threadCópiaDiretório;

public class AppThreadCópiaDiretório {
	
	public static void main(String[] args) {
		CópiaDeDiretórioThread copiarOrigemParaDestino = new CópiaDeDiretórioThread("d:/origem", "d:/backup-01");
		CópiaDeDiretórioThread copiarWindowsParaBackup = new CópiaDeDiretórioThread("d:/origem", "d:/backup-02");
		
		//copiarOrigemParaDestino.run();
		copiarOrigemParaDestino.start();
		System.out.println("Primeira iniciada...");

		//copiarWindowsParaBackup.run();
		copiarWindowsParaBackup.start();
		System.out.println("Segunda iniciada...");
		
		System.out.println("Fim.");
	}

}
