package adsis5s2021Bim2.aula20210526.gerenciandoThreads;

public class NovoContadorThread extends Thread {
	private int de;
	private int até;
	private NovoContadorThreadListener listener = null;
	private boolean parada;

	public NovoContadorThread(int de, int até) {
		this.de = de;
		this.até = até;
	}

	public void setListener(NovoContadorThreadListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void run() {
		for (int i = de; i <= até; i++) {
			if (listener != null) {
				listener.valorAlterado(i);
			}
			try {
				synchronized (this) {
					while (this.parada) {
						this.wait();
						System.out.println("Opa, fui notificada!");
					}
				}
				this.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void parar() {
		this.parada = true;
	}
	public synchronized void reiniciar() {
		this.parada = false;
		this.notify();
	}

}
