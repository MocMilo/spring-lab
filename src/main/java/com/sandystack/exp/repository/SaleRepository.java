package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {

}
