package dominio;

public class UserInputs {
	private boolean doubleTouched;
	private int touchPositioX, touchPositionY;

	public UserInputs(){
		this.touchPositionY = 0;
		this.touchPositioX = 0;
		this.doubleTouched = false;
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
}
