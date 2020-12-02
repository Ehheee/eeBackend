package kaur.ee.trial.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kaur.ee.trial.data.dto.ProductDto;

@Service
@Transactional
public interface ProductService {

	public void saveProduct(ProductDto product);

	List<ProductDto> getProducts();
}
