package com.rideshare.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Mileage {
	
	private int mileageId;
	private int vehicleId;
	private Date startDate;
	private LocalTime startTime;
	private Date endDate;
	private LocalTime endTime;
	private int startMileage;
	private int endMileage;
	
	//getters and setters
	public int getMileageId() {
		return mileageId;
	}
	public void setMileageId(int mileageId) {
		this.mileageId = mileageId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public int getStartMileage() {
		return startMileage;
	}
	public void setStartMileage(int startMileage) {
		this.startMileage = startMileage;
	}
	public int getEndMileage() {
		return endMileage;
	}
	public void setEndMileage(int endMileage) {
		this.endMileage = endMileage;
	}
	
	public int getMiles(List<Mileage> mileage) {
		int miles = 0;
		for(int i = 0; i < mileage.size(); i++) {
			if(mileage.get(i).getEndMileage() == 0) {	
			} else {
				miles += mileage.get(i).getEndMileage() - mileage.get(i).getStartMileage();
			}
		}
		return miles;
	}
	
	
	
	


}
