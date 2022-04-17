package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Account;
import entities.Address;
import entities.Admin;
import entities.Donation;
import entities.Donor;
import entities.Expense;
import entities.Family;
import entities.FamilyMember;
import entities.Member;
import entities.Mission;
import entities.Photo;
import entities.Video;

public class Test {

	public static void main(String[] args) {
		
		/*
		 * FamilyMember member = new FamilyMember(1, "kali", "sam", 54, "games",
		 * "25414523", "14521465", 5521F, true); FamilyMember member2 = new
		 * FamilyMember(1, "ubuntu", "jam", 54, "science", "25414523", "14521465", 45F,
		 * false); List<FamilyMember> members = Arrays.asList(member, member2); Address
		 * address = new Address("italy", "verona", "bourg la reine", "1425");
		 * 
		 * int id = FamilyDAO.addFamily(members, address);
		 */
		 
		
		/*
		 * List<FamilyMember> members = FamilyDAO.getAllFamilyMembersByFamilyId(3);
		 * 
		 * members.forEach(System.out::println);
		 */
		
		/*
		 * int id = FamilyDAO.deleteFamilyMemberById(10); System.out.println(id);
		 */
		Address address = new Address("egypt", "cairo", "maadi", "2544");

		
		/*
		 * Member m = new Member("jack", "james", "volunteer", "02145685", "45874685",
		 * address, "/location/image.jpg"); int id = MemberDAO.addMember(m);
		 */
		/*
		 * Member m = MemberDAO.getMemberById(2); List<Member> members =
		 * MemberDAO.getAllMembers(); members.forEach(System.out::println);
		 * 
		 * List<Admin> ads = AdminDAO.getAllAdmins(); ads.forEach(System.out::println);
		 * 
		 * List<Family> fls = FamilyDAO.getAllFamilies();
		 * fls.forEach(System.out::println);
		 */
		
		/*
		 * List<Member> members = MemberDAO.getAllMembers();
		 * members.forEach(System.out::println);
		 */
		/*
		 * Admin admin = new Admin("gold", "bold", "14587452", "22541547", "secretary",
		 * new Address("south africa", "kouk", "bridge", "3652")); Account account = new
		 * Account("test@gmail.com", "Pass@145@", admin); int accId =
		 * AccountDAO.addAccount(account); System.out.println(accId);
		 */
		
		//System.out.println(AccountDAO.deleteAccountById(2));
		
		//System.out.println(ExpenseDAO.addExpense(new Expense(658.15F), missionId));
		/*
		 * Member m = MemberDAO.getMemberById(1); m.setProfilePhoto(new
		 * Photo("/var/images/photo.jpg"));
		 * System.out.println(MediaDAO.addMemberPhoto(m.getProfilePhoto(),
		 * m.getMemberId())); Photo p = MediaDAO.getMemberPhotoByMemberId(1);
		 * System.out.println(p);
		 */
		
		/*
		 * Photo p1 = new Photo("/target/image1.jpg"); Photo p2 = new
		 * Photo("/target/image2.jpg"); List<Photo> photos = Arrays.asList(p1,p2); Video
		 * v1 = new Video("/target/video1.mp4"); Video v2 = new
		 * Video("/target/video2.mp4"); List<Video> videos = Arrays.asList(v1,v2);
		 * Family family = FamilyDAO.getFamilyById(5); List<Member> members =
		 * MemberDAO.getAllMembers(); Mission mission = new Mission(LocalDate.of(2022,
		 * 7, 1), LocalDate.of(2022, 7, 4), "high", "accomplished", members, family, new
		 * Expense(99.9F), address, photos, videos); int mId =
		 * MissionDAO.addMission(mission); System.out.println(mId);
		 */
		 
		
		/*
		 * Mission mission = MissionDAO.getMissionById(6); System.out.println(mission);
		 */
		
		/*
		 * Address address1 = new Address("italy", "verona", "bourg la reine", "1425");
		 * int adI = AddressDAO.addAddress(address1); System.out.println(adI);
		 */
		//System.out.println(AddressDAO.getAddressById(17));
	
		/*
		 * List<Mission> missions = MissionDAO.getMissionsByState("unaccomplished");
		 * missions.forEach(System.out::println);
		 */
		//System.out.println(MissionDAO.updateMissionStateById(2, "unaccomplished"));
		/*
		 * int pId = MissionDAO.addPhotoToMissionById(new Photo("/target/image3.png"),
		 * 7); System.out.println(pId);
		 */
		/*
		 * List<Photo> photos = MediaDAO.getAllMissionPhotosByMissionId(7);
		 * photos.forEach(System.out::println);
		 */
		/*
		 * int vId = MissionDAO.addVideoToMissionById(new
		 * Video("/home/videos/movie.mp4"), 6); System.out.println(vId);
		 */
		
		/*
		 * List<Video> videos = MediaDAO.getAllMissionVideosByMissionId(6);
		 * videos.forEach(System.out::println);
		 */
		 
		
		/*
		 * Member member = MemberDAO.getMemberById(1);
		 * 
		 * System.out.println(MissionDAO.addMemberToMissionById(member, 5));
		 */
		
		/*
		 * Donor donor = new Donor("sylvester", "stalone", "06546587", "Actor");
		 * Donation donation = new Donation(75846.5F, LocalDate.of(1999, 6, 15), donor);
		 * System.out.println(DonationDAO.addDonation(donation));
		 */
		
		/*
		 * Donor donor = DonationDAO.getDonorByid(2); System.out.println(donor);
		 */
		/*
		 * List<Donor> donors = DonationDAO.getAllDonors(); System.out.println(donors);
		 */
		/*
		 * Donation donation = DonationDAO.getDonationById(1);
		 * System.out.println(donation);
		 */
		/*
		 * List<Donation> donations = DonationDAO.getAllDonations();
		 * donations.forEach(System.out::println);
		 */
		System.out.println(DonationDAO.deleteDonationById(2));
	}
	
	

}
