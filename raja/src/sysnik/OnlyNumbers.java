package sysnik;

public class OnlyNumbers {

	public static void main(String[] args) {
		String req="123qsqe 13456dsdadf";
		req=req.replaceAll("[^0-9]", "");
		System.out.println(req);
	}
}
