package vn.HiepKa.controller;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.HiepKa.entity.Customer;


@RestController
@EnableMethodSecurity
public class CustumerController {
	final private List<Customer> customers = List.of(
			Customer.builder()
			.id("001")
			.name("Ngô Minh Chung")
			.email("chungngonbkqna@gmail.com")
			.phoneNumber("982893822")
			.build(),
			Customer.builder()
			.id("002")
			.name("Minh Chung")
			.email("nmchung94@gmail.com")
			.phoneNumber("0123456789")
			.build());
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("Hello is Guest");
	}
	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList(){
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
		List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
	
}
