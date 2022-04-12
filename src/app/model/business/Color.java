package app.model.business;

public enum Color {
	BLACK(0, 0, 0, "Negro"), 
	WHITE(255, 255, 255, "Blanco"), 
	RED(255, 0, 0, "Rojo"), 
	GREEN(0, 255, 0, "Verde"),
	BLUE(0, 0, 255, "Azul"),
	YELLOW(255, 255, 0, "Amarillo"),
	CYAN(0, 255, 255, "Cian"),
	MAGENTA(255, 0, 255, "Magenta"),
	SILVER(192, 192, 192, "Plata"),
	GRAY(128, 128, 128, "Gris"),
	MAROON(128, 0, 0, "Granate"),
	PURPLE(128, 0, 128, "Morado"),
	NAVYBLUE(0, 0, 128, "Azul Marino"),
	CORAL(255, 127, 80, "Coral"),
	GOLD(255, 215, 0, "Dorado"),
	KHAKI(240, 230, 140, "Caqui"),
	TURQUOISE(64, 240, 208, "Turquesa"),
	SKYBLUE(135, 206, 235, "Azul Cielo"),
	INDIGO(75, 0, 130, "Indigo"),
	PINK(255, 192, 203, "Pink"),
	BEIGE(245, 245, 220, "Beige"),
	SIENNA(160, 82, 45, "Sienna"),
	SANDYBR(244, 164, 96, "Marron Tierra"),
	MISTYRO(255, 228 ,225, "Rosa Bruma");
	
	int _red;
	
	int _green;
	
	int _blue;
	
	String _col;
	
	private Color (int red, int green, int blue, String col) {
		_red = red;
		_green = green;
		_blue = blue;
		_col = col;
	}
	
	public int getRed() {
		return _red;
	}
	
	public int getGreen() {
		return _green;
	}
	
	public int getBlue() {
		return _blue;
	}
	
	public String getColorName() {
		return _col;
	}
	
	public static Color findColor(String col) {
		for(int i = 0; i < values().length; ++i) {
			if(values()[i]._col.equalsIgnoreCase(col))
				return values()[i];
		}
		return null;
	}
}
