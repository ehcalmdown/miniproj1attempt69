package sg.nus.iss.mini6969.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.nus.iss.mini6969.models.ProductMatches;
import sg.nus.iss.mini6969.repositories.PairingRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Service
public class PairingService {
    private static final String URL="https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/wine/pairing";

    
    @Value("${API_KEY}")
    private String apiKey;
    
    @Autowired
    private PairingRepository pairRepo;

    public List<ProductMatches>getProductMatches(String food){
        //check for caching
        Optional<String> opt = pairRepo.get(food);
        String payload;

        System.out.printf(">>> food: %s\n", food);

        //chuk's empty box rule
        if(opt.isEmpty()){
            System.out.println("obtaining recommendations");

            try {//query string url
                String url = UriComponentsBuilder
                .fromUriString(URL)
                .queryParam("food",food)
                .encode()
                .toUriString();

                HttpResponse<String> resp = Unirest.get(url)
                // .header("food", food)
                .header("X-RapidAPI-Key", "70c6b7aaaamshff74d5a45a9bf81p14ae31jsn0263850fec91")
                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .asString();

            System.out.println(url);

            //make API call
            RestTemplate template = new RestTemplate();
            // ResponseEntity<String>resp;
            // //throw exception in case of 400+ or 500+
            // resp = template.exchange(req, String.class);
            //no idle payload
            payload = resp.getBody();
            System.out.println("payload: "+payload);//print out the payload to check
                pairRepo.save(food, payload);
               
            } catch (Exception e) {
                System.err.printf("Error: %s\n", e.getMessage());
                return Collections.emptyList();
                
            }
        }else
            payload = opt.get();
            System.out.printf(">>>> cache: %s\n", payload);
        
        //convert payload to jsonobject and then convert string to reader
        // Reader strReader = new StringReader(payload);
        //create jsonreader from reader
        // JsonReader jsonReader = Json.createReader(strReader);
        //read payload as jsonObject
        // JsonObject productMatchesResult=jsonReader.readObject();
        // JsonArray foods = productMatchesResult.getJsonArray("productMatches");
        List<ProductMatches> list = new LinkedList<>();
        // for(int i = 0; i<=foods.size(); i++){
        //     JsonObject jo = foods.getJsonObject(i);
        //     list.add(ProductMatches.create(jo));
        // }
        return list;
        
    }
    
}
        
 
