package com.training.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.training.bean.FailedProduct;
import com.training.bean.Product;

public class ProductItemProcessor implements ItemProcessor<Product, Product> {

	private static final Logger log = LoggerFactory.getLogger(ProductItemProcessor.class);
	public static int processCounter;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQQueue queue;

	@Override
	public Product process(final Product product) throws Exception {

		log.info("Started processing "+ processCounter++ +" from csv file..");

		if(product.getUpc().length()==0)
		{	FailedProduct failedProduct = new FailedProduct(product,1);
		jmsTemplate.convertAndSend(queue,failedProduct);
		return null;
		} else {}

		if(product.getArtistId().length()>0 && product.getArtistId().length()<=7)
		{	
			product.setArtistId("ING"+product.getArtistId());
		} else
		{ 
			FailedProduct failedProduct = new FailedProduct(product,2);
			jmsTemplate.convertAndSend(queue,failedProduct);
			return null;
		}

		if(product.getOrgId().length()==0)
		{	FailedProduct failedProduct = new FailedProduct(product,3);
		jmsTemplate.convertAndSend(queue,failedProduct);
		return null;
		} else {}

		if(product.getReleaseDate().length()==8) {
			String releaseDate= verifyInput(product.getReleaseDate());
			if(releaseDate!=null) {
				product.setReleaseDate(releaseDate);
			}
			else {
				FailedProduct failedProduct = new FailedProduct(product,4);
				jmsTemplate.convertAndSend(queue,failedProduct);
				return null;
			}
		} else if(product.getReleaseDate().length()==0) {
			LocalDate today= LocalDate.now();
			String s="";
			for(String i: today.toString().split("-")) {
				s+=i;
			}
			product.setReleaseDate( s );
		} else
		{	
			FailedProduct failedProduct = new FailedProduct(product,5);
			jmsTemplate.convertAndSend(queue,failedProduct);
			return null;
		}

		return product;

	}

//	public static String verifyInput(String input) {
//		String yyyy="";String mm="";String dd="";
//		yyyy+=input.substring(0, 4);
//		mm+=input.substring(4, 6);
//		dd+=input.substring(6, 8);
//		try {
//			LocalDate.parse(yyyy+"-"+mm+"-"+dd);
//			return input;
//		}catch(DateTimeParseException e) {
//			return null;
//		}
//	}
	
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