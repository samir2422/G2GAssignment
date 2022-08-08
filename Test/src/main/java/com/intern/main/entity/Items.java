package com.intern.main.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Items {

	@Id
	private String Id;
	
	private String Name;
	private double Price;
	private String Description;
	private boolean Available;
	
	public Items() {
		super();
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public boolean isAvailable() {
		return Available;
	}
	public void setAvailable(boolean available) {
		Available = available;
	}
	public Items(String id, String name, double price, String description, boolean available) {
		super();
		Id = id;
		Name = name;
		Price = price;
		Description = description;
		Available = available;
	}
	@Override
	public String toString() {
		return "item [Id=" + Id + ", Name=" + Name + ", Price=" + Price + ", Description=" + Description
				+ ", Available=" + Available + "]";
	}
	
	
}
