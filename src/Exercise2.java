import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class Exercise2 {

	
	public static void main(String[] args) throws IOException 
    {
		ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		byte[] jsonData;
		if(args.length > 0) {
			jsonData = Files.readAllBytes(Paths.get(args[0]));
		}else {
			String exemplo = "[\r\n"
					+ "{\r\n"
					+ "country: \"US\",\r\n"
					+ "languages: [ \"en\" ]\r\n"
					+ "},\r\n"
					+ "{\r\n"
					+ "country: \"BE\",\r\n"
					+ "languages: [ \"nl\", \"fr\", \"de\" ]\r\n"
					+ "},\r\n"
					+ "{\r\n"
					+ "country: \"NL\",\r\n"
					+ "languages: [ \"nl\", \"fy\" ]\r\n"
					+ "},\r\n"
					+ "{\r\n"
					+ "country: \"DE\",\r\n"
					+ "languages: [ \"de\" ]\r\n"
					+ "},\r\n"
					+ "{\r\n"
					+ "country: \"ES\",\r\n"
					+ "languages: [ \"es\" ]\r\n"
					+ "}\r\n"
					+ "]";
			jsonData = exemplo.getBytes();
		}		
		
		List<Country> countries = objectMapper.readValue(jsonData,  objectMapper.getTypeFactory().constructCollectionType(List.class, Country.class));
		
//		- returns the number of countries in the world		
		System.out.println("number of countries in the world: " + countries.size());
		
//		- finds the country with the most official languages, where they officially speak German (de).
		List<Country> germanSpeaking = countries.stream().filter( c-> c.getLanguages().contains("de")).toList();
		
		int maxGermanSpeaking = 0;
		Country countryGermanSpeaking = new Country();
		for(Country c : germanSpeaking) {
			if(c.getLanguages().size() > maxGermanSpeaking) {
				maxGermanSpeaking = c.getLanguages().size();
				countryGermanSpeaking = c;
			}
		}
		System.out.println("country with the most official languages, where they officially speak German: " + countryGermanSpeaking.getCountry());	
		
//		- that counts all the official languages spoken in the listed countries.
		Set<String> languages = countries.stream().flatMap(c -> c.getLanguages().stream()).collect(Collectors.toSet());
		System.out.println("counts all the official languages spoken in the listed countries : " + languages.size() + " " +languages);
		
//		- to find the country with the highest number of official languages.
		int maxSpeakingLanguages = 0;
		Country countrySpeakingLanguages = new Country();
		for(Country c : countries) {
			if(c.getLanguages().size() > maxSpeakingLanguages) {
				maxSpeakingLanguages = c.getLanguages().size();
				countrySpeakingLanguages = c;
			}
		}
		System.out.println("country with the highest number of official languages: " + countrySpeakingLanguages.getCountry());
		
//		- to find the most common official language(s), of all countries.
		Map<String, Integer> mapaContador = new HashMap<>();
		int maxReps = 0;
		Set<String> topLanguages = new HashSet<>();
		for(Country c : countries) {
			for(String language : c.getLanguages()) {
				if(mapaContador.containsKey(language)) {
					mapaContador.put(language, mapaContador.get(language) +1);
				}else {
					mapaContador.put(language, 1);
				}
				
				if(mapaContador.get(language) == maxReps) {
					topLanguages.add(language);
				}
				else if(mapaContador.get(language) > maxReps) {
					topLanguages.clear();
					topLanguages.add(language);
					maxReps = mapaContador.get(language);
				}
			}
		}
		
		System.out.print("the most common official language(s), of all countries is/are: ");
		int x = 0;
		for(String lang : topLanguages) {
			System.out.print(lang);
			x++;
			if(x>0 && x < (topLanguages.size())) {
				System.out.print(", ");
			}
		}
		System.out.print("["+maxReps+"].");
;

    }
	
	
}
