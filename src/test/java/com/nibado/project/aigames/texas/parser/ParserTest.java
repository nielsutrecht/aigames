package com.nibado.project.aigames.texas.parser;

import com.nibado.project.aigames.Output;
import com.nibado.project.aigames.texas.model.Match;
import org.junit.Test;

public class ParserTest {
    @Test
    public void match() throws Exception {
        Match match = new Match();
        Parser parser = new Parser(match);
        Output.read("01").forEach(parser::match);
    }

}