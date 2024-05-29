package fr.epita.training.services;

import fr.epita.training.datamodel.Insurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceReader {

    public List<Insurance> readAll(String filePath) {
        List<Insurance> insurances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int insuranceId = Integer.parseInt(values[0]);
                String insuranceName = values[1];

                Insurance insurance = new Insurance(insuranceId, insuranceName);
                insurances.add(insurance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insurances;
    }

    public static void main(String[] args) {
        InsuranceReader reader = new InsuranceReader();
        List<Insurance> insurances = reader.readAll("insurances.csv");
        for (Insurance insurance : insurances) {
            System.out.println(insurance);
        }
    }
}
