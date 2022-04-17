package services;

import java.util.List;

import entities.Donation;

public interface DonationService {
	public int addDonation(); // Return the id of the added donation
	public Donation getDonationById(int id);
	public List<Donation> getAllDonations();
	public Boolean deleteDonationById(int id);
	
}
