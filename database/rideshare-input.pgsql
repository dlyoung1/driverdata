INSERT INTO profile (first_name, last_name, email) VALUES ('Frank', 'Martin', 'idrive@fast.org');
INSERT INTO profile (first_name, last_name, email) VALUES ('Esmarelda', 'Villalobos', 'ilike@pulp.gov');


INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('1', 'seat covers', 'maintenance', '10.00', '2018-11-01');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('1', 'carpet cleaner', 'maintenance', '10.00', '2018-11-01');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('1', 'air freshener', 'misc', '10.00', '2018-11-11');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('1', 'professional vomit removal', 'maintenance', '10.00', '2018-11-17');

INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('2', 'water for passengers', 'misc', '5.00', '2018-11-01');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('2', 'ambient soundtrack', 'misc', '10.00', '2018-11-11');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('2', 'mints for passengers', 'misc', '10.00', '2018-11-21');
INSERT INTO expenses (profile_id, name, category, cost, day) VALUES ('2', 'cell phone charger', 'misc', '7.50', '2018-11-22');


INSERT INTO vehicle (profile_id, make, model, year, color, image) VALUES ('1', 'Audi', 'A8', '2016', 'black', 'img/2016-audi-a8.png');
INSERT INTO vehicle (profile_id, make, model, year, color, image) VALUES ('1', 'Land Rover', 'Range Rover', '2018', 'black', 'img/range-rover.png');
INSERT INTO vehicle (profile_id, make, model, year, color, image) VALUES ('2', 'Ford', 'Fusion SE Taxi', '2017', 'yellow', 'img/Fusion_SE_Taxi.jpg');
INSERT INTO vehicle (profile_id, make, model, year, color, image) VALUES ('2', 'Mini Cooper', 'CountryMan', '2017', 'blue', 'img/mini-cooper-countryman-s.png');


INSERT INTO mechanic (name, address, phone) VALUES ('Mikes Auto', '1234 Main', '123-456-7890');
INSERT INTO mechanic (name, address, phone) VALUES ('Auto Max', '23443 Birch St', '453-626-4647');
INSERT INTO mechanic (name, address, phone) VALUES ('Custom Speed', '56632 Perry Dr', '756-564-5445');
INSERT INTO mechanic (name, address, phone) VALUES ('Freemont Ford', '12222 Dealer Ave', '132-565-7984');
INSERT INTO mechanic (name, address, phone) VALUES ('Import Design', '54644 Linda Ln', '456-442-1384');


INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'oil change', '3000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'tire change', '45000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'tire rotation', '7500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'spark plugs', '37500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'air filter', '50000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'radiator flush', '100000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('1', 'performance', '10000');

INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'oil change', '3000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'tire change', '30000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'tire rotation', '5000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'spark plugs', '37500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'air filter', '40000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'radiator flush', '100000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('2', 'performance', '10000');

INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'oil change', '3000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'tire change', '45000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'tire rotation', '7500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'spark plugs', '37500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'air filter', '50000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('3', 'radiator flush', '100000');

INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'oil change', '3000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'tire change', '30000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'tire rotation', '5000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'spark plugs', '37500');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'air filter', '40000');
INSERT INTO maintenance_schedule (vehicle_id, name, interval) VALUES ('4', 'radiator flush', '100000');

INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '1', 'changed oil', '11000', '50.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '2', 'racing slicks', '5000', '550.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '3', 'rotated tires', '10000', '5.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '4', 'changed plugs', '0', '90.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '5', 'new air filter', '10000', '30.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '6', 'flushed radiator', '10000', '75.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '2', '7', 'nitrous refill', '10000', '85.00');

INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '5', '8', 'changed oil', '0', '0');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('2', '2', '9', 'snow tires', '500', '500.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('2', '3', '10', 'rotated tires', '0', '0');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('1', '5', '11', 'new plugs', '0', '0');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('2', '2', '12', 'k&n filter', '300', '55.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('2', '3', '13', 'new antifreeze', '11000', '50.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('2', '3', '14', 'performance chip', '11000', '200.00');

INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '15', 'oil change', '57000', '45.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '16', 'new tires', '51000', '300.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '17', 'rotated tires', '56000', '5.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '18', 'spark plugs', '49800', '70.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '19', 'new filter', '50000', '15.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('3', '1', '20', 'new antifreeze', '0', '0');

INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '21', 'changed oil', '30000', '30.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '22', 'new tires', '0', '0');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '23', 'rotated tires', '30000', '10.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '24', 'new spark plugs', '0', '0');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '25', 'new air filter', '30000', '19.00');
INSERT INTO maintenance (vehicle_id, mechanic_id, maintenance_schedule_id, name, mileage, cost) VALUES ('4', '4', '26', 'flushed antifreeze', '0', '0');


INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-01', '9750', '2018-11-01', '9950');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-02', '10050', '2018-11-02', '10100');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-04', '10150', '2018-11-04', '10250');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-05', '10251', '2018-11-05', '10375');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-06', '10385', '2018-11-06', '10450');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-07', '10500', '2018-11-07', '10650');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-10', '10685', '2018-11-10', '10795');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-11', '10850', '2018-11-11', '10990');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-15', '11005', '2018-11-15', '11200');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-15', '11250', '2018-11-15', '11395');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('1', '2018-11-16', '11400', '2018-11-16', '11475');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-16', '50', '2018-11-16','60');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-17', '61', '2018-11-17', '99');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-18', '105', '2018-11-18', '199');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-19', '209', '2018-11-19', '285');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-20', '305', '2018-11-20', '404');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-20', '450', '2018-11-20', '550');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-21', '560', '2018-11-21', '650');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-22', '705', '2018-11-22', '800');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-23', '850', '2018-11-23', '875');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-24', '900', '2018-11-24', '1005');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('2', '2018-11-25', '1037', '2018-11-25', '1101');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-01', '49750', '2018-11-01', '49850');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-02', '49855', '2018-11-02', '49975');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-03', '50010', '2018-11-03', '50200');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-04', '50300', '2018-11-04', '50500');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-05', '55000', '2018-11-05', '55150');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-06', '57000', '2018-11-06', '57200');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-07', '57205', '2018-11-07', '57300');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-08', '57380', '2018-11-08', '57450');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-09', '57900', '2018-11-09', '58101');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-10', '58950', '2018-11-10', '59100');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('3', '2018-11-11', '60875', '2018-11-11', '60950');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-12', '30510', '2018-11-12', '30575');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-13', '30600', '2018-11-13', '30715');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-14', '30800', '2018-11-14', '30915');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-15', '30978', '2018-11-15', '31015');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-16', '31150', '2018-11-16', '31208');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-17', '31286', '2018-11-17', '31352');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-18', '32455', '2018-11-18', '32850');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-20', '32960', '2018-11-20', '33021');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-22', '33089', '2018-11-22', '33129');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-23', '33211', '2018-11-23', '33248');
INSERT INTO mileage (vehicle_id, start_day, start_mileage, end_day, end_mileage) VALUES ('4', '2018-11-25', '33264', '2018-11-25', '33295');

INSERT INTO feedback (name, source, rating, notes) VALUES ('M. A. Turing', 'darrenyoung.design', '5', 'Upon retirement from Formula One racing and after publishing a bestselling autobiography I decided to give ride-sharing a spin. For me the driving was not the problem, it was the monotonous record keeping process that slowed me down. Never in my life had I considered defeat an option, but after a few weeks of spending all my free time organizing mileage and expenses I almost quit the race. Then, it happened - a moment I will never forget. The day I discovered DriverData I went from just another run-of-the-mill professional race-car driver best-selling author with a PhD in data science to the top-ranked Ride-Share driver that I know of. Thanks DriverData!');
INSERT INTO feedback (name, source, rating, notes) VALUES ('Seymour Butz', 'other', '1', 'yo mama was a hamster...lol');
