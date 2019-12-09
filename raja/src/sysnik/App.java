package sysnik;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import static com.jayway.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import testBase.TestBase;

public class App extends TestBase{
	
	public static void main(String[] args) {
		
	
	String APIUrl="https://api.jsonbin.io/b/5c5ebe37e9e7c118390f9ef0";
	Response response =given().authentication().preemptive().basic("", "").get(APIUrl);
	 String resp=response.asString();
	StringBuilder sb = new StringBuilder(resp);
	String needed=sb.deleteCharAt(0).toString();
	System.out.println(needed);
	JSONObject JSONResponseBody = new JSONObject(needed);
	//int required=JSONResponseBody.getInt("section");
	//keyFromExcel=instructionId.trim();

	JSONArray dataFromPS = JSONResponseBody.getJSONArray("formTabs");
	
	for(int i=0;i<dataFromPS.length();i++){
		JSONObject innInnerObj=dataFromPS.getJSONObject(i);
		
		Iterator<String> InnerIterator=innInnerObj.keys();
		//System.out.println(innInnerObj.get(InnerIterator.next())+"------>InnerIterator");
		while(InnerIterator.hasNext()){
			System.out.println("InnInnerObject value is :"+innInnerObj.get(InnerIterator.next()));
			JSONArray innerArray=	innInnerObj.getJSONArray(InnerIterator.next());
System.out.println(innerArray);
			for(int j=0;j<dataFromPS.length();j++){
				JSONObject innInnerObj2=innerArray.getJSONObject(i);
				
				Iterator<String> InnerIterator2=innInnerObj2.keys();

				try {
					String tagName=innInnerObj2.getString("type");
					String label=innInnerObj2.getString("label");
					String name=innInnerObj2.getString("name");
					System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			//innInnerObj.get(InnerIterator.next());
			/*JSONArray dataFromPS2 = innInnerObj.getJSONArray("fields");
			JSONObject innInnerObj2=dataFromPS2.getJSONObject(i);
			Iterator<String> InnerIterator2=innInnerObj2.keys();

					try {
						String tagName=innInnerObj2.getString("type");
						String label=innInnerObj2.getString("label");
						String name=innInnerObj2.getString("name");
						System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
			
			 }

		//JSONArray dataFromPS2 = innInnerObj.getJSONArray("fields");

	/*for(int j=0;j<dataFromPS2.length();j++){
		JSONObject innInnerObj2=dataFromPS2.getJSONObject(j);
		Iterator<String> InnerIterator2=innInnerObj2.keys();

				try {
					String tagName=innInnerObj2.getString("type");
					String label=innInnerObj2.getString("label");
					String name=innInnerObj2.getString("name");
					System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	JSONArray dataFromP = JSONResponseBody.getJSONArray("buttons");
	for(int i=0;i<dataFromP.length();i++){
		JSONObject innInnerObj=dataFromP.getJSONObject(i);
		Iterator<String> InnerIterator=innInnerObj.keys();


				try {
					String tagName=innInnerObj.getString("type");
					String label=innInnerObj.getString("label");
					String name=innInnerObj.getString("name");
					System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
	JSONArray dataFrom = JSONResponseBody.getJSONArray("fields");
	for(int i=0;i<dataFrom.length();i++){
		JSONObject innInnerObj=dataFrom.getJSONObject(i);
		Iterator<String> InnerIterator=innInnerObj.keys();


				try {
					String tagName=innInnerObj.getString("type");
					String label=innInnerObj.getString("label");
					String name=innInnerObj.getString("name");
					System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
	
	
	}
	*/
	
 	}}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*JSONArray dataFromPS = JSONResponseBody.getJSONArray("formTabs");
	
for(int i=0;i<dataFromPS.length();i++){
JSONObject innInnerObj=dataFromPS.getJSONObject(i);
Iterator<String> InnerIterator=innInnerObj.keys();*/


/*JSONArray dataFromPS2 = innInnerObj.getJSONArray("fields");


for(int j=0;j<dataFromPS2.length();j++){
JSONObject innInnerObj2=dataFromPS2.getJSONObject(j);
Iterator<String> InnerIterator2=innInnerObj2.keys();

		try {
			String tagName=innInnerObj2.getString("type");
			String label=innInnerObj2.getString("label");
			String name=innInnerObj2.getString("name");
			System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
		JSONArray dataFromPS3= JSONResponseBody.getJSONArray("fields");	
		for(int Z=0;Z<dataFromPS3.length();Z++){
			JSONObject innInnerObj3=dataFromPS3.getJSONObject(Z);
			Iterator<String> InnerIterator3=innInnerObj3.keys();

					try {
						String tagName=innInnerObj3.getString("type");
						String label=innInnerObj3.getString("label");
						String name=innInnerObj3.getString("name");
						System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
		
		JSONArray dataFromPS4= JSONResponseBody.getJSONArray("buttons");	
		for(int Z=0;Z<dataFromPS4.length();Z++){
			JSONObject innInnerObj4=dataFromPS4.getJSONObject(Z);
			Iterator<String> InnerIterator3=innInnerObj4.keys();

					try {
						String tagName=innInnerObj4.getString("type");
						String label=innInnerObj4.getString("label");
						String name=innInnerObj4.getString("name");
						System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}*/
		
		
/*JSONArray dataFromPS3 = innInnerObj2.getJSONArray("buttons");


for(int z=0;z<dataFromPS3.length();z++){
JSONObject innInnerObj3=dataFromPS3.getJSONObject(i);
Iterator<String> InnerIterator3=innInnerObj3.keys();
while(InnerIterator.hasNext()){
	
		String tagName=innInnerObj3.getString("type");
		String label=innInnerObj3.getString("label");
		String name=innInnerObj3.getString("name");
		System.out.println(label+"_"+tagName+"=//"+tagName+"[@name='"+name+"']");
	} 
}*/


}
	







 






















	//System.out.println(dataFromPS+"--------------->dataFromPS");
	
	  /* String str = needed; 
	   
	  
	   String[] words = str.split("\\{+");
	   for(String a:words)
           System.out.println("SPLITED STRING----->"+a.toString());
	   
	   System.out.println();
	   */
	   
	   
       /*
	   String[] sam=null;
       for (int i=0;i<str.length();i++) {
    	 sam=str.split("//{");
    	 
       }
       for(String a:sam)
           System.out.println("SPLITED STRING----->"+a.toString()); */
/*{String driverPath = "C:\\Users\\sriraja.garlapati\\Downloads";
TestBase obj =new TestBase();
ElementFinder elementFinder=new ElementFinder();

@Test
public void launchBrowser() {
	WebDriver d=obj.strartBrowser("chrome");
}

@Test
public void openApplication() {
	d.navigate().to("http://172.16.0.126:4200/dashboard/withdrawal");
	d.manage().window().maximize();
	//d.findElement(By.xpath("//input[@name='q']")).sendKeys("RAJA");;
elementFinder.find_IB_Elmtn(d.findElement(By.xpath("//input[@name='q']")), 2, 20).sendKeys("Raja");
}

@AfterTest
public void closeDriver() {
	if(d!=null) {
		d.close();
	}
}}
}}}*/

