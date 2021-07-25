import io.restassured.http.ContentType;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

class TesteCliente {

    String urlApiCliente = "http://localhost:8080";
    String endpointCadastroCliente = "/cliente";

    @Test
    @DisplayName("Quando pegar eu todos os clientes na primeira vez, então a lista deve retornar vazia")
    void pegaTodosOsClientes() {

        String respostaEsperada = "{\"1\":{\"nome\":\"George\",\"idade\":41,\"id\":1,\"risco\":0}}";

        given()
                .contentType(ContentType.JSON)
        .when()
                .get(urlApiCliente)
        .then()
                .statusCode(200)
                .assertThat().body(new IsEqual<>(respostaEsperada));
    }

    @Test
    @DisplayName("Quando eu cadastrar um clientes, então deve retornar o cliente cadastrado")
    void cadastraCliente() {

        String clienteParaCadastrar = "{\n" +
                "  \"id\": 1,\n" +
                "  \"idade\": 41,\n" +
                "  \"nome\": \"George\",\n" +
                "  \"risco\": 0\n" +
                "}";

        String retornoApiClienteCadastrado = "{\"1\":{\"nome\":\"George\",\"idade\":41,\"id\":1,\"risco\":0}}";

        given()
                .contentType(ContentType.JSON)
                .body(clienteParaCadastrar)
        .when()
                .post(urlApiCliente + endpointCadastroCliente)
        .then()
                .statusCode(201)
                .assertThat().body(containsString(retornoApiClienteCadastrado));
    }

}
