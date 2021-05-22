package dominio;

/**
 * Clase DTO que registra los Inputs de un usuario concreto
 * @author Basilio David Gómez Fernández
 */
public class UserInputs {
	private boolean doubleTouched;
	private int touchPositioX, touchPositionY;

	public UserInputs(){
		this.touchPositionY = 0;
		this.touchPositioX = 0;
		this.doubleTouched = false;
	}

	/**
	 * Método que setea todos los valores dados por una cadena serializada a atributos de la clase
	 * @param serialized Parametros de un UserInputs previamente serializador
	 */
	public void newVariables(String serialized){
		String[] datos = serialized.split(",");
		this.setDoubleTouched(Boolean.getBoolean(datos[0]));
		this.setTouchPositioX(Integer.parseInt(datos[1]));
		this.setTouchPositioX(Integer.parseInt(datos[2]));
	}

	public boolean isDoubleTouched(){
		return this.doubleTouched;
	}

	public void setDoubleTouched(boolean doubleTouched) {
		this.doubleTouched = doubleTouched;
	}

	public int getTouchPositioX() {
		return touchPositioX;
	}

	public void setTouchPositioX(int touchPositioX) {
		this.touchPositioX = touchPositioX;
	}

	public int getTouchPositionY() {
		return touchPositionY;
	}

	public void setTouchPositionY(int touchPositionY) {
		this.touchPositionY = touchPositionY;
	}

	/**
	 * Método que serializa los atributos.
	 * Método centrado principalmente en el client-side para pasar esa cadena al servidor
	 * @return Cadena de atributos serializada, separada mediante comas
	 */
	public String serialize(){
		return doubleTouched + "," + touchPositioX + "," + touchPositionY;
	}
}
