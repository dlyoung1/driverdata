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

import com.rideshare.model.Expenses;
import com.rideshare.model.Maintenance;
import com.rideshare.model.MaintenanceSchedule;
import com.rideshare.model.Mileage;
import com.rideshare.model.Profile;
import com.rideshare.model.Vehicle;
import com.rideshare.model.dao.ExpensesDAO;
import com.rideshare.model.dao.MaintenanceDAO;
import com.rideshare.model.dao.MaintenanceScheduleDAO;
import com.rideshare.model.dao.MileageDAO;
import com.rideshare.model.dao.ProfileDAO;
import com.rideshare.model.dao.VehicleDAO;

@Controller
public class HomeController {
	
	@Autowired
	private ProfileDAO user;
	@Autowired
	private VehicleDAO vehicle;
	@Autowired
	private MileageDAO mileage;
	@Autowired
	private ExpensesDAO expenses;
	@Autowired
	private MaintenanceDAO maintenance;
	@Autowired
	private MaintenanceScheduleDAO maintenanceSchedule;
	
	@RequestMapping("/")
	public String getUser(ModelMap map) {
		List<Profile> profiles = user.displayAllUsers();
		map.put("profile", profiles);
		return "welcome";
	}
	
	@RequestMapping(path="/setSession", method=RequestMethod.GET)
	public String storeNameInSession(HttpSession session, @RequestParam String userName) {
		session.setAttribute("userName", userName);
		return "redirect:/switchVehicle";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(path="/home", method=RequestMethod.GET)
	public String displayProfile(ModelMap map, 
								HttpSession session) {
		String greeting;
		String userName = (String) session.getAttribute("userName");
		Profile currentUser = user.getCurrentUser(userName);
		int profileId = currentUser.getProfileId();
		Date today = Calendar.getInstance().getTime();
		if(today.getHours() > 5 && today.getHours() < 12) {
			greeting = "Good Morning";
		} else if(today.getHours() >= 12 && today.getHours() < 17) {
			greeting = "Good Afternoon";
		} else if(today.getHours() >= 17 && today.getHours() < 22) {
			greeting = "Good Evening";
		} else {
			greeting = "Hello";
		}
		
		int vehicleId = (int)session.getAttribute("vehicleId");
		Vehicle currentVehicle = vehicle.getVehicleDetails(vehicleId);
		String image = currentVehicle.getImage();
		
		
		session.setAttribute("button", "Start Trip");
		List<Mileage> miles = mileage.getMileage(profileId, today);
		long totalMiles = 0;
		if(miles.isEmpty()) {
		} else {
			for(int i = 0; i < miles.size(); i++) {
				if(miles.get(i).getEndMileage() == 0) {
					session.setAttribute("button", "End Trip");
				} else {
					totalMiles += miles.get(i).getEndMileage() - miles.get(i).getStartMileage();
				}
			}
		}
			
		List<Expenses> expense = expenses.getExpenses(today, profileId);
		BigDecimal totalExpenses = BigDecimal.ZERO;
		for(int i = 0; i < expense.size(); i++) {
			totalExpenses = totalExpenses.add(expense.get(i).getCost());
		}
		
		Mileage mile = mileage.getLastMileage(vehicleId);
		List<String> upcomingMaintenance = new ArrayList<>();
		List<Long> upcomingMiles = new ArrayList<>();
		List<MaintenanceSchedule> maintenanceScheduleList = maintenanceSchedule.getMaintenanceSchedule(vehicleId);
		for(int i = 0; i < maintenanceScheduleList.size(); i++) {
			Maintenance maintenanceList = maintenance.getLastMaintenance(vehicleId, maintenanceSchedule.getMaintenanceSchedule(vehicleId).get(i).getMaintenanceScheduleId());
			long maintenanceMiles = 0;
			if(mile.getEndMileage() == 0) {
				maintenanceMiles = (maintenanceList.getMileage() + maintenanceScheduleList.get(i).getInterval()) - mile.getStartMileage();
			} else {
				maintenanceMiles = (maintenanceList.getMileage() + maintenanceScheduleList.get(i).getInterval()) - mile.getEndMileage();
			}
			if(maintenanceMiles < 500) {
				upcomingMaintenance.add(maintenanceScheduleList.get(i).getName());
				upcomingMiles.add(maintenanceMiles);
			}
		}
		
		session.setAttribute("period", "weekly");
		session.setAttribute("profileId", profileId);
		session.setAttribute("image", image);
		map.put("upcomingMiles", upcomingMiles);
		map.put("upcomingMaintenance", upcomingMaintenance);
		map.put("totalExpenses", totalExpenses);
		map.put("miles", totalMiles);
		map.put("currentUser", currentUser);
		map.put("date", today);
		map.put("greeting", greeting);
		return "home";
	}
	
	@RequestMapping("/reports")
	public String displayReports() {
		return "reports";
	}
	
	@RequestMapping(path="/reportInput", method=RequestMethod.POST)
	public String reportInput(HttpSession session,
							@RequestParam String maintenance,
							@RequestParam String expenses,
							@RequestParam String mileage) {
		if(maintenance.equals("serviceSchedule")) {
			return "redirect:/maintenance";
		} else if(maintenance.equals("maintenanceExpenses")) {
			return "redirect:/maintenanceExpenses";
		}
		if(expenses.equals("Daily")) {
			return "redirect:/expenses";
		} else if(expenses.equals("Weekly")) {
			return "redirect:/weekly";
		} else if(expenses.equals("Monthly")) {
			return "redirect:/monthly";
		}
		if(mileage.equals("weekly")) {
			session.setAttribute("period", "weekly");
			return "redirect:/mileageReport";
		} else if(mileage.equals("monthly")) {
			session.setAttribute("period", "monthly");
			return "redirect:/mileageReport";
		} 
		return "redirect:/home";
	}
	
}
