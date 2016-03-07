package com.bionic.edu;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Named
public class Application{
	@Inject
    MerchantService merchantService;
	@Inject
    PaymentService paymentService;
	@Inject
    CustomerService customerService;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    	Application application = (Application)context.getBean("application");
    	application.findPayment(2);
    	application.getMerchantPayments();
     }
    
    public void findPayment(int id){
    	
    	Payment p = paymentService.findById(3);
    	SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    	System.out.println("=======================START========================");
        System.out.println("Date = " + fmt.format(p.getDt()) + "   merchant = " + p.getMerchant().getName() + "   sum = " + p.getSumPayed());
        System.out.println("=======================FINISH========================");
    }
    
    /*public void getTotalReport(){
    	List<Result> list = paymentService.getTotalReport();
    	System.out.println("    Merchant              Total Charge");
    	for (Result result: list){
    		System.out.format("%1$-25s  %2$8.2f %n", result.getName(), result.getSum()); 
    	}
    }*/
    
    public void getMerchantPayments(){
    	List<Merchant> list = merchantService.getSortedByNeedToPay();
    	System.out.println("=======================START========================");
    	for(Merchant m: list){
        	System.out.println("=======================");
        	Collection<Payment> payments = m.getPayments();
        	SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        	for (Payment p : payments) {
        		System.out.println("Merchant Name: "+m.getName()+" , Merchant ID: "+m.getId()+" , Merchant minSUM:"+m.getMinSum()+ " , Payments sum = " + p.getSumPayed() + " , Payment goods = " + p.getGoods());
        	}
        }
    	System.out.println("=======================FINISH========================");
    }
    /*
    public void getMerchantsByCustomer(int id){
    	Customer customer = customerService.findById(id);
        if (customer != null){
        	System.out.println(customer.getName() + "\n==================");
        	Collection<Merchant> merchants = customer.getMerchants();
        	for (Merchant m : merchants) {	
        		System.out.println(m.getName());
        	}
        }
    }*/
 }
