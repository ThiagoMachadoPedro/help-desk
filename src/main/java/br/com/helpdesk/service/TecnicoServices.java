package br.com.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpdesk.exceptions.ObjectnotFoundException;
import br.com.helpdesk.model.Pessoa;
import br.com.helpdesk.model.Tecnico;
import br.com.helpdesk.model.DTO.TecnicoDTO;
import br.com.helpdesk.repository.PessoaRepository;
import br.com.helpdesk.repository.TecnicoRepository;

@Service
public class TecnicoServices {


  

  @Autowired
  private TecnicoRepository tecnicorepository;

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private BCryptPasswordEncoder enconder;

  public Tecnico findById(Long id) {
    Optional<Tecnico> obj = tecnicorepository.findById(id);
    return obj.orElseThrow(() -> new ObjectnotFoundException("OBEJETO NÂO ENCONTRADO id " + id));

  }

  public List<Tecnico> buscarTodos(Tecnico obj) {

    return tecnicorepository.findAll();
  }

  public Tecnico salvar(TecnicoDTO obj) {
    /* passar id nulo caso usuario passe o id para obj */
    obj.setId(null);
    obj.setSenha(enconder.encode(obj.getSenha()));
    validaPorCpfEmail(obj);
    Tecnico newObj = new Tecnico(obj);
    return tecnicorepository.save(newObj);
  }

  /* verifica se ja tem o email e o cpf ja cadastrado */
  private void validaPorCpfEmail(TecnicoDTO objDTO) {

    Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("CPF ja cadastrado, verifique os dados!");
    }
    obj = pessoaRepository.findByEmail(objDTO.getEmail());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("Email ja cadastrado, verifique os dados!");

    }

  }

  /* pega o tecnico po id e o dto seta o id valida campos */
  public Tecnico update(Long id, @Valid TecnicoDTO objDTO) {
    objDTO.setId(id);
    Tecnico obj = findById(id);
// verifica se foi alterado a senha
    if(!objDTO.getSenha().equals(obj.getSenha())){
         objDTO.setSenha(enconder.encode(objDTO.getSenha()));
    }

    validaPorCpfEmail(objDTO);
    obj = new Tecnico(objDTO);
    return tecnicorepository.save(obj);
  }

  public void deletar(Long id) {

    Tecnico obj = findById(id);
    if (obj.getChamados().size() > 0) {
      throw new DataIntegrityViolationException("Tecnico possui ordens de servido ativas!!!");
    }
    tecnicorepository.deleteById(id);
  }

}
