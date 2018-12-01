package com.rideshare;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rideshare.model.Maintenance;
import com.rideshare.model.MaintenanceSchedule;
import com.rideshare.model.Mechanic;
import com.rideshare.model.Vehicle;
import com.rideshare.model.dao.MaintenanceDAO;
import com.rideshare.model.dao.MaintenanceScheduleDAO;
import com.rideshare.model.dao.MechanicDAO;
import com.rideshare.model.dao.MileageDAO;
import com.rideshare.model.dao.VehicleDAO;

@Controller
public class MaintenanceController {
	
	@Autowired
	private MaintenanceDAO maintenance;
	@Autowired
	private MaintenanceScheduleDAO maintenanceSchedule;
	@Autowired
	private MechanicDAO mechanic;
	@Autowired
	private MileageDAO mileage;
	@Autowired
	private VehicleDAO vehicle;

//	MAINTENANCE SCHEDULE
	@RequestMapping("/maintenance")
	public String displayMaintenance(HttpSession session,
									ModelMap map) {
		int vehicleId = (int) session.getAttribute("vehicleId");
		List<MaintenanceSchedule> schedule = maintenanceSchedule.getMaintenanceSchedule(vehicleId);
		Map<Integer, List<String>> serviceItems = new HashMap<Integer, List<String>>();
		for(int i = 0; i < schedule.size(); i++) {
			List<String> serviceItem = new ArrayList<>();
			int maintenanceScheduleId = schedule.get(i).getMaintenanceScheduleId();
			String maintenanceScheduleName = schedule.get(i).getName();
			int maintenanceScheduleInterval = schedule.get(i).getInterval();
			serviceItem.add(maintenanceScheduleName);
			serviceItem.add(Integer.toString(maintenanceScheduleInterval));
			Maintenance serviceMileage = maintenance.getLastMaintenance(vehicleId, maintenanceScheduleId);
			int mileageService = (int)serviceMileage.getMileage();
			serviceItem.add(Integer.toString(mileageService));
			int currentMileage = mileage.getLastMileage(vehicleId).getEndMileage();
			if(currentMileage == 0) {
				currentMileage = mileage.getLastMileage(vehicleId).getStartMileage();
			}
			int mileageRemaining = (mileageService + maintenanceScheduleInterval) - currentMileage;
			serviceItem.add(Integer.toString(mileageRemaining));
			serviceItems.put(i, serviceItem);
		}
		map.put("serviceItems", serviceItems);
		return "maintenance";
	}
	
	@RequestMapping(path="/addMaintenance", method=RequestMethod.POST)
	public String addMaintenance(@RequestParam String name, @RequestParam String interval, HttpSession session, RedirectAttributes redirAttribs) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(name.isEmpty() || interval.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(name, validChars)) {
				isValid = false;
				errorMessages.add("The Service Type contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			validChars = "1234567890";
			if(!usesValidChars(interval, validChars)) {
				isValid = false;
				errorMessages.add("The Frequency contains invalid characters. Only numbers (miles until next service) may be entered.");
			} 
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("name", name);
			redirAttribs.addFlashAttribute("interval", interval);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/maintenance";
		}
		
		MaintenanceSchedule maintenanceType = new MaintenanceSchedule();
		maintenanceType.setVehicleId((int)session.getAttribute("vehicleId"));
		maintenanceType.setName(name);
		maintenanceType.setInterval(Integer.valueOf(interval));
		maintenanceSchedule.addMaintenanceSchedule(maintenanceType);
		return "redirect:/maintenance";
	}
	
	@RequestMapping(path="/deleteMaintenance", method=RequestMethod.GET)
	public String deleteMaintenance(Model model, @RequestParam String vehicleId) {
		model.addAttribute("vehicleId", vehicleId);
		maintenanceSchedule.removeMaintenanceSchedule(Integer.valueOf(vehicleId));
		return "redirect:deleteVehicleInput";
	}

	@RequestMapping("/deleteUserMaintenance")
	public String deleteUserMaintenance(HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		List <Vehicle> vehicles = vehicle.getVehicle(profileId);
		for(int i = 0; i < vehicles.size(); i++) {
			int vehicleId = (int)vehicles.get(i).getVehicleId();
			maintenanceSchedule.removeMaintenanceSchedule(vehicleId);
		}
		return "redirect:deleteUserVehicleInput";
	}
	
//	MECHANICS
	@RequestMapping("/mechanic")
	public String displayMechanics(ModelMap map) {
		List<Mechanic> mechanics = mechanic.getMechanic();
		map.put("mechanics",  mechanics);
		return "mechanic";
	}
	
	@RequestMapping(path="/addMechanic", method=RequestMethod.POST)
	public String addMechanic(@RequestParam String name,
							@RequestParam String address,
							@RequestParam String phone) {
		Mechanic newMechanic = new Mechanic();
		newMechanic.setName(name);
		newMechanic.setAddress(address);
		newMechanic.setPhone(phone);
		mechanic.addMechanic(newMechanic);
		return "redirect:maintenanceService";				
	}
	
	@RequestMapping(path="/deleteMechanic", method=RequestMethod.POST)
	public String deleteMechanic(@RequestParam String mechanicId) {
		mechanic.removeMechanic(Integer.valueOf(mechanicId));
		return "redirect:mechanic";
	}
	
//	MAINTENANCE ITEMS
	@RequestMapping("/maintenanceService")
	public String displayMaintenanceService(HttpSession session,
											ModelMap map) {
		int vehicleId = (int) session.getAttribute("vehicleId");
		List<MaintenanceSchedule> maintenanceScheduleList = maintenanceSchedule.getMaintenanceSchedule(vehicleId);
		List<Mechanic> mechanics = mechanic.getMechanic();
		
		map.put("maintenanceSchedule", maintenanceScheduleList);
		map.put("mechanic", mechanics);
		return "maintenanceService";
	}
	
	@RequestMapping(path="/maintenanceInput", method=RequestMethod.POST)
	public String submitMaintenanceInput(@RequestParam String category,
										@RequestParam String maintMileage,
										@RequestParam String cost,
										@RequestParam String location,
										@RequestParam String description,
										RedirectAttributes redirAttribs,
										HttpSession session) {
		int vehicleId = (int) session.getAttribute("vehicleId");
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(category.isEmpty() || maintMileage.isEmpty() || cost.isEmpty() || location.isEmpty() || description.isEmpty() || maintMileage.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(category, validChars)) {
				isValid = false;
				errorMessages.add("The Category contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(location, validChars)) {
				isValid = false;
				errorMessages.add("The Location contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(description, validChars)) {
				isValid = false;
				errorMessages.add("The Description contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			} 
			validChars = "1234567890\\.";
			if(!usesValidChars(cost, validChars)) {
				isValid = false;
				errorMessages.add("The Cost contains invalid characters. Must be in a '##.##' format - the decimal is optional.");
			}
			validChars = "1234567890";
			if(!usesValidChars(maintMileage, validChars)) {
				isValid = false;
				errorMessages.add("The Mileage contains invalid characters. Only numbers may be entered.");
			} 
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("category", category);
			redirAttribs.addFlashAttribute("maintMileage", maintMileage);
			redirAttribs.addFlashAttribute("cost", cost);
			redirAttribs.addFlashAttribute("location", location);
			redirAttribs.addFlashAttribute("description", description);
			redirAttribs.addFlashAttribute("maintMileage", maintMileage);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/maintenanceService";
		}
		
		
		Maintenance newMaintenance = new Maintenance();
		newMaintenance.setMaintenanceScheduleId(9);
		newMaintenance.setCost(new BigDecimal(cost));
		newMaintenance.setMechanicId(4);
		newMaintenance.setName(description);
		newMaintenance.setVehicleId(vehicleId);
		newMaintenance.setMileage(Integer.valueOf(maintMileage));
		maintenance.addMaintenance(newMaintenance);
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
	
	@RequestMapping("/deleteMaintenanceService")
	public String deleteMaintenanceService(@RequestParam String vehicleId, Model model) {
		model.addAttribute("vehicleId", vehicleId);
		maintenance.removeMaintenance(Integer.valueOf(vehicleId));
		return "redirect:deleteMaintenance";
	}
	
	@RequestMapping("/deleteUserMaintenanceService")
	public String deleteUserMaintenanceService(HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		List <Vehicle> vehicles = vehicle.getVehicle(profileId);
		for(int i = 0; i < vehicles.size(); i++) {
			int vehicleId = (int)vehicles.get(i).getVehicleId();
			maintenance.removeMaintenance(vehicleId);
		}
		return "redirect:deleteUserMaintenance";
	}
	
	@RequestMapping("/maintenanceExpenses")
	public String displayMaintenanceExpenses(HttpSession session, ModelMap map) {
		int vehicleId = (int)session.getAttribute("vehicleId");
		Calendar cal = Calendar.getInstance();
		Date day = cal.getTime();
		List<Maintenance> expenses = maintenance.getMaintenanceByDate(vehicleId, day);
		for(int i = 0; i < 30; i++) {
			cal.add(Calendar.DATE, -1);
			day = cal.getTime();
			expenses.addAll(maintenance.getMaintenanceByDate(vehicleId, day));
		}
		BigDecimal totalCost = new BigDecimal(0);
		for(int i = 0; i < expenses.size(); i++) {
			totalCost = totalCost.add(expenses.get(i).getCost());
		}
		map.put("totalCost", totalCost);
		map.put("expenses", expenses);
		return "maintenanceExpenses";
	}
	
}
