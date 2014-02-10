package com.cgi.soa.masterclass.samplebank.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cgi.soa.masterclass.samplebank.model.Account;
import com.cgi.soa.masterclass.samplebank.service.Repository;

@FacesConverter(value="accountConverter")
public class AccountConverter implements Converter {

	@Inject
	Repository repository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return repository.findAccountById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Account) value).getId().toString();
	}

}
