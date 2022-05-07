package entities;

public class Address {
	private int addressId;
	private String country;
	private String city;
	private String street;
	private String zipCode;
	
	
	
	
	public Address() {
		super();
	}
	public Address(int addressId, String country, String city, String street, String zipCode) {
		super();
		this.addressId = addressId;
		this.country = country;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}
	
	
	
	public Address(String country, String city, String street, String zipCode) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", country=" + country + ", city=" + city + ", street=" + street
				+ ", zipCode=" + zipCode + "]";
	}
	
	
	
	
}
