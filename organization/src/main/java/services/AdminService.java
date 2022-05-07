package services;

import java.util.List;

import entities.Admin;
import exceptions.AddFailureException;
import exceptions.EntityNotFoundException;

public interface AdminService {
	public int addAdmin(Admin admin, int accountId) throws AddFailureException; // return the id of the added admin
	public Admin getAdminById(int id) throws EntityNotFoundException;
	public List<Admin> getAllAdmins() throws EntityNotFoundException;
	public List<Admin> getAdminsByRole(String role) throws EntityNotFoundException;
	public Admin getAdminByAccountId(int accountId) throws EntityNotFoundException;
	public String getAdminRolByAccountId(int accountId) throws EntityNotFoundException;
}
