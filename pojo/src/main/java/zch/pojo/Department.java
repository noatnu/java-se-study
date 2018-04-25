package zch.pojo;

import java.util.HashSet;
import java.util.Set;

public class Department {

	private String id;
	private String name;
	
	private Set<Employee> emps = new HashSet<Employee>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}

	@Override
	public String toString() {
		return "Department{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
