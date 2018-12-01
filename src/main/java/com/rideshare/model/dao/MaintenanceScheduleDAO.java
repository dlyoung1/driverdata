package com.rideshare.model.dao;

import java.util.List;

import com.rideshare.model.MaintenanceSchedule;

public interface MaintenanceScheduleDAO {

	public List<MaintenanceSchedule> getMaintenanceSchedule(long vehicleId);
	public void addMaintenanceSchedule(MaintenanceSchedule maintenance);
	public void removeMaintenanceSchedule(long vehicleId);

}
