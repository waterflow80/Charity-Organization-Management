package entities;

public class Photo {
	private int photoId;
	private String photoPath;
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Photo(int photoId, String photoPath) {
		super();
		this.photoId = photoId;
		this.photoPath = photoPath;
	}
	/**
	 * Constructor without photoId*/
	public Photo(String photoPath) {
		super();
		this.photoPath = photoPath;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	@Override
	public String toString() {
		return "MissionPhoto [photoId=" + photoId + ", photoPath=" + photoPath + "]";
	}
	
	
	
	
}
