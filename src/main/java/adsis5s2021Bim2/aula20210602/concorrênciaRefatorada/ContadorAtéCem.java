package adsis5s2021Bim2.aula20210602.concorrĂȘnciaRefatorada;

public class ContadorAtĂ©Cem extends Thread {
	private ContadorVisual cv;

	public ContadorAtĂ©Cem(ContadorVisual cv) {
		this.cv = cv;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100000; i++) {
			cv.incrementar();
		}
	}

}
