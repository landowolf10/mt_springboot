package com.example.mt.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer> {
    List<Clients> getClientByCategory(String category);
    List<Clients> getClientByPremium(String isPremium);
    Integer countByCategory(String category);
}