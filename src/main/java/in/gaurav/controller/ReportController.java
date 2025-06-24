package in.gaurav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.gaurav.entity.CitizenPlan;
import in.gaurav.request.SearchRequest;
import in.gaurav.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	
	/**
	 * This method is used to download the PDF and create file of the search data
	 * @param HttpServletResponse
	 * @return 
	 */
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response,Model model) throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		boolean status = reportService.exportPdf(response);
		model.addAttribute("msg", "PDF Report sent to your email");
	}
	
	/**
	 * This method is used to download the excel and create file of the search data
	 * @param HttpServletResponse
	 * @return 
	 */
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response,Model model) throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		boolean status = reportService.exportExcel(response);
		model.addAttribute("msg", "Excel Report sent to your email");
	}
	
	
	/**
	 * This method is used to give search records based on criteria
	 * @param model
	 * @param request
	 * @return String
	 */
	@PostMapping("/search1")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> plans = reportService.search(request);
		model.addAttribute("plans", plans);
		
		init(model);
		return "index";
	}
	
	/**
	 * This method is used to load index page
	 * @param model
	 * @return String
	 */
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}


	private void init(Model model) {
		
		model.addAttribute("names", reportService.getPlanNames());
		model.addAttribute("status", reportService.getPlanStatus());
	}
}