package client;


import common.WWypozyczenie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.MY_GLOBAL;

import java.util.List;

public class TestGetReturnedWypozyczenia {
public static void main(String[] args) {

        try {

            HttpHeaders httpHeaders = new HttpHeaders();                                                
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        
            String url = MY_GLOBAL.adress+"/getreturnedwyp";
                        
            ParameterizedTypeReference<List<WWypozyczenie>>  parameterizedTypeReference = new ParameterizedTypeReference<List<WWypozyczenie>>(){};
            ResponseEntity<List<WWypozyczenie>> res = restTemplate.exchange(url, HttpMethod.POST, null,parameterizedTypeReference);
                                 
            
            List<WWypozyczenie> wypozyczeniaList = res.getBody();
           
                                    
            for (int i=0;i<wypozyczeniaList.size(); i++)
            {
                WWypozyczenie wypozyczenia = wypozyczeniaList.get(i);
                System.out.println(wypozyczenia.getId()+","+wypozyczenia.getCzytelnikFirstname()+","+wypozyczenia.getCzytelnikLastname()+","+wypozyczenia.getKsiazkaTitle()+","+wypozyczenia.getKsiazkaAuthor()+","+wypozyczenia.getDate()+","+wypozyczenia.getReturndate());
            }
                                    
                                                
        } catch (Exception e) {
            System.out.println("***** WYSTĄPIŁ BŁĄD *****");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }    
    
   
}

