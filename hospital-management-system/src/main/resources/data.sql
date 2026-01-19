-- =========================
-- 1. DEPARTMENT (FIRST)
-- =========================
INSERT INTO department (id, name) VALUES (1, 'Cardiology');
INSERT INTO department (id, name) VALUES (2, 'Neurology');
INSERT INTO department (id, name) VALUES (3, 'Orthopedics');

-- =========================
-- 2. DOCTOR
-- =========================
INSERT INTO doctor (id, name, specialization, email, phone, experience_years)
VALUES (1, 'Dr. Sharma', 'Cardiologist', 'sharma@gmail.com', '9876543210', 10);

INSERT INTO doctor (id, name, specialization, email, phone, experience_years)
VALUES (2, 'Dr. Reddy', 'Neurologist', 'reddy@gmail.com', '9876543222', 8);

INSERT INTO doctor (id, name, specialization, email, phone, experience_years)
VALUES (3, 'Dr. Khan', 'Orthopedic', 'khan@gmail.com', '9876543333', 12);

-- =========================
-- PATIENT (no insurance yet)
INSERT INTO patient (id, name, age, email, gender, phone, blood_group)
VALUES (1, 'Rahul', 25, 'rahul@gmail.com', 'MALE', '9999999991', 'O_POS');

INSERT INTO patient (id, name, age, email, gender, phone, blood_group)
VALUES (2, 'Sneha', 30, 'sneha@gmail.com', 'FEMALE', '9999999992', 'A_POS');

INSERT INTO patient (id, name, age, email, gender, phone, blood_group)
VALUES (3, 'Arjun', 40, 'arjun@gmail.com', 'MALE', '9999999993', 'B_POS');

-- INSURANCE
INSERT INTO insurance (id, policy_number, provider, validuntil, created_at)
VALUES (1, 'POL123', 'Star Health', '2026-12-31', CURRENT_TIMESTAMP);

INSERT INTO insurance (id, policy_number, provider, validuntil, created_at)
VALUES (2, 'POL456', 'LIC Health', '2027-06-30', CURRENT_TIMESTAMP);

-- LINK PATIENT TO INSURANCE (foreign key lives here)
UPDATE patient SET insurance_id = 1 WHERE id = 1;
UPDATE patient SET insurance_id = 2 WHERE id = 2;

-- =========================
-- 5. APPOINTMENT
-- =========================
INSERT INTO appointment (id, appointment_date, appointment_time, doctor_id, patient_id, reason)
VALUES (1, '2025-01-20', '10:30:00', 1, 1, 'Heart Checkup');

INSERT INTO appointment (id, appointment_date, appointment_time, doctor_id, patient_id, reason)
VALUES (2, '2025-01-21', '11:00:00', 2, 1, 'Headache');

INSERT INTO appointment (id, appointment_date, appointment_time, doctor_id, patient_id, reason)
VALUES (3, '2025-01-22', '12:00:00', 3, 2, 'Knee Pain');

-- =========================
-- 6. JOIN TABLE (LAST)
-- =========================
INSERT INTO doctor_departments (department_id, doctor_id) VALUES (1, 1);
INSERT INTO doctor_departments (department_id, doctor_id) VALUES (2, 2);
INSERT INTO doctor_departments (department_id, doctor_id) VALUES (3, 3);
INSERT INTO doctor_departments (department_id, doctor_id) VALUES (1, 2);
