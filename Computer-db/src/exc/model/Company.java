package exc.model;

public class Company {
	private int id=0;
	private String name="none";

	public int getId() {
		return id;
	}
	public Company setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Company setName(String name) {
		this.name = name;
		return this;
	}
	

}
