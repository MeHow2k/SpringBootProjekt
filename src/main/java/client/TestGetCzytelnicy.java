package client;


import common.CCzytelnik;
import common.KKsiazka;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.MY_GLOBAL;

import java.util.List;

public class TestGetCzytelnicy {
public static void main(String[] args) {

        try {

            HttpHeaders httpHeaders = new HttpHeaders();                                                
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        
            String url = MY_GLOBAL.adress+"/getczytelnicy";
                        
            ParameterizedTypeReference<List<CCzytelnik>>  parameterizedTypeReference = new ParameterizedTypeReference<List<CCzytelnik>>(){};
            ResponseEntity<List<CCzytelnik>> res = restTemplate.exchange(url, HttpMethod.POST, null,parameterizedTypeReference);
                                 
            
            List<CCzytelnik> czytelnikList = res.getBody();
           
                                    
            for (int i=0;i<czytelnikList.size(); i++)
            {
                CCzytelnik czytelnik = czytelnikList.get(i);
                System.out.println(czytelnik.getId()+","+czytelnik.getFirstname()+","+czytelnik.getLastname());
            }
                                    
                                                
        } catch (Exception e) {
            System.out.println("***** WYSTĄPIŁ BŁĄD *****");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }    
    
   
}

