package com.cgi.soa.masterclass.samplebank.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.service.CustomerRepository;

@FacesConverter(value="customerConverter")
public class CustomerConverter implements Converter {

	@Inject
	CustomerRepository customerRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return customerRepository.findById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Customer) value).getId().toString();
	}

}
