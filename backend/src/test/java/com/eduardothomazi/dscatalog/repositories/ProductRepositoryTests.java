package com.eduardothomazi.dscatalog.repositories;

import com.eduardothomazi.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        Long existentId = 1l;

        repository.deleteById(existentId);

        Optional<Product> result = repository.findById(existentId);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()-> {
            Long notExistentId = 10000l;

            repository.deleteById(notExistentId);

        });
    }
}
