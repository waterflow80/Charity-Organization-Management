package services;

import java.util.List;

import entities.Admin;

public interface AdminService {
	public int addAdmin(Admin admin); // return the id of the added admin
	public Admin getAdminById(int id);
	public List<Admin> getAllAdmins();
	public List<Admin> getAdminsByRole(String role);
	public boolean deleteAdminById(int id);
}
