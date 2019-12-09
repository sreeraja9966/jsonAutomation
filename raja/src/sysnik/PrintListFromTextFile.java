package sysnik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import helper.WebTableHelper;
import testBase.TestBase;

public class PrintListFromTextFile extends TestBase{
static WebTableHelper name = new WebTableHelper();
public  void map( ) throws IOException
{
    String filePath = relativePath()+"\\sysnikdata\\custcreation\\tableData\\locatorOld.txt";
    System.out.println(filePath);
    HashMap<String, String> map = new HashMap<String, String>();

    String line;
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    while ((line = reader.readLine()) != null)
    {
        String[] parts = line.split("=", 2);
        if (parts.length >= 2)
        {
            String key = parts[0];
            String value = parts[1];
            map.put(key, value);
        } 
    reader.close();
    }
}
public static void main(String[] args) throws IOException {
	PrintListFromTextFile neww=new PrintListFromTextFile();
	neww.map();
	/*List<String> req=name.getTableDataFromTextFile("//sysnikdata//custcreation//data0");
	System.out.println(req.size());
	for(int i=0;i<req.size();i++) {
		
		System.out.println(i+")"+req.get(i));
	}*/
}
}
