package sysnik;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class CommaNumber {
public static void main(String[] args) throws ParseException {
	Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
	System.out.println(format.format(new BigDecimal("100000000")));
	
	System.out.println(new DecimalFormat("##,##,###").format(450000 ));
	
	
}
}
