package com.vaadin.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vaadin.demo.entities.AppointmentType;
import com.vaadin.demo.entities.Doctor;
import com.vaadin.demo.entities.Gender;
import com.vaadin.demo.entities.JournalEntry;
import com.vaadin.demo.entities.Patient;
import com.vaadin.demo.repositories.DoctorRepository;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Transactional
public class DBInitService {

    @Value("${db.number.doctors}")
    public Integer NUM_DOCTORS;
    @Value("${db.number.patients}")
    public Integer NUM_PATIENTS;
    @Value("${db.number.journal}")
    public Integer MAX_JOURNAL_ENTRIES;
    // random data can be used for demo purposes
    @Value("${db.random.data}")
    private Boolean useRandomData;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;


    private int staticRows;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Lorem lorem = new LoremIpsum();
    private List<Doctor> doctors;
    private Random random = new Random();
    private long medicalRecordId = random.nextInt(1000);

    public DBInitService() {
    }

    public void initDatabase() {
        if (useRandomData) {
            createRandomData();
        } else {
            createStaticData();
        }
    }

    private void createRandomData() {
        final ArrayList<Doctor> doctors = new ArrayList<>(NUM_DOCTORS);

        getRandomUsers(NUM_DOCTORS, "doctors").ifPresent(result -> result.forEach(doctor -> {
            doctors.add(new Doctor(capitalize(doctor.get("name").get("first").asText()),
                    capitalize(doctor.get("name").get("last").asText())));
        }));

        doctorRepository.save(doctors);

        doctors.clear();
        doctors.addAll(doctorRepository.findAll());
        ArrayList<Patient> patients = new ArrayList<>(NUM_PATIENTS);

        getRandomUsers(NUM_PATIENTS, "patients").ifPresent(result -> result.forEach(r -> {
            Patient patient = new Patient();
            patient.setGender(Gender.valueOf(r.get("gender").asText().toUpperCase()));

            patient.setTitle(capitalize(r.get("name").get("title").asText()));
            patient.setFirstName(capitalize(r.get("name").get("first").asText()));
            patient.setMiddleName(patient.getGender() == Gender.FEMALE ? lorem.getFirstNameFemale() : lorem.getFirstNameMale());
            patient.setLastName(capitalize(r.get("name").get("last").asText()));
            patient.setSsn(r.get("id").get("value").asText());
            patient.setMedicalRecord(medicalRecordId++);

            try {
                patient.setBirthDate(df.parse(r.get("dob").asText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            patient.setDoctor(doctors.get(random.nextInt(doctors.size())));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -random.nextInt(2));
            cal.add(Calendar.MONTH, -random.nextInt(12));
            patient.setJournalEntries(Stream.generate(() -> {
                cal.add(Calendar.DAY_OF_YEAR, -random.nextInt(365));
                JournalEntry journalEntry = new JournalEntry();
                journalEntry.setDate(cal.getTime());
                journalEntry.setAppointmentType(AppointmentType.values()[random.nextInt(AppointmentType.values().length)]);
                journalEntry.setEntry(lorem.getParagraphs(1, 4));
                journalEntry.setDoctor(patient.getDoctor());

                return journalEntry;
            }).limit(random.nextInt(MAX_JOURNAL_ENTRIES)).collect(Collectors.toList()));

            patient.setPictureUrl(r.get("picture").get("large").asText());

            patients.add(patient);
        }));
        patientRepository.save(patients);
    }

    private void createStaticData() {
        ArrayList<Doctor> doctors = new ArrayList<>(NUM_DOCTORS);
        Doctor doctor;
        for (int i = 0; i <NUM_DOCTORS; i++) {
            doctor = new Doctor("Doc ", "Number " + i);
            doctors.add(doctor);
        }

        doctorRepository.save(doctors);

        doctors.clear();
        doctors.addAll(doctorRepository.findAll());

        ArrayList<Patient> patients = new ArrayList<>(NUM_PATIENTS);

        Patient patient;
        for (int i = 0; i < NUM_PATIENTS; i++) {
            patient = new Patient();
            patient.setGender(Gender.FEMALE);

            patient.setTitle("Doc");
            patient.setFirstName("First" + i);
            patient.setMiddleName("Middle" + i);
            patient.setLastName("Last " + i);
            patient.setSsn("123456-A1111");
            patient.setMedicalRecord((long)i);

            Calendar birthDay = Calendar.getInstance();
            birthDay.set(2000,0,i);
            patient.setBirthDate(birthDay.getTime());

            Doctor doc = doctors.get(i % doctors.size());
            patient.setDoctor(doc);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -i);
            cal.add(Calendar.MONTH, -i);
            int mark = i;
            patient.setJournalEntries(Stream.generate(() -> {
                cal.add(Calendar.DAY_OF_YEAR, -mark);
                JournalEntry journalEntry = new JournalEntry();
                journalEntry.setDate(cal.getTime());
                journalEntry.setAppointmentType(AppointmentType.values()[mark % AppointmentType.values().length]);
                journalEntry.setEntry(lorem.getParagraphs(1, 4));
                journalEntry.setDoctor(doc);

                return journalEntry;
            }).limit(mark % MAX_JOURNAL_ENTRIES).collect(Collectors.toList()));

            // TODO should we have some static image to use too ?

            patients.add(patient);
        }

        patientRepository.save(patients);
    }

    private Optional<JsonNode> getRandomUsers(int num, String seed) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://randomuser.me/api/?results=" + num + "&exc=login,location&nat=us&noinfo&seed=" + seed, String.class);
        ObjectMapper om = new ObjectMapper();
        try {
            return Optional.of(om.readTree(response.getBody()).get("results"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return Optional.empty();
    }

    private String capitalize(String s) {
        return String.join(" ", Arrays.stream(s.split(" "))
                .map(name -> name.substring(0, 1)
                        .toUpperCase() + name.substring(1))
                .collect(Collectors.toList())
        );
    }
}
