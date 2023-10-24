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
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test line.");
        counter.addLine("This is another test line!");
        int characterCount = counter.getCharacterCount();
        Assertions.assertEquals(46, characterCount);
    }

    @Test
    public void testLineCount() //Testar antal rader utan att användaren skriver stop.
    {
        TextCounter counter = new TextCounter();
        counter.addLine("Test line 1");
        counter.addLine("Test line 2");
        counter.addLine("Test line 3");
        int lineCount = counter.getLineCount();
        Assertions.assertEquals(3, lineCount);
    }

    @Test
    public void testLineCountWithStop()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("Test line 1");
        counter.addLine("Test line 2");
        counter.addLine("stop");
        int lineCount = counter.getLineCount();
        Assertions.assertEquals(2, lineCount);
    }

    @Test
    public void testWordCount()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Count these words.");
        int wordCount = counter.getWordCount();
        Assertions.assertEquals(7, wordCount);
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
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("In this test we are testing the result");
        counter.addLine("of our efforts to show words that");
        counter.addLine("have the same length as each other");
        counter.addLine("Seven letter words, such as tripods and bIcYcLe");
        ArrayList<String> longestWords = counter.getLongestWords();
        String longestWordsWithSplit = String.join(", ", longestWords);
        Assertions.assertEquals("testing, efforts, tripods, bIcYcLe", longestWordsWithSplit); //Förväntat resultat skrivs ut med komma-tecken som split
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
