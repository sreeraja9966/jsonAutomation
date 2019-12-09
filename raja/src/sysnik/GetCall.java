package sysnik;

import helper.CacheHelper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import testBase.TestBase;

public class GetCall extends TestBase {

	static CacheHelper cacheHelper = new CacheHelper();
	public static void main(String[] args) {
String avilableDenom="5780";
		
		String transferDenom="2860";
		
		 int balanceDenomofFromUser=Integer.parseInt(avilableDenom)-Integer.parseInt(transferDenom);
		//cacheHelper.setCacheWithaString("balanceDenomAmount", Integer.toString(balanceDenomofFromUser));
		int a=10;
		int b=20;
		System.out.println(a+b);
		//String req=cacheHelper.getCache("balanceDenomAmount");
	//System.out.println(req);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void getcallTesting() {
		RestAssured.baseURI = "https://api.jsonbin.io/b/5c5ebe37e9e7c118390f9ef0\r\n";
		RequestSpecification request = RestAssured.given();

		Response response = request.get();
		System.out.println("Response Body -> " + response.body().asString());

		// We can convert the Json Response directly into a Java Array by using
		// JsonPath.getObject method. Here we have to specify that we want to
		// deserialize the Json into an Array of Book. This can be done by specifying
		// Book[].class as the second argument to the getObject method.
		Book[] books = response.jsonPath().getObject("books",Book[].class );

		for(Book book : books)
		{
			System.out.println("Book title " + book.title);
		}
	}*/
}
