package com.autoestudo.gerenciadorDeMembros.repository;

import com.autoestudo.gerenciadorDeMembros.entity.membro.MembroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiaconoRepository extends CrudRepository<MembroEntity, Long> {
    public <T> Page<T> findByDiaconoResponsavelNull(Pageable pageable, Class<T> type);
    public <T> Page<T> findByDiaconoResponsavelId(Pageable pageable, Long id, Class<T> type);
}
