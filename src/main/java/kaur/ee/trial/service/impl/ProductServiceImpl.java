package kaur.ee.trial.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kaur.ee.trial.data.db.entities.Product;
import kaur.ee.trial.data.db.repositories.ProductRepository;
import kaur.ee.trial.data.dto.ProductDto;
import kaur.ee.trial.data.mapping.ProductMapper;
import kaur.ee.trial.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void saveProduct(ProductDto productDto) {
		Product entity = ProductMapper.mapToEntity(productDto);
		productRepository.save(entity);
	}

	@Override
	public List<ProductDto> getProducts() {
		return productRepository.findAll(Sort.by("name")).stream().map(p -> ProductMapper.mapToDto(p)).collect(Collectors.toList());
	}

}
