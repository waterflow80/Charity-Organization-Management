package services.implementation;

import java.util.List;

import dao.AdminDAO;
import entities.Admin;
import exceptions.AddFailureException;
import exceptions.EntityNotFoundException;
import services.AdminService;

public class AdminServiceImpl implements AdminService {

	@Override
	public int addAdmin(Admin admin, int accountId) throws AddFailureException {
		int adminId = AdminDAO.addAdmin(admin, accountId);
		if (adminId == 0) {
			throw new AddFailureException("Failed to add admin: " + admin.getFirstName() + " " + admin.getLastName());
		}
		return adminId;
	}

	@Override
	public Admin getAdminById(int id) throws EntityNotFoundException {
		Admin admin = AdminDAO.getAdminById(id);
		if (admin == null)
			throw new EntityNotFoundException("Cannot find admin with id " + id);
		
		return admin;
	}

	@Override
	public List<Admin> getAllAdmins() throws EntityNotFoundException {
		List<Admin> admins = AdminDAO.getAllAdmins();
		if (admins == null)
			throw new EntityNotFoundException("Cannot get the list of all admins");
		return admins;
	}

	@Override
	public List<Admin> getAdminsByRole(String role) throws EntityNotFoundException {
		List<Admin> admins = AdminDAO.getAdminsByRole(role);
		if (admins == null)
			throw new EntityNotFoundException("Cannot get the list of admins with the role: " + role);
		return null;
	}

	@Override
	public Admin getAdminByAccountId(int accountId) throws EntityNotFoundException {
		Admin admin = AdminDAO.getAdminByAccountId(accountId);
		if (admin == null)
			throw new EntityNotFoundException("Cannot find admin with accountId: " + accountId);
		return admin;
	}

	@Override
	public String getAdminRolByAccountId(int accountId) throws EntityNotFoundException {
		String role = AdminDAO.getAdminRoleByAccountId(accountId);
		if (role == null)
			throw new EntityNotFoundException("Cannot get the role of admin who has the accountId: " + accountId);
		return role;
	}

	

	
}
