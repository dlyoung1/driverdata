package com.rideshare.model.dao;

import java.util.Date;
import java.util.List;

import com.rideshare.model.Mileage;

public interface MileageDAO {
	
	public List<Mileage> getMileage(int profileId, Date day);
	public Mileage getLastMileage(int vehicleId);
	public void addStartMileage(Mileage miles);
	public void addEndMileage(Mileage miles);
	public void removeMileage(long vehicleId);

}
