package com.eduardothomazi.dscatalog.services;


import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.entities.Category;
import com.eduardothomazi.dscatalog.repositories.CategoryRepository;
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

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<Category> categoryList =  repository.findAll(pageRequest);
        Page<CategoryDTO> categoryDTOList = categoryList.map(x -> new CategoryDTO(x));
        return categoryDTOList;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj= repository.findById(id);
        Category category = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        CategoryDTO categoryDTO = new CategoryDTO(category);
        return categoryDTO;
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        repository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category entity = null;
        try {
            entity = repository.getById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
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
