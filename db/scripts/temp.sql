DELIMITER //
 create procedure newPrescription(
	in _patient_id binary(16),
    in _doctor_id binary(16),
    in _diagnosis varchar(255)
)
BEGIN

    DECLARE newId BINARY(16);

    set newId = uuid_to_bin(uuid());

START TRANSACTION;
	insert into ePrescription(id,patient_id,doctor_id,diagnosis) 
    values (newId,_patient_id,_doctor_id,_diagnosis);

    select newId;

    COMMIT;
END //
DELIMITER ;
