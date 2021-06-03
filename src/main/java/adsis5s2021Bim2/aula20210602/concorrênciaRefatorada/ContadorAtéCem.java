package adsis5s2021Bim2.aula20210602.concorrênciaRefatorada;

public class ContadorAtéCem extends Thread {
	private ContadorVisual cv;

	public ContadorAtéCem(ContadorVisual cv) {
		this.cv = cv;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100000; i++) {
			cv.incrementar();
		}
	}

}
