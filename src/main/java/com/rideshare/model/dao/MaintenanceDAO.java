package com.rideshare.model.dao;

import java.util.Date;
import java.util.List;

import com.rideshare.model.Maintenance;

public interface MaintenanceDAO {
	
	public List<Maintenance> getAllMaintenance(long vehicleId, int maintenanceScheduleId);
	public Maintenance getLastMaintenance(long vehicleId, int maintenanceScheduleId);
	public List<Maintenance> getMaintenanceByDate(long vehicleId, Date day);
	public void addMaintenance(Maintenance maintenance);
	public void removeMaintenance(long vehicleId);

}
