package br.com.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.model.Tecnico;
import br.com.helpdesk.model.DTO.TecnicoDTO;
import br.com.helpdesk.service.TecnicoServices;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

  @Autowired
  private TecnicoServices tecnicoServices;

  @GetMapping(value = "/{id}")
  public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {

    Tecnico obj = tecnicoServices.findById(id);
    return ResponseEntity.ok().body(new TecnicoDTO(obj));
  }

  @GetMapping
  public ResponseEntity<List<TecnicoDTO>> buscarTodos(Tecnico obj) {

    List<Tecnico> list = tecnicoServices.buscarTodos(obj);
    List<TecnicoDTO> lisDTO = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(lisDTO);
  }

  @PreAuthorize("hasAnyRole('ADMIN')")// so admin pode acessar esse perfil
  @PostMapping
  public ResponseEntity<TecnicoDTO> createTecnico(@RequestBody @Valid TecnicoDTO objDTO) {

    Tecnico newObj = tecnicoServices.salvar(objDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
@PreAuthorize("hasAnyRole('ADMIN')")
  @PutMapping(value = "/{id}")
  public ResponseEntity<TecnicoDTO> update(@PathVariable Long id, @Valid @RequestBody TecnicoDTO objDTO) {

    Tecnico obj = tecnicoServices.update(id, objDTO);

    return ResponseEntity.ok(new TecnicoDTO(obj));
  }
@PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<TecnicoDTO> deletar(@PathVariable Long id) {

    tecnicoServices.deletar(id);

    return ResponseEntity.noContent().build();
  }

}
