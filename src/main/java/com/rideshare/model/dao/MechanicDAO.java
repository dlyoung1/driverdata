package com.rideshare.model.dao;

import java.util.List;

import com.rideshare.model.Mechanic;

public interface MechanicDAO {

	public List<Mechanic> getMechanic();
	public void addMechanic(Mechanic mechanic);
	public void removeMechanic(int mechanic);
	
}
