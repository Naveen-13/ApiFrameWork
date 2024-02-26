package resources;

import java.util.ArrayList;
import java.util.List;

import bdd.spojo.Data;
import bdd.spojo.Location;

public class TestData {
	public Data addPlaceData(String name, String address, String language) {
		List<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		
		Data ds = new Data();
		ds.setAccuracy(50);
		ds.setName(name);
		ds.setPhone_number("(+91) 983 893 3937");
		ds.setAddress(address);
		ds.setWebsite("http://google.com");
		ds.setLanguage(language);
		ds.setTypes(ls);
		
		Location l = new Location();
		l.setLng(-38.383494);
		l.setLat(33.427362);
		ds.setLocation(l);
		return ds;
	}
	public String getDeletePayload(String placeID) {
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}

}
