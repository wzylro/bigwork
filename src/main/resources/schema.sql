# -- 用户表 (users)
# CREATE TABLE users (
#                        user_id INT AUTO_INCREMENT PRIMARY KEY,
#                        username VARCHAR(50) UNIQUE NOT NULL,
#                        password VARCHAR(255) NOT NULL,
#                        role ENUM('patient', 'doctor', 'admin') NOT NULL,
#                        phone VARCHAR(15),
#                        email VARCHAR(100)
# );
#
# -- 挂号记录表 (appointments)
# CREATE TABLE appointments (
#                               appointment_id INT AUTO_INCREMENT PRIMARY KEY,
#                               patient_id INT NOT NULL,
#                               doctor_id INT NOT NULL,
#                               appointment_time DATETIME NOT NULL,
#                               status ENUM('pending', 'completed', 'canceled'),
#                               CONSTRAINT fk_appointments_patient FOREIGN KEY (patient_id) REFERENCES users(user_id),
#                               CONSTRAINT fk_appointments_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
# );
#
# -- 病历表 (medical_records)
# CREATE TABLE medical_records (
#                                  record_id INT AUTO_INCREMENT PRIMARY KEY,
#                                  appointment_id INT NOT NULL,
#                                  diagnosis TEXT NOT NULL,
#                                  treatment_plan TEXT NOT NULL,
#                                  created_at DATETIME DEFAULT NOW(),
#                                  CONSTRAINT fk_medical_records_appointment FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
# );
#
# -- 医生表 (doctors)
# CREATE TABLE doctors (
#                          doctor_id INT AUTO_INCREMENT PRIMARY KEY,
#                          user_id INT NOT NULL,
#                          name VARCHAR(50) NOT NULL,
#                          department VARCHAR(50),
#                          title VARCHAR(50),
#                          specialization VARCHAR(100),
#                          CONSTRAINT fk_doctors_user FOREIGN KEY (user_id) REFERENCES users(user_id)
# );
#
# -- 管理员表 (admins)
# CREATE TABLE admins (
#                         admin_id INT AUTO_INCREMENT PRIMARY KEY,
#                         user_id INT NOT NULL,
#                         name VARCHAR(50) NOT NULL,
#                         CONSTRAINT fk_admins_user FOREIGN KEY (user_id) REFERENCES users(user_id)
# );
# -- 药品表 (medicines)
# CREATE TABLE medicines (
#                            medicine_id INT AUTO_INCREMENT PRIMARY KEY,
#                            name VARCHAR(100) UNIQUE NOT NULL,
#                            stock INT NOT NULL,
#                            price DECIMAL(10, 2) NOT NULL
# );
#
# -- 病历-药品关联表 (record_medicines)
# CREATE TABLE record_medicines (
#                                   id INT AUTO_INCREMENT PRIMARY KEY,
#                                   record_id INT NOT NULL,
#                                   medicine_id INT NOT NULL,
#                                   quantity INT NOT NULL,
#                                   CONSTRAINT fk_record_medicines_record FOREIGN KEY (record_id) REFERENCES medical_records(record_id),
#                                   CONSTRAINT fk_record_medicines_medicine FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
# );
#
#
# INSERT INTO users (username, password, role)
# VALUES ('admin', '$2a$10$w7L/Ev92zTxvD7NsGkKoCuYwQC9jszOx8AhdVo.cHpKFEyKn0KPqC', 'admin'),
#        ('doctor1', '$2a$10$w7L/Ev92zTxvD7NsGkKoCuYwQC9jszOx8AhdVo.cHpKFEyKn0KPqC', 'doctor'),
#        ('patient1', '$2a$10$w7L/Ev92zTxvD7NsGkKoCuYwQC9jszOx8AhdVo.cHpKFEyKn0KPqC', 'patient');
-- Password: 123456 (encrypted with BCrypt)
ALTER TABLE appointments
    ADD COLUMN scheduled_time DATETIME DEFAULT NULL;
