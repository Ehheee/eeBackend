package kaur.ee.trial.data.dto;

public class SaleProductDto {

	private ProductDto product;
	private SaleDto sale;
	private long amount;
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public SaleDto getSale() {
		return sale;
	}
	public void setSale(SaleDto sale) {
		this.sale = sale;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

}
