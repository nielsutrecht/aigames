package com.nibado.project.aigames.texas.parser;

import com.nibado.project.aigames.texas.model.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern SETTINGS_PATTERN = Pattern.compile("Settings ([a-z_]+) ([0-9a-zA-Z_]+)");
    private static final Pattern MATCH_PATTERN = Pattern.compile("Match ([a-z_]+) (.+)");
    private static final Pattern ACTION_PATTERN = Pattern.compile("Action ([0-9A-Za-z_]+) ([0-9]+)");
    private static final Pattern PLAYER_PATTERN = Pattern.compile("([0-9A-Za-z_]+) ([a-z_]+) (.+)");

    private final Match match;

    public Parser(Match match) {
        this.match = match;
    }

    public void match(String line) {
        if (parseSetting(line)) {
        } else if (parseMatch(line)) {
        } else if (parseAction(line)) {
        } else if (parsePlayer(line)) {
        } else {
            System.out.println("Can't parse: " + line);
        }
    }

    private boolean parseSetting(String line) {
        Matcher m = SETTINGS_PATTERN.matcher(line);

        if (!m.matches()) {
            return false;
        }

        String v = m.group(2);

        switch (m.group(1)) {
            case "hands_per_level":
                match.settings().setHandsPerLevel(toInt(v));
                break;
            case "starting_stack":
                match.settings().setStartingStack(toInt(v));
                break;
            case "your_bot":
                match.settings().setBotName(v);
                match.getPlayer(v).setMe(true);
                break;
            case "timebank":
                match.settings().setTimebank(toInt(v));
                break;
            case "time_per_move":
                match.settings().setTimePerMove(toInt(v));
                break;
            default:
                throw new IllegalArgumentException("Can't parse string " + line);
        }

        System.out.println(match.settings());

        return true;
    }

    private int toInt(String s) {
        return Integer.parseInt(s);
    }

    private boolean parseMatch(String line) {
        Matcher m = MATCH_PATTERN.matcher(line);

        if (!m.matches()) {
            return false;
        }

        String v = m.group(2);

        switch (m.group(1)) {
            case "round":
                match.setRound(toInt(v));
                break;
            case "small_blind":
                match.setSmallBlind(toInt(v));
                break;
            case "big_blind":
                match.setBigBlind(toInt(v));
                break;
            case "max_win_pot":
                match.setMaxWinPot(toInt(v));
                break;
            case "amount_to_call":
                match.setAmountToCall(toInt(v));
                break;
            case "on_button":
                match.setOnButton(v);
                break;
            case "table":
                match.getTable().parse(v);
                break;
            default:
                throw new IllegalArgumentException("Can't parse string " + line);
        }
        System.out.println(match);

        return true;
    }

    private boolean parseAction(String line) {
        Matcher m = ACTION_PATTERN.matcher(line);

        if (!m.matches()) {
            return false;
        }

        System.out.println("ACT: " + m.group(1) + ": " + m.group(2));

        return true;
    }

    private boolean parsePlayer(String line) {
        Matcher m = PLAYER_PATTERN.matcher(line);

        if (!m.matches()) {
            return false;
        }

        String player = m.group(1);
        String v = m.group(3);

        switch (m.group(2)) {
            case "stack":
                match.getPlayer(player).setStack(toInt(v));
                break;
        }
        System.out.println(match);

        return true;
    }
}
