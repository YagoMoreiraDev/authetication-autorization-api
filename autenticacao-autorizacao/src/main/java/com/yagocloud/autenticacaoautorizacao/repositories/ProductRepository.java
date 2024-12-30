package com.yagocloud.autenticacaoautorizacao.repositories;

import com.yagocloud.autenticacaoautorizacao.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
