package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.rideshare.model.Mileage;
import com.rideshare.model.dao.MileageDAO;

@Component
public class JDBCMileageDAO implements MileageDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMileageDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Mileage> getMileage(int profileId, Date day) {
		List<Mileage> miles = new ArrayList<Mileage>();
		String sqlGetMileage = "SELECT * FROM profile JOIN vehicle ON profile.profile_id = vehicle.profile_id "
							+ "JOIN mileage ON vehicle.vehicle_id = mileage.vehicle_id WHERE profile.profile_id = ? AND mileage.start_day = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMileage, profileId, day);
		while(results.next()) {
			Mileage mile = new Mileage();
//			mile.setMileageId(results.getInt("mileage.mileage_id"));
//			mile.setVehicleId(results.getInt("mileage.vehicle_id"));
			mile.setStartDate(results.getDate("start_day"));
//			mile.setStartTime(results.getTime("start_time"));
			mile.setEndDate(results.getDate("end_day"));
//			mile.setEndTime(results.getTime("end_time"));
			mile.setStartMileage(results.getInt("start_mileage"));
			mile.setEndMileage(results.getInt("end_mileage"));
			miles.add(mile);
		}
		return miles;
	}
	
	@Override
	public Mileage getLastMileage(int vehicleId) {
		Mileage mile = new Mileage();
		String sqlGetMileage = "SELECT start_mileage, end_mileage FROM mileage WHERE vehicle_id = ? ORDER BY mileage_id DESC LIMIT 1 ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMileage, vehicleId);
		while(results.next()) {
//			mile.setMileageId(results.getInt("mileage_id"));
//			mile.setVehicleId(results.getInt("vehicle_id"));
//			mile.setStartDate(results.getDate("start_day"));
//			mile.setStartTime(results.getTime("start_time"));
//			mile.setEndDate(results.getDate("end_day"));
//			mile.setEndTime(results.getTime("end_time"));
			mile.setStartMileage(results.getInt("start_mileage"));
			mile.setEndMileage(results.getInt("end_mileage"));
		}
		return mile;
	}

	@Override
	public void addStartMileage(Mileage miles) {
		String sqlAddMileage = "INSERT INTO mileage (vehicle_id, start_mileage, end_mileage) "
						+ "VALUES (?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddMileage, miles.getVehicleId(), miles.getStartMileage(), miles.getEndMileage());
	}

	@Override
	public void addEndMileage(Mileage miles) {
		String sqlAddMileage = "UPDATE mileage SET end_day = ?, end_time = now()::timestamp, end_mileage = ? " 
							+ "WHERE mileage_id = (select MAX(mileage_id) FROM mileage WHERE vehicle_id = ?) ";
		this.jdbcTemplate.update(sqlAddMileage, miles.getEndDate(), miles.getEndMileage(), miles.getVehicleId());
	}
	
	@Override
	public void removeMileage(long vehicleId) {
		String sql = "DELETE FROM mileage WHERE vehicle_id = ? ";
		this.jdbcTemplate.update(sql, vehicleId);
	}


}
