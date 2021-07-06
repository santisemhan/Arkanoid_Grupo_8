package views;

public class BarraView {
	private int ejeX,ejeY;

	public BarraView() {}

	public BarraView(int ejeX,int ejeY) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
	}

	public int getejeY() {
		return ejeY;
	}

	public void setejeY(int ejeY) {
		this.ejeY = ejeY;
	}

	public int getejeX() {
		return ejeX;
	}

	public void setejeX(int ejeX) {
		this.ejeX = ejeX;
	}
	
}
