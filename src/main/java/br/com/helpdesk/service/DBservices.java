package br.com.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpdesk.enums.Perfil;
import br.com.helpdesk.enums.Prioridade;
import br.com.helpdesk.enums.StatusTicket;
import br.com.helpdesk.model.Chamado;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Tecnico;
import br.com.helpdesk.repository.ChamadoRepository;
import br.com.helpdesk.repository.PessoaRepository;

@Service
public class DBservices {

  @Autowired
  private PessoaRepository pessoaRepository;

   @Autowired
   private ChamadoRepository chamadoRepository;

  @Autowired
  private BCryptPasswordEncoder enconder;

  public void instanciaDB() {

    Tecnico tec1 = new Tecnico(null, "Thiago Machado", "372.960.388-45", "tmachado807@gmail.com",
        enconder.encode("123"), "");
    tec1.addPerfil(Perfil.ADMIN);
    Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", enconder.encode("123"),
        null);
    Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com",
        enconder.encode("123"), null);
    Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39",
    "lee@mail.com", enconder.encode("123"), null);
    Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27",
    "linus@mail.com", enconder.encode("123"), null);

    Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74",
    "einstein@mail.com", enconder.encode("123"), null);
    Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06",
    "curie@mail.com", enconder.encode("123"), null);
    Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62",
    "darwin@mail.com", enconder.encode("123"),null);
    Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30",
    "hawking@mail.com", enconder.encode("123"),null);
    Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83",
    "planck@mail.com",  enconder.encode("123"), null);

    Chamado c1 = new Chamado(null, Prioridade.MEDIA, StatusTicket.ANDAMENTO,
    "Chamado 1", "Teste chamado 1", tec1, cli1);
    Chamado c2 = new Chamado(null, Prioridade.ALTA, StatusTicket.ABERTO, "sem internet", "Teste chamado 2", tec1, cli2);
    Chamado c3 = new Chamado(null, Prioridade.BAIXA, StatusTicket.ENCERRADO,
    "Chamado 3", "Teste chamado 3", tec2, cli1);
    Chamado c4 = new Chamado(null, Prioridade.ALTA, StatusTicket.ABERTO, "sem navegação", "Teste chamado 4", tec3, cli1);
    Chamado c5 = new Chamado(null, Prioridade.MEDIA, StatusTicket.ANDAMENTO,
    "Chamado 5", "Teste chamado 5", tec2, cli1);
    Chamado c6 = new Chamado(null, Prioridade.BAIXA, StatusTicket.ENCERRADO,
    "Chamado 7", "Teste chamado 6", tec1, cli2);

    pessoaRepository.saveAll(Arrays.asList(tec1, tec2,tec3,tec4,tec5, cli1,cli2,cli3,cli4,cli5));

     chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
  }

}
