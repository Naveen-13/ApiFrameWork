package resources;

public enum ApiResourse {
	
	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json");
	private String resource;
	
	public String getResource() {
		return resource;
	}
	ApiResourse(String resourse) {
		this.resource = resourse;
	}
	
	
	

}
