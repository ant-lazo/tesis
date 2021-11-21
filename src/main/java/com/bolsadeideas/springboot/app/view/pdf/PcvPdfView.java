package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("dashboard/pcv")
public class PcvPdfView extends AbstractPdfView{

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<PcvIndicador> pcv= (List<PcvIndicador>) model.get("pcv");
		
		MessageSourceAccessor mensajes= getMessageSourceAccessor();
		Locale locale = localeResolver.resolveLocale(request);
		
		document.setPageSize(PageSize.A4);
		document.open();
		
		/**/
		PdfPTable tablaimg= new PdfPTable(1);
		tablaimg.setSpacingAfter(20);
		PdfPCell celd=null;
		celd= new PdfPCell(new Phrase("Porcentaje de Crecimiento en Ventas"));
		celd.setBorder(0);
		celd.setBackgroundColor(new Color(255, 182, 120));
		celd.setPadding(8f);
		celd.setHorizontalAlignment(Element.ALIGN_CENTER);
		celd.setVerticalAlignment(Element.ALIGN_CENTER);
		tablaimg.addCell(celd);
		document.add(tablaimg);
		/**/
		
		PdfPTable tabla = new PdfPTable(1);
		
		PdfPCell cell= null;
		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);
		
		cell= new PdfPCell(new Phrase(messageSource.getMessage("text.dashboard.ver.datos.sede", null, locale)));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		
		document.add(tabla);
		document.add(tabla2);
		
		PdfPTable tabla3= new PdfPTable(5);
		tabla3.setWidths(new float [] {1,1,1,1,1});;
		tabla3.addCell("Item");
		tabla3.addCell("Fecha");
		tabla3.addCell("Valor Anterior");
		tabla3.addCell("Valor Reciente");
		tabla3.addCell("Resultado");
		
		int i = 0;
		for(PcvIndicador item: pcv) {
			i++;
			tabla3.addCell(i+"");
			tabla3.addCell(item.getFecha().toString());
			tabla3.addCell(this.transformar(item.getValorAnterior()));
			tabla3.addCell(this.transformar(item.getValorReciente()));
			tabla3.addCell(this.transformar(item.getResultado())+"%");
		}
		
		
		document.add(tabla3);
		
	}
	
	public String transformar(double valor){
		DecimalFormat f = new DecimalFormat("#.00");
		String salida=f.format(valor);
		return salida;
	}

}
