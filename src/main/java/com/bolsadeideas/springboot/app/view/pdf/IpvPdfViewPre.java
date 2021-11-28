package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("dashboard/ipvpre")
public class IpvPdfViewPre extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		URL imageUrl = getClass().getResource("/static/images/ipvpre.png");
		Image logo = Image.getInstance(imageUrl);
		
		PdfPTable tablaimg= new PdfPTable(1);
		tablaimg.setSpacingAfter(20);
		PdfPCell celd=null;
		celd= new PdfPCell(logo);
		celd.setBorder(0);
		celd.setPadding(8f);
		celd.setHorizontalAlignment(Element.ALIGN_CENTER);
		celd.setVerticalAlignment(Element.ALIGN_CENTER);
		tablaimg.addCell(celd);
		document.add(tablaimg);
	}

}
