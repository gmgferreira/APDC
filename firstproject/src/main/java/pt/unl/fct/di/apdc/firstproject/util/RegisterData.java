package pt.unl.fct.di.apdc.firstproject.util;

public class RegisterData {
	
	public String username;
	public String password;
	public String mail;
	public String address;
	public String localidade;
	public String codPostal;
	public String phone;
	public String mobile;

	public RegisterData() {
		
	}
	
	public RegisterData(String username, String password, String mail, String address, String localidade, String codPostal, String phone, String mobile) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.address = address;
		this.localidade = localidade;
		this.codPostal = codPostal;
		this.phone = phone;
		this.mobile = mobile;
		
	}

}
