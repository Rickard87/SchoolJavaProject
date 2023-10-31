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
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("The longest word in this test is 'nationalencyklopedin'."); //Längsta ordet avslutas med punkt
        String longestWord = counter.getLongestWord();
        Assertions.assertEquals("'nationalencyklopedin'", longestWord); //Förväntat resultat, utan punkt
        String longestWordFromList = counter.getLongestWords().get(0);
        Assertions.assertEquals(("'nationalencyklopedin'"), longestWordFromList); //Vi förväntar oss också samma ord från ordlistan på plats 0.
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
    public void testUserTypedStopLowerCase() //Testar om boolean userTypedStop() deklareras 'false' med endast gemener
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");
        Assertions.assertFalse(counter.userTypedStop());
        counter.addLine("stop");
        Assertions.assertTrue(counter.userTypedStop());
    }

    @Test
    public void testUserTypedStopUpperCase() //Testar om boolean userTypedStop() deklareras 'false' oavsett case sensitivity
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");
        Assertions.assertFalse(counter.userTypedStop());
        counter.addLine("Stop");
        Assertions.assertTrue(counter.userTypedStop());
    }

    @Test
    public void testUserTypedStopAndBlankSpace() //Testar om boolean userTypedStop() fortfarande är 'true' med blanksteg/utropstecken efter 'stop'
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");
        Assertions.assertFalse(counter.userTypedStop());
        counter.addLine("stop ");
        Assertions.assertFalse(counter.userTypedStop());
        counter.addLine("stop!");
        Assertions.assertFalse(counter.userTypedStop());
    }
    //</editor-fold>
}
