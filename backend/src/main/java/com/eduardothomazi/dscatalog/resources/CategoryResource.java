package com.eduardothomazi.dscatalog.resources;

import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoriesList = service.findAll();
        return ResponseEntity.ok().body(categoriesList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO categoryDTO = service.findById(id);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
