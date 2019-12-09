package sysnik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePlusDays {
public static void main(String[] args) {
	String date1="2019/02/22";
	LocalDate date =LocalDate.parse(date1);
	System.out.println(date.plusDays(30));
	System.out.println(date.now().format(DateTimeFormatter.ofPattern("dd.MMMM yyyy")));
}
}
