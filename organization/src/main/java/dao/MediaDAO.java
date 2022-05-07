package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Photo;
import entities.Video;
import utils.DBConnect;

/**
 * Handles the media (photos and videos) data from and to the database. */
public class MediaDAO {
	private static Connection connection = DBConnect.getInstance();
	
	/**
	 * Save the member's photo to the table memberPhoto in the database. Return the generated key | 0 when fails*/
	public static int addMemberPhoto(Photo photo, int memberId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return 0; // Failed to get a connection instance
		}
		
		// Query
		String query = "INSERT INTO memberPhoto(photoPath, memberId) VALUES(?, ?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, photo.getPhotoPath());
			ps.setInt(2, memberId);
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
			// No key was selected
			return 0;
		} catch (Exception e) {
			// Failed to save the member's photo
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Return the photo associated with the given memberId | null when fails*/
	public static Photo getMemberPhotoByMemberId(int memberId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return null; // Failed to get a connection instance
		}
		
		//Query
		String query = "SELECT * FROM memberPhoto WHERE memberId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Photo(rs.getInt("photoId"), rs.getString("photoPath"));	
			}
		} catch (Exception e) {
			// Failed to fetch the memberPhoto
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Save the the photo to the table missionPhotos in the database. Return the generated key | 0 when fails*/
	public static int addMissionPhoto(Photo photo, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return 0; // Failed to get a connection instance
		}
		
		//Query 
		String query = "INSERT INTO missionPhotos(photoPath, missionId) VALUES(?, ?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, photo.getPhotoPath());
			ps.setInt(2, missionId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			// No key was returned
			return 0;
		} catch (Exception e) {
			// Failed to save the mission's photo
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Save the list of all the mission photos to the database. Return true : success | false: Fails*/
	public static void addMisisonPhotos(List<Photo> photos, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return;
		}
		
		for (Photo p: photos) 
			addMissionPhoto(p, missionId);
	}
	
	/**
	 * Return a list of all photos of associated with the given missionId*/
	public static List<Photo> getAllMissionPhotosByMissionId(int missionId){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return null; // Failed to get a connection instance
		}
		
		// Query
		String query = "SELECT * FROM missionPhotos WHERE missionId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			List<Photo> photos = new ArrayList<>();
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(rs.getInt("photoId"));
				photo.setPhotoPath(rs.getString("photoPath"));
				photos.add(photo);
			}
			return photos;
		} catch (Exception e) {
			// Failed to fetch the mission photos
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Save the video of the mission in the table missionVideos in the database.
	 * Return the generated key | 0 when fails*/
	public static int addMissionVideo(Video video, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return 0; // Failed to get a connection instance
		}
		// Query
		String query = "INSERT INTO missionVideos(videoPath, missionId) VALUES (?,?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, video.getVideoPath());
			ps.setInt(2, missionId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			// No key was returned
			return 0;
		} catch (Exception e) {
			// Failed to insert the video
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Save all given videos to the table missionVideos in the database.*/
	public static void addMissionVideos(List<Video> videos, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return;
		}
		for (Video v : videos)
			addMissionVideo(v, missionId);
	}
	
	/**
	 * Return a list of all mission videos associated with the given missionId.*/
	public static List<Video> getAllMissionVideosByMissionId(int missionId){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MediaDAO !");
			return null; // Failed to get a connection instance
		}
		//Query
		String query = "SELECT * FROM missionVideos WHERE missionId = ?";
		List<Video> videos = new ArrayList<>();
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setVideoId(rs.getInt("videoId"));
				video.setVideoPath(rs.getString("videoPath"));
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
			// Cannot fetch mission videos
			e.printStackTrace();
			return null;
		}
	}
}
