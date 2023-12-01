package br.com.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpdesk.model.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
