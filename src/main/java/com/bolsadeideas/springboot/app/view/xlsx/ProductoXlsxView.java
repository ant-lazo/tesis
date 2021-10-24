package com.bolsadeideas.springboot.app.view.xlsx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.bolsadeideas.springboot.app.models.entity.Producto;

@Component("producto/listarproductos.xlsx")
public class ProductoXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"ProductosMikarFarma.xlsx\"");
		List<Producto> productos =  (List<Producto>) model.get("lproductos");
		Sheet sheet = workbook.createSheet("Productos MikarFarma");
		
		Row row = sheet.createRow(0);
		Cell cell= row.createCell(0);
		cell.setCellValue("Listado de Producto MikarFarma");
		row = sheet.createRow(1);
		cell= row.createCell(0);
		/**/
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle style2 = workbook.createCellStyle();
		style2.setDataFormat(creationHelper.createDataFormat().getFormat("mm/dd/yyyy"));
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		/**/
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		Row header = sheet.createRow(2);
		header.createCell(0).setCellValue("Id");
		header.createCell(1).setCellValue("Nombre");
		header.createCell(2).setCellValue("Laboratorio");
		header.createCell(3).setCellValue("Precio");
		header.createCell(4).setCellValue("Stock");
		header.createCell(5).setCellValue("Fecha");
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		header.getCell(4).setCellStyle(theaderStyle);
		header.getCell(5).setCellStyle(theaderStyle);
		
		int rownum = 3;
		for(Producto item: productos) {
			Row fila = sheet.createRow(rownum++);
			
			cell= fila.createCell(0);
			cell.setCellValue(item.getId());
			cell.setCellStyle(tbodyStyle);
			
			cell= fila.createCell(1);
			cell.setCellValue(item.getNombre());
			cell.setCellStyle(tbodyStyle);
			
			cell= fila.createCell(2);
			cell.setCellValue(item.getLaboratorio());
			cell.setCellStyle(tbodyStyle);
			
			cell= fila.createCell(3);
			cell.setCellValue(item.getPrecio());
			cell.setCellStyle(tbodyStyle);
			
			cell= fila.createCell(4);
			cell.setCellValue(item.getStock());
			cell.setCellStyle(tbodyStyle);
			
			cell= fila.createCell(5);
			cell.setCellValue(item.getCreateAt());
			cell.setCellStyle(style2);
			//cell.setCellStyle(tbodyStyle);
		}
	}

}
