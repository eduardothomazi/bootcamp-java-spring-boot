package com.eduardothomazi.dscatalog.resources;

import com.eduardothomazi.dscatalog.dto.ProductDTO;
import com.eduardothomazi.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO productDTO = service.findById(id);
        return ResponseEntity.ok().body(productDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        ProductDTO productDTO = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
