package com.rideshare.model.jdbc;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.rideshare.model.Vehicle;
import com.rideshare.model.dao.VehicleDAO;

@Controller
public class JDBCVehicleDAO implements VehicleDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCVehicleDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Vehicle> getVehicle(long profileId) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String sqlGetVehicle = "SELECT * FROM vehicle WHERE profile_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVehicle, profileId);
		while(results.next()) {
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleId(results.getInt("vehicle_id"));
			vehicle.setProfileId(results.getLong("profile_id"));
			vehicle.setMake(results.getString("make"));
			vehicle.setModel(results.getString("model"));
			vehicle.setYear(results.getInt("year"));
			vehicle.setColor(results.getString("color"));
			vehicle.setImage(results.getString("image"));
			vehicles.add(vehicle);
		}
		return vehicles;
	}
	
	@Override
	public Vehicle getVehicleDetails(long vehicleId) {
		Vehicle vehicle = new Vehicle();
		String sqlGetVehicleDetails = "SELECT * FROM vehicle WHERE vehicle_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVehicleDetails, vehicleId);
		while(results.next()) {
			vehicle.setVehicleId(results.getInt("vehicle_id"));
			vehicle.setProfileId(results.getLong("profile_id"));
			vehicle.setMake(results.getString("make"));
			vehicle.setModel(results.getString("model"));
			vehicle.setYear(results.getInt("year"));
			vehicle.setColor(results.getString("color"));
			vehicle.setImage(results.getString("image"));
		}
		return vehicle;
	}
	
	@Override
	public Vehicle getVehicleId(String image) {
		Vehicle vehicle = new Vehicle();
		String sqlGetVehicleDetails = "SELECT vehicle_id FROM vehicle WHERE image = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVehicleDetails, image);
		while(results.next()) {
			vehicle.setVehicleId(results.getInt("vehicle_id"));
		}
		return vehicle;
	}

	@Override
	public void addVehicle(Vehicle vehicle) {
		String sqlAddVehicle = "INSERT INTO vehicle (vehicle_id, profile_id, make, model, year, color, image) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?) ";
		vehicle.setVehicleId(getNextVehicleId());
		this.jdbcTemplate.update(sqlAddVehicle, vehicle.getVehicleId(), vehicle.getProfileId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getImage());
	}
	
	@Override
	public void removeVehicle(long vehicleId) {
		String sql = "DELETE FROM vehicle WHERE vehicle_id = ?; ";
		this.jdbcTemplate.update(sql, vehicleId);
	}
	
	@Override
	public void modifyVehicle(Vehicle vehicle, long vehicleId) {
		String sql = "UPDATE vehicle SET make = ?, model = ?, year = ?, color = ?, image = ? "
					+ "WHERE vehicle_id = ? ";
		this.jdbcTemplate.update(sql, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getImage(), vehicleId);
	}
	
	private int getNextVehicleId() {
		int val = 0;
		String sql = "SELECT nextval('vehicle_vehicle_id_seq') ";
		SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql);
		if(result.next()) {
			val = result.getInt(1);
		}
		return val;
	}

}
