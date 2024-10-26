package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.application.exception.EmailValidationException;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.FiltroDeBusca;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final ImageService imageService;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;

    @Transactional
    public void save(Restaurante restaurante) {
        validateEmail(restaurante.getEmail(), restaurante.getId());

        if (restaurante.getId() != null) {
            Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(restaurante.getId());

            if(restauranteEncontrado.isPresent()) {
                Restaurante rest = restauranteEncontrado.get();
                restaurante.setSenha(rest.getSenha());
            }
        } else {
            restaurante.encryptPassword();
            restaurante = restauranteRepository.save(restaurante);

            if (!restaurante.getArquivoLogotipo().isEmpty()) {
                restaurante.setNomeDoArquivoDoLogotipo();
                imageService.uploadLogotipo(restaurante.getArquivoLogotipo(), restaurante.getNomeDoArquivoLogotipo());
            }
        }
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

    public List<Restaurante> searchByFilter(FiltroDeBusca filtroDeBusca) {
        List<Restaurante> restaurantes = null;

        switch (filtroDeBusca.getTipoDeBusca()) {
            case TEXTO -> {
                restaurantes = restauranteRepository.findByNomeIgnoreCaseContaining(filtroDeBusca.getTexto());
            }
            case CATEGORIA -> {
                restaurantes = restauranteRepository.findByCategorias_Id(filtroDeBusca.getCategoriaId());
            }
            default -> throw new IllegalArgumentException("O Tipo de Busca " + filtroDeBusca.getTipoDeBusca()
                    + "não é suportado");
        }

        return restaurantes;
    }

}
