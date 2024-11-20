package tests;

import controllers.StartApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = StartApp.class)
@AutoConfigureMockMvc        
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    void testAddWyp() throws Exception {
        String jsonBody = """
        {
            "params": {
                "czytelnikId": "1",
                "ksiazkaId": "1",
                "date": "22-12-2023"
            }
        }
        """;
        mockMvc.perform(post("/client/addwyp")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Dodano wypożyczenie")));
        //Sprawdzenie zwracanego tekstu
        
    }
    @Test
    void testAddWypWithBadCreds() throws Exception {
        String jsonBody = """
        {
            "params": {
                "czytelnikId": "1",
                "ksiazkaId": "1",
                "date": "22-12-2023"
            }
        }
        """;
        mockMvc.perform(post("/client/addwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isUnauthorized());


    }
    
    @Test
    void testGetWyp() throws Exception {
        
        mockMvc.perform(get("/client/getwyp")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isOk())                
                .andExpect(jsonPath("$", hasSize(20)))  // sprawdzenie, czy lista ma rozmiar 20
                .andExpect(jsonPath("$[0].czytelnikFirstname", is("Krzysztof")));  //Sprawdzenie imienia klienta z pierwszego przelewu
        
    }
    @Test
    void testGetWypWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testGetUnreturnedWyp() throws Exception {

        mockMvc.perform(get("/client/getunreturnedwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(16)))
                .andExpect(jsonPath("$[0].czytelnikFirstname", is("Andrzej")));
    }
    @Test
    void testGetUnreturnedWypWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getunreturnedwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testGetReturnedWyp() throws Exception {

        mockMvc.perform(get("/client/getreturnedwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].czytelnikFirstname", is("Tomasz")));
    }
    @Test
    void testGetReturnedWypWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getreturnedwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testCzytelnikWyp() throws Exception {

        mockMvc.perform(get("/client/getczytelnikwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .param("czytelnikidtofind","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(14)));
    }
    @Test
    void testGetCzytelnikWypWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getczytelnikwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testKsiazkaWyp() throws Exception {

        mockMvc.perform(get("/client/getksiazkawyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .param("ksiazkaidtofind","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
    @Test
    void testGetKsiazkaWypWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getksiazkawyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testDeleteWyp() throws Exception {
        String jsonBody = """
        {
            "params": {
                "wypidtodelete": "1"
            }
        }
        """;
        mockMvc.perform(post("/admin/deletewyp")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usunięto wypozyczenie o id: 1")));
        //Sprawdzenie zwracanego tekstu
        
    }
    @Test
    void testDeleteWypNoID() throws Exception {
        String jsonBody = """
        {
            "params": {
                "wypidtodelete": "999"
            }
        }
        """;
        mockMvc.perform(post("/admin/deletewyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nie ma w bazie wypozyczenia o id: 999")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testDeleteCzytelnik() throws Exception {
        String jsonBody = """
        {
            "params": {
                "czytidtodelete": "2"
            }
        }
        """;
        mockMvc.perform(post("/admin/deleteczytelnik")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usunięto czytelnika o id: 2")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testDeleteCzytelnikViolationOfIntegrity() throws Exception {
        String jsonBody = """
        {
            "params": {
                "czytidtodelete": "1"
            }
        }
        """;
        mockMvc.perform(post("/admin/deleteczytelnik")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nie można usunąć czytelnika - powiązanie z wypożyczeniem.")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testDeleteKsiazka() throws Exception {
        String jsonBody = """
        {
            "params": {
                "ksidtodelete": "20"
            }
        }
        """;
        mockMvc.perform(post("/admin/deleteksiazka")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usunięto książkę o id: 20")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testDeleteKsiazkaAsClient() throws Exception {
        String jsonBody = """
        {
            "params": {
                "ksidtodelete": "20"
            }
        }
        """;
        mockMvc.perform(post("/admin/deleteksiazka")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isForbidden());
    }
    @Test
    void testEndWyp() throws Exception {
        String jsonBody = """
        {
            "params": {
                "wypidtoend": "1"
            }
        }
        """;
        mockMvc.perform(post("/client/endwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Zakończono wypozyczenie o id: 1")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testEndWypAlreadyReturned() throws Exception {
        String jsonBody = """
        {
            "params": {
                "wypidtoend": "3"
            }
        }
        """;
        mockMvc.perform(post("/client/endwyp")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Wypożyczenie o id: 3 zostało już zwrócone!")));
    }
    @Test
    void testGetKsiazki() throws Exception {

        mockMvc.perform(get("/client/getksiazki")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)))  // sprawdzenie, czy lista ma rozmiar 20
                .andExpect(jsonPath("$[0].title", is("Władca Pierścieni")));

    }
    @Test
    void testGetKsiazkiWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getksiazki")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testAddKsiazka() throws Exception {
        String jsonBody = """
        {
            "params": {
                "title": "testTitle",
                "author": "testAuthor",
                "isbn": "testISBN"
            }
        }
        """;
        mockMvc.perform(post("/client/addksiazka")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Dodano książkę")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testAddKsiazkaWithBadCreds() throws Exception {
        String jsonBody = """
        {
            "params": {
                "title": "1",
                "author": "1",
                "isbn": "22-12-2023"
            }
        }
        """;
        mockMvc.perform(post("/client/addksiazka")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testAddCzytelnik() throws Exception {
        String jsonBody = """
        {
            "params": {
                "firstname": "testFirstname",
                "lastname": "testLastname",
            }
        }
        """;
        mockMvc.perform(post("/client/addczytelnik")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Dodano czytelnika")));
        //Sprawdzenie zwracanego tekstu

    }
    @Test
    void testAddCzytelnikWithBadCreds() throws Exception {
        String jsonBody = """
        {
            "params": {
                "firstname": "1",
                "lastname": "1",              
            }
        }
        """;
        mockMvc.perform(post("/client/addczytelnik")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds"))
                        .contentType(MediaType.APPLICATION_JSON) // Ustawienie typu zawartości na JSON
                        .content(jsonBody)) // Przekazanie JSON jako ciało żądania
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testGetCzytelnicy() throws Exception {

        mockMvc.perform(get("/client/getczytelnicy")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)))  // sprawdzenie, czy lista ma rozmiar 20
                .andExpect(jsonPath("$[0].firstname", is("Adam")));

    }
    @Test
    void testGetCzytelnicyWithBadCreds() throws Exception {

        mockMvc.perform(get("/client/getczytelnicy")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("bad", "creds")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testGetLogs() throws Exception {

        mockMvc.perform(get("/admin/getlogs")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].message", is("Testowy log")));

    }
    @Test
    void testGetLogsAsClient() throws Exception {

        mockMvc.perform(get("/admin/getlogs")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isForbidden());
    }
    @Test
    void testGetLogsByString() throws Exception {

        mockMvc.perform(get("/admin/getlogsbystring")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .param("logstringtofind","Testowy log22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message", is("Testowy log22")));

    }
    @Test
    void testGetLogsByStringAsClient() throws Exception {

        mockMvc.perform(get("/admin/getlogsbystring")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("client", "client123")))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetLogsByDateWithDatesOnly() throws Exception {

        mockMvc.perform(get("/admin/getlogbydate")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .param("logstringtofind","")
                        .param("logbegindate","1999-01-01 00:00:00")
                        .param("logenddate","2001-01-01 00:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message", is("Testowy log2")));

    }
    @Test
    void testGetLogsByDateWithStringAndDates() throws Exception {

        mockMvc.perform(get("/admin/getlogbydate")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                        .param("logstringtofind","log2")
                        .param("logbegindate","1960-01-01 00:00:00")
                        .param("logenddate","2001-01-01 00:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message", is("Testowy log2")));

    }
}
