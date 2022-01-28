package com.eduardothomazi.dscatalog.services;


import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.dto.ProductDTO;
import com.eduardothomazi.dscatalog.entities.Category;
import com.eduardothomazi.dscatalog.entities.Product;
import com.eduardothomazi.dscatalog.repositories.ProductRepository;
import com.eduardothomazi.dscatalog.services.exceptions.DataBaseException;
import com.eduardothomazi.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> products =  repository.findAll(pageRequest);
        Page<ProductDTO> productDTO = products.map(x -> new ProductDTO(x));
        return productDTO;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> obj= repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        ProductDTO productDTO = new ProductDTO(entity, entity.getCategories());
        return productDTO;
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product product = new Product(dto);
        Set<Category> cat = product.getCategories();//only to return on requisition body
        repository.save(product);
        return new ProductDTO(product, cat);//returns categories set
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = null;
        try {
            entity = repository.getById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found! Id: " + id);
        }
    }

    public void deleteById(Long id) {
        try {

            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("ID not found! ID: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("DB integrity violation!");
        }
    }

}
