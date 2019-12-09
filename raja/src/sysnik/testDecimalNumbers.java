package sysnik;
import java.lang.Math.*;
import java.math.BigDecimal;

import com.gargoylesoftware.htmlunit.javascript.host.InstallTrigger;
public class testDecimalNumbers {
public static void main(String[] args) {
	String num="15000";
	String exp="15000";
	exp=exp.replace(",","");
	BigDecimal expected=new BigDecimal(exp);
	BigDecimal numberinIntFromDB=new BigDecimal(num);
	//int i=Integer.parseInt(num);
	if(numberinIntFromDB.compareTo(expected)==0) {
	System.out.println("equal");
	}
}
}
