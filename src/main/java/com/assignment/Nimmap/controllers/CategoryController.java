package com.assignment.Nimmap.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Nimmap.enums.MessageCodes;
import com.assignment.Nimmap.exceptions.ApplicationException;
import com.assignment.Nimmap.model.BaseResponse;
import com.assignment.Nimmap.model.CategoryModel;
import com.assignment.Nimmap.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createCategory(@Valid @RequestBody CategoryModel categoryModel) {
    	LOGGER.info("-------CategoryController /createCategory()--------- Started ");
        CategoryModel savedModel = null;
        try {
            savedModel = categoryService.saveCategory(categoryModel);  	       	
        }catch(ApplicationException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }catch(EntityNotFoundException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        	       	
        }
        catch (Exception e) {
            LOGGER.error("Exception :", e);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    "Error while creating new Category!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(savedModel), null,MessageCodes.MESSAGE.getStrValue());
        LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCategory(@Valid @RequestBody CategoryModel categoryModel, @PathVariable ("id") Long id) {
    	
    	LOGGER.info("-------CategoryController /createCategory()--------- Started ");
        CategoryModel updated = null;
        try {
        	 updated = categoryService.updateCategory(categoryModel, id);  	       	
        }catch(ApplicationException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }catch(EntityNotFoundException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        	       	
        }
        catch (Exception e) {
            LOGGER.error("Exception :", e);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    "Error while creating new Category!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(updated), null,MessageCodes.MESSAGE.getStrValue());
        LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable("id") Long id) {
    	LOGGER.info("-------CategoryController /createCategory()--------- Started ");
        try {
        	categoryService.deleteCategory(id);  	       	
        }catch(ApplicationException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }catch(EntityNotFoundException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        	       	
        }
        catch (Exception e) {
            LOGGER.error("Exception :", e);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    "Error while creating new Category!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), null, null, "Deleted successfully");
        LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getByIdCategory(@PathVariable("id") Long id) {
    	LOGGER.info("-------CategoryController /createCategory()--------- Started ");
        CategoryModel getid = null;
        try {
        	 getid = categoryService.getCategoryById(id);  	       	
        }catch(ApplicationException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }catch(EntityNotFoundException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        	       	
        }
        catch (Exception e) {
            LOGGER.error("Exception :", e);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    "Error while creating new Category!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(getid), null,MessageCodes.MESSAGE.getStrValue());
        LOGGER.info("-------CategoryController /createCategory()--------- End " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllCategories(Pageable pageable) {
        LOGGER.info("-------CategoryController /getAllCategories()--------- Started ");
        Page<CategoryModel> models = null;
        try {
        	models = categoryService.getAllCategories(pageable);
        	
        }catch(ApplicationException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }catch(EntityNotFoundException ex) {
        	LOGGER.error("Exception :", ex);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), null, null,
                    ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        	       	
        }catch (Exception e) {
            LOGGER.error("Exception :", e);
            BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, null,
                    "Error while getting the Category");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BaseResponse response = new BaseResponse(String.valueOf(HttpStatus.OK.value()), List.of(models), null,MessageCodes.MESSAGE.getStrValue());
        LOGGER.info("-------CategoryController /getAllCategories()--------- End " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
