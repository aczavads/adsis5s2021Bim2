package adsis5s2021Bim2.aula20210602.concorrênciaRefatorada;

public class AppConcorrência {
	
	public static void main(String[] args) {
		ContadorVisual cv = new ContadorVisual();
				
		new ContadorAtéCem(cv).start();
		new ContadorAtéCem(cv).start();
		new ContadorAtéCem(cv).start();
				
	}

}
