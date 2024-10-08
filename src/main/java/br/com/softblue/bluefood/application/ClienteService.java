package br.com.softblue.bluefood.application;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

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
        Cliente clienteEncontrado = clienteRepository.findByEmail(email);

        if (clienteEncontrado != null) {
            boolean isIdClienteEncontradoEqualAoId = clienteEncontrado.getId().equals(id);

            if (!isIdClienteEncontradoEqualAoId) {
                throw new EmailValidationException("O E-mail está sendo utilizado");
            }
        }
    }

}
