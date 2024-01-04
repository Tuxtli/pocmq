package com.tuxtli.pocmq.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;
import com.tuxtli.pocmq.config.RabbitMqConfig;
import com.tuxtli.pocmq.model.Customer;
import com.tuxtli.pocmq.repository.CustomerRepository;

@Service
public class CustomerService {
	public final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    RabbitTemplate rabbitTemplate;
	
	public String addCustomer(Customer customer) {
		LOGGER.info("**********customer*************");
		try {
			customer = customerRepository.insert(customer);
			return customer.getId();
		}catch(DataAccessResourceFailureException e) {
			LOGGER.info("************** Error de Base de Datos");
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, customer.toString());
			return "Error de Base de Datos, datos enviados a MQ ";
		}
		
		
	}
	
	public Customer getCustomer(String id) {
		return null;
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<>();
		
		try {
			customers = customerRepository.findAll();
					
		}catch(MongoException me) {
			new Throwable("Error de Base de Datos ");
			return null;
		}
		
		return customers;
	}
	
	public Boolean deleteCustom(String id) {
		
		return null;
	}
}
