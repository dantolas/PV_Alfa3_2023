START TRANSACTION;
BEGIN;
INSERT INTO Medication (name, short_description, detailed_description, type)
VALUES 
    ('Aspirin', 'Pain reliever and fever reducer', 'Aspirin is a nonsteroidal anti-inflammatory drug (NSAID) used to reduce pain, inflammation, and fever. It is commonly used to treat headaches, muscle aches, and various types of pain.', 'tablets'),
    ('Ibuprofen', 'Pain reliever and anti-inflammatory', 'Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain, reduce inflammation, and manage fever. It is commonly used for conditions such as arthritis, menstrual cramps, and mild to moderate pain.', 'tablets'),
    ('Acetaminophen', 'Pain reliever and fever reducer', 'Acetaminophen is a common over-the-counter medication used to relieve pain and reduce fever. It is often used for headaches, muscle aches, and as a general pain reliever. Unlike NSAIDs, it does not have anti-inflammatory effects.', 'pills'),
    ('Amoxicillin', 'Antibiotic', 'Amoxicillin is an antibiotic used to treat a variety of bacterial infections. It is commonly prescribed for respiratory tract infections, urinary tract infections, and skin infections. It works by stopping the growth of bacteria.', 'tablets'),
    ('Loratadine', 'Antihistamine', 'Loratadine is an antihistamine used to relieve symptoms of allergies, such as sneezing, runny nose, and itchy eyes. It is commonly used for hay fever and other allergic conditions.', 'tablets'),
    ('Omeprazole', 'Proton pump inhibitor', 'Omeprazole is a proton pump inhibitor (PPI) used to reduce stomach acid. It is prescribed for conditions such as gastroesophageal reflux disease (GERD), ulcers, and Zollinger-Ellison syndrome.', 'tablets'),
    ('Simvastatin', 'Cholesterol-lowering medication', 'Simvastatin is a medication used to lower cholesterol levels in the blood. It is part of a class of drugs known as statins and is prescribed to reduce the risk of cardiovascular events in individuals with high cholesterol.', 'tablets'),
    ('Fluoxetine', 'Selective serotonin reuptake inhibitor (SSRI)', 'Fluoxetine is an antidepressant medication belonging to the class of selective serotonin reuptake inhibitors (SSRIs). It is used to treat depression, obsessive-compulsive disorder (OCD), and panic disorder.', 'pills'),
    ('Cough Syrup', 'Cough suppressant', 'Cough syrup is a liquid medication used to relieve cough symptoms. It often contains ingredients such as dextromethorphan or codeine to suppress coughing and soothe the throat.', 'syrup'),
    ('Eye Drops', 'Eye lubricant', 'Eye drops are used to lubricate and relieve dry eyes. They may contain artificial tears to provide moisture and relieve irritation.', 'ointment'),
    ('Herbal Tea', 'Herbal remedy', 'Herbal tea is a natural remedy made from dried herbs and plants. It is often consumed for its therapeutic effects, such as relaxation, digestion, and immune support.', 'herbs'),
    ('Hydrocortisone Cream', 'Topical corticosteroid', 'Hydrocortisone cream is a topical corticosteroid used to reduce inflammation and itching of the skin. It is commonly used for conditions such as eczema and insect bites.', 'ointment'),
    ('Vitamin Supplement', 'Dietary supplement', 'Vitamin supplements are used to provide essential nutrients that may be lacking in the diet. They come in various forms, such as tablets or capsules, and are taken to support overall health and well-being.', 'pills'),
    ('Nasal syrup', 'Decongestant', 'Nasal syrup is a medication delivered through the nose to relieve nasal congestion. It may contain decongestant agents to reduce swelling of nasal tissues.', 'syrup'),
    ('Muscle Relaxant', 'Muscle relaxer', 'Muscle relaxants are medications used to relax muscles and reduce muscle spasms. They are prescribed for conditions such as muscle strains and sprains.', 'tablets'),
    ('Antacid', 'Acid reducer', 'Antacids are medications that neutralize stomach acid to relieve heartburn, indigestion, and acid reflux. They provide quick relief from symptoms.', 'tablets'),
    ('Allergy Nasal syrup', 'Nasal corticosteroid', 'Allergy nasal syrup is a corticosteroid medication used to treat nasal symptoms of allergies, such as congestion, sneezing, and runny nose.', 'syrup'),
    ('Anti-inflammatory Ointment', 'Topical anti-inflammatory', 'Anti-inflammatory ointment is applied to the skin to reduce inflammation and relieve pain. It is commonly used for conditions such as arthritis and joint pain.', 'ointment'),
    ('Multivitamin', 'Nutritional supplement', 'Multivitamins are dietary supplements that contain a combination of vitamins and minerals. They are taken to supplement the diet and ensure adequate nutrient intake.', 'pills'),
    ('Throat Lozenges', 'Sore throat relief', 'Throat lozenges are medicated candies that are sucked to relieve a sore throat. They often contain ingredients such as menthol or benzocaine for soothing effects.', 'tablets'),
    ('Laxative', 'Bowel movement aid', 'Laxatives are medications used to promote bowel movements and relieve constipation. They come in various forms, including tablets and liquid.', 'tablets'),
 ('Blood Pressure Medication', 'Antihypertensive', 'Blood pressure medication is prescribed to manage high blood pressure. It may belong to different classes, such as ACE inhibitors or beta-blockers.', 'tablets'),
    ('Probiotic Supplement', 'Digestive health', 'Probiotic supplements contain beneficial bacteria that support digestive health. They are often taken to promote a healthy balance of gut microflora.', 'pills'),
    ('Migraine Relief', 'Migraine medication', 'Migraine relief medication is used to alleviate symptoms of migraines, such as severe headaches and nausea. It may contain pain relievers and anti-nausea agents.', 'tablets'),
    ('Sleep Aid', 'Insomnia treatment', 'Sleep aids are medications used to promote sleep and manage insomnia. They may contain ingredients such as melatonin or sedative-hypnotic drugs.', 'pills'),
    ('Antiemetic', 'Nausea and vomiting relief', 'Antiemetics are medications used to prevent or relieve nausea and vomiting. They may be prescribed for conditions such as chemotherapy-induced nausea or motion sickness.', 'tablets'),
    ('Calcium Supplement', 'Bone health', 'Calcium supplements are taken to support bone health and prevent conditions like osteoporosis. They provide additional calcium, an essential mineral for bone strength.', 'pills'),
    ('Hemorrhoid Cream', 'Topical hemorrhoid treatment', 'Hemorrhoid cream is applied to the anal area to relieve symptoms of hemorrhoids, such as itching and swelling. It may contain vasoconstrictors or soothing agents.', 'ointment'),
    ('Omega-3 Fish Oil', 'Dietary supplement', 'Omega-3 fish oil supplements provide essential fatty acids that are beneficial for heart health. They are often taken to support cardiovascular function and overall well-being.', 'pills');
COMMIT;