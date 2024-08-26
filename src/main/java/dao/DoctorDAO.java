package dao;

import jdbc.DatabaseConnection;
import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void createDoctor(Doctor doctor) throws SQLException
    {
        String insertQuery = "INSERT INTO doctors (firstName, lastName, specialty, email) VALUES (?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(insertQuery))
        {
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpeciality());
            ps.setString(4, doctor.getEmail());

            ps.executeUpdate();
        }
    }

    public Doctor getDoctorByID(int doctorId) throws SQLException
    {
        Doctor doctor = null;
        String selectQueryById = "SELECT * FROM doctors WHERE doctorId = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(selectQueryById))
        {

            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpeciality(rs.getString("specialty"));
                doctor.setEmail(rs.getString("email"));

                return doctor;
            }
            return null;
        }
    }

    //To Update Doctor record
    public void updateDoctor(Doctor doctor) throws SQLException
    {
        String updateQuery = "UPDATE DOCTORS SET firstName = ?, lastName = ?, specialty = ?, email = ? WHERE doctorId = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateQuery))
        {

            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpeciality());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getDoctorId());

            ps.executeUpdate();
        }
    }

    //to delete doctor record
    public void deleteDoctor(int doctorId) throws SQLException
    {
        String deleteQuery = "DELETE FROM doctors WHERE doctorId = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteQuery);)
        {

            ps.setInt(1, doctorId);
            ps.executeUpdate();
        }
    }

    //To get all doctor details
    public List<Doctor> getAllDoctors() throws SQLException
    {
        String getDoctorsQuery = "SELECT * FROM doctors";
        List<Doctor> doctors = new ArrayList<Doctor>();;
        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(getDoctorsQuery)) {

            while (rs.next()) {


                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpeciality(rs.getString("specialty"));
                doctor.setEmail(rs.getString("email"));

                doctors.add(doctor);
            }
            return doctors;
        }
    }
}
