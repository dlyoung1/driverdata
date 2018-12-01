package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import com.rideshare.model.Mechanic;
import com.rideshare.model.dao.MechanicDAO;

@Controller
public class JDBCMechanicDAO implements MechanicDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMechanicDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Mechanic> getMechanic() {
		List<Mechanic> mechanics = new ArrayList<>();
		String sqlGetMechanic = "SELECT * FROM mechanic ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMechanic);
		while(results.next()) {
			Mechanic mechanic = new Mechanic();
			mechanic.setMechanicId(results.getInt("mechanic_id"));
			mechanic.setName(results.getString("name"));
			mechanic.setAddress(results.getString("address"));
			mechanic.setPhone(results.getString("phone"));
			mechanics.add(mechanic);
		}
		return mechanics;
	}

	@Override
	public void addMechanic(Mechanic mechanic) {
		String sqlAddMechanic = "INSERT INTO mechanic (name, address, phone) "
							+ "VALUES (?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddMechanic, mechanic.getName(), mechanic.getAddress(), mechanic.getPhone());
		
	}

	@Override
	public void removeMechanic(int mechanicId) {
		String sql = "DELETE FROM mechanic WHERE mechanic_id = ?; ";
		this.jdbcTemplate.update(sql, mechanicId);
		
	}

}
