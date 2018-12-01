
CREATE TABLE profile
(
    profile_id SERIAL,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    constraint pk_profile_id primary key(profile_id)
);
ALTER SEQUENCE profile_profile_id_seq RESTART WITH 1;

CREATE TABLE vehicle
(
    vehicle_id SERIAL,
    profile_id int,
    make varchar(50) not null,
    model varchar(50) not null,
    year int not null,
    color varchar(50) not null,
    image varchar(500),
    constraint pk_vehicle_id primary key(vehicle_id),
    constraint fk_profile_id foreign key(profile_id) references profile(profile_id)
);
ALTER SEQUENCE vehicle_vehicle_id_seq RESTART WITH 1;

CREATE TABLE mileage
(
    mileage_id SERIAL,
    vehicle_id int,
    start_day date not null,
    start_time time not null,
    end_day date,
    end_time time,
    start_mileage int not null,
    end_mileage int,
    constraint pk_mileage_id primary key(mileage_id),
    constraint fk_vehicle_id foreign key(vehicle_id) references vehicle(vehicle_id)
);
ALTER TABLE mileage ALTER start_time SET DEFAULT now();
ALTER TABLE mileage ALTER end_time SET DEFAULT now();
ALTER TABLE mileage ALTER start_day SET DEFAULT now();
ALTER TABLE mileage ALTER end_day SET DEFAULT now();
ALTER SEQUENCE mileage_mileage_id_seq RESTART WITH 1;

CREATE TABLE mechanic
(
    mechanic_id SERIAL,
    name varchar(100) not null,
    address varchar(100) not null,
    phone varchar(50) not null,
    constraint pk_mechanic_id primary key(mechanic_id)
);
ALTER SEQUENCE mechanic_mechanic_id_seq RESTART WITH 1;

CREATE TABLE expenses
(
    expense_id SERIAL,
    profile_id int,
    name varchar(100) not null,
    category varchar(50) not null,
    cost Decimal(10,2) not null,
    day date not null,
    constraint pk_expense_id primary key(expense_id),
    constraint fk_profile_id foreign key(profile_id) references profile(profile_id)
);
ALTER SEQUENCE expenses_expense_id_seq RESTART WITH 1;

CREATE TABLE maintenance_schedule
(
    maintenance_schedule_id SERIAL,
    vehicle_id int,
    name varchar(100) not null,
    interval int not null,
    constraint pk_maintenance_schedule_id primary key(maintenance_schedule_id),
    constraint fk_vehicle_id foreign key(vehicle_id) references vehicle(vehicle_id)
);
ALTER SEQUENCE maintenance_schedule_maintenance_schedule_id_seq RESTART WITH 1;

CREATE TABLE maintenance
(
    maintenance_id SERIAL,
    vehicle_id int,
    mechanic_id int,
    maintenance_schedule_id int,
    name varchar(100) not null,
    mileage int not null,
    cost Decimal(10,2) not null,
    day Date,
    constraint pk_maintenance_id primary key(maintenance_id),
    constraint fk_vehicle_id foreign key(vehicle_id) references vehicle(vehicle_id),
    constraint fk_mechanic_id foreign key(mechanic_id) references mechanic(mechanic_id),
    constraint fk_maintenance_schedule_id foreign key(maintenance_schedule_id) references maintenance_schedule(maintenance_schedule_id)
);
ALTER SEQUENCE maintenance_maintenance_id_seq RESTART WITH 1;
ALTER TABLE maintenance ALTER day SET DEFAULT now();

CREATE TABLE feedback
(
    feedback_id SERIAL,
    day date,
    name varchar(100) not null,
    source varchar(100) not null,
    rating int not null,
    notes varchar(10000) not null,
    constraint pk_feedback_id primary key(feedback_id)
);
ALTER SEQUENCE feedback_feedback_id_seq RESTART WITH 1;
ALTER TABLE feedback ALTER day SET DEFAULT now();