package souts;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Souter {
	public static String CODE_LOG = "\u001B[0m";
	public static String CODE_ERROR = "\u001B[31m";
	public static String CODE_WARNING = "\u001B[33m";

	public synchronized static void log(String code, String mensaje){
		int hora = new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
		int minuto = new GregorianCalendar().get(Calendar.MINUTE);

		System.out.printf("%s[%s:%s] %s %s\n", code, hora, minuto, mensaje, CODE_LOG);
	}


}
