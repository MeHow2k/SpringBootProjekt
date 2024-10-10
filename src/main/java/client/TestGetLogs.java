package client;


import common.WWypozyczenie;
import controllers.models.MyLogRecord;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.MY_GLOBAL;

import java.util.List;

public class TestGetLogs {
public static void main(String[] args) {

        try {

            HttpHeaders httpHeaders = new HttpHeaders();                                                
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        
            String url = MY_GLOBAL.adress+"/getlogs";
                        
            ParameterizedTypeReference<List<MyLogRecord>>  parameterizedTypeReference = new ParameterizedTypeReference<List<MyLogRecord>>(){};
            ResponseEntity<List<MyLogRecord>> res = restTemplate.exchange(url, HttpMethod.POST, null,parameterizedTypeReference);
                                 
            
            List<MyLogRecord> logList = res.getBody();
           
                                    
            for (int i=0;i<logList.size(); i++)
            {
                MyLogRecord log = logList.get(i);
                System.out.println(log.getId()+": "+log.getTimestamp()+","+log.getLevel()+","+log.getMessage());
            }
                                    
                                                
        } catch (Exception e) {
            System.out.println("***** WYSTĄPIŁ BŁĄD *****");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }    
    
   
}

