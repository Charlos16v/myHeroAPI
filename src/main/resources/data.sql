INSERT INTO Hero (id, name, description, dob)
VALUES
    (NEXTVAL('hero_sequence'), 'Superman', 'Man of Steel', '1980-01-01'),
    (NEXTVAL('hero_sequence'), 'Batman', 'Dark Knight', '1975-03-15'),
    (NEXTVAL('hero_sequence'), 'Wonder Woman', 'Amazon Princess', '1985-06-10'),
    (NEXTVAL('hero_sequence'), 'The Flash', 'Speedster', '1990-12-05'),
    (NEXTVAL('hero_sequence'), 'Aquaman', 'King of Atlantis', '1988-08-20');
