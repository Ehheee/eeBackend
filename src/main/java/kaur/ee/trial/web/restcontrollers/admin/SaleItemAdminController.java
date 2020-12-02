package kaur.ee.trial.web.restcontrollers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.dto.ResponseCode;
import kaur.ee.trial.data.dto.ResultDto;
import kaur.ee.trial.service.ProductService;

@RestController
public class SaleItemAdminController {

	@RequestMapping("/admin/save")
	public ResultDto saveProduct(@RequestBody ProductDto productDto) {
		productService.saveProduct(productDto);
		return new ResultDto(ResponseCode.OK, productDto);
	}

	@Autowired
	ProductService productService;
}
