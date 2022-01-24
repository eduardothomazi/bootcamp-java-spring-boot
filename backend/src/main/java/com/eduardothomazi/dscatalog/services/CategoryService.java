package com.eduardothomazi.dscatalog.services;


import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.entities.Category;
import com.eduardothomazi.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
