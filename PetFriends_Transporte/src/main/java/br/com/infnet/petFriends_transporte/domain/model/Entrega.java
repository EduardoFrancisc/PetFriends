package br.com.infnet.petFriends_transporte.domain.model;

import br.com.infnet.petFriends_transporte.domain.enums.StatusTransporte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_original_id", nullable = false, unique = true)
    private Long pedidoOriginalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransporte status;

    @Column(name = "data_despacho", nullable = false)
    private LocalDateTime dataDespacho;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    public Entrega(Long pedidoOriginalId) {
        this.pedidoOriginalId = pedidoOriginalId;
        this.status = StatusTransporte.EM_TRANSITO;
        this.dataDespacho = LocalDateTime.now();
    }


    public void registrarRecebimento() {
        validarTransicao();
        this.status = StatusTransporte.ENTREGUE;
        this.dataConclusao = LocalDateTime.now();
    }

    public void registrarRejeicao() {
        validarTransicao();
        this.status = StatusTransporte.DEVOLVIDO;
        this.dataConclusao = LocalDateTime.now();
    }

    public void registrarExtravio() {
        validarTransicao();
        long diasEmTransito = ChronoUnit.DAYS.between(this.dataDespacho, LocalDateTime.now());
        if (diasEmTransito < 30) {
            throw new IllegalStateException("O extravio só pode ser declarado após 30 dias em trânsito.");
        }
        this.status = StatusTransporte.EXTRAVIADO;
        this.dataConclusao = LocalDateTime.now();
    }

    private void validarTransicao() {
        if (this.status != StatusTransporte.EM_TRANSITO) {
            throw new IllegalStateException("A entrega já foi concluída e não pode mudar de estado.");
        }
    }
}
