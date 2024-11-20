package utils;
/**
 * Klasa StringWrap służy do opakowywania wartości typu String.
 * Pozwala na przechowywanie jednej wartości tekstowej i jej manipulowanie.
 * Klasa ta udostępnia metody do ustawiania, pobierania oraz reprezentacji tekstu.
 *  @author Michał Pasieka
 *  @version 1.0, 20.11.2024
 */
public class StringWrap {

    String stringValue;
    /**
     * Konstruktor domyślny. Tworzy pusty obiekt StringWrap z wartością początkową będącą pustym ciągiem znaków.
     */

    public StringWrap() {
        stringValue = new String();
    }
    /**
     * Konstruktor inicjalizujący obiekt StringWrap z podaną wartością tekstową.
     *
     * @param val wartość tekstowa, którą chcemy przechować w obiekcie.
     */

    public StringWrap(String val) {
        stringValue = new String(val);
    }
    /**
     * Zwraca wartość przechowywaną w obiekcie StringWrap.
     *
     * @return wartość tekstowa przechowywana w obiekcie.
     */

    public String getString() {
        return stringValue;
    }
    /**
     * Ustawia nową wartość tekstową w obiekcie StringWrap.
     *
     * @param val nowa wartość tekstowa, którą chcemy przechować.
     */

    public void setString(String val) {
        stringValue = new String(val);
    }
    /**
     * Zwraca reprezentację tekstową obiektu StringWrap.
     *
     * @return reprezentacja tekstowa przechowywanego ciągu znaków.
     */
    public String toString() {
        return stringValue;
    }

}

