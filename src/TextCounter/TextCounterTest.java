package TextCounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TextCounterTest
{
    //<editor-fold desc="Tester för korrekt beräkning">
    @Test
    public void testCharacterCount() //Beräknat antal tecken inkluderar blanksteg och skiljetecken
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test line.");
        counter.addLine("This is another test line!");
        //Expected
        int expected = 46;
        //Actual
        int actual = counter.getCharacterCount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLineCount() //Testar antal rader utan att användaren skriver stop.
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("Test line 1");
        counter.addLine("Test line 2");
        counter.addLine("Test line 3");
        //Expected
        int expected = 3;
        //Actual
        int actual = counter.getLineCount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLineCountWithStop()
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("Test line 1");
        counter.addLine("Test line 2");
        counter.addLine("stop");
        //Expected
        int expected = 2;
        //Actual
        int actual = counter.getLineCount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWordCount()
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Count these words.");
        //Expected
        int expected = 7;
        //Actual
        int actual = counter.getWordCount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLongestWord()
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("The longest word in this test is 'nationalencyklopedin'."); //Längsta ordet avslutas med punkt
        //Expected
        String expected = "'nationalencyklopedin'";
        //Actual
        String actual = counter.getLongestWord();
        Assertions.assertEquals(expected, actual); //Förväntat resultat, utan punkt
        //Samma ord förekommer i listan för de längsta orden med index 0.
        //Actual2
        actual = counter.getLongestWords().get(0);
        Assertions.assertEquals(expected, actual); //Vi förväntar oss också samma ord från ordlistan på plats 0.
    }

    @Test
    public void testLongestWords()
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("In this test we are testing the result");
        counter.addLine("of our efforts to show words that");
        counter.addLine("have the same length as each other");
        counter.addLine("Seven letter words, such as tripods and bIcYcLe");
        //Expected
        String expected = "testing, efforts, tripods, bIcYcLe";
        //Actual
        ArrayList<String> longestWords = counter.getLongestWords();
        String actual = String.join(", ", longestWords); //Egentligen inte så bra, men utskrivningen sker så i scriptet. Man kan göra en metod av det.
        Assertions.assertEquals(expected, actual); //Förväntat resultat skrivs ut med komma-tecken som split
    }
    //</editor-fold>

    //<editor-fold desc="Tester för korrekt terminering">
    @Test
    public void testUserTypesStopLowerCase() //Testar om boolean userTypedStop() deklareras 'false' med endast gemener
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");

        //Expected = False

        //Actual
        boolean actual = counter.userTypedStop();
        Assertions.assertFalse(actual);
        //Arrange
        counter.addLine("stop");

        //Expected = True

        //Actual2
        actual = counter.userTypedStop();
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUserTypesStopUpperCase() //Testar om boolean userTypedStop() deklareras 'false' oavsett case sensitivity
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");

        //Expected = False

        //Actual
        boolean actual = counter.userTypedStop();
        Assertions.assertFalse(actual);
        //Arrange
        counter.addLine("Stop");

        //Expected = True

        //Actual2
        actual = counter.userTypedStop();
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUserTypedStopAndBlankSpace() //Testar om boolean userTypedStop() fortfarande är 'true' med blanksteg/utropstecken efter 'stop'
    {
        //Arrange
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");

        //Expected = False

        //Actual
        boolean actual = counter.userTypedStop();
        Assertions.assertFalse(actual);

        //Arrange
        counter.addLine("stop ");

        //Expected = False

        //Actual
        actual = counter.userTypedStop();
        Assertions.assertFalse(actual);

        //Arrange
        counter.addLine("stop!");

        //Expected = False

        //Actual
        actual = counter.userTypedStop();
        Assertions.assertFalse(actual);
    }
    //</editor-fold>
}
