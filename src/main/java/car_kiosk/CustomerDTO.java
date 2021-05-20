package car_kiosk;

import java.util.ArrayList;

public class CustomerDTO {

	private static CustomerDTO customerDTO = new CustomerDTO();
	
	public static CustomerDTO getInstance() {
		if(customerDTO == null)
			customerDTO = new CustomerDTO();
		return customerDTO;
	}
	

	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}
	


	public CustomerDTO( String li_num, String li_Date, String ph_Num, String car_Num, boolean agree, String lent_Time,
			String return_Time, String pay_Method, int pay_Money, String overpay_Method, String overpay_Money,
			boolean isKey, String start_Time) {
		super();
		this.li_num = li_num;
		this.li_Date = li_Date;
		this.ph_Num = ph_Num;
		this.car_Num = car_Num;
		this.Agree = agree;
		this.lent_Time = lent_Time;
		this.return_Time = return_Time;
		this.pay_Method = pay_Method;
		this.pay_Money = pay_Money;
		this.overpay_Method = overpay_Method;
		this.overpay_Money = overpay_Money;
		this.isKey = isKey;
		this.start_Time = start_Time;

	}


	String li_num;
	String li_Date;
	String ph_Num;
	String car_Num;
	boolean Agree;
	String lent_Time;
	String return_Time;
	String pay_Method;
	int pay_Money;
	String overpay_Method;
	String overpay_Money;
	boolean isKey;
	String start_Time;


	public String getPh_Num() {
		return ph_Num;
	}

	public void setPh_Num(String ph_Num) {
		this.ph_Num = ph_Num;
	}

	public String getCar_Num() {
		return car_Num;
	}

	public void setCar_Num(String car_Num) {
		this.car_Num = car_Num;
	}

	public boolean isAgree() {
		return Agree;
	}

	public void setAgree(boolean agree) {
		Agree = agree;
	}


	public String getLent_Time() {
		return lent_Time;
	}

	public void setLent_Time(String lent_Time) {
		this.lent_Time = lent_Time;
	}

	public String getReturn_Time() {
		return return_Time;
	}

	public void setReturn_Time(String return_Time) {
		this.return_Time = return_Time;
	}

	public String getPay_Method() {
		return pay_Method;
	}

	public void setPay_Method(String pay_Method) {
		this.pay_Method = pay_Method;
	}

	public int getPay_Money() {
		return pay_Money;
	}

	public void setPay_Money(int pay_Money) {
		this.pay_Money = pay_Money;
	}

	public String getOverpay_Method() {
		return overpay_Method;
	}

	public void setOverpay_Method(String overpay_Method) {
		this.overpay_Method = overpay_Method;
	}

	public String getOverpay_Money() {
		return overpay_Money;
	}

	public void setOverpay_Money(String overpay_Money) {
		this.overpay_Money = overpay_Money;
	}

	public boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}

	public String getLi_Date() {
		return li_Date;
	}

	public void setLi_Date(String li_Date) {
		this.li_Date = li_Date;
	}

	public String getLi_num() {
		return li_num;
	}

	public void setLi_num(String li_num) {
		this.li_num = li_num;
	}
	public String getStart_Time() {
		return start_Time;
	}

	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	//만약 전체적으로 잘 들어가있는지 확인하고 싶다면?-> 이 메소드 호출하세요!
	@Override
	public String toString() {
		return "CustomerDTO [li_num=" + li_num + ", li_Date=" + li_Date + ", ph_Num=" + ph_Num + ", car_Num=" + car_Num
				+ ", Agree=" + Agree + ", lent_Time=" + lent_Time + ", return_Time=" + return_Time + ", pay_Method="
				+ pay_Method + ", pay_Money=" + pay_Money + ", overpay_Method=" + overpay_Method + ", overpay_Money="
				+ overpay_Money + ", isKey=" + isKey + ", start_Time=" + start_Time +"]";
	}
	
	


}
