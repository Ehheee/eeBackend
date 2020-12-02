package kaur.ee.trial.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kaur.ee.trial.ErrorMessageKeys;
import kaur.ee.trial.data.db.entities.Product;
import kaur.ee.trial.data.db.repositories.ProductRepository;
import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.dto.SaleDto;
import kaur.ee.trial.service.SaleService;
import kaur.ee.trial.service.exceptions.ShopException;

public class SaleServiceTest {

	private static ProductRepository productRepository;
	private static SaleService saleService;
	private static Product product1;
	private static Product product2;
	private static ProductDto product1Dto;
	private static ProductDto product2Dto;
	@BeforeEach
	public void setUp() {
		product1 = new Product();
		product1.setAmount(48L);
		product1.setId(1L);
		product1.setName("Brownie");
		product1.setPrice(new BigDecimal("0.65"));
		product2 = new Product();
		product2.setAmount(36L);
		product2.setId(2L);
		product2.setName("Muffin");
		product2.setPrice(new BigDecimal(1.00));
		product1Dto = new ProductDto();
		product1Dto.setId(1L);
		product2Dto = new ProductDto();
		product2Dto.setId(2L);
		productRepository = Mockito.mock(ProductRepository.class);
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
		Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
		saleService = new SaleServiceImpl(productRepository);
	}

	@Test
	public void testReserveProduct() throws ShopException {
		SaleDto saleDto = new SaleDto();
		saleService.reserveItem(saleDto, product1Dto, 10L);
		assertTrue(saleDto.getSaleProducts().size() == 1);
		assertEquals(10L, saleDto.getSaleProducts().iterator().next().getAmount());
		assertEquals(38L, product1.getAmount());
		assertEquals(38L, product1Dto.getAmount());
		product1.setAmount(0L);
		ShopException exception = assertThrows(ShopException.class, () -> {saleService.reserveItem(saleDto, product1Dto, 1L);});
		assertEquals(ErrorMessageKeys.PRODUCT_OUT_OF_STOCK, exception.getMessageKey());
	}

	@Test
	public void testPayment() throws ShopException {
		SaleDto saleDto = new SaleDto();
		saleService.reserveItem(saleDto, product1Dto, 48L);
		saleService.reserveItem(saleDto, product2Dto, 18L);
		assertEquals(0L, product1.getAmount());
		assertEquals(18L, product2.getAmount());
		saleDto.setAmountPaid(new BigDecimal("50.3"));
		saleService.payment(saleDto);
		assertEquals(new BigDecimal("49.20"), saleDto.getSaleValue());
		assertEquals(new BigDecimal("1.10"), saleDto.getChange());
		saleDto.setAmountPaid(new BigDecimal("49.1"));
		ShopException exception = assertThrows(ShopException.class, () -> {saleService.payment(saleDto);});
		assertEquals(ErrorMessageKeys.SALE_PAYMENT_INSUFFICIENT, exception.getMessageKey());
	}
}
