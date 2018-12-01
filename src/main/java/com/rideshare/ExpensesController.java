package com.rideshare;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rideshare.model.Expenses;
import com.rideshare.model.dao.ExpensesDAO;

@Controller
public class ExpensesController {

	@Autowired
	private ExpensesDAO expense;
	
	@RequestMapping(path="/expenses", method=RequestMethod.GET)
	public String displayExpenses(HttpSession session,
								ModelMap map) {
		List<Expenses> expenseList = expense.getExpenses(Calendar.getInstance().getTime(), (int)session.getAttribute("profileId"));
		map.addAttribute("expenses", expenseList);
		map.addAttribute("period", "Daily");
		map.addAttribute("period2", "Weekly");
		map.addAttribute("period2link", "/weekly");
		map.addAttribute("period3", "Monthly");
		map.addAttribute("period3link", "/monthly");
		return "expenses";
	}
	
	@RequestMapping(path="/weekly", method=RequestMethod.GET)
	public String displayWeeklyExpenses(HttpSession session,
								ModelMap map) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DAY_OF_WEEK, -(day.get(Calendar.DAY_OF_WEEK)-1));
		List<Expenses> expenseList = expense.getExpenses(day.getTime(), (int)session.getAttribute("profileId"));
		map.addAttribute("expenses", expenseList);
		map.addAttribute("period", "Weekly");
		map.addAttribute("period2", "Daily");
		map.addAttribute("period2link", "/expenses");
		map.addAttribute("period3", "Monthly");
		map.addAttribute("period3link", "/monthly");
		return "expenses";
	}
	
	@RequestMapping(path="/monthly", method=RequestMethod.GET)
	public String displayMonthlyExpenses(HttpSession session,
								ModelMap map) {
		Calendar day = Calendar.getInstance();
		day.set(Calendar.DAY_OF_MONTH, 1);
		List<Expenses> expenseList = expense.getExpenses(day.getTime(), (int)session.getAttribute("profileId"));
		map.addAttribute("expenses", expenseList);
		map.addAttribute("period", "Monthly");
		map.addAttribute("period2", "Daily");
		map.addAttribute("period2link", "/expenses");
		map.addAttribute("period3", "Weekly");
		map.addAttribute("period3link", "/weekly");
		return "expenses";
	}
	
	@RequestMapping("/expense")
	public String displayExpense() {
		return "expense";
	}
	
	@RequestMapping(path="/addExpense", method=RequestMethod.POST)
	private String addExpense(@RequestParam String name,
							@RequestParam String category,
							@RequestParam String cost,
							RedirectAttributes redirAttribs,
							HttpSession session) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(name.isEmpty() || category.isEmpty() || cost.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(name, validChars)) {
				isValid = false;
				errorMessages.add("Item Description contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(category, validChars)) {
				isValid = false;
				errorMessages.add("The Category contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			} 
			validChars = "1234567890\\.";
			if(!usesValidChars(cost, validChars)) {
				isValid = false;
				errorMessages.add("The Cost contains invalid characters. Must be in a '##.##' format - the decimal is optional.");
			} 
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("name", name);
			redirAttribs.addFlashAttribute("category", category);
			redirAttribs.addFlashAttribute("cost", cost);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/expense";
		}
		
		int profileId = (int) session.getAttribute("profileId");
		Expenses newExpense = new Expenses();
		newExpense.setProfileId(profileId);
		newExpense.setName(name);
		newExpense.setCategory(category);
		newExpense.setCost(new BigDecimal(cost));
		Date today = Calendar.getInstance().getTime();
		newExpense.setDay(today);
		expense.addExpense(newExpense);
		return "redirect:/home";
	}
	
	private boolean usesValidChars(String value, String validChars) {
		boolean hasInvalidChars = false;
		for(int i = 0; i < value.length() && !hasInvalidChars; i++) {
			if(validChars.indexOf(value.substring(i, i + 1)) < 0) {
				hasInvalidChars = true;
			}
		}
		return !hasInvalidChars;
	}
	
	@RequestMapping("/deleteExpense")
	public String deleteExpense(ModelMap map,
								HttpSession session) {
		Calendar day = Calendar.getInstance();
		day.set(Calendar.DAY_OF_MONTH, 1);
		List<Expenses> expenseList = expense.getExpenses(day.getTime(), (int)session.getAttribute("profileId"));
		map.put("expenses", expenseList);
		return "deleteExpense";
	}
	
	@RequestMapping(path="/deleteExpenseInput", method=RequestMethod.POST)
	public String deleteExpenseInput(@RequestParam String expenseId) {
		expense.removeExpense(Integer.valueOf(expenseId));
		return "redirect:/home";
	}
	
	@RequestMapping("/deleteUserExpenses")
	public String deleteUserExpenses(HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		expense.removeAllExpenses(profileId);
		return "redirect:deleteUser";
	}
	
}
