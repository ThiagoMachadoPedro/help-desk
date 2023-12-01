package br.com.helpdesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.enums.Prioridade;
import br.com.helpdesk.enums.StatusTicket;
import br.com.helpdesk.exceptions.ObjectnotFoundException;
import br.com.helpdesk.model.Chamado;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Tecnico;
import br.com.helpdesk.model.DTO.ChamadoDTO;
import br.com.helpdesk.repository.ChamadoRepository;

@Service
public class ChamadoServices {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoServices tecnicoServices;

	@Autowired
	private ClienteServices clienteServices;

	public Chamado findById(Long id) {
		Optional<Chamado> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectnotFoundException("não localizamod esse id " + id));

	}

	public List<Chamado> findAll() {

		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {

		return repository.save(newChamado(objDTO));
	}

	/* verifica se o id do tecnico e do cliente existe */
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoServices.findById(obj.getTecnico());
		Cliente cliente = clienteServices.findById(obj.getCliente());

		/* define se o id do chamado não é nulo se for nulo quer cadastrar se vinher o id quer atualizar */
		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
/* se for igual a dois  ele pegara a  data do encerramento do chamado*/
		if (obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(StatusTicket.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacao());
		return chamado;
	}

	public Chamado update(Long id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado obj = findById(id);
		obj = newChamado(objDTO);
		return repository.save(obj);
	}

}
