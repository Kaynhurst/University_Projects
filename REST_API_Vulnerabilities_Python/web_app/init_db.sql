CREATE TABLE IF NOT EXISTS rest_api_tasks (
    id SERIAL PRIMARY KEY,
    task VARCHAR(180) NOT NULL,
    description VARCHAR(400),
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    completed BOOLEAN,
    updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER
);

-- Insert task data into the table

INSERT INTO rest_api_tasks (id, task, description, timestamp, completed, updated, user_id)
VALUES 
    (1, 'Make soup', 'Gather the ingridients', '2024-12-15 20:13:20.566959+00', FALSE, '2024-12-15 20:13:20.566978+00', 1),
    (2, 'Get the dog', 'Animal shelter near the store.', '2024-12-15 20:13:33.205051+00', FALSE, '2024-12-15 20:13:33.205082+00', 1),
    (3, 'New Simple Task', 'Be productive', '2024-12-15 20:30:56.693376+00', TRUE, '2024-12-15 20:30:56.6934+00', 1),
    (4, 'Finish the Project', 'Do everything', '2024-12-15 20:37:17.940634+00', FALSE, '2024-12-15 20:37:17.940653+00', 1),
    (5, 'Touch grass', 'You know what to do.', '2024-12-15 20:15:03.86957+00', FALSE, '2024-12-15 20:15:03.869768+00', 1)

    (6, 'Make the report', 'Do everything', '2024-12-15 20:37:17.940634+00', FALSE, '2024-12-15 20:37:17.940653+00', 2),
    (7, 'Eat shrimps', 'Yummy.', '2024-12-15 20:15:03.86957+00', FALSE, '2024-12-15 20:15:03.869768+00', 2),

    (8, 'Pet the cat', 'Yes', '2024-12-15 20:37:17.940634+00', FALSE, '2024-12-15 20:37:17.940653+00', 3),
    (9, 'Ride Bike', 'Vroom Vroom.', '2024-12-15 20:15:03.86957+00', FALSE, '2024-12-15 20:15:03.869768+00', 3),

    (10, 'Buy Flowers', 'All the Colors !!', '2024-12-15 20:37:17.940634+00', FALSE, '2024-12-15 20:37:17.940653+00', 4),
    (11, 'Sleep', 'You need it.', '2024-12-15 20:15:03.86957+00', FALSE, '2024-12-15 20:15:03.869768+00', 4)

ON CONFLICT (id) DO NOTHING;

SELECT setval('rest_api_tasks_id_seq', (SELECT MAX(id) FROM rest_api_tasks));
