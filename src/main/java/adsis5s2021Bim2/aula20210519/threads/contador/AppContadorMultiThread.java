package adsis5s2021Bim2.aula20210519.threads.contador;

import java.util.Random;

public class AppContadorMultiThread {
	
	public static void main(String[] args) {
//		Contador c1 = new Contador(50);
//		Contador c2 = new Contador(50);
//		Contador c3 = new Contador(50);
//		c1.start();
//		c2.start();
//		c3.start();
		for (int i = 0; i < 10; i++) {
			Contador c = new Contador(999999999L);
			c.start();			
		}
		
	}
	
	private static class Contador extends Thread {
		private long contarAté;
		
		public Contador(long contarAté) {
			this.contarAté = contarAté;
		}
		
		@Override
		public void run() {
			Random r = new Random();
			long início = System.currentTimeMillis();
			for (int i = 0; i <= contarAté; i++) {
				//System.out.println( i + " => " + this.toString());
//				try {
//					this.sleep((int)(r.nextDouble() * 200));
//				} catch (Exception e) {
//				}
			}
			long término = System.currentTimeMillis();
			System.out.println("Tempo total= " + (término-início));
		}
	}

}
