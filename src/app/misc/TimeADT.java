package app.misc;

public class TimeADT {
	private int hour;
	private int minute;
	private int second;
	
	public TimeADT(int h, int m, int s) {
		if (h < 0 || h >= 24)
			throw new IllegalArgumentException("Las horas deben estar entre 0 y 23");
		else if (m < 0 || m >= 60)
			throw new IllegalArgumentException("Los minutos deben estar entre 0 y 60");
		else if (s < 0 || s >= 60)
			throw new IllegalArgumentException("Los segundos deben estar entre 0 y 60");
		
		hour = h;
		minute = m;
		second = s;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (hour < 10);
			sb.append("0");
		sb.append(hour).append(":");
		if (minute < 10)
			sb.append("0");
		sb.append(minute).append(":");
		if (second < 10)
			sb.append("0");
		sb.append(second);
		
		return sb.toString();
	}
}
