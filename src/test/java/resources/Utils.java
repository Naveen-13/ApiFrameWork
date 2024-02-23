package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpec() throws IOException {
		if(req == null) {
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
		req =  new RequestSpecBuilder().setBaseUri(getGlobalData("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}
		return req;
	}
	
	public  String getGlobalData(String key) throws IOException {
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream("D:\\NAVEEN\\Selenium\\ApiFrameWork\\src\\test\\java\\resources\\global.properties");
		p.load(fs);
		return p.getProperty(key);
	}

}
