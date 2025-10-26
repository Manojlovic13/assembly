package com.assembly;

import com.assembly.config.ConfigProvider;
import com.assembly.config.ConfigProvider02;
import com.assembly.config.ConfigurationException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("Assembly successfully set up! [i=" + i + "]");
        }

        // Testing ConfigProvider
        try {
            final var configProvider = ConfigProvider.getInstance();
            configProvider.getExampleConfig()
                    .ifPresent(exampleConfig -> System.out.println("[this.is.example.config=" + exampleConfig + "]"));
        } catch (ConfigurationException exception) {
            System.err.println("Couldn't initialize configuration provider: " + exception.getMessage());
        }

        // Testing ConfigProvider02
        try {
            final var configProvider02 = new ConfigProvider02();
            configProvider02.readExampleConfig()
                    .ifPresent(System.out::println);
        } catch (ConfigurationException exception) {
            System.err.println("Couldn't initialize configuration provider 02: " + exception.getMessage());
        }
    }
}