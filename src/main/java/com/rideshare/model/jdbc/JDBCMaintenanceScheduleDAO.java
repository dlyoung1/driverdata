package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import com.rideshare.model.MaintenanceSchedule;
import com.rideshare.model.dao.MaintenanceScheduleDAO;

@Controller
public class JDBCMaintenanceScheduleDAO implements MaintenanceScheduleDAO {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMaintenanceScheduleDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<MaintenanceSchedule> getMaintenanceSchedule(long vehicleId) {
		List<MaintenanceSchedule> maintenanceSchedule = new ArrayList<MaintenanceSchedule>();
		String sqlGetMaintenanceSchedule = "SELECT * FROM maintenance_schedule WHERE vehicle_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMaintenanceSchedule, vehicleId);
		while(results.next()) {
			MaintenanceSchedule maintenance = new MaintenanceSchedule();
			maintenance.setMaintenanceScheduleId(results.getInt("maintenance_schedule_id"));
			maintenance.setVehicleId(results.getInt("vehicle_id"));
			maintenance.setName(results.getString("name"));
			maintenance.setInterval(results.getInt("interval"));
			maintenanceSchedule.add(maintenance);
		}
		return maintenanceSchedule;
	}

	@Override
	public void addMaintenanceSchedule(MaintenanceSchedule maintenance) {
		String sqlAddMaintenanceSchedule = "INSERT INTO maintenance_schedule (vehicle_id, name, interval) "
								+ "VALUES (?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddMaintenanceSchedule, maintenance.getVehicleId(), maintenance.getName(), maintenance.getInterval());
	}
	
	@Override
	public void removeMaintenanceSchedule(long vehicleId) {
		String sql = "DELETE FROM maintenance_schedule WHERE vehicle_id = ? ";
		this.jdbcTemplate.update(sql, vehicleId);
	}

	
	
}
