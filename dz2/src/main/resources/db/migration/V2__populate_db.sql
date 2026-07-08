-- Insert workers
INSERT INTO worker (name, birthday, level, salary) VALUES
('John Doe', '1995-03-15', 'Junior', 1200),
('Jane Smith', '1990-07-22', 'Middle', 2500),
('Bob Johnson', '1985-11-05', 'Senior', 5500),
('Alice Williams', '1998-01-30', 'Trainee', 800),
('Charlie Brown', '1992-05-12', 'Middle', 2800),
('David Davis', '1988-09-18', 'Senior', 6000),
('Eva Elks', '2000-12-01', 'Trainee', 900),
('Frank Fur', '1994-04-25', 'Junior', 1500),
('Grace Green', '1987-10-10', 'Senior', 7000),
('Henry Hill', '1991-08-08', 'Middle', 3000);

-- Insert clients
INSERT INTO client (name) VALUES
('Acme Corporation'),
('Globex Biotech'),
('Umbrella Corp'),
('Initech LLC'),
('Hooli Inc');

-- Insert projects
INSERT INTO project (client_id, start_date, finish_date) VALUES
(1, '2023-01-01', '2023-06-01'),
(1, '2023-03-01', '2023-12-01'),
(2, '2022-05-15', '2023-05-15'),
(2, '2023-06-01', '2024-06-01'),
(3, '2021-10-01', '2023-10-01'),
(3, '2023-01-15', '2023-04-15'),
(4, '2023-02-01', '2023-08-01'),
(4, '2023-05-01', '2023-11-01'),
(5, '2023-07-01', '2024-01-01'),
(5, '2023-09-01', '2024-09-01');

-- Assign workers to projects
INSERT INTO project_worker (project_id, worker_id) VALUES
(1, 1), (1, 2),
(2, 2), (2, 3),
(3, 3), (3, 4),
(4, 5), (4, 6),
(5, 6), (5, 7), (5, 8),
(6, 8),
(7, 9), (7, 10),
(8, 1), (8, 10),
(9, 2), (9, 4),
(10, 3), (10, 5), (10, 6), (10, 9);
