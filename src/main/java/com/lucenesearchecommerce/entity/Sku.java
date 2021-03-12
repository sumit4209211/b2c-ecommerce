package com.lucenesearchecommerce.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Indexed
@Table(name = "B2C_CSJ")
public class Sku {
	@Id
	private Integer id;
	private String name;
	private String code;

	@JsonIgnoreProperties("Sku")
	@OneToMany(mappedBy = "Sku", fetch = FetchType.LAZY)
	private Set<Product> products;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}

}
