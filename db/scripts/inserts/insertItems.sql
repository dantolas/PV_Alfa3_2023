START TRANSACTION;
BEGIN;

-- Prescription Item Data
INSERT INTO Prescription_item (prescription_id, medication_id, amount, insurance_covered, description)
VALUES
-- Prescription ID: 0x54F95C8BC33F11EEB62A8C1759ECD7D1
(0x54F95C8BC33F11EEB62A8C1759ECD7D1, 0xB5E373C8C33D11EEB62A8C1759ECD7D1, 2, b'1', 'Take one tablet in the morning.'),
(0x54F95C8BC33F11EEB62A8C1759ECD7D1, 0xB5E385E4C33D11EEB62A8C1759ECD7D1, 1, b'0', 'Apply ointment twice daily.'),

-- Prescription ID: 0x54F964F0C33F11EEB62A8C1759ECD7D1
(0x54F964F0C33F11EEB62A8C1759ECD7D1, 0xB5E374F2C33D11EEB62A8C1759ECD7D1, 3, b'1', 'Take three pills daily.'),

-- Prescription ID: 0x54F967D1C33F11EEB62A8C1759ECD7D1
(0x54F967D1C33F11EEB62A8C1759ECD7D1, 0xB5E384CAC33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one tablet before bedtime.'),
(0x54F967D1C33F11EEB62A8C1759ECD7D1, 0xB5E386F4C33D11EEB62A8C1759ECD7D1, 2, b'0', 'Apply cream once a day.'),

-- Prescription ID: 0x54F96A8CC33F11EEB62A8C1759ECD7D1
(0x54F96A8CC33F11EEB62A8C1759ECD7D1, 0xB5E38F83C33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one tablet with meals.'),

-- Prescription ID: 0x54F96D60C33F11EEB62A8C1759ECD7D1
(0x54F96D60C33F11EEB62A8C1759ECD7D1, 0xB5E36AF4C33D11EEB62A8C1759ECD7D1, 2, b'1', 'Take two pills in the morning.'),

-- Prescription ID: 0x54F97036C33F11EEB62A8C1759ECD7D1
(0x54F97036C33F11EEB62A8C1759ECD7D1, 0xB5E38B57C33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one tablet daily.'),

-- Prescription ID: 0x54F9748EC33F11EEB62A8C1759ECD7D1
(0x54F9748EC33F11EEB62A8C1759ECD7D1, 0xB5E39093C33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one pill in the evening.'),
(0x54F9748EC33F11EEB62A8C1759ECD7D1, 0xB5E37D01C33D11EEB62A8C1759ECD7D1, 3, b'0', 'Apply lotion as needed.'),

-- Prescription ID: 0x54F97759C33F11EEB62A8C1759ECD7D1
(0x54F97759C33F11EEB62A8C1759ECD7D1, 0xB5E37E1AC33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one tablet in the morning.'),

-- Prescription ID: 0x54F97961C33F11EEB62A8C1759ECD7D1
(0x54F97961C33F11EEB62A8C1759ECD7D1, 0xB5E37B9DC33D11EEB62A8C1759ECD7D1, 2, b'0', 'Apply ointment as directed.'),
(0x54F97961C33F11EEB62A8C1759ECD7D1, 0xB5E391A9C33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one pill with meals.'),

-- Prescription ID: 0x54F97B0EC33F11EEB62A8C1759ECD7D1
(0x54F97B0EC33F11EEB62A8C1759ECD7D1, 0xB5E37F31C33D11EEB62A8C1759ECD7D1, 1, b'1', 'Take one tablet before bedtime.')
;

COMMIT;
