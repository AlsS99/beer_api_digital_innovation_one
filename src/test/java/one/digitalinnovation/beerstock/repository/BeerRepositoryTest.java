package one.digitalinnovation.beerstock.repository;

import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de Integração para a camada Repository.
 * Usa o @DataJpaTest para carregar apenas a configuração JPA
 * e o banco de dados H2 em memória.
 */
@DataJpaTest
public class BeerRepositoryTest {

    // O repositório que queremos testar
    @Autowired
    private BeerRepository beerRepository;

    // Utilitário para persistir dados diretamente no contexto de teste
    @Autowired
    private TestEntityManager entityManager;

    private Beer createTestBeer() {
        return Beer.builder()
                .name("Test Lager")
                .brand("Repo Brand")
                .max(100)
                .quantity(15)
                .type(BeerType.LAGER)
                .build();
    }

    @Test
    void whenFindByNameIsCalledThenReturnBeer() {
        // given: Persiste uma cerveja diretamente no banco H2
        Beer expectedBeer = createTestBeer();
        entityManager.persistAndFlush(expectedBeer);

        // when: Executa o método do Repository
        Optional<Beer> foundBeerOptional = beerRepository.findByName(expectedBeer.getName());

        // then: Verifica se a cerveja foi encontrada
        assertTrue(foundBeerOptional.isPresent());
        assertThat(foundBeerOptional.get().getName(), is(expectedBeer.getName()));
        assertThat(foundBeerOptional.get().getQuantity(), is(expectedBeer.getQuantity()));
    }

    @Test
    void whenFindByNameIsCalledWithNonExistingNameThenReturnEmpty() {
        // given: Nenhuma cerveja persistida com este nome

        // when: Executa o método do Repository com um nome que não existe
        Optional<Beer> foundBeerOptional = beerRepository.findByName("Cerveja Inexistente");

        // then: Verifica se o Optional está vazio
        assertTrue(foundBeerOptional.isEmpty());
    }

    @Test
    void whenSaveIsCalledThenPersistBeer() {
        // given
        Beer newBeer = createTestBeer();

        // when: Chama o método save
        Beer savedBeer = beerRepository.save(newBeer);

        // then: Verifica se a cerveja foi salva (agora tem ID) e pode ser recuperada
        Optional<Beer> foundBeer = beerRepository.findById(savedBeer.getId());

        assertTrue(foundBeer.isPresent());
        assertThat(foundBeer.get().getName(), is(newBeer.getName()));
    }
}