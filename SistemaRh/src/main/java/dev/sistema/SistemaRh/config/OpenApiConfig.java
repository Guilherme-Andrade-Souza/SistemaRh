package dev.sistema.SistemaRh.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração da documentação OpenAPI (Swagger UI).
 * Acesse em: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sistemaRhOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Sistema RH — API REST")
                .description("""
                    API de gestão de Recursos Humanos.
                    
                    Funcionalidades:
                    - Cadastro, listagem, edição e exclusão de funcionários
                    - Paginação e ordenação
                    - Validação de dados (CPF, e-mail, telefone)
                    """)
                .version("1.0.0")
                .contact(new Contact()
                    .name("Equipe Dev")
                    .email("dev@empresa.com"))
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/licenses/MIT")));
    }
}
