package br.com.helpdesk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.helpdesk.enums.Perfil;
import br.com.helpdesk.model.DTO.ClienteDTO;

@Entity
public class Cliente extends Pessoa {
  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  List<Chamado> chamados = new ArrayList<>();

  public Cliente() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  public Cliente(List<Chamado> chamados) {
    this.chamados = chamados;
  }

  public Cliente(Long id, String nome, String cpf, String email, String senha,String nomeImagem) {
    super(id, nome, cpf, email, senha, nomeImagem);

    addPerfil(Perfil.CLIENTE);
  }

  public List<Chamado> getChamados() {
    return chamados;
  }

  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }

  /* para fazer o post de dto para objeto */

  public Cliente(ClienteDTO obj) {
    super();
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    /* Aqui esta convertendo o interget para lista de set perfil */
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }

}
