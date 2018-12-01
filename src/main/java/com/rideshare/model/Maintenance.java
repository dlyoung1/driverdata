package com.rideshare.model;

import java.math.BigDecimal;
import java.util.Date;

public class Maintenance {
	
	private long maintenanceId;
	private int vehicleId;
	private int mechanicId;
	private int maintenanceScheduleId;
	private String name;
	private long mileage;
	private BigDecimal cost;
	private Date day;
	
	// getters and setters
	public long getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getMechanicId() {
		return mechanicId;
	}
	public void setMechanicId(int mechanicId) {
		this.mechanicId = mechanicId;
	}
	public int getMaintenanceScheduleId() {
		return maintenanceScheduleId;
	}
	public void setMaintenanceScheduleId(int maintenanceScheduleId) {
		this.maintenanceScheduleId = maintenanceScheduleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMileage() {
		return mileage;
	}
	public void setMileage(long mileage) {
		this.mileage = mileage;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}

}
