package views;

public class BolaView {

	private float velocidad;
	private float x, y;

	public BolaView() {}

	public BolaView(float x, float y,float velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
