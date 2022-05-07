package services.implementation;

import java.util.List;

import dao.MissionDAO;
import entities.Member;
import entities.Mission;
import entities.Photo;
import entities.Video;
import exceptions.AddFailureException;
import exceptions.EntityNotFoundException;
import exceptions.UpdateFailureException;
import services.MissionService;

public class MissionServiceImpl implements MissionService {

	@Override
	public int addMission(Mission mission) throws AddFailureException {
		int missionId = MissionDAO.addMission(mission);
		if (missionId == 0)
			throw new AddFailureException("Cannot add mission !");
		return missionId;
	}

	@Override
	public List<Mission> getAllMissions() throws EntityNotFoundException {
		List<Mission> missions = MissionDAO.getAllMissions();
		if (missions == null)
			throw new EntityNotFoundException("Cannot get the list of all missions !");
		return missions;
	}

	@Override
	public List<Mission> getMissionsByState(String state) throws EntityNotFoundException {
		List<Mission> missions = MissionDAO.getMissionsByState(state);
		if (missions == null)
			throw new EntityNotFoundException("Cannot get the list of missions with state: " + state);
		return missions;
	}

	@Override
	public boolean updateMissionStateById(int missionId, String newState) throws UpdateFailureException {
		boolean stateUpdated = MissionDAO.updateMissionStateById(missionId, newState);
		if (!stateUpdated)
			throw new UpdateFailureException("Failed to update the state for missionId: " + missionId + " to state: " + newState);
		return true;
	}

	@Override
	public boolean addPhotoToMissionById(Photo photo, int missionId) throws AddFailureException {
		int photoId = MissionDAO.addPhotoToMissionById(photo, missionId);
		if (photoId == 0)
			throw new AddFailureException("Failed to add the photo: " + photo.getPhotoPath()
			+ " to missionId: " + missionId);
		return true;
	}

	@Override
	public boolean addVideoToMissionById(Video video,int missionId) throws AddFailureException {
		int videoId = MissionDAO.addVideoToMissionById(video, missionId);
		if (videoId == 0)
			throw new AddFailureException("Cannot add video: " + video.getVideoPath() + "to missionId: " + missionId);
		return false;
	}

	@Override
	public Mission getMissionById(int missionId) throws EntityNotFoundException {
		Mission mission = MissionDAO.getMissionById(missionId);
		if (mission == null)
			throw new EntityNotFoundException("Cannot get mission with id: " + missionId);
		return mission;
	}

	@Override
	public boolean addMemberToMissionById(Member member, int missionId) throws AddFailureException {
		boolean memberAdded = MissionDAO.addMemberToMissionById(member, missionId);
		if (!memberAdded)
			throw new AddFailureException("Cannot add member: " + member.getFirstName() + " " + member.getLastName() + 
					" to mission with id: " + missionId);
		return true;
	}


	
	
}
