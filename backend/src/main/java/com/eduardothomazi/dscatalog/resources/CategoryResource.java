package com.eduardothomazi.dscatalog.resources;

import com.eduardothomazi.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(new Category(1l, "Books"));
        categoriesList.add(new Category(2l, "Electronics"));
        return ResponseEntity.ok().body(categoriesList);
    }
}
