package com.rideshare.model;

public class MaintenanceSchedule {

	private int maintenanceScheduleId;
	private int vehicleId;
	private String name;
	private int interval;
	
	public int getMaintenanceScheduleId() {
		return maintenanceScheduleId;
	}
	public void setMaintenanceScheduleId(int maintenanceScheduleId) {
		this.maintenanceScheduleId = maintenanceScheduleId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
}
