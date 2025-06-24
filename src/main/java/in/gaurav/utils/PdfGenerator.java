package in.gaurav.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.gaurav.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class PdfGenerator {

	public void PDFGenerator(HttpServletResponse response, List<CitizenPlan> plans,File f) throws Exception {
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		Paragraph p=new Paragraph("Citizen Plans Info");
		document.add(p);
		PdfPTable table=new PdfPTable(6);
		table.addCell("Citizen Id");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");
		
		for (CitizenPlan citizenPlan : plans) {
			table.addCell(String.valueOf(citizenPlan.getCitizenId()));
			table.addCell(citizenPlan.getCitizenName());
			table.addCell(citizenPlan.getPlanName());
			table.addCell(citizenPlan.getPlanStatus());
			table.addCell(citizenPlan.getPlanStartDate()+"");
			table.addCell(citizenPlan.getPlanEndDate()+"");
		}
		document.add(table);
		document.close();
	}
}
