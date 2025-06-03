package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Pedido;
import com.example.demo.model.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description="DTO que representa um pedido")
public class PedidoDTO {

	@Valid
    @NotNull(message = "O cliente não pode estar vazio")
    @Schema(description="Nome do cliente que fez o pedido")
    private String cliente;

	@Valid
    @NotEmpty(message = "A lista de produtos não pode estar vazia")
    @Schema(description="Lista de produtos do pedido")
    private List<PedidoProdutoDTO> itens;

	@Valid
    @NotNull(message = "O status do pedido não pode estar vazio")
    @Schema(description="Status atual do pedido")
    private Status status;

	@Valid
    @NotNull(message = "A data do pedido não pode estar vazia")
    @Schema(description="Data em que o pedido foi realizado")
    private LocalDate dataPedido;

    @Schema(description="Valor total do pedido")
	private Double valorTotal;

    @Schema(description="Cupom de desconto aplicado ao pedido")
    private String cupom;

    public PedidoDTO(Pedido pedido) {
        this.cliente = pedido.getCliente().getNome();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens().stream()
            .map(item -> new PedidoProdutoDTO(
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getValorVenda(),
                item.getDesconto()
            ))
            .collect(Collectors.toList());
		this.valorTotal = pedido.getValorTotal();
    }

    public PedidoDTO() {}

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<PedidoProdutoDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProdutoDTO> itens) {
        this.itens = itens;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }
}

