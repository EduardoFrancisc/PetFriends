package br.com.infnet.petFriends_almoxarifado.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ItemAlmoxarifado {
    private Long produtoId;
    private Integer quantidade;
}
