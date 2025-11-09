// src/test/java/one/digitalinnovation/beerstock/builder/BeerDTOBuilder.java (ou onde estiver sua classe builder)

package one.digitalinnovation.beerstock.builder;

import lombok.Builder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;

@Builder
public class BeerDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Brahma";

    @Builder.Default
    private String brand = "Ambev";

    @Builder.Default
    private int max = 50;

    @Builder.Default
    private int quantity = 10;

    @Builder.Default
    private BeerType type = BeerType.LAGER;

    public BeerDTO toBeerDTO() {
        return new BeerDTO(
                id,
                name,
                brand,
                max,
                quantity,
                type
        );
    }

    /**
     * NOVO MÉTODO: Converte o Builder para a Entidade Beer (Model)
     * Necessário para os Testes de Integração que usam o Repository.
     */
    public Beer toBeerModel() {
        return new Beer(
                id,
                name,
                brand,
                max,
                quantity,
                type
        );
    }
}