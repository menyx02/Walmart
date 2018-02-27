package Tests.Utils;

import Model.Position;
import Utils.Tools;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ToolsTest {
    @Test
    public void convertUserInputToPositions() throws Exception {
        //Can't be tested through a Junit test because the console doesn't
        //accept input through here, but it was manually tested
    }

    @Test
    public void checkFormatInputTickets() throws Exception {
        //ERROR
        //empty string
        Assert.assertEquals(null, Tools.checkFormatInputTickets(""));

        //no comma
        assertEquals(null, Tools.checkFormatInputTickets("1 1"));
        assertEquals(null, Tools.checkFormatInputTickets("1/1"));
        assertEquals(null, Tools.checkFormatInputTickets("1a1"));

        //2+ commas
        assertEquals(null, Tools.checkFormatInputTickets("1,,1"));

        //first not a number
        assertEquals(null, Tools.checkFormatInputTickets("a,1"));

        //second not a number
        assertEquals(null, Tools.checkFormatInputTickets("1,a"));


        //CORRECT
        //Incorrect input, but it's interpreted correctly
        assertEquals(new Position(1,1), Tools.checkFormatInputTickets(",1,1"));

        //single digit
        assertEquals(new Position(1,1), Tools.checkFormatInputTickets("1,1"));

        //multi-digit
        assertEquals(new Position(12,15), Tools.checkFormatInputTickets("12,15"));
        assertEquals(new Position(123,456), Tools.checkFormatInputTickets("123,456"));
    }

}