package com.training.controller;

import java.io.IOException;
import java.util.List;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.training.queue.ActiveMqConsumer;
import com.training.bean.FailedProduct;
import com.training.bean.Product;

//@Controller
@RequestMapping("")
public class ConsumerController {

	@Autowired
	private ActiveMqConsumer activeMqConsumer;

	/*
	 * @RequestMapping(value="/" ) public String index() { return "index"; }
	 */

	/*
	 * @RequestMapping(value="/index", method=RequestMethod.POST) public
	 * ModelAndView index(@ModelAttribute List<FailedProduct> prods) { prods =
	 * activeMqConsumer.list; ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("index");
	 * modelAndView.addObject("failedProductList", prods);
	 * //model.addAttribute("failedProductList", prods); return modelAndView; }
	 * 
	 * @RequestMapping(value="/save", method=RequestMethod.POST) public ModelAndView
	 * save(@ModelAttribute FailedProduct failedProduct){ String[] reasonArray =
	 * {"", "UPC can't be empty!!",
	 * "Code for Artist ID cannot be empty or have more than 7 digits.",
	 * "Code for Organisation ID cannot be empty or more than 8 digits."
	 * ,"Release Date format is incorrect. Only YYYYMMDD allowed.",
	 * "Release Date must have 8 digits."}; String[] columnArray = {"",
	 * "New UPC ID:", "New Artist ID:", "New Org ID:","New Release Date: ",
	 * "New Release Date: "}; ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("user-data");
	 * modelAndView.addObject("failedProduct", failedProduct);
	 * modelAndView.addObject("message", reasonArray[failedProduct.getMessageId()]);
	 * return modelAndView; }
	 */


	//@JmsListener(destination = "ProductQueue")

	@GetMapping("products/modify")
	public String modifier(){
		//ActiveMqConsumer activeMqConsumer=new ActiveMqConsumer();
		//activeMqConsumer.listener();
		activeMqConsumer.processFailedProducts(activeMqConsumer.list);
		return "All products processed successfully.";
	}



}
