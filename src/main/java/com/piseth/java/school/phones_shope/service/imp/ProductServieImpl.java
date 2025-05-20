package com.piseth.java.school.phones_shope.service.imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.ProductMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory;
import com.piseth.java.school.phones_shope.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phones_shope.repository.ProductRepository;
import com.piseth.java.school.phones_shope.service.ProductServie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServieImpl implements ProductServie {
	private final ProductRepository productRepository;
	private final ProductMapper ProductMapper;
	private final ProductImportHistoryRepository productHistoryRepo;

	@Override
	public Product createProduct(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getbyId(Integer id) {
		return productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not found this brand, id : " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public void importProduct(ProductImportDTO dto) {
		// import product
		Product product = getbyId(dto.getProductId());
		if (product.getAvailableUnit() != null) {
			product.setAvailableUnit(product.getAvailableUnit() + dto.getImportUnit());
		} else {
			product.setAvailableUnit(dto.getImportUnit());
		}

		productRepository.save(product);
		// save to product history
		ProductImportHistory productHistory = ProductMapper.toProductIpmortHistory(dto);
		productHistory.setProduct(product);
		productHistoryRepo.save(productHistory);
	}

	@Override
	public void setSalePrice(Integer id, BigDecimal price) {
		Product product = getbyId(id);
		product.setSalePrice(price);
		productRepository.save(product);
	}

	@Override
	public List<ProductImportDTO> uploadProduct(MultipartFile file) {
		List<ProductImportDTO> loadProduct = new ArrayList<>();
		if (isExelFile(file)) {
			try {
				XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream());
				XSSFSheet sheet = workBook.getSheetAt(0);
				boolean isHeaderRow = true;
				for (Row row : sheet) {
					if (isHeaderRow) {
						isHeaderRow = false;
						continue;
					}
					Iterator<Cell> cellIterator = row.iterator(); // cell in row => 1 row have more cell
					int cellIndex = 0;
					ProductImportDTO product = new ProductImportDTO();
					Integer modelId = 0, colorId = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
						case 0:
							modelId = (int) cell.getNumericCellValue();
							break;
						case 1:
							colorId = (int) cell.getNumericCellValue();
							break;
						case 2:
							product.setImportUnit((int) cell.getNumericCellValue());
							break;
						case 3:
							double price = cell.getNumericCellValue();
							product.setPricePerUnit(BigDecimal.valueOf(price));
							break;
						case 4:
							product.setImportDate(cell.getLocalDateTimeCellValue());
							break;
						}
						cellIndex++;
					}
					try {
						if (modelId != 0 && colorId != 0) {
							Product pd = findByModel_ModelIdAndColor_ColorId(modelId, colorId);
							product.setProductId(pd.getId());
							importProduct(product);
							loadProduct.add(product);
						}
					} catch (ResourceNotFoundException e) {
						System.out.println("row : " + row.getRowNum() + e.getLocalizedMessage());
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String contentType = file.getContentType();
			throw new ResourceNotFoundException("Wrong file: " + contentType, HttpStatus.BAD_REQUEST);
		}
		return loadProduct;
	}

	@Override
	public boolean isExelFile(MultipartFile file) {
		String contentType = file.getContentType();
		return contentType != null && (contentType.equals("application/vnd.ms-excel")
				|| contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	}

	@Override
	public Product findByModel_ModelIdAndColor_ColorId(Integer modelId, Integer colorId) {
		return productRepository.findByModel_ModelIdAndColor_ColorId(modelId, colorId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"not found product, color id: %d and model id: %d".formatted(modelId, colorId),
						HttpStatus.NOT_FOUND));
	}

}
