package br.com.infnet.petFriends_almoxarifado.repository;

import br.com.infnet.petFriends_almoxarifado.domain.model.PedidoAlmoxarifado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PedidoAlmoxarifadoRepository extends JpaRepository<PedidoAlmoxarifado, Long> {
    Optional<PedidoAlmoxarifado> findByPedidoOriginalId(Long pedidoOriginalId);
}
