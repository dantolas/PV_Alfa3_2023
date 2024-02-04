create view Prescriptions_Handed_Out as
select count(ePrescription.id) as "Prescriptions handed" ,CONCAT(Doctor.fname," ",Doctor.lname) as Doctor from Doctor inner join ePrescription on Doctor.id = ePrescription.doctor_id group by Doctor order by "Prescriptions handed" desc;

COMMIT;
