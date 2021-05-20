package car_kiosk;

public class CarDTO {
	
	private String car_Name;
	private String car_Num;
	private boolean isKey;
	private String car_Url;
	private String size;
	
	private static CarDTO carDTO = new CarDTO();
	
	public static CarDTO getInstance() {
		if(carDTO == null){ //최초 한번만 new 연산자를 통하여 메모리에 할당한다.
			carDTO = new CarDTO();
		}		
		return carDTO;
	}
	
	public CarDTO() {}
	
	public CarDTO(String car_Name, String car_Num, boolean isKey, String car_Url, String size) {
		super();
		this.car_Name = car_Name;
		this.car_Num = car_Num;
		this.isKey = isKey;
		this.car_Url = car_Url;
		this.size = size;
		
	}

	

	public String getCar_Name() {
		return car_Name;
	}
	public void setCar_Name(String car_Name) {
		this.car_Name = car_Name;
	}
	public String getCar_Num() {
		return car_Num;
	}
	public void setCar_Num(String car_Num) {
		this.car_Num = car_Num;
	}
	public boolean getIsKey() {
		return isKey;
	}
	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}
	public String getCar_Url() {
		return car_Url;
	}

	public void setCar_Url(String car_Url) {
		this.car_Url = car_Url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "CarDTO [car_Name=" + car_Name + ", car_Num=" + car_Num + ", isKey=" + isKey + ", car_Url=" + car_Url + ", size=" + size +"]";
	}
	
	
	
}
