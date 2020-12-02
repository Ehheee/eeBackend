package kaur.ee.trial.data.db.entities;

import javax.persistence.Entity;

public class SaleProduct {

	private Product product;
	private long amount;
	private long saleValue;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(long saleValue) {
		this.saleValue = saleValue;
	}
}
