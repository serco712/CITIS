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
		hour = toTime(h);
		minute = toTime(m);
		second = toTime(s);
	}
	
	public int toTime(int num) {
		StringBuilder sb = new StringBuilder(num);
		if(sb.length() > 2)
			return Integer.parseInt((String) sb.subSequence(sb.length() - 2, sb.length()));
		else
			return num;
	}
	
	public void setHour(int h) {
		hour = h;
	}
	
	public void setMinute(int m) {
		minute = m;
	}
	
	public void setSecond(int s) {
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
	
	public TimeADT toTimeADT(String time) {
		TimeADT ret = new TimeADT(0,0,0);
		String [] adt = time.split(":");
		
		ret.setHour(toTime(Integer.parseInt(adt[0])));
		ret.setMinute(toTime(Integer.parseInt(adt[1])));
		ret.setSecond(toTime(Integer.parseInt(adt[2])));
		
		return ret;
	}
}
