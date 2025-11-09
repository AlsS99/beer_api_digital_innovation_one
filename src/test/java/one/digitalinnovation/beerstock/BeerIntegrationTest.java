package one.digitalinnovation.beerstock;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static one.digitalinnovation.beerstock.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerIntegrationTest {

    private static final String BEER_API_URL_PATH = "/api/v1/beers";
    private static final long VALID_BEER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BeerRepository beerRepository;

    @BeforeEach
    void setUp() {
        // Limpa o banco H2 antes de cada teste
        beerRepository.deleteAll();
    }

    // --- Teste de Criação (POST) ---
    @Test
    void whenPOSTIsCalledThenABeerIsCreatedAndPersisted() throws Exception {
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

        mockMvc.perform(post(BEER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beerDTO)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(beerDTO.getName())));

        assertTrue(beerRepository.findByName(beerDTO.getName()).isPresent());
    }

    // --- Teste de Exclusão (DELETE) ---
    @Test
    void whenDELETEIsCalledWithValidIdThenBeerIsRemovedFromDatabase() throws Exception {
        // given: Persiste uma cerveja, CAPTURANDO o ID gerado (Isso resolve o problema)
        // 1. Cria a entidade Beer a partir do builder
        Beer beerToSave = BeerDTOBuilder.builder().build().toBeerModel();

        // 2. Salva no banco e recebe o objeto com o ID real gerado pelo H2
        Beer savedBeer = beerRepository.save(beerToSave);
        Long generatedId = savedBeer.getId();

        // 3. Verifica se a cerveja existe antes de deletar
        assertTrue(beerRepository.findById(generatedId).isPresent());

        // when / then (Executa a API de DELETE usando o ID gerado)
        mockMvc.perform(delete(BEER_API_URL_PATH + "/" + generatedId) // <-- USA O ID DINÂMICO
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()); // Espera o status de sucesso

        // 4. Verifica se o dado foi removido do Banco de Dados
        assertTrue(beerRepository.findById(generatedId).isEmpty());
    }
}