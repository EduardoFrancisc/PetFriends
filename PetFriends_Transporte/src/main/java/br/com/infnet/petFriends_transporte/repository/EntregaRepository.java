package br.com.infnet.petFriends_transporte.repository;

import br.com.infnet.petFriends_transporte.domain.enums.StatusTransporte;
import br.com.infnet.petFriends_transporte.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    Optional<Entrega> findByPedidoOriginalId(Long pedidoOriginalId);
    List<Entrega> findByStatusAndDataDespachoBefore(StatusTransporte status, LocalDateTime dataLimite);
}
