package com.restful.webservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restful.webservices.model.Payer;
import com.restful.webservices.restfulwebservices.PayerRepository;

@RestController
@RequestMapping(value = "/rest")
public class HelloWorldController {

	private List<Payer> payerList = new ArrayList<>();
	
	@Autowired
	private PayerRepository payerRepository;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, path = "/payers")
	public List<Payer> getAllPayers() {
		return payerRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/payers")
	public Payer addPayer(@RequestBody Payer payer) {
		payerList.add(payer);
		return payer;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/payers/{index}")
	public Resource<Payer> getUserById(@PathVariable int index) {
		//filter example
//		Payer p = new Payer(1, "");
//		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("PayerFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(p);
//		mapping.setFilters(filters);

		Payer payer = payerList.stream().filter(x -> x.getId() == index).findFirst().orElse(null);

		// hateos Example
		Resource<Payer> resource = new Resource<Payer>(payer);
		// ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(this.getClass(),
		// getAllPayers());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllPayers());

		resource.add(linkTo.withRel("all-payers"));
		return resource;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	public String getInternationalised(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null, locale);
		
	}
	@RequestMapping(method = RequestMethod.GET, path = "/hello1")
	public String getInternationalised() {
		
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
		
	}

}
