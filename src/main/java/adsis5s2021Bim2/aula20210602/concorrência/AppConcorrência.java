package adsis5s2021Bim2.aula20210602.concorrĂȘncia;

public class AppConcorrĂȘncia {
	
	public static void main(String[] args) {
		ContadorVisual cv = new ContadorVisual();
		
		Runnable r = () -> {
			for (int i = 1; i <= 100000; i++) {
				cv.incrementar();
			}
		};
		
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
				
	}

}
