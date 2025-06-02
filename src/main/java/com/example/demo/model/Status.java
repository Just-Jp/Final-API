package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status atual do pedido")
public enum Status {
	@Schema(description = "Pedido foi realizado mas ainda não começou a ser processado")
    PENDENTE,
    
    @Schema(description = "Pedido está em fase de preparação ou entrega")
    EM_ANDAMENTO,
    
    @Schema(description = "Pedido foi entregue ao cliente")
    ENTREGUE,
    
    @Schema(description = "Pedido foi cancelado")
    CANCELADO
}
