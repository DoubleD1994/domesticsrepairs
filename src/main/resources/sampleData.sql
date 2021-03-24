insert into ENGINEER (NAME, EMAIL, PHONE_NUMBER, PASSWORD) values ('John Smith', 'johnsmith@domesticengineers.com', '+447123456789', 'password');
insert into ENGINEER (NAME, EMAIL, PHONE_NUMBER, PASSWORD) values ('Ian Long', 'ianlong@domesticengineers.com', '+447789456123', 'testpassword');

insert into APPOINTMENT (ENGINEER_ID, CUSTOMER_NAME, CUSTOMER_EMAIL, CUSTOMER_ADDRESS, CUSTOMER_PHONE_NUMBER, TIMESLOT, APPOINTMENT_DATE, IS_COMPLETE) values ((SELECT ENGINEER_ID FROM ENGINEER WHERE NAME = 'John Smith'), 'Clair Thomson', 'clairt@outlook.com', '15 London Road, Edinburgh, EH7 5DR', '+447741852963', 'MORNING', '2021-03-25', 'false');

insert into HOLIDAY (ENGINEER_ID, HOLIDAY_DATE) values ((SELECT ENGINEER_ID FROM ENGINEER WHERE NAME = 'Ian Long'), '2021-04-02');