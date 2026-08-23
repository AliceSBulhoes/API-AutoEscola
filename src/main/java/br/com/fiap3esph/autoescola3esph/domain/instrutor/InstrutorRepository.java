package br.com.fiap3esph.autoescola3esph.domain.instrutor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);
}