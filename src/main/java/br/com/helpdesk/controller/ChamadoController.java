package br.com.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.model.Chamado;
import br.com.helpdesk.model.DTO.ChamadoDTO;
import br.com.helpdesk.service.ChamadoServices;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

  @Autowired
  private ChamadoServices services;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {

    Chamado obj = services.findById(id);

    return ResponseEntity.ok().body(new ChamadoDTO(obj));
  }

  @GetMapping
  public ResponseEntity<List<ChamadoDTO>> findAll() {

    List<Chamado> obj = services.findAll();
    List<ChamadoDTO> objDTO = obj.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());

    return ResponseEntity.ok().body(objDTO);

  }

  @PostMapping
  public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO objDTO) {

    Chamado obj = services.create(objDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @Valid @RequestBody ChamadoDTO objDTO) {
    Chamado newObj = services.update(id, objDTO);
    return ResponseEntity.ok().body(new ChamadoDTO(newObj));
  }

}
