package controllers;


//To jest kontroler do przykładu z bankiem bez autoryzacji

import common.CCzytelnik;
import common.KKsiazka;
import common.WWypozyczenie;
import controllers.models.Czytelnik;
import controllers.models.Ksiazka;
import controllers.models.MyLogRecord;
import controllers.models.Wypozyczenie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import controllers.repositories.MyLogRecordRepository;
import jakarta.servlet.ServletRequest;

import controllers.repositories.CzytelnikRepository;
import controllers.repositories.KsiazkaRepository;
import controllers.repositories.WypozyczenieRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//To jest kontroler do częsci aplikacji bez JWT (baza danych osób)

//CORS (Cross-origin resource sharing) polega na tym, że domyślnie nie można w aplikacji pod danym adresem
//wysłać zapytania do innej aplikacji sieciowej.
//Mechanizm ten wprowadzono celem udaremnienia prób ataków na witryny internetowe typu Cross-site Request Forgery.
//Atak ten polega na wysłaniu zapytania HTTP do innych serwisów wykorzystując uprawnienia użytkownika
//np. stan zalogowania, inne dane zapisane w sesji lub w plikach cookie.

//Zabezpieczenie to jednak przeszkadza w testowaniu aplikacji
//Dlatego można je usunąć adnotacją: @CrossOrigin(maxAge = 3600) (3600 oznacza na jaki czas wyłączamy, tj. liczbę sekund wyłaczenia)


//@CrossOrigin(allowCredentials = "true", //Włączenie poświadczeń (nazwa uzytkownika i haslo
//        origins = "http://localhost:3000", //Okreslenie adresów, z których dopuszcza sie ruch sieciowy
//        allowedHeaders = {"Authorization","Content-Type"}, //Nagłówki dopuszczalne
//        maxAge = 3600, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
@RestController
public class MyController2 {

    @Autowired
    WypozyczenieRepository wypRepository;
    @Autowired
    KsiazkaRepository ksiazkiRepository;
    @Autowired
    CzytelnikRepository czytelnikRepository;
    @Autowired
    MyLogRecordRepository myLogRecordRepository;

    @Autowired
    private Logger logger;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String systemInfo() {

        return "Testowy serwis webowy";
    }

    @RequestMapping(value = "/client/addwyp", method = RequestMethod.POST)
    public ResponseEntity<String> addWyp(@RequestBody String jsonString)
    {

        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONObject params = obj.getJSONObject("params");

            String czytelnikId = params.getString("czytelnikId");
            String ksiazkaId = params.getString("ksiazkaId");
            String date = params.getString("date");

            Czytelnik czytelnik = czytelnikRepository.findById(Integer.parseInt(czytelnikId));
            Ksiazka ksiazka = ksiazkiRepository.findById(Integer.parseInt(ksiazkaId));

            Wypozyczenie wypozyczenie = new Wypozyczenie(date);
            wypozyczenie.setCzytelnik(czytelnik);
            wypozyczenie.setKsiazka(ksiazka);

            wypRepository.save(wypozyczenie);

            //log
            String logString = "Dodano wypożyczenie: "+wypozyczenie.toString();
            logger.info(logString);

            ResponseEntity<String> res = new ResponseEntity("Dodano wypożyczenie", HttpStatus.OK);
            return res;

        } catch (Exception e) {
            String text = new String("ERROR:"+e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }

    }


    @RequestMapping(value = "/client/getwyp", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<WWypozyczenie>> getWyp(ServletRequest request) {
        try
        {
            List<Wypozyczenie> wypList = wypRepository.findAll();

            if (wypList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<WWypozyczenie> locWypozyczeniaList = new ArrayList<WWypozyczenie>();

            for (int i=0; i<wypList.size(); i++)
            {
                Wypozyczenie wypozyczenia = wypList.get(i);
                WWypozyczenie locWypozyczenia = new WWypozyczenie(wypozyczenia);
                locWypozyczeniaList.add(locWypozyczenia);
            }

            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locWypozyczeniaList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<WWypozyczenie> locTransferList = new ArrayList<WWypozyczenie>();
            WWypozyczenie locTransfer = new WWypozyczenie();
            locTransfer.setDate("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }

    @RequestMapping(value = "/client/getunreturnedwyp", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<WWypozyczenie>> getUnreturnedWyp(ServletRequest request) {

        try
        {

            //List<Transfer> transferList = transferRepository.findByUsername(userName);

            List<Wypozyczenie> wypList = wypRepository.findByReturndate("nie oddano");

            if (wypList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<WWypozyczenie> locWypozyczeniaList = new ArrayList<WWypozyczenie>();

            for (int i=0; i<wypList.size(); i++)
            {
                Wypozyczenie wypozyczenia = wypList.get(i);
                WWypozyczenie locWypozyczenia = new WWypozyczenie(wypozyczenia);
                locWypozyczeniaList.add(locWypozyczenia);
            }

            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locWypozyczeniaList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<WWypozyczenie> locTransferList = new ArrayList<WWypozyczenie>();
            WWypozyczenie locTransfer = new WWypozyczenie();
            locTransfer.setDate("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/getreturnedwyp", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<WWypozyczenie>> getReturnedWyp(ServletRequest request) {

        try
        {

            //List<Transfer> transferList = transferRepository.findByUsername(userName);
            List<Wypozyczenie> wypList = wypRepository.findAll();

            if (wypList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<WWypozyczenie> locWypozyczeniaList = new ArrayList<WWypozyczenie>();

            for (int i=0; i<wypList.size(); i++)
            {
                Wypozyczenie wypozyczenia = wypList.get(i);
                WWypozyczenie locWypozyczenia = new WWypozyczenie(wypozyczenia);
                if(!locWypozyczenia.getReturndate().equals("nie oddano")) locWypozyczeniaList.add(locWypozyczenia);
            }

            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locWypozyczeniaList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<WWypozyczenie> locTransferList = new ArrayList<WWypozyczenie>();
            WWypozyczenie locTransfer = new WWypozyczenie();
            locTransfer.setDate("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/getczytelnikwyp", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<WWypozyczenie>> getCzytelnikWyp(@RequestParam String czytelnikidtofind) {

        try
        {

            String czytelnikid = czytelnikidtofind;
            int intczytelnikid = Integer.parseInt(czytelnikid);


            List<Wypozyczenie> wypList = wypRepository.findByCzytelnikId(intczytelnikid);

            if (wypList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<WWypozyczenie> locWypozyczeniaList = new ArrayList<WWypozyczenie>();


            for (int i=0; i<wypList.size(); i++)
            {
                Wypozyczenie wypozyczenia = wypList.get(i);
                WWypozyczenie locWypozyczenia = new WWypozyczenie(wypozyczenia);
                locWypozyczeniaList.add(locWypozyczenia);
            }

            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locWypozyczeniaList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<WWypozyczenie> locTransferList = new ArrayList<WWypozyczenie>();
            WWypozyczenie locTransfer = new WWypozyczenie();
            locTransfer.setDate("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/getksiazkawyp", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<WWypozyczenie>> getKsiazkaWyp(@RequestParam String ksiazkaidtofind) {

        try
        {
            String ksiazkaid = ksiazkaidtofind;
            int intksiazkaid = Integer.parseInt(ksiazkaid);


            List<Wypozyczenie> wypList = wypRepository.findByKsiazkaId(intksiazkaid);

            if (wypList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<WWypozyczenie> locWypozyczeniaList = new ArrayList<WWypozyczenie>();


            for (int i=0; i<wypList.size(); i++)
            {
                Wypozyczenie wypozyczenia = wypList.get(i);
                WWypozyczenie locWypozyczenia = new WWypozyczenie(wypozyczenia);
                locWypozyczeniaList.add(locWypozyczenia);
            }

            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locWypozyczeniaList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<WWypozyczenie> locTransferList = new ArrayList<WWypozyczenie>();
            WWypozyczenie locTransfer = new WWypozyczenie();
            locTransfer.setDate("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<WWypozyczenie>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/admin/deletewyp", method = RequestMethod.POST)
    public ResponseEntity<String> deleteWyp(@RequestBody String jsonString) {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));

            String wypidtodelete = param.getString("wypidtodelete");
            int intWypidtodelete = Integer.parseInt(wypidtodelete);


            Wypozyczenie locTransfer = wypRepository.findById(intWypidtodelete);

            if (locTransfer == null) {
                throw new IllegalArgumentException("Nie ma w bazie wypozyczenia o id: " + wypidtodelete);
            }

            wypRepository.delete(locTransfer);

            //log
            String logString = "Usunięto wypożyczenie: "+ locTransfer.toString();
            logger.severe(logString);

            ResponseEntity<String> res = new ResponseEntity("Usunięto wypozyczenie o id: "+wypidtodelete, HttpStatus.OK);
            return res;
        }
        catch (Exception e) {
            String text = new String(e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/admin/deleteczytelnik", method = RequestMethod.POST)
    public ResponseEntity<String> deleteCzytelnik(@RequestBody String jsonString) {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));
            String wypidtodelete = param.getString("czytidtodelete");
            int intWypidtodelete = Integer.parseInt(wypidtodelete);


            Czytelnik locTransfer = czytelnikRepository.findById(intWypidtodelete);

            if (locTransfer == null) {
                throw new IllegalArgumentException("Nie ma w bazie czytelnika o id: " + wypidtodelete);
            }

            czytelnikRepository.delete(locTransfer);

            //log
            String logString = "Usunięto czytelnika: "+ locTransfer.toString();
            logger.severe(logString);

            ResponseEntity<String> res = new ResponseEntity("Usunięto czytelnika o id: "+wypidtodelete, HttpStatus.OK);
            return res;
        }catch (DataIntegrityViolationException e) {
            String text = new String("Nie można usunąć czytelnika - powiązanie z wypożyczeniem.");
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
        catch (Exception e) {
            String text = new String(e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/admin/deleteksiazka", method = RequestMethod.POST)
    public ResponseEntity<String> deleteKsiazka(@RequestBody String jsonString) {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));
            String wypidtodelete = param.getString("ksidtodelete");
            int intWypidtodelete = Integer.parseInt(wypidtodelete);


            Ksiazka locTransfer = ksiazkiRepository.findById(intWypidtodelete);

            if (locTransfer == null) {
                throw new IllegalArgumentException("Nie ma w bazie ksiażki o id: " + wypidtodelete);
            }

            ksiazkiRepository.delete(locTransfer);

            //log
            String logString = "Usunięto książkę: "+ locTransfer.toString();
            logger.severe(logString);

            ResponseEntity<String> res = new ResponseEntity("Usunięto książkę o id: "+wypidtodelete, HttpStatus.OK);
            return res;
        }catch (DataIntegrityViolationException e) {
            String text = new String("Nie można usunąć książki - powiązanie z wypożyczeniem.");
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
        catch (Exception e) {
            String text = new String(e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/endwyp", method = RequestMethod.POST)
    public ResponseEntity<String> endWyp(@RequestBody String jsonString) {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));
            String wypidtoend = param.getString("wypidtoend");
            int intWypidtoend = Integer.parseInt(wypidtoend);


            Wypozyczenie locTransfer = wypRepository.findById(intWypidtoend);

            if (locTransfer == null) {
                throw new IllegalArgumentException("Nie ma w bazie wypozyczenia o id: " + wypidtoend);
            }else if(!locTransfer.getReturndate().equals("nie oddano")){
                throw new IllegalArgumentException("Wypożyczenie o id: " + wypidtoend+ " zostało już zwrócone!");
            }else{

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                locTransfer.setReturndate(dateFormat.format(date));
                wypRepository.save(locTransfer);

                //log
                String logString = "Zakończono wypożyczenie: "+ locTransfer.toString();
                logger.info(logString);

                ResponseEntity<String> res = new ResponseEntity("Zakończono wypozyczenie o id: "+wypidtoend, HttpStatus.OK);
                return res;
            }
        }
        catch (Exception e) {
            String text = new String(e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/getksiazki", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<KKsiazka>> getKsiazki(ServletRequest request) {

        try
        {

            //List<Transfer> transferList = transferRepository.findByUsername(userName);
            List<Ksiazka> ksiazkiList = ksiazkiRepository.findAll();

            if (ksiazkiList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<KKsiazka> locKsiazkiList = new ArrayList<KKsiazka>();

            for (int i=0; i<ksiazkiList.size(); i++)
            {
                Ksiazka ksiazka = ksiazkiList.get(i);
                KKsiazka locKsiazki = new KKsiazka(ksiazka);
                locKsiazkiList.add(locKsiazki);
            }

            ResponseEntity<ArrayList<KKsiazka>> res = new ResponseEntity(locKsiazkiList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<KKsiazka> locTransferList = new ArrayList<KKsiazka>();
            KKsiazka locTransfer = new KKsiazka();
            locTransfer.setTitle("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<KKsiazka>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/client/addksiazka", method = RequestMethod.POST)
    public ResponseEntity<String> addKasiazka(@RequestBody String jsonString)
    {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));

            String title = param.getString("title");
            String author = param.getString("author");
            String isbn = param.getString("isbn");


            Ksiazka ksiazka = new Ksiazka(title,  author,isbn);

            ksiazkiRepository.save(ksiazka);

            //log
            String logString = "Dodano książkę: "+ ksiazka.toString();
            logger.info(logString);

            ResponseEntity<String> res = new ResponseEntity("Dodano książkę", HttpStatus.OK);
            return res;

        } catch (Exception e) {
            String text = new String("ERROR:"+e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }

    }
    @RequestMapping(value = "/client/addczytelnik", method = RequestMethod.POST)
    public ResponseEntity<String> addCzytelnik(@RequestBody String jsonString)
    {

        try {

            JSONObject obj = new JSONObject(jsonString);
            JSONObject param = new JSONObject(obj.getString("params"));

            String firstname = param.getString("firstname");
            String lastname = param.getString("lastname");

            Czytelnik czytelnik = new Czytelnik(firstname,  lastname);

            czytelnikRepository.save(czytelnik);

            //log
            String logString = "Dodano czytelnika: "+ czytelnik.toString();
            logger.info(logString);

            ResponseEntity<String> res = new ResponseEntity("Dodano czytelnika", HttpStatus.OK);
            return res;

        } catch (Exception e) {
            String text = new String("ERROR:"+e.getMessage());
            ResponseEntity<String> res = new ResponseEntity(text, HttpStatus.OK);
            return res;
        }

    }
    @RequestMapping(value = "/client/getczytelnicy", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<CCzytelnik>> getCzytelnicy(ServletRequest request) {

        try
        {

            //List<Transfer> transferList = transferRepository.findByUsername(userName);
            List<Czytelnik> czytelnikList = czytelnikRepository.findAll();

            if (czytelnikList==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<CCzytelnik> locCzytelnikList = new ArrayList<CCzytelnik>();

            for (int i=0; i<czytelnikList.size(); i++)
            {
                Czytelnik czytelnik = czytelnikList.get(i);
                CCzytelnik locCzytelnik = new CCzytelnik(czytelnik);
                locCzytelnikList.add(locCzytelnik);
            }

            ResponseEntity<ArrayList<CCzytelnik>> res = new ResponseEntity(locCzytelnikList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<CCzytelnik> locTransferList = new ArrayList<CCzytelnik>();
            CCzytelnik locTransfer = new CCzytelnik();
            locTransfer.setFirstname("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<CCzytelnik>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }

    @RequestMapping(value = "/admin/getlogs", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MyLogRecord>> getLogs(ServletRequest request) {

        try
        {
            ArrayList<MyLogRecord> logList = (ArrayList<MyLogRecord>) myLogRecordRepository.findAll();

            if (myLogRecordRepository==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(logList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<MyLogRecord> locTransferList = new ArrayList<MyLogRecord>();
            MyLogRecord locTransfer = new MyLogRecord();
            locTransfer.setMessage("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }

    @RequestMapping(value = "/admin/getlogsbystring", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MyLogRecord>> getLogsByString(@RequestParam String logstringtofind) {
        try
        {
            //JSONObject obj = new JSONObject(jsonString);
            String stringToFind = logstringtofind.toLowerCase();


            ArrayList<MyLogRecord> logList = (ArrayList<MyLogRecord>) myLogRecordRepository.findAll();

            if (myLogRecordRepository==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<MyLogRecord> foundLogList = new ArrayList<MyLogRecord>();

            for (int i=0; i < logList.size(); i++){
                MyLogRecord log = logList.get(i);
                if(log.getMessage().toLowerCase().contains(stringToFind)){
                    foundLogList.add(log);
                }
            }

            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(foundLogList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<MyLogRecord> locTransferList = new ArrayList<MyLogRecord>();
            MyLogRecord locTransfer = new MyLogRecord();
            locTransfer.setTimestamp("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }
    @RequestMapping(value = "/admin/getlogbydate", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MyLogRecord>> getLogsByDate(@RequestParam String logstringtofind
            ,@RequestParam String logbegindate,@RequestParam String logenddate) {

        try
        {
            //JSONObject obj = new JSONObject(jsonString);
            String stringToFind = logstringtofind.toLowerCase();
            String beginDateToFind = logbegindate;
            String endDateToFind = logenddate;
            if (beginDateToFind.isBlank()) beginDateToFind="1970-01-01 00:00:00";
            if (endDateToFind.isBlank()) endDateToFind="2200-12-31 23:59:59";

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begindate,enddate;

            begindate = dateFormat.parse(beginDateToFind);
            enddate = dateFormat.parse(endDateToFind);

            ArrayList<MyLogRecord> logList = (ArrayList<MyLogRecord>) myLogRecordRepository.findAll();

            if (myLogRecordRepository==null)
            {
                throw new IllegalArgumentException("Nie ma danych");
            }

            ArrayList<MyLogRecord> foundLogList = new ArrayList<MyLogRecord>();

            if(stringToFind.isBlank() || stringToFind==null) {
                foundLogList=logList;
            }else {
                for (int i = 0; i < logList.size(); i++) {
                    MyLogRecord log = logList.get(i);
                    if (log.getMessage().toLowerCase().contains(stringToFind)) {
                        foundLogList.add(log);
                    }
                }
            }

            foundLogList.removeIf(log -> {
                try {
                    Date logDate = dateFormat.parse(log.getTimestamp());
                    return logDate.before(begindate) || logDate.after(enddate);
                } catch (Exception e) {
                    System.out.println("Date parsing error: " + e.getMessage());
                    return true; // W razie błędu parsowania usuń ten element
                }
            });

            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(foundLogList, HttpStatus.OK);
            return res;

        }
        catch (Exception e)
        {
            ArrayList<MyLogRecord> locTransferList = new ArrayList<MyLogRecord>();
            MyLogRecord locTransfer = new MyLogRecord();
            locTransfer.setTimestamp("ERROR:"+e.getMessage());
            locTransferList.add(locTransfer);
            ResponseEntity<ArrayList<MyLogRecord>> res = new ResponseEntity(locTransferList, HttpStatus.OK);
            return res;
        }
    }


}
