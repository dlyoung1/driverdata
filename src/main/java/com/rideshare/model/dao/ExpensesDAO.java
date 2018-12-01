package com.rideshare.model.dao;

import java.util.Date;
import java.util.List;

import com.rideshare.model.Expenses;

public interface ExpensesDAO {

	public List<Expenses> getExpenses(Date day, int profileId);
	public void addExpense(Expenses expense);
	public void removeExpense(long expenseId);
	public void removeAllExpenses(long profileId);
	
}
