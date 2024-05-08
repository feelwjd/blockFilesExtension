package com.example.blockfilesextension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BlockFilesExtensionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockFilesExtensionApplication.class, args);
    }

}
