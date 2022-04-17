package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mission {
	private int missionId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String priority;
	private String State;  // accomplished | unaccomplished
	private List<Member> members = new ArrayList<Member> ();
	private Family family;
	private Expense expense;
	private Address place;
	private List<Photo> photos = new ArrayList<Photo> ();
	private List<Video> videos = new ArrayList<Video> ();
	
	public Mission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mission(int missionId, LocalDate startDate, LocalDate endDate, String priority, String state,
			List<Member> members, Family family, Expense expense, Address place, List<Photo> photos,
			List<Video> videos) {
		super();
		this.missionId = missionId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		State = state;
		this.members = members;
		this.family = family;
		this.expense = expense;
		this.place = place;
		this.photos = photos;
		this.videos = videos;
	}
	
	
	/**
	 * Constructor without the missionId*/
	public Mission(LocalDate startDate, LocalDate endDate, String priority, String state, List<Member> members,
			Family family, Expense expense, Address place, List<Photo> photos,
			List<Video> videos) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		State = state;
		this.members = members;
		this.family = family;
		this.expense = expense;
		this.place = place;
		this.photos = photos;
		this.videos = videos;
	}
	public int getMissionId() {
		return missionId;
	}
	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public Address getPlace() {
		return place;
	}
	public void setPlace(Address place) {
		this.place = place;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	@Override
	public String toString() {
		return "Mission [missionId=" + missionId + ", startDate=" + startDate + ", endDate=" + endDate + ", priority="
				+ priority + ", State=" + State + ", members=" + members + ", family=" + family + ", expense=" + expense
				+ ", place=" + place + ", photos=" + photos + ", videos=" + videos + "]";
	}
	
	
	
}
