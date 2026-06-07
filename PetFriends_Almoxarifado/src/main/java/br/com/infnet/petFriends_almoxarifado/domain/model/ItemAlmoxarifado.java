package br.com.infnet.petFriends_almoxarifado.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ItemAlmoxarifado {

    private Long produtoId;
    private Integer quantidade;

    public ItemAlmoxarifado(Long produtoId, Integer quantidade) {
        if (produtoId == null) {
            throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
        }
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a ser separada deve ser maior que zero.");
        }

        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }
}
