package services.implementation;

import java.util.List;

import entities.Mission;
import services.MissionService;

public class MissionServiceImpl implements MissionService {

	@Override
	public int addMission(Mission mission) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Mission> getAllMissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> getMissionsByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMissionStateById(int missionId, String newState) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPhotoToMissionById(int missionId, String photoName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addVideoToMissionById(int missionId, String videoName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMissionById(int missionId) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
