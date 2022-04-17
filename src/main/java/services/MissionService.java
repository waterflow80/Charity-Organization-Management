package services;

import java.util.List;

import entities.Mission;

public interface MissionService {
	public int addMission(Mission mission); // Return the missionId of the added mission
	public List<Mission> getAllMissions();
	public List<Mission> getMissionsByState(String state);
	public boolean updateMissionStateById(int missionId, String newState);
	public boolean addPhotoToMissionById(int missionId, String photoName);
	public boolean addVideoToMissionById(int missionId, String videoName);
	public boolean deleteMissionById(int missionId);
}
