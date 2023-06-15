CREATE TABLE `mentors` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(64),
  `password` varchar(256),
  `name` varchar(32),
  `description` text,
  `earned` decimal(8, 2),
  `salary_per_hour` decimal(8, 2),
  `total_job` int,
  `total_hours` decimal(8, 2),
  `success_rate` decimal(8, 2),
  `avg_rate` decimal(8, 2),
  `skills` json,
  `created_at` datetime,
  `updated_at` datetime
);
