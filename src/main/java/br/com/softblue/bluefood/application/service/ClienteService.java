package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.application.exception.EmailValidationException;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;

    @Transactional
    public void save(Cliente cliente) {
        validateEmail(cliente.getEmail(), cliente.getId());

        if (cliente.getId() != null) {
            Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());
            clienteEncontrado.ifPresent(cli -> cliente.setSenha(cli.getSenha()));
        } else {
            cliente.encryptPassword();
        }

        clienteRepository.save(cliente);
    }

    public void validateEmail(String email, Long id) throws EmailValidationException {
        Restaurante restauranteEncontrado = restauranteRepository.findByEmail(email);

        if (restauranteEncontrado != null) {
            throw new EmailValidationException("O E-mail está sendo utilizado");
        }

        Cliente clienteEncontrado = clienteRepository.findByEmail(email);

        if (clienteEncontrado != null) {
            boolean isIdClienteEncontradoEqualAoId = clienteEncontrado.getId().equals(id);

            if (!isIdClienteEncontradoEqualAoId) {
                throw new EmailValidationException("O E-mail está sendo utilizado");
            }
        }
    }

}
