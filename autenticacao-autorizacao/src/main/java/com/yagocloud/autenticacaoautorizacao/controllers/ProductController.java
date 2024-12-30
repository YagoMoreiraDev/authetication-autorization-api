package com.yagocloud.autenticacaoautorizacao.controllers;

import com.yagocloud.autenticacaoautorizacao.domain.product.Product;
import com.yagocloud.autenticacaoautorizacao.domain.product.ProductRequestDTO;
import com.yagocloud.autenticacaoautorizacao.domain.product.ProductResponseDTO;
import com.yagocloud.autenticacaoautorizacao.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @Transactional
    public void adicionarProduto(@RequestBody ProductRequestDTO requestDTO) {
        productRepository.save(new Product(requestDTO));
    }

    @GetMapping
    public List<ProductResponseDTO> listarTodosProdutos() {
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();

    }
}
