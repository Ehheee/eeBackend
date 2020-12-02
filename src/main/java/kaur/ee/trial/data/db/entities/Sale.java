package kaur.ee.trial.data.db.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Sale {

	private Long id;
	private Set<SaleProduct> saleProducts;
	private BigDecimal saleValue;
	
	public Sale() {
		this.saleProducts = new HashSet<>();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<SaleProduct> getSaleProducts() {
		return saleProducts;
	}
	public void setSaleProducts(Set<SaleProduct> saleProducts) {
		this.saleProducts = saleProducts;
	}
	public BigDecimal getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(BigDecimal saleValue) {
		this.saleValue = saleValue;
	}

}
