package com.assignment.Nimmap.controllers;

import com.assignment.Nimmap.enums.MessageCodes;
import com.assignment.Nimmap.exceptions.ApplicationException;
import com.assignment.Nimmap.model.BaseResponse;
import com.assignment.Nimmap.model.ProductModel;
import com.assignment.Nimmap.services.ProductService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<BaseResponse> getAllProductsByCategory(@PathVariable("categoryId") Long categoryId,
			Pageable pageable) {

		LOGGER.info("-------ProductController /getAllProductsByCategory()--------- Started ");
		Page<ProductModel> products = null;
		try {
			products = productService.getAllProductsByCategory(categoryId, pageable);
		} catch (ApplicationException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (EntityNotFoundException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error("Exception :", e);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, "Error while creating new Blog!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(products), null,
				MessageCodes.MESSAGE.getStrValue());
		LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<BaseResponse> getAllProducts(Pageable pageable) {

	    LOGGER.info("-------ProductController /getAllProducts()--------- Started ");
	    Page<ProductModel> products = null;
	    try {
	        products = productService.getAllProducts(pageable);
	    } catch (ApplicationException ex) {
	        LOGGER.error("Exception :", ex);
	        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
	                null, ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	    } catch (Exception e) {
	        LOGGER.error("Exception :", e);
	        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
	                null, "Error while fetching products!");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(products), null,
	            MessageCodes.MESSAGE.getStrValue());
	    LOGGER.info("-------ProductController /getAllProducts()--------- End " + response.toString());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse> getProductById(@PathVariable("id") Long id) {
		LOGGER.info("-------ProductController /getAllProductsByCategory()--------- Started ");
		ProductModel products = null;
		try {
			products = productService.getProductById(id);
		} catch (ApplicationException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (EntityNotFoundException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error("Exception :", e);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, "Error while creating new Blog!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(products), null,
				MessageCodes.MESSAGE.getStrValue());
		LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());

		return response != null ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/")
	public ResponseEntity<BaseResponse> createProduct(@Valid @RequestBody ProductModel productModel) {
		LOGGER.info("-------ProductController /getAllProductsByCategory()--------- Started ");
		ProductModel createdProduct = null;
		try {
			createdProduct = productService.saveProduct(productModel);
		} catch (ApplicationException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (EntityNotFoundException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error("Exception :", e);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, "Error while creating new Blog!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(createdProduct), null,
				MessageCodes.MESSAGE.getStrValue());
		LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse> updateProduct(@PathVariable("id") Long id,
			@Valid @RequestBody ProductModel productModel) {
		LOGGER.info("-------ProductController /getAllProductsByCategory()--------- Started ");
		ProductModel updatedProduct = null;
		try {
			updatedProduct = productService.updateProduct(id, productModel);
		} catch (ApplicationException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (EntityNotFoundException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error("Exception :", e);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, "Error while creating new Product!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(updatedProduct), null,
				MessageCodes.MESSAGE.getStrValue());
		LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());

		return response != null ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponse> deleteProduct(@PathVariable("id") Long id) {
		LOGGER.info("-------ProductController /getAllProductsByCategory()--------- Started ");

		try {
			productService.deleteProduct(id);

		} catch (ApplicationException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (EntityNotFoundException ex) {
			LOGGER.error("Exception :", ex);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error("Exception :", e);
			BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null,
					null, "Error while creating new Blog!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), null, null, "Deleted successfully");
		LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
