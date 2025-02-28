package com.expensetrackersystem.ExpTrackSys;


import com.expensetrackersystem.ExpTrackSys.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpTrackSysApplication implements CommandLineRunner {

	@Autowired
	private ExpenseService expenseService;

	public static void main(String[] args) {
		SpringApplication.run(ExpTrackSysApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception {

	}
}
