package br.com.infnet.petFriends_almoxarifado.domain.model;

import br.com.infnet.petFriends_almoxarifado.domain.enums.StatusPreparacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos_almoxarifado")
public class PedidoAlmoxarifado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_original_id", nullable = false, unique = true)
    private Long pedidoOriginalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPreparacao status;

    @Column(name = "data_recebimento", nullable = false)
    private LocalDateTime dataRecebimento;

    @ElementCollection
    @CollectionTable(name = "itens_almoxarifado", joinColumns = @JoinColumn(name = "pedido_almoxarifado_id"))
    private List<ItemAlmoxarifado> itens = new ArrayList<>();

    public PedidoAlmoxarifado(Long pedidoOriginalId, List<ItemAlmoxarifado> itens) {
        this.pedidoOriginalId = pedidoOriginalId;
        this.itens = itens;
        this.status = StatusPreparacao.RECEBIDO;
        this.dataRecebimento = LocalDateTime.now();
    }

    public void iniciarPreparacao() {
        this.status = StatusPreparacao.EM_PREPARACAO;
    }

    public void concluirPreparacao() {
        this.status = StatusPreparacao.PRONTO_PARA_DESPACHO;
    }
}

