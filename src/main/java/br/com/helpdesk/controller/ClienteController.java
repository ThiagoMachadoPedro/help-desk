package br.com.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.DTO.ClienteDTO;
import br.com.helpdesk.service.ClienteServices;
import br.com.helpdesk.service.EmailServices;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

  @Autowired
  private ClienteServices services;


  @Autowired
  private EmailServices emailServices;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {

    Cliente obj = services.findById(id);
    return ResponseEntity.ok().body(new ClienteDTO(obj));
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> buscarTodos(Cliente obj) {

    List<Cliente> list = services.buscarTodos(obj);
    List<ClienteDTO> lisDTO = list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(lisDTO);
  }

  @PostMapping
  public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO ObjDTO) {


    emailServices.enviarEmailTexto(ObjDTO.getEmail(), "Cadastro realizado com Sucesso!!!",
    ObjDTO.getNome() + " Seu login é: " + ObjDTO.getEmail() + " sua senha é: " + ObjDTO.getSenha());


    Cliente newObj = services.salvar(ObjDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO objDTO) {

    Cliente obj = services.update(id, objDTO);

    return ResponseEntity.ok(new ClienteDTO(obj)); 
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ClienteDTO> deletar(@PathVariable Long id) {

    services.deletar(id);

    return ResponseEntity.noContent().build();
  }

}
