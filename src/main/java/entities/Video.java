package entities;

public class Video {
	private int videoId;
	private String videoPath; // The path of the video
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(int videoId, String videoPath) {
		super();
		this.videoId = videoId;
		this.videoPath = videoPath;
	}
	
	
	
	public Video(String videoPath) {
		super();
		this.videoPath = videoPath;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	@Override
	public String toString() {
		return "MissionVideo [videoId=" + videoId + ", videoPath=" + videoPath + "]";
	}
	
	
	
	
	
}
