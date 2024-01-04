package com.tuxtli.pocmq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuxtli.pocmq.model.Customer;
import com.tuxtli.pocmq.service.CustomerService;

@RestController
@RequestMapping("/pocmq/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("customer/add")
	public String addCustomer(@RequestParam String name,
							  @RequestParam String email,
							  @RequestParam String phone) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhone(phone);
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("customer/all")
	public List<Customer> getCustomers(){
		return customerService.getAllCustomers();
	}

}
