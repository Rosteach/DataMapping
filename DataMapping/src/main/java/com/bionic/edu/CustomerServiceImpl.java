package com.bionic.edu;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

@Named
public class CustomerServiceImpl implements CustomerService{
    @Inject
    private CustomerDao customerDao;
    
    public Customer findById(int id) { 
    	return customerDao.findById(id); 
	}
    
    @Transactional
	public void save(Customer customer){
		customerDao.save(customer);
	}
   
    @Transactional
    public void remove(int id){
    	customerDao.remove(id);
    }
    
    @Transactional
    public void updateCcNo(int id, String ccNo){
    	customerDao.updateCcNo(id, ccNo);
    }

    public List<String> getNames(double sumPayed){
    	return customerDao.getNames(sumPayed);
    }
}
