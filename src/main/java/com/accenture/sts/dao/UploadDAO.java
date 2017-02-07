package com.accenture.sts.dao;

import com.accenture.sts.exception.STSGenericException;

public interface UploadDAO {

	public void saveorUpdate(Object entity) throws STSGenericException;
	public void showdate(Object entity);
	void showcreate(Object entity);


}
