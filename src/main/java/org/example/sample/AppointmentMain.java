package org.example.sample;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import model.Appointment;
import model.Doctor;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppointmentMain {
    public static void manageAppointments(AppointmentDAO appointmentDAO, Scanner scanner) throws SQLException {
        loadAppointmentsMenu();
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while(choice != 6) {
                switch (choice) {
                    case 1:
                        // Create Appointment
                        Appointment appointment = new Appointment();
                        System.out.print("Enter Patient ID: ");
                        appointment.setPatientId(scanner.nextInt());
                        System.out.print("Enter Doctor ID: ");
                        appointment.setDoctorId(scanner.nextInt());
                        scanner.nextLine(); //Since there is a change in datatype
                        System.out.print("Enter Appointment Date: ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter Notes: ");
                        appointment.setNotes(scanner.nextLine());

                        appointmentDAO.createAppointment(appointment);
                        System.out.println("Appointment created successfully.");
                        break;
                    case 2:
                        // Read Appointment
                        System.out.print("Enter Appointment ID: ");
                        int appointmentId = scanner.nextInt();
                        appointment = appointmentDAO.getAppointmentByID(appointmentId);
                        if (appointment != null) {
                            System.out.println("Appointment ID: " + appointment.getAppointmentId());
                            System.out.println("Patient ID: " + appointment.getPatientId());
                            System.out.println("Doctor ID: " + appointment.getDoctorId());
                            System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                            System.out.println("Notes: " + appointment.getNotes());
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;
                    case 3:
                        // Update Appointment
                        System.out.print("Enter Appointment ID: ");
                        appointmentId = scanner.nextInt();
                        scanner.nextLine();  // consume newline
                        appointment = appointmentDAO.getAppointmentByID(appointmentId);
                        if (appointment != null) {
                            System.out.print("Enter Patient ID: ");
                            appointment.setPatientId(scanner.nextInt());
                            System.out.print("Enter Doctor ID: ");
                            appointment.setDoctorId(scanner.nextInt());
                            scanner.nextLine();
                            System.out.print("Enter Appointment Date: ");
                            appointment.setAppointmentDate(scanner.nextLine());
                            System.out.print("Enter Notes: ");
                            appointment.setNotes(scanner.nextLine());

                            appointmentDAO.updateAppointment(appointment);
                            System.out.println("Appointment updated successfully.");
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;
                    case 4:
                        // Delete Appointment
                        System.out.print("Enter Appointment ID: ");
                        appointmentId = scanner.nextInt();
                        appointment = appointmentDAO.getAppointmentByID(appointmentId);
                        if(appointment != null) {
                            appointmentDAO.deleteAppointment(appointmentId);
                            System.out.println("Appointment deleted successfully.");
                        }else {
                            System.out.println("Appointment not found.");
                        }
                        break;
                    case 5:
                        //Print all doctor details
                        List<Appointment> appointments = appointmentDAO.getAllAppointments();
                        for(Appointment appointmentObj : appointments) {
                            System.out.println(appointmentObj);
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            loadAppointmentsMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        }

        public static void loadAppointmentsMenu(){
            System.out.println("--- APPOINTMENTS MENU ---");
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Print Appointment");
            System.out.println("6. Exit");
        }

}

