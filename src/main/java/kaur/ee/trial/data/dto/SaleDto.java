package kaur.ee.trial.data.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SaleDto {

	private Long id;
	private BigDecimal saleValue;
	private Set<SaleProductDto> saleProducts;
	private BigDecimal amountPaid;
	private BigDecimal change;

	public SaleDto() {
		saleProducts = new HashSet<>();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(BigDecimal saleValue) {
		this.saleValue = saleValue;
	}
	public Set<SaleProductDto> getSaleProducts() {
		return saleProducts;
	}

	public void setSaleProducts(Set<SaleProductDto> saleProducts) {
		this.saleProducts = saleProducts;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

}
