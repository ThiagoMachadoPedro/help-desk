package br.com.helpdesk.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpdesk.enums.Perfil;
import br.com.helpdesk.model.Tecnico;

public class TecnicoDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  protected Long id;
  @NotNull(message = "O campo NOME é Obrigatorio")
  protected String nome;
  @CPF
  @NotNull(message = "O campo CPF é Obrigatorio")
  protected String cpf;
  @NotNull(message = "O campo E-MAIL é Obrigatorio")
  protected String email;
  @NotNull(message = "O campo SENHA é Obrigatorio")
  protected String senha;
  protected String nomeImagem;


  public String getNomeImagem() {
    return nomeImagem;
  }

  public void setNomeImagem(String nomeImagem) {
    this.nomeImagem = nomeImagem;
  }

  protected Set<Integer> perfis = new HashSet<>();

  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate dataCriacao = LocalDate.now();

  public TecnicoDTO() {
    super();
    addPerfis(Perfil.CLIENTE);
  }

  public TecnicoDTO(Tecnico obj) {
    super();
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    /* Aqui esta convertendo o interget para lista de set perfil */
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
    addPerfis(Perfil.CLIENTE);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Set<Perfil> getPerfis() {
    return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
  }

  /* convertendo integer em perfil */
  public void addPerfis(Perfil perfis) {
    this.perfis.add(perfis.getCodigo());
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

}
