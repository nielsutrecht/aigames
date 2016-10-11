package com.nibado.project.aigames;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
    public static List<String> read(String file) {
        return read(file, true);
    }

    public static List<String> read(String file, boolean filterOutput) {
        return readResource("/output/" + file + ".txt")
                .stream()
                .filter(s -> !filterOutput || !isOutput(s))
                .collect(Collectors.toList());
    }

    private static List<String> readResource(String resource) {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(Output.class.getResourceAsStream(resource)))) {
            return buffer
                    .lines()
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toList());
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isOutput(String line) {
        return line.startsWith("Output from") || line.startsWith("Engine says");
    }

    @Test
    public void test() {
        System.out.println(read("01"));
    }
}
