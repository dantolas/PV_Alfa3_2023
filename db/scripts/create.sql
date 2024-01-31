START TRANSACTION;
create table IF NOT EXISTS Patient(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    fname varchar(255) not null,
    lname varchar(255) not null,
    birth_number varchar(255) not null unique,
    dof date not null,
    gender BIT(1)  not null,
    constraint gender_check check(gender = 1 OR gender = 0),
    insurance_company_id BINARY(16),
    constraint fk_insurance foreign key(insurance_company_id) references InsuranceCompany(id),
    insurance_number varchar(200)
);

COMMIT;

create table IF NOT EXISTS Doctor(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    fname varchar(255) not null,
    lname varchar(255) not null,
    started_practice date
);

COMMIT;

create table IF NOT EXISTS InsuranceCompany(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    name varchar(255) not null unique,
    country_of_origin not null,
    shortcut varchar(10)
);

COMMIT;

create table IF NOT EXISTS Medication(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    name varchar(255) unique not null,
    short_description varchar(500) not null,
    detailed_description blob,
    type ENUM("pills","tablets","syrup","ointment","herbs")
);

COMMIT;

create table IF NOT EXISTS ePrescription(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    patient_id BINARY(16),
    constraint fk_patient foreign key(patient_id) references Patient(id),
    doctor_id BINARY(16),
    constraint fk_doctor foreign key(doctor_id) references Doctor(id),
    diagnosis varchar(255),
    date_prescribed date not null default (NOW());
);

COMMIT;

create table Prescription_item(
    id BINARY(16) primary key default (UUID_TO_BIN(UUID())),
    prescription_id BINARY(16),
    constraint fk_prescription foreign key(prescription_id) references ePrescription(id),
    medication_id BINARY(16),
    constraint fk_medication foreign key(medication_id) references Medication(id),
    amount int not null,
    insurance_covered BIT(1) not null,
    description varchar(255)
);
