package kaur.ee.trial.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaur.ee.trial.ErrorMessageKeys;
import kaur.ee.trial.data.db.entities.Product;
import kaur.ee.trial.data.db.repositories.ProductRepository;
import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.dto.SaleDto;
import kaur.ee.trial.data.dto.SaleProductDto;
import kaur.ee.trial.service.SaleService;
import kaur.ee.trial.service.exceptions.ShopException;

@Service
public class SaleServiceImpl implements SaleService {


	@Autowired
	public SaleServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private final ProductRepository productRepository;

	@Override
	public SaleDto payment(SaleDto saleDto) throws ShopException {
		generateSaleValue(saleDto);
		saleDto.setChange(saleDto.getAmountPaid().subtract(saleDto.getSaleValue()));
		if (saleDto.getChange().compareTo(new BigDecimal(0)) < 0) {
			throw new ShopException(ErrorMessageKeys.SALE_PAYMENT_INSUFFICIENT);
		}
		return saleDto;
	}

	@Override
	public void reset(SaleDto saleDto) {
		if (saleDto != null) {
			saleDto.getSaleProducts().stream().forEach(sp -> {
				Product product = productRepository.getOne(sp.getProduct().getId());
				product.setAmount(product.getAmount() + sp.getAmount());
				productRepository.save(product);
			});
		}
	}

	@Override
	public SaleDto reserveItem(SaleDto saleDto, ProductDto productDto, Long amount) throws ShopException {
		Product product = productRepository.findById(productDto.getId()).get();
		SaleProductDto saleProductDto = getSaleProductFromSale(saleDto, productDto);
		setProductAmounts(product, saleProductDto, amount);
		generateSaleValue(saleDto);
		return saleDto;
	}

	protected void setProductAmounts(Product product, SaleProductDto saleProductDto, Long amount) throws ShopException {
		if (product.getAmount() >= amount) {
			product.setAmount(product.getAmount() - amount);
			productRepository.save(product);
			saleProductDto.setAmount(saleProductDto.getAmount() + amount);
			saleProductDto.getProduct().setAmount(product.getAmount());
		} else {
			throw new ShopException(ErrorMessageKeys.PRODUCT_OUT_OF_STOCK); 
		}
	}

	/**
	 * Return the details for given product in given sale.
	 * Creates empty details if not already existing.
	 * 
	 * @param saleDto
	 * @param productDto
	 * @return
	 */
	protected SaleProductDto getSaleProductFromSale(SaleDto saleDto, ProductDto productDto) {
		SaleProductDto saleProduct;
		Optional<SaleProductDto> optional = saleDto.getSaleProducts().stream().filter(sp -> sp.getProduct().getId().equals(productDto.getId())).findFirst();
		if (optional.isEmpty()) {
			saleProduct = new SaleProductDto();
			saleProduct.setAmount(0L);
			saleProduct.setProduct(productDto);
			saleDto.getSaleProducts().add(saleProduct);
		} else {
			saleProduct = optional.get();
		}
		return saleProduct;
	}

	/**
	 * Generates the total amount to be paid in given sale.
	 * Gets product prices from db.
	 * @param saleDto
	 */
	protected void generateSaleValue(SaleDto saleDto) {
		BigDecimal totalPrice = new BigDecimal(0);
		for (SaleProductDto saleProduct: saleDto.getSaleProducts()) {
			Product product = productRepository.findById(saleProduct.getProduct().getId()).get();
			totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(saleProduct.getAmount())));
		}
		saleDto.setSaleValue(totalPrice);
	}

}
