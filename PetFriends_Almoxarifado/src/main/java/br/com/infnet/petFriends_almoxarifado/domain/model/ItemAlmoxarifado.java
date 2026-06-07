package br.com.infnet.petFriends_almoxarifado.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemAlmoxarifado that = (ItemAlmoxarifado) o;
        return Objects.equals(produtoId, that.produtoId) &&
                Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, quantidade);
    }
}
