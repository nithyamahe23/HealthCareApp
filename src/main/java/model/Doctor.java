package model;

public class Doctor {

    private int doctorId;

    private String firstName;

    private String lastName;

    private String speciality;

    private String email;

    public Doctor() {
    }

    public Doctor(int doctorId, String firstName, String lastName, String speciality, String email) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.email = email;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
