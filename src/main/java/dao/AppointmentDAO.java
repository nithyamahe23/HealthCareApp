package dao;

import jdbc.DatabaseConnection;
import model.Appointment;
import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void createAppointment(Appointment appointment) throws SQLException
    {
        String insertQuery = "INSERT INTO appointments (patientId, doctorId, appointmentDate, notes) VALUES (?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(insertQuery))
        {
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setString(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getNotes());

            ps.executeUpdate();
        }
    }

    public Appointment getAppointmentByID(int appointmentId) throws SQLException
    {
        Appointment appointment = null;
        String selectQueryById = "SELECT * FROM appointments WHERE appointmentId = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(selectQueryById))
        {

            ps.setInt(1, appointmentId);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointmentId"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointment.setAppointmentDate(rs.getString("appointmentDate"));
                appointment.setNotes(rs.getString("notes"));

                return appointment;
            }
            return null;
        }
    }

    //To Update Appointment record
    public void updateAppointment(Appointment appointment) throws SQLException
    {
        String updateQuery = "UPDATE appointments SET patientId = ?, doctorId = ?, appointmentDate = ?, notes = ? WHERE appointmentId = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateQuery))
        {

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setString(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getNotes());

            ps.executeUpdate();
        }
    }

    //to delete appointment record
    public void deleteAppointment(int appointmentId) throws SQLException
    {
        String deleteQuery = "DELETE FROM appointments WHERE appointmentId = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteQuery);)
        {

            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }
    }

    //To get all appointment details
    public List<Appointment> getAllAppointments() throws SQLException
    {
        String getAppointmentsQuery = "SELECT * FROM appointments";
        List<Appointment> appointments = new ArrayList<Appointment>();;
        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(getAppointmentsQuery)) {

            while (rs.next()) {


                Appointment appointment = new Appointment();

                appointment.setAppointmentId(rs.getInt("appointmentId"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointment.setAppointmentDate(rs.getString("appointmentDate"));
                appointment.setNotes(rs.getString("notes"));

                appointments.add(appointment);
            }
            return appointments;
        }
    }
}
