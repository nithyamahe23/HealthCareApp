package org.example.sample;

import dao.DoctorDAO;
import model.Doctor;
import model.Patient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DoctorMain {
    public static void main(String[] args) {

        DoctorDAO doctorDao = new DoctorDAO();
        Scanner scanner = new Scanner(System.in);


        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");
        System.out.println("5. Print Doctors");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Create Doctor
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Enter Specialty: ");
                    newDoctor.setSpeciality(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());

                    doctorDao.createDoctor(newDoctor);
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:
                    // Read Doctor
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    Doctor doctor = doctorDao.getDoctorByID(doctorId);
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Specialty: " + doctor.getSpeciality());
                        System.out.println("Email: " + doctor.getEmail());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3:
                    // Update Doctor
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    doctor = doctorDao.getDoctorByID(doctorId);
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Enter Specialty ");
                        doctor.setSpeciality(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());

                        doctorDao.updateDoctor(doctor);
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    // Delete Doctor
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    doctor = doctorDao.getDoctorByID(doctorId);
                    if(doctor != null) {
                        doctorDao.deleteDoctor(doctorId);
                        System.out.println("Patient deleted successfully.");
                    }else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                    case 5:
                        //Print all doctor details
                        List<Doctor> doctors = doctorDao.getAllDoctors();
                        for(Doctor doctorObj : doctors) {
                            System.out.println(doctorObj);
                        }
                        break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }
    }
