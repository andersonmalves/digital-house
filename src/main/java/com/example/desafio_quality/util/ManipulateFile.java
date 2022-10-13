package com.example.desafio_quality.util;

import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.entity.Property;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ManipulateFile {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String districtsLinkFile = "src/main/resources/districts.json";
    private static final String propertiesLinkFile = "src/main/resources/properties.json";

    public static void saveProperties(List<Property> properties) {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(propertiesLinkFile), properties);
        } catch (Exception ex) {
            System.out.println("Error writing properties");
        }
    }

    public static void saveDistricts(List<District> districts) {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(districtsLinkFile), districts);
        } catch (Exception ex) {
            System.out.println("Error writing districts");
        }
    }

    public static List<Property> loadProperties() {
        try {
            mapper.findAndRegisterModules();
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(propertiesLinkFile), Property[].class)));
        } catch (Exception ex) {
            System.out.println("Error reading file" + "Estou aqui!!" + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<District> loadDistricts() {
        try {
            mapper.findAndRegisterModules();
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(districtsLinkFile), District[].class)));
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return new ArrayList<>();
        }
    }
}
