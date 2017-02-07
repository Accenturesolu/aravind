package com.accenture.sts.service;

import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import com.accenture.sts.entity.Upload_ticket;
import com.accenture.sts.exception.STSGenericException;

public interface UploadService {

	public void saveorupdate(Object entity) throws STSGenericException;

	public List<Upload_ticket> readData(Sheet firstSheet,String employeename) throws STSGenericException;


}