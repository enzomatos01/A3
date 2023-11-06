package br.com.i4business.money.infrastructure.pote.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoteRepository extends JpaRepository<PoteJpaEntity, String> {

    Page<PoteJpaEntity> findAll(Specification<PoteJpaEntity> whereClause, Pageable page);

    @Query(value = "select g.id from Pote g where g.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}

