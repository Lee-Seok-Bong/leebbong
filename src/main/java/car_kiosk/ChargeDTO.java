package car_kiosk;

public class ChargeDTO {
	private String CarSize;
	private int ten_Min;
	private int one_Hour;
	private int three_Hour;
	private int six_Hour;
	private int one_Day;
	private int three_Day;
	
	private static ChargeDTO chargeDTO = new ChargeDTO();
	
	public static ChargeDTO getInstance() {
		if(chargeDTO == null){ //최초 한번만 new 연산자를 통하여 메모리에 할당한다.
			chargeDTO = new ChargeDTO();
		}		
		return chargeDTO;
	}

	public ChargeDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChargeDTO [CarSize=" + CarSize + ", ten_Min=" + ten_Min + ", one_Hour=" + one_Hour + ", three_Hour="
				+ three_Hour + ", six_Hour=" + six_Hour + ", one_Day=" + one_Day + ", three_Day=" + three_Day + "]";
	}

	public ChargeDTO(String carSize, int ten_Min, int one_Hour, int three_Hour, int six_Hour, int one_Day,
			int three_Day) {
		super();
		CarSize = carSize;
		this.ten_Min = ten_Min;
		this.one_Hour = one_Hour;
		this.three_Hour = three_Hour;
		this.six_Hour = six_Hour;
		this.one_Day = one_Day;
		this.three_Day = three_Day;
	}
	

	public String getCarSize() {
		return CarSize;
	}
	public void setCarSize(String carSize) {
		CarSize = carSize;
	}
	public int getTen_Min() {
		return ten_Min;
	}
	public void setTen_Min(int ten_Min) {
		this.ten_Min = ten_Min;
	}
	public int getOne_Hour() {
		return one_Hour;
	}
	public void setOne_Hour(int one_Hour) {
		this.one_Hour = one_Hour;
	}
	public int getThree_Hour() {
		return three_Hour;
	}
	public void setThree_Hour(int three_Hour) {
		this.three_Hour = three_Hour;
	}
	public int getSix_Hour() {
		return six_Hour;
	}
	public void setSix_Hour(int six_Hour) {
		this.six_Hour = six_Hour;
	}
	public int getOne_Day() {
		return one_Day;
	}
	public void setOne_Day(int one_Day) {
		this.one_Day = one_Day;
	}
	public int getThree_Day() {
		return three_Day;
	}
	public void setThree_Day(int three_Day) {
		this.three_Day = three_Day;
	}
	
	
	

}
