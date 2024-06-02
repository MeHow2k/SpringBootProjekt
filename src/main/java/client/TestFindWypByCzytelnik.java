package client;

import common.WWypozyczenie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import utils.MY_GLOBAL;

import java.util.List;

public class TestFindWypByCzytelnik {
    
      
    
    public static void main(String[] args) {

        
        try {           
            
            HttpHeaders httpHeaders = new HttpHeaders();                                                
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                       
            
            String url = MY_GLOBAL.adress+"/getczytelnikwyp";
            
            String jsonString = "{'czytelnikidtofind':'1'}";

            HttpEntity<String> entity = new HttpEntity<String>(jsonString,httpHeaders);
            ResponseEntity<List<WWypozyczenie>> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<WWypozyczenie>>(){});

            List<WWypozyczenie> wypozyczeniaList = response.getBody();

            for (WWypozyczenie wypozyczenie : wypozyczeniaList) {
                System.out.println(wypozyczenie.getId() + "," + wypozyczenie.getCzytelnikFirstname() + "," + wypozyczenie.getCzytelnikLastname() + "," + wypozyczenie.getKsiazkaTitle() + "," + wypozyczenie.getKsiazkaAuthor() + "," + wypozyczenie.getDate() + "," + wypozyczenie.getReturndate());
            }
                        
                                                
        } catch (Exception e) {
            System.out.println("***** WYSTĄPIŁ BŁĄD *****");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
         
