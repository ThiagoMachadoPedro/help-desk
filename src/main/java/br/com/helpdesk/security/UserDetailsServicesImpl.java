package br.com.helpdesk.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.helpdesk.model.Pessoa;
import br.com.helpdesk.repository.PessoaRepository;


@Service
public class UserDetailsServicesImpl implements UserDetailsService {


@Autowired
private PessoaRepository repository;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
// busca o email no banco
Optional<Pessoa> user = repository.findByEmail(email);
// se for presente no banco
		if(user.isPresent()) {
      /* retorno useSS class ontem o email senha e autoridades dessa pessoa */
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
		}
    /* se não achar retorna mensagem formatada por cliente */
		throw new UsernameNotFoundException(email);
	}


}
