package br.com.helpdesk.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpdesk.model.Chamado;

public class ChamadoDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  @NotNull(message = "O campo Titulo é requirido")
  private String titulo;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now();
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento;
  @NotNull(message = "O campo Observações é requirido")
  private String observacao;
  @NotNull(message = "O campo Status é requirido")
  private Integer status;
  @NotNull(message = "O campo Prioridade é requirido")
  private Integer prioridade;
  @NotNull(message = "O campo Tecnico é requirido")
  private Long tecnico;
  @NotNull(message = "O campo Cliente é requirido")
  private Long cliente;

  private String nomeTecnico;
  private String nomeCliente;

  public ChamadoDTO() {
    super();
  }

  public ChamadoDTO(Chamado obj) {
    this.id = obj.getId();
    this.titulo = obj.getTitulo();
    this.dataAbertura = obj.getDataAbertura();
    this.dataFechamento = obj.getDataFechamento();
    this.observacao = obj.getObservacoes();
    this.status = obj.getStatus().getCodigo();
    this.prioridade = obj.getPrioridade().getCodigo();
    this.tecnico = obj.getTecnico().getId();
    this.cliente = obj.getCliente().getId();
    this.nomeTecnico = obj.getTecnico().getNome();
    this.nomeCliente = obj.getCliente().getNome();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public LocalDate getDataAbertura() {
    return dataAbertura;
  }

  public void setDataAbertura(LocalDate dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public LocalDate getDataFechamento() {
    return dataFechamento;
  }

  public void setDataFechamento(LocalDate dataFechamento) {
    this.dataFechamento = dataFechamento;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(Integer prioridade) {
    this.prioridade = prioridade;
  }

  public Long getTecnico() {
    return tecnico;
  }

  public void setTecnico(Long tecnico) {
    this.tecnico = tecnico;
  }

  public Long getCliente() {
    return cliente;
  }

  public void setCliente(Long cliente) {
    this.cliente = cliente;
  }

  public String getNomeTecnico() {
    return nomeTecnico;
  }

  public void setNomeTecnico(String nomeTecnico) {
    this.nomeTecnico = nomeTecnico;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

}
