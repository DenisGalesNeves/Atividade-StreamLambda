package br.edu.fatec.emailmkt;

import br.edu.fatec.emailmkt.services.ConsomeApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class EmailMktApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmailMktApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsomeApi lerApi = new ConsomeApi();
		String dados = lerApi.obterdados("https://jsonplaceholder.typicode.com/comments");
		System.out.print(dados);
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode jsonNode = objMapper.readTree(dados);
//		System.out.println(jsonNode);
		List<JsonNode> jsonlist = new ArrayList<>();
		jsonNode.forEach(jsonlist::add);
//		jsonlist.stream().forEach(System.out::println);
		List<String> emailList = jsonlist.stream()
				.map(node -> node.get("email")
						.asText())
				.toList();
		emailList.forEach(System.out::println);
		System.out.println(emailList.size());
	}
}
