package com.artsgard.hotelbookingbackend.repository;

import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
     Optional<ClientEntity> findByUsername(String client);
}
