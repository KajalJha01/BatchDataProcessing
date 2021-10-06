package com.training.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.training.bean.Product;
import com.training.queue.ActiveMqConsumer;
import com.training.queue.SendEmail;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ActiveMqConsumer activeMqConsumer;
	
	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public SendEmail sendEmail;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED)
		{
			log.info("!!! JOB FINISHED! Time to notify the owner");
			
			int passedProducts = jdbcTemplate.queryForObject(
				    "SELECT COUNT(*) FROM product", Integer.class);
			
			sendEmail.mail(ProductItemProcessor.processCounter,passedProducts);
			
			activeMqConsumer.processFailedProducts(activeMqConsumer.list);
			
		}
	}
}