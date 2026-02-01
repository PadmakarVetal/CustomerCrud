package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidAddressException;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cr;

	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		String mob=customer.getMob();
		String address=customer.getAddress();
		if(mob.length()==10) {
			if(mob.charAt(0)=='0' || mob.charAt(0)=='1' || mob.charAt(0)=='2' || mob.charAt(0)=='3' || mob.charAt(0)=='4' || mob.charAt(0)=='5') throw new InvalidMobileNumber("Invalid Mobile Number !!");
			
			for(int i=0;i<mob.length();i++) {
				if(!Character.isDigit(mob.charAt(i))) throw new InvalidMobileNumber("INVALID MOBILE NUMBER !!");
			}
			
		} else {
			throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
		}
		if(cr.findByMob(mob)!=null) {
			
			throw new InvalidMobileNumber("Mobile Number Already Exists");
		}
		if(!cr.findByAddress(address).isEmpty()) {
			throw new InvalidAddressException("Address Already Exists!!");
		}
		
		cr.save(customer);
	}

	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll(); // Select *From ALL
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);
			return temp;
		}
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);

	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		if(id<=0) {
			throw new InvalidIdException("Please Enter Valid ID!!");
		}
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			return temp;
		}
		return null;
	}

	@Override
	public void addAll(List<Customer> list) {
		// TODO Auto-generated method stub
		cr.saveAll(list);
	}

	@Override
	public Customer findMob(String mob) {
		// TODO Auto-generated method stub
		return cr.findByMob(mob);
	}
}
