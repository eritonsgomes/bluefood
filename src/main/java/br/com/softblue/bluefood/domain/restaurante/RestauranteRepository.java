package br.com.softblue.bluefood.domain.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Restaurante findByEmail(String email);
    List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
    List<Restaurante> findByCategorias_Id(Long id);

}
