package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import com.rideshare.model.Expenses;
import com.rideshare.model.dao.ExpensesDAO;

@Controller
public class JDBCExpensesDAO implements ExpensesDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCExpensesDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Expenses> getExpenses(Date day, int profileId) {
		List<Expenses> expenses = new ArrayList<Expenses>();
		String sqlGetExpenses = "SELECT * FROM expenses WHERE day >= ? AND profile_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetExpenses, day, profileId);
		while(results.next()) {
			Expenses expense = new Expenses();
			expense.setExpenseId(results.getLong("expense_id"));
			expense.setProfileId(results.getInt("profile_id"));
			expense.setName(results.getString("name"));
			expense.setCategory(results.getString("category"));
			expense.setCost(results.getBigDecimal("cost"));
			expense.setDay(results.getDate("day"));
			expenses.add(expense);
		}
		return expenses;
	}

	@Override
	public void addExpense(Expenses expense) {
		String sqlAddExpense = "INSERT INTO expenses (profile_id, name, category, cost, day) "
							+ "VALUES (?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddExpense, expense.getProfileId(), expense.getName(), expense.getCategory(), expense.getCost(), expense.getDay());
	}

	
	@Override
	public void removeExpense(long expenseId) {
		String sql = "DELETE FROM expenses WHERE expense_id = ? ";
		this.jdbcTemplate.update(sql, expenseId);
	}
	
	@Override
	public void removeAllExpenses(long profileId) {
		String sql = "DELETE FROM expenses WHERE profile_id = ? ";
		this.jdbcTemplate.update(sql, profileId);
	}
	
}
