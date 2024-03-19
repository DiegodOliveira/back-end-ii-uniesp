package com.backenii.revisao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<cliente, Long> {
    Optional<cliente> findByNome(String nome);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cliente c WHERE c.nome = :nome")
    void deleteByNome(String nome);
}

