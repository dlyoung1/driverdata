package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import com.rideshare.model.Maintenance;
import com.rideshare.model.dao.MaintenanceDAO;

@Controller
public class JDBCMaintenanceDAO implements MaintenanceDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMaintenanceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Maintenance> getAllMaintenance(long vehicleId, int maintenanceScheduleId) {
		List<Maintenance> maintenance = new ArrayList<Maintenance>();
		String sqlGetMaintenance = "SELECT * FROM maintenance WHERE vehicle_id = ? AND maintenance_schedule_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMaintenance, vehicleId, maintenanceScheduleId);
		while(results.next()) {
			Maintenance maintenanceItem = new Maintenance();
			maintenanceItem.setMaintenanceId(results.getLong("maintenance_id"));
			maintenanceItem.setVehicleId(results.getInt("vehicle_id"));
			maintenanceItem.setMechanicId(results.getInt("mechanic_id"));
			maintenanceItem.setMaintenanceScheduleId(results.getInt("maintenance_schedule_id"));
			maintenanceItem.setName(results.getString("name"));
			maintenanceItem.setMileage(results.getLong("mileage"));
			maintenanceItem.setCost(results.getBigDecimal("cost"));
			maintenanceItem.setDay(results.getDate("day"));
			maintenance.add(maintenanceItem);
		}
		return maintenance;
	}
	
	@Override
	public Maintenance getLastMaintenance(long vehicleId, int maintenanceScheduleId) {
		Maintenance maintenance = new Maintenance();
		String sqlGetMaintenance = "SELECT * FROM maintenance WHERE vehicle_id = ? AND maintenance_schedule_id = ? " 
								+ "ORDER BY maintenance_id DESC LIMIT '1' ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMaintenance, vehicleId, maintenanceScheduleId);
		while(results.next()) {
			maintenance.setMaintenanceId(results.getLong("maintenance_id"));
			maintenance.setVehicleId(results.getInt("vehicle_id"));
			maintenance.setMechanicId(results.getInt("mechanic_id"));
			maintenance.setMaintenanceScheduleId(results.getInt("maintenance_schedule_id"));
			maintenance.setName(results.getString("name"));
			maintenance.setMileage(results.getLong("mileage"));
			maintenance.setCost(results.getBigDecimal("cost"));
			maintenance.setDay(results.getDate("day"));
		}
		return maintenance;
	}
	
	@Override
	public List<Maintenance> getMaintenanceByDate(long vehicleId, Date day) {
		List<Maintenance> maintenance = new ArrayList<Maintenance>();
		String sqlGetMaintenance = "SELECT * FROM maintenance WHERE vehicle_id = ? AND day = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMaintenance, vehicleId, day);
		while(results.next()) {
			Maintenance maintenanceItem = new Maintenance();
			maintenanceItem.setMaintenanceId(results.getLong("maintenance_id"));
			maintenanceItem.setVehicleId(results.getInt("vehicle_id"));
			maintenanceItem.setMechanicId(results.getInt("mechanic_id"));
			maintenanceItem.setMaintenanceScheduleId(results.getInt("maintenance_schedule_id"));
			maintenanceItem.setName(results.getString("name"));
			maintenanceItem.setMileage(results.getLong("mileage"));
			maintenanceItem.setCost(results.getBigDecimal("cost"));
			maintenanceItem.setDay(results.getDate("day"));
			maintenance.add(maintenanceItem);
		}
		return maintenance;
	}

	@Override
	public void addMaintenance(Maintenance maintenance) {
		String sqlAddMaintenance = "INSERT INTO maintenance (maintenance_schedule_id, vehicle_id, mechanic_id, name, mileage, cost) "
								+ "VALUES (?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddMaintenance, maintenance.getMaintenanceScheduleId(), maintenance.getVehicleId(), maintenance.getMechanicId(),
								maintenance.getName(), maintenance.getMileage(), maintenance.getCost());
	}

	
	@Override
	public void removeMaintenance(long vehicleId) {
		String sql = "DELETE FROM maintenance WHERE vehicle_id = ? ";
		this.jdbcTemplate.update(sql, vehicleId);
	}

}
