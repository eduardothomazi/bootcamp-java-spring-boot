package com.eduardothomazi.dscatalog.services;


import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.entities.Category;
import com.eduardothomazi.dscatalog.repositories.CategoryRepository;
import com.eduardothomazi.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> categoryList =  repository.findAll();
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        return categoryDTOList;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj= repository.findById(id);
        Category category = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        CategoryDTO categoryDTO = new CategoryDTO(category);
        return categoryDTO;
    }
}
