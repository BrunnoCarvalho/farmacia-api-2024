package me.dio.farmacia_2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestão Farmacêutica",
        version = "1.0",
        description = "Bem-vindo à API de Gestão Farmacêutica. Recomendações para uso correto da API:\n" +
                "- **1- Cadastrar um farmacêutico:** O farmacêutico deve ser cadastrado antes de realizar operações no sistema.\n" +
                "- **2- Gerenciamento de Produtos:** Um produto só pode ser adicionado ou removido por um farmacêutico já cadastrado.\n" +
                "- **3- Gerenciamento de Estoques:** Um estoque só pode ser criado por um farmacêutico cadastrado no sistema.\n" +
                "- **4- Cadastro de Lotes:** Um lote pode ser cadastrado apenas por um farmacêutico cadastrado. O lote pode conter um produto que também deve estar previamente cadastrado no sistema. O lote pode ser atualizado e ter sua quantia atualizada.\n" +
                "- **5- Visualização de Transações:** É possível visualizar as transações realizadas por farmacêuticos, facilitando o acompanhamento das atividades.",
        contact = @Contact(name = "Bruno Carvalho", email = "brunno_carvalho@outlook.com")
    )
)

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
