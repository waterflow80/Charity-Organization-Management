package services;

import java.util.List;

import entities.Member;
import entities.Mission;
import entities.Photo;
import entities.Video;
import exceptions.AddFailureException;
import exceptions.EntityNotFoundException;
import exceptions.UpdateFailureException;

public interface MissionService {
	public int addMission(Mission mission) throws AddFailureException; // Return the missionId of the added mission
	public List<Mission> getAllMissions() throws EntityNotFoundException;
	public List<Mission> getMissionsByState(String state) throws EntityNotFoundException;
	public boolean updateMissionStateById(int missionId, String newState) throws UpdateFailureException;
	public boolean addPhotoToMissionById(Photo photo, int missionId) throws AddFailureException;
	public boolean addVideoToMissionById(Video video,int missionId) throws AddFailureException;
	public Mission getMissionById(int missionId) throws EntityNotFoundException;
	public boolean addMemberToMissionById(Member member, int missionId) throws AddFailureException;
}
