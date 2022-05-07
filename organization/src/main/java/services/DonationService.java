package services;

import java.util.List;

import entities.Donation;
import entities.Donor;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;

public interface DonationService {
	public int addDonation(Donation donation) throws AddFailureException; // Return the id of the added donation
	public Donation getDonationById(int donationId) throws EntityNotFoundException;
	public List<Donation> getAllDonations() throws EntityNotFoundException;
	public Boolean deleteDonationById(int donationId) throws DeleteFailureException;
	public int addDonor(Donor donor, int donationId) throws AddFailureException;
	public Donor getDonorById(int donorId) throws EntityNotFoundException;
	public List<Donor> getAllDonors() throws EntityNotFoundException;
	
}
