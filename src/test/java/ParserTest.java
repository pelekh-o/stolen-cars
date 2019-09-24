import org.junit.Before;
import org.junit.Test;
import parser.Parser;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @Test
    public void isUpdateNeeded() throws Exception {
        assertTrue(parser.isUpdateNeeded());
    }

    @Test
    public void readDatasetAsStream() {
    }
}