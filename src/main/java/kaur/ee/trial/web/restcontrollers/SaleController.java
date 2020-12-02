package kaur.ee.trial.web.restcontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.dto.ResponseCode;
import kaur.ee.trial.data.dto.ResultDto;
import kaur.ee.trial.data.dto.SaleDto;
import kaur.ee.trial.service.ProductService;
import kaur.ee.trial.service.SaleService;
import kaur.ee.trial.service.exceptions.ShopException;

@RestController
public class SaleController {

	@RequestMapping("/products")
	public ResultDto getProducts() {
		return new ResultDto(ResponseCode.OK, productService.getProducts());
	}

	@RequestMapping("/sale/reserve/{amount}")
	public ResultDto reserveProduct(@RequestBody ProductDto productDto, @PathVariable("amount") Long amount, HttpSession session) {
		try {
			SaleDto saleDto = saleService.reserveItem(getOrInitializeSale(session), productDto, amount);
			session.setAttribute("currentSale", saleDto);
			return new ResultDto(ResponseCode.OK, saleDto);
		} catch (ShopException e) {
			return new ResultDto(ResponseCode.ERROR, e.getMessageKey());
		}
	}

	private SaleDto getOrInitializeSale(HttpSession session) {
		Object o = session.getAttribute("currentSale");
		SaleDto result;
		if (o != null) {
			result = (SaleDto) o;
		} else {
			result = new SaleDto();
		}
		return result;
	}

	@RequestMapping("/sale/payment")
	public ResultDto payment(@RequestBody SaleDto saleDto, HttpSession session) {
		try {
			return new ResultDto(ResponseCode.OK, saleService.payment(saleDto));
		} catch (ShopException e) {
			return new ResultDto(ResponseCode.ERROR, e.getMessageKey());
		}
	}

	@RequestMapping("/sale/reset")
	public ResultDto reset(@RequestBody SaleDto ss, HttpSession session) {
		SaleDto saleDto = getOrInitializeSale(session);
		saleService.reset(saleDto);
		session.setAttribute("currentSale", null);
		return new ResultDto(ResponseCode.OK, null);
	}

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleService saleService;
}
