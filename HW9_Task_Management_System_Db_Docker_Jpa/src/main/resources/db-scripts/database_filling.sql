INSERT INTO users (id, name)
VALUES (1, 'First User'),
       (2, 'Second User'),
       (3, 'Third User'),
       (4, 'Fourth User');

INSERT INTO tasks (task_id, title, description, deadline, priority, status, user_id)
VALUES (1001, 'Task 1', 'Task one description', '2024-06-01', 'MEDIUM', 'NEW', 1),
       (1002, 'Task 2', 'Task two description', '2024-06-02', 'LOW', 'NEW', 2),
       (1003, 'Task 3', 'Task three description', '2024-06-03', 'HIGH', 'NEW', 3),
       (1004, 'Task 4', 'Task four description', '2024-06-04', 'HIGH', 'NEW', 4),
       (1005, 'Task 5', 'Task five description', '2024-06-05', 'MEDIUM', 'NEW', 1),
       (1006, 'Task 6', 'Task six description', '2024-06-06', 'HIGH', 'NEW', 2),
       (1007, 'Task 7', 'Task seven description', '2024-06-07', 'LOW', 'NEW', 3),
       (1008, 'Task 8', 'Task eight description', '2024-06-08', 'HIGH', 'NEW', 4),
       (1009, 'Task 9', 'Task nine description', '2024-06-09', 'MEDIUM', 'NEW', 1);