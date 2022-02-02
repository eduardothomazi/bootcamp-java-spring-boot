package com.eduardothomazi.dscatalog.resources;

import com.eduardothomazi.dscatalog.dto.CategoryDTO;
import com.eduardothomazi.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable){
        Page<CategoryDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        CategoryDTO categoryDTO = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
