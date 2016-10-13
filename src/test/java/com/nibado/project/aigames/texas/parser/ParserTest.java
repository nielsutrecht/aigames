package com.nibado.project.aigames.texas.parser;

import com.nibado.project.aigames.Output;
import com.nibado.project.aigames.texas.model.Match;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    private Match match;

    @Before
    public void setup() {
        this.match = new Match();
    }


    @Test
    public void match() throws Exception {
        Parser parser = new Parser(match, t -> {});
        Output.read("01").forEach(parser::parse);
    }

    @Test
    public void testCorrectState() throws Exception {
        Iterator<String> lines = Output.read("00").iterator();
        final AtomicInteger round = new AtomicInteger(-1);
        Parser parser = new Parser(match, m -> round.set(m.getRound()));

        apply(lines, parser, 5);

        assertSettings(500, 10000, 10, 2000, "player1");
    }

    private void assertMatch(final int round, final int smallBlind, final int bigBlind, final int maxWinPot, final int amountToCall, final String onButton) {
        assertThat(match.getRound()).isEqualTo(round);
        assertThat(match.getSmallBlind()).isEqualTo(smallBlind);
        assertThat(match.getBigBlind()).isEqualTo(bigBlind);
        assertThat(match.getMaxWinPot()).isEqualTo(maxWinPot);
        assertThat(match.getAmountToCall()).isEqualTo(amountToCall);
        assertThat(match.getOnButton().getName()).isEqualTo(onButton);
    }

    private void assertSettings(final int timePerMove, final int timeBank, final int handsPerLevel, final int startingStack, final String botName) {
        assertThat(match.settings().getTimePerMove()).isEqualTo(timePerMove);
        assertThat(match.settings().getTimebank()).isEqualTo(timeBank);
        assertThat(match.settings().getHandsPerLevel()).isEqualTo(handsPerLevel);
        assertThat(match.settings().getStartingStack()).isEqualTo(startingStack);
        assertThat(match.settings().getBotName()).isEqualTo(botName);
    }

    private static void apply(Iterator<String> lines, Parser parser, int count) {
        for(int i = 0;i < count && lines.hasNext();i++) {
            parser.parse(lines.next());
        }
    }

    private static class MatchReference {
        private Match match;
    }
}