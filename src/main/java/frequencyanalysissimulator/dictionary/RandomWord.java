package frequencyanalysissimulator.dictionary;

import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.util.Random;

public class RandomWord {
    public static String generateEnglish() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://wordsapiv1.p.rapidapi.com/words/?random=true"))
            .header("X-RapidAPI-Key", "8ea8c1a062mshf5811670055c72ap19c014jsn09f409ff2a7c")
            .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        String body = response.body();
        String wordPropertyName = "{\"word\":\"";
        return body.substring(body.indexOf(wordPropertyName) + wordPropertyName.length(), body.indexOf("\",\"")).replaceAll(" ", "");
    }
    
    public static String generate(int len) {
        Random sr = new SecureRandom();
        return sr.ints(len, 0, 26)
            .mapToObj(i -> LetterArithmetic.ALPHABET[i])
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }
}