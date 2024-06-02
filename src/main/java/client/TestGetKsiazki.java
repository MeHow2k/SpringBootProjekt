package client;


import common.KKsiazka;
import common.WWypozyczenie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.MY_GLOBAL;

import java.util.List;

public class TestGetKsiazki {
public static void main(String[] args) {

        try {

            HttpHeaders httpHeaders = new HttpHeaders();                                                
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        
            String url = MY_GLOBAL.adress+"/getksiazki";
                        
            ParameterizedTypeReference<List<KKsiazka>>  parameterizedTypeReference = new ParameterizedTypeReference<List<KKsiazka>>(){};
            ResponseEntity<List<KKsiazka>> res = restTemplate.exchange(url, HttpMethod.POST, null,parameterizedTypeReference);
                                 
            
            List<KKsiazka> ksiazkiList = res.getBody();
           
                                    
            for (int i=0;i<ksiazkiList.size(); i++)
            {
                KKsiazka ksiazki = ksiazkiList.get(i);
                System.out.println(ksiazki.getId()+","+ksiazki.getTitle()+","+ksiazki.getAuthor()+","+ksiazki.getISBN());
            }
                                    
                                                
        } catch (Exception e) {
            System.out.println("***** WYSTĄPIŁ BŁĄD *****");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }    
    
   
}

