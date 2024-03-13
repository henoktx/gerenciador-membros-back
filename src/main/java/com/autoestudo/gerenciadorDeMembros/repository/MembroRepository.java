package com.autoestudo.gerenciadorDeMembros.repository;

import com.autoestudo.gerenciadorDeMembros.entity.membro.MembroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends CrudRepository<MembroEntity, Long> {
    public <T> Page<T> findByNomeCompletoContainsIgnoreCase(Pageable pageable, String nome, Class<T> type);
    public <T> Optional<T> findById(long id, Class<T> type);
}
