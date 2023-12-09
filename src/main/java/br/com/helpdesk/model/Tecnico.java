package br.com.helpdesk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.helpdesk.enums.Perfil;
import br.com.helpdesk.model.DTO.TecnicoDTO;

@Entity
public class Tecnico  extends Pessoa{

  private static final long serialVersionUID = 1L;


  @JsonIgnore
  @OneToMany(mappedBy = "tecnico")
  List<Chamado>chamados = new ArrayList<>();


public Tecnico(){
  super();
  addPerfil(Perfil.CLIENTE);
}


  public Tecnico(List<Chamado> chamados) {
    this.chamados = chamados;
  }


  public Tecnico(Long id, String nome, String cpf, String email, String senha,String nomeImagem) {
    super(id, nome, cpf, email, senha, nomeImagem);
      addPerfil(Perfil.CLIENTE);
  }


  public List<Chamado> getChamados() {
    return chamados;
  }


  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }

  /*para fazer o post de dto para objeto */

    public Tecnico(TecnicoDTO obj) {
    super();
    this.id = obj.getId();
    this.nomeImagem=obj.getNomeImagem();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    /* Aqui esta convertendo o interget para lista de set perfil */
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }


}
