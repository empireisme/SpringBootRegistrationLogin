package net.mike.project.spend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mike.project.spend.dao.DailySpendRepository;
import net.mike.project.spend.model.DailySpend;
import net.mike.project.user.dao.UserRepository;
import net.mike.project.util.UserUtil;

@Controller
public class ViewSpendController {
//	List<Shop> list = service.getAllShops();
//	  model.addAttribute("list", list);

	@Autowired
	DailySpendRepository dailySpendRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserUtil userUtil;

	@GetMapping("/getDailySpendList")
	public String getUserDailySpend(Principal principal, Model model) {

		Long userId = userUtil.getLoginUserId(principal);

		List<DailySpend> userDailySpendList = dailySpendRepository.findByUserId(userId);

		model.addAttribute("list", userDailySpendList);

		return "spend/dailyspend";

	}

	@GetMapping("/add")
	public String showAddForm(Model model) {

		model.addAttribute("dailyspend", new DailySpend());
		return "spend/adddailyspend";
	}

	@PostMapping("/save")
	public String create(DailySpend dailyspend, Model model, Principal principal) {

		Long userId = userUtil.getLoginUserId(principal);
		dailyspend.setUserId(userId);
		dailySpendRepository.save(dailyspend);

//		return "spend/dailyspend"; //
		return "redirect:/getDailySpendList"; // 這邊記得放的是 url 而非在資料夾的位置
	}

	@GetMapping(path = { "/delete/{id}" })
	public String delete(Model model, @PathVariable("id") Long id) {
		dailySpendRepository.deleteById(id);
		return "redirect:/getDailySpendList";
	}

	@RequestMapping(path = { "/update", "/update/{id}" })
	public String update(Model model, @PathVariable("id") Long id) {
		
		if (id != null) {
			
			DailySpend updateDailySpend = dailySpendRepository.findById(id).orElse(null);
			
			model.addAttribute("dailyspend", updateDailySpend);
			
		} else {
			
			model.addAttribute("dailyspend", new DailySpend());
		}
		
		return "spend/adddailyspend";
	}
}
