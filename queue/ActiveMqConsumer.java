package com.training.queue;

import java.lang.annotation.Retention;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.training.bean.FailedProduct;
import com.training.entity.Product;
import com.training.repo.ProductRepo;

@Component
public class ActiveMqConsumer {

	@Autowired
	private ProductRepo productRepo;

	public static List<FailedProduct> list = new ArrayList<FailedProduct>();

	@JmsListener(destination = "ProductQueue" )
	public void listener(FailedProduct failedProduct){
		list.add(failedProduct);
	}

	public void processFailedProducts(List<FailedProduct> list){
		String[] reasonArray = {"", "UPC can't be empty!!", "Code for Artist ID cannot be empty or have more than 7 digits.",
				"Code for Organisation ID cannot be empty or more than 8 digits.","Release Date format is incorrect. Only YYYYMMDD allowed.",
		"Release Date must have 8 digits."};
		String[] columnArray = {"","New UPC ID:", "New Artist ID:", "New Org ID:","New Release Date: ","New Release Date: "};
		Scanner sc = new Scanner(System.in);
		FailedProduct failedProduct;
		String modifiedData="";
		int i=0;
		//fetching data from console
		while(i<list.size() ) {
			failedProduct= list.get(i);
			String reason=reasonArray[failedProduct.getMessageId()];
			String columnToEdit=columnArray[failedProduct.getMessageId()];
			System.out.println("------------------------------------------------------");
			System.out.println("Failed Product no. "+i+".");
			System.out.println("Reason of failure: "+reason);
			System.out.println("Please enter "+columnToEdit);
			modifiedData=sc.next();
			if(failedProduct.getMessageId()==1) { failedProduct.setUpc(modifiedData);}
			else if(failedProduct.getMessageId()==2) {failedProduct.setArtistId(modifiedData);}
			else if(failedProduct.getMessageId()==3) {failedProduct.setOrgId(modifiedData);}
			else if(failedProduct.getMessageId()==4) {failedProduct.setReleaseDate(modifiedData);}
			else {failedProduct.setReleaseDate(modifiedData);}

			if(! isCorrect(failedProduct)) {continue;}

			productRepo.save(new Product(failedProduct));
			i++;
			//send to ui for modification with message
			//after modification reprocess the failed product

		}


	}
	public boolean isCorrect(FailedProduct failedProduct) {
		if(failedProduct.getUpc().length()==0)
		{	return false;
		} else {}

		if(failedProduct.getArtistId().length()>0 && failedProduct.getArtistId().length()<=7)
		{	
			failedProduct.setArtistId("ING"+failedProduct.getArtistId());
		} else
		{ 
			return false;
		}

		if(failedProduct.getOrgId().length()==0)
		{	return false;
		} else {}

		if(failedProduct.getReleaseDate().length()==8) {
			String releaseDate= verifyInput(failedProduct.getReleaseDate());
			if(releaseDate!=null) {
				failedProduct.setReleaseDate(releaseDate);
				return true;
			}
			else {
				return false;
			}
		} else if(failedProduct.getReleaseDate().length()==0) {
			LocalDate today= LocalDate.now();
			String s="";
			for(String i: today.toString().split("-")) {
				s+=i;
			}
			failedProduct.setReleaseDate( s );
		} else
		{	
			return false;
		}

		return true;
	}
	
	private static String verifyInput(String strDate){
		String strDateRegEx = "\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|[3][01])";
		if(strDate.matches(strDateRegEx)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try{
				sdf.parse(strDate);
				return strDate;
			}catch(ParseException e){}
		}
		return null;
	}



}