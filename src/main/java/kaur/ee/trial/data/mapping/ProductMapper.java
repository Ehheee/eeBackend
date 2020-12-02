package kaur.ee.trial.data.mapping;

import kaur.ee.trial.data.db.entities.Product;
import kaur.ee.trial.data.dto.ProductDto;

public class ProductMapper {

	public static ProductDto mapToDto(Product entity) {
		ProductDto result = new ProductDto();
		result.setId(entity.getId());
		result.setAmount(entity.getAmount());
		result.setName(entity.getName());
		result.setPrice(entity.getPrice());
		return result;
	}
	
	public static Product mapToEntity(ProductDto dto) {
		Product result = new Product();
		result.setAmount(dto.getAmount());
		result.setId(dto.getId());
		result.setName(dto.getName());
		result.setPrice(dto.getPrice());
		return result;
	}
}
