package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estrutura padrão para respostas de erro da API")
public class ErroResposta {

	@Schema(description = "Código HTTP da resposta", example = "400")
	private Integer status;
	
	@Schema(description = "Mensagem de erro genérica", example = "Existem campos inválidos, confira o preenchimento")
	private String titulo;
	
	@Schema(description = "Data e hora do erro")
	private LocalDateTime dataHora;
	
	@Schema(description = "Lista de detalhes sobre os erros de validação")
	private List<String> erros;

	public ErroResposta(Integer status, String titulo, LocalDateTime dataHora, List<String> erros) {
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.erros = erros;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}