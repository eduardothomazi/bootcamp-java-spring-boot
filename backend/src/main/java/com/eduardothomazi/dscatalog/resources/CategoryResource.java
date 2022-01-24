package com.eduardothomazi.dscatalog.resources;

import com.eduardothomazi.dscatalog.entities.Category;
import com.eduardothomazi.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categoriesList = service.findAll();
        return ResponseEntity.ok().body(categoriesList);
    }
}
