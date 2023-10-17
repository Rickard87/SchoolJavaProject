package WordCounterTest;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TextCounterTest
{

    @Test
    public void testCharacterCount()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");
        int characterCount = counter.getCharacterCount();
        assertEquals(28, characterCount);
    }

    @Test
    public void testLineCount()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("Line 1");
        counter.addLine("Line 2");
        counter.addLine("Line 3");
        int lineCount = counter.getLineCount();
        assertEquals(3, lineCount);
    }

    @Test
    public void testLineCountWithStop()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("Line 1");
        counter.addLine("Line 2");
        counter.addLine("stop");
        int lineCount = counter.getLineCount();
        assertEquals(2, lineCount);
    }

    @Test
    public void testWordCount()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Count these words.");
        int wordCount = counter.getWordCount();
        assertEquals(7, wordCount);
    }

    @Test
    public void testLongestWord()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Longest word in this line is 'elephant'."); //Längsta ordet avslutas med punkt
        String longestWord = counter.getLongestWord();
        assertEquals("'elephant'", longestWord);
    }

    @Test
    public void testUserTypedStop()
    {
        TextCounter counter = new TextCounter();
        counter.addLine("This is a test.");
        counter.addLine("Another line.");
        assertFalse(counter.userTypedStop());
        counter.addLine("stop");
        assertTrue(counter.userTypedStop());
    }
}
