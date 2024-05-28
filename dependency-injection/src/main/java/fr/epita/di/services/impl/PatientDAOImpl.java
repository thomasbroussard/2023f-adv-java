package fr.epita.di.services.impl;

import fr.epita.di.Patient;
import fr.epita.di.services.api.IPatientDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements IPatientDAO {

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public PatientDAOImpl() throws Exception {
        // Initialize database and create table if not exists
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS PATIENT (" +
                         "ID INT PRIMARY KEY AUTO_INCREMENT," +
                         "NAME VARCHAR(255))");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public void create(Patient patient) throws Exception {
        String sql = "INSERT INTO PATIENT (NAME) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, patient.getName());
            pstmt.executeUpdate();

            // Retrieve the generated keys
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Patient read(int id) throws Exception {
        String sql = "SELECT ID, NAME FROM PATIENT WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Patient patient = new Patient();
                    patient.setId(rs.getInt("ID"));
                    patient.setName(rs.getString("NAME"));
                    return patient;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Patient patient) throws Exception {
        String sql = "UPDATE PATIENT SET NAME = ? WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM PATIENT WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Patient> getAll() throws Exception {
        String sql = "SELECT ID, NAME FROM PATIENT";
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("ID"));
                patient.setName(rs.getString("NAME"));
                patients.add(patient);
            }
        }
        return patients;
    }
}
