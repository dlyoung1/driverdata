package com.rideshare.model.dao;

import java.util.List;

import com.rideshare.model.Vehicle;


public interface VehicleDAO {

	public List<Vehicle> getVehicle(long profileId);
	public Vehicle getVehicleDetails(long vehicleId);
	public Vehicle getVehicleId(String image);
	public void addVehicle(Vehicle vehicle);
	public void removeVehicle(long vehicleId);
	public void modifyVehicle(Vehicle vehicle, long vehicleId);
}
