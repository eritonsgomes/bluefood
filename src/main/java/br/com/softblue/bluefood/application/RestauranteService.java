package br.com.softblue.bluefood.application;

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
public class RestauranteService {

    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;

    @Transactional
    public void save(Restaurante restaurante) {
        validateEmail(restaurante.getEmail(), restaurante.getId());

        if (restaurante.getId() != null) {
            Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(restaurante.getId());
            restauranteEncontrado.ifPresent(cli -> restaurante.setSenha(cli.getSenha()));
        } else {
            restaurante.encryptPassword();
        }

        restauranteRepository.save(restaurante);
    }

    public void validateEmail(String email, Long id) throws EmailValidationException {
        Cliente clienteEncontrado = clienteRepository.findByEmail(email);

        if (clienteEncontrado != null) {
            throw new EmailValidationException("O E-mail está sendo utilizado");
        }

        Restaurante restauranteEncontrado = restauranteRepository.findByEmail(email);

        if (restauranteEncontrado != null) {
            boolean isIdRestauranteEncontradoEqualAoId = restauranteEncontrado.getId().equals(id);

            if (!isIdRestauranteEncontradoEqualAoId) {
                throw new EmailValidationException("O E-mail está sendo utilizado");
            }
        }
    }

}
