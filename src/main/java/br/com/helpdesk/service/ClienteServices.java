package br.com.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpdesk.exceptions.ObjectnotFoundException;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Pessoa;
import br.com.helpdesk.model.DTO.ClienteDTO;
import br.com.helpdesk.repository.ClienteRepository;
import br.com.helpdesk.repository.PessoaRepository;

@Service
public class ClienteServices {


 

  @Autowired
  private ClienteRepository repository;


  @Autowired
  private PessoaRepository pessoaRepository;


  @Autowired
  private BCryptPasswordEncoder enconder;

  public Cliente findById(Long id) {
    Optional<Cliente> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectnotFoundException("OBEJETO NÂO ENCONTRADO id " + id));

  }

  public List<Cliente> buscarTodos(Cliente obj) {

    return repository.findAll();
  }

  public Cliente salvar(ClienteDTO obj) {
    /* passar id nulo caso usuario passe o id para obj */
    obj.setId(null);
    obj.setSenha(enconder.encode(obj.getSenha()));
    validaPorCpfEmail(obj);
    Cliente newObj = new Cliente(obj);
    return repository.save(newObj);
  }
/* verifica se ja tem o email e o cpf ja cadastrado */
  private void validaPorCpfEmail(ClienteDTO objDTO) {

  Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
  if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
 throw new DataIntegrityViolationException("CPF ja cadastrado, verifique os dados!");
  }
obj =pessoaRepository.findByEmail(objDTO.getEmail());
if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
   throw new DataIntegrityViolationException("Email ja cadastrado, verifique os dados!");

}


  }
/* pega o tecnico po id e o dto seta o id valida campos  */
  public Cliente update(Long id, @Valid ClienteDTO objDTO) {
    objDTO.setId(id);
    Cliente obj = findById(id);

    if(!objDTO.getSenha().equals(obj.getSenha())){
      objDTO.setSenha(enconder.encode(objDTO.getSenha()));
 }

    validaPorCpfEmail(objDTO);
    obj = new Cliente(objDTO);
    return repository.save(obj);
  }

  public void deletar(Long id) {

 Cliente obj = findById(id);
 /* não permite deletar com ordens de chamado associadas */
 if(obj.getChamados().size() > 0){
    throw new DataIntegrityViolationException("Cliente possui ordens de servido ativas!!!");
 }
    repository.deleteById(id);
  }

}
