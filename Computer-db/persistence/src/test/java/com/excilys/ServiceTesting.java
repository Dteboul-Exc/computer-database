package com.excilys;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.DAO.QueryCompanyInterface;
import com.excilys.DAO.QueryComputerInterface;
import com.excilys.configuration.JdbcSpringConfiguration;
import com.excilys.model.Company;
import com.excilys.model.Computer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcSpringConfiguration.class)
public class ServiceTesting {
    private QueryCompanyInterface company;
    private QueryComputerInterface computer;


    @Test
    public void TestgetAComputer() {
    	Computer test = computer.findById((long) 1).get();
    	assertEquals(test.getName(),"MacBook Pro 15.4 inch");
    }
    
    @Test
    public void TestinvalidComputer() {
    	Computer test = computer.findById((long) 65).get();
    	assertEquals(null,test);
    }
    
    @Test 
    public void TestgetAllComputer()
    {
    	List<Computer> test = new ArrayList<>();
    	test.add(Computer.Builder.newInstance().setId(1).setName("MacBook").setCompany(Company.Builder.newInstance().setId(1).setName("Apple Inc.").build()).build());
    	test.add(Computer.Builder.newInstance().setId(1).setName("CM-2a").setCompany(Company.Builder.newInstance().setId(2).setName("Thinking Machines").build()).build());
    	test.add(Computer.Builder.newInstance().setId(1).setName("CM-200").setCompany(Company.Builder.newInstance().setId(2).setName("Thinking Machines").build()).build());
    	assertEquals(test,computer.findAll());
    }
    
    @Test 
    public void TestgetcountComputer()
    {
    	List<Computer> test = new ArrayList<>();
    	test.add(Computer.Builder.newInstance().setId(1).setName("CM-2a").setCompany(Company.Builder.newInstance().setId(2).setName("Thinking Machines").build()).build());
    	test.add(Computer.Builder.newInstance().setId(1).setName("CM-200").setCompany(Company.Builder.newInstance().setId(2).setName("Thinking Machines").build()).build());
    	assertEquals(test,computer.findByNameContainingIgnoreCase("CM"));
    }
    
    @Test 
    public void TestgetSpecificComputer()
    {
    	Computer expected = Computer.Builder.newInstance().setId(1).setName("MacBook").setCompany(Company.Builder.newInstance().setId(1).setName("Apple Inc.").build()).build();
    	assertEquals(expected,computer.findById((long) 1).get());
    }
    
    
    

}
