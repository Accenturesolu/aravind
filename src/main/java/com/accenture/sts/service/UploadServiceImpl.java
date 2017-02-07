package com.accenture.sts.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.dao.UploadDAO;
import com.accenture.sts.entity.Upload_ticket;
import com.accenture.sts.exception.STSGenericException;

public class UploadServiceImpl implements UploadService {
	@Autowired
	private UploadDAO uploadDAO;

	@Override
	public List<Upload_ticket> readData(Sheet firstSheet,String employeename) throws STSGenericException {

		List<Upload_ticket> tUpload = new ArrayList<Upload_ticket>();
		
		@SuppressWarnings("unused")
		Date date=new Date();
		
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        String string  = dateFormat.format(new Date());
	        System.out.println(string);
			
		int rowNum = firstSheet.getLastRowNum();
		for (int i = 0; i <= rowNum; i++) {
			Row nextRow = firstSheet.getRow(i);
			if (nextRow.getRowNum() != 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Upload_ticket ult = new Upload_ticket();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnindex = nextCell.getColumnIndex();
					if (columnindex == 0) {
						ult.setTicket_id(nextCell.getStringCellValue());
						System.out.println(ult.getTicket_id());
					} else if (columnindex == 1) {
						ult.setTicket_desc(nextCell.getStringCellValue());
						System.out.println(ult.getTicket_desc());
					} else if (columnindex == 2) {
						ult.setApplication_name(nextCell.getStringCellValue());
						System.out.println(ult.getApplication_name());
					} else {
						continue;
					}
					ult.setCreatedon(string);
					System.out.println(employeename);
					ult.setCreatedby(employeename);
					tUpload.add(ult);
				}
			}

		}
		return tUpload;
	}

	@Override
	public void saveorupdate(Object entity) throws STSGenericException {

		try {
			 uploadDAO.saveorUpdate(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
