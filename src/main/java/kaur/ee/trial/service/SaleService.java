package kaur.ee.trial.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.dto.SaleDto;
import kaur.ee.trial.service.exceptions.ShopException;

@Service
@Transactional
public interface SaleService {
	public SaleDto reserveItem(SaleDto saleDto, ProductDto productDto, Long amount) throws ShopException;
	public SaleDto payment(SaleDto saleDto) throws ShopException;
	public void reset(SaleDto saleDto);
}
