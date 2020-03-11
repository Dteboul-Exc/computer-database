package exc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Computer {
	private int id;
	private String name;
	private Company Computer_company;
	private Company company = new Company();
	private LocalDate introduced;
	private LocalDate discontinued;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public Company getComputer_company() {
		return Computer_company;
	}
	public void setComputer_company(Company computer_company) {
		Computer_company = computer_company;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	
	
}
