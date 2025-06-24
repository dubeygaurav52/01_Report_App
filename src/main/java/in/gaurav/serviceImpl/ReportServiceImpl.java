package in.gaurav.serviceImpl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.gaurav.entity.CitizenPlan;
import in.gaurav.repository.CitizenPlanRepository;
import in.gaurav.request.SearchRequest;
import in.gaurav.service.ReportService;
import in.gaurav.utils.EmailUtils;
import in.gaurav.utils.ExcelGenerator;
import in.gaurav.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanNames() {
		return citizenPlanRepository.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return citizenPlanRepository.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity=new CitizenPlan();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null!=request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String date = request.getPlanStartDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(date, formatter);
			entity.setPlanStartDate(localDate);
		}
		if(null!=request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String date = request.getPlanEndDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(date, formatter);
			entity.setPlanEndDate(localDate);
		}
		
		return citizenPlanRepository.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> records = citizenPlanRepository.findAll();
		File f=new File("plans.xls");
		
		excelGenerator.generate(response, records,f);
		String subject="Test Subject";
		String body="<h1>Test Body</h1>";
		String to="dubeygaurav52@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return false;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = citizenPlanRepository.findAll();
		File f=new File("Plans.pdf");
		pdfGenerator.PDFGenerator(response, plans,f);	
		
		String subject="Test Subject";
		String body="<h1>Test Body</h1>";
		String to="dubeygaurav52@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return false;
	}

	
}

