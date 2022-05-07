package services.implementation;

import java.util.List;

import dao.DonationDAO;
import entities.Donation;
import entities.Donor;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;
import services.DonationService;

public class DonationServiceImpl implements DonationService {

	@Override
	public int addDonation(Donation donation) throws AddFailureException {
		int donationId = DonationDAO.addDonation(donation);
		if (donationId == 0)
			throw new AddFailureException("Failed to add donation for " + donation.getDonor().getFirstName());
		return 0;
	}

	@Override
	public Donation getDonationById(int donationId) throws EntityNotFoundException {
		Donation donation = DonationDAO.getDonationById(donationId);
		if (donation == null)
			throw new EntityNotFoundException("Cannot find donation with id " + donationId);
		return null;
	}

	@Override
	public List<Donation> getAllDonations() throws EntityNotFoundException {
		List<Donation> donations = DonationDAO.getAllDonations();
		if (donations == null)
			throw new EntityNotFoundException("Cannot get the list of all donations");
		return donations;
	}

	@Override
	public Boolean deleteDonationById(int donationId) throws DeleteFailureException {
		boolean donationDeleted = DonationDAO.deleteDonationById(donationId);
		if (!donationDeleted)
			throw new DeleteFailureException("Failed to delete donation with id " + donationId);
		return true;
	}

	@Override
	public int addDonor(Donor donor, int donationId) throws AddFailureException {
		int donorId = DonationDAO.addDonor(donor, donationId);
		if (donorId == 0)
			throw new AddFailureException("Failed to add donor " + donor.getFirstName() + " " + donor.getLastName());
		return donorId;
	}

	@Override
	public Donor getDonorById(int donorId) throws EntityNotFoundException {
		Donor donor = DonationDAO.getDonorById(donorId);
		if (donor == null)
			throw new EntityNotFoundException("Cannot find donor " + donor.getFirstName() + " " + donor.getLastName());
		return donor;
	}

	@Override
	public List<Donor> getAllDonors() throws EntityNotFoundException {
		List<Donor> donors = DonationDAO.getAllDonors();
		if (donors == null)
			throw new EntityNotFoundException("Cannot get the list of all donors !");
		return donors;
	}


	
	
	

}
