package entities;

import java.time.LocalDate;

public class Donation {
	private int donationId;
	private Float amountOfMoney;
	private LocalDate date;
	private Donor donor;
	public Donation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Donation(int donationId, Float amountOfMoney, LocalDate date, Donor donor) {
		super();
		this.donationId = donationId;
		this.amountOfMoney = amountOfMoney;
		this.date = date;
		this.donor = donor;
	}
	
	/**
	 * Constructor without donationId*/
	public Donation(Float amountOfMoney, LocalDate date, Donor donor) {
		super();
		this.amountOfMoney = amountOfMoney;
		this.date = date;
		this.donor = donor;
	}
	public int getDonationId() {
		return donationId;
	}
	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}
	public Float getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Donor getDonor() {
		return donor;
	}
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", amountOfMoney=" + amountOfMoney + ", date=" + date + ", donor="
				+ donor + "]";
	}
	
	
}
