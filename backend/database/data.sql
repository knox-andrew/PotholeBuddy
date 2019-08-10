-- Password for this user is 'greatwall'
INSERT INTO users ("username", "password", "salt", "role") VALUES
(
'user',
'EmoCMCuUlCLW7t8e00GO9w==',
'SucIO9OtpEp7b5b18B9S5e18SWaiqkcZy3OtisNlhiqsp8z2ISNsvByumL/iCVoymij8GpE8XQak8YjLNS9m/H8Xj/rOYnudCkGdNGlTqLGSmSND5uXMvjmvWDtDzXI6br4n4rTaXPuf9HyNfzeKabnQmhG6qb7WpEldLm8j40g=',
'user');

INSERT INTO users ("username", "password", "salt", "role") VALUES
(
'admin',
'cW2AONpnQ+ObaO3czTel6g==',
'e5iKdR3wo1gKD7TJIeANVxKZRYZmO0XPqo2vDwRFiTI7SD2ol1iHyH4FeBPaeoJLyichw7muJXR7Z//L33/KpWmkjXjkS8/R39p5cfZpFLsANrIobO6ZMFeMD1ZXraVAOYS5ncU3/AXgvSTwTWZMfwgvvI3KEEiKfMeEfXPOJ1I=',
'admin');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.193684289509065', '-84.32208179208072', 'moderate', 
        'Not too bad. Located on the left side of the road.');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.22214908061195', '-84.36568378182682', 'severe', 
        'Fairly deep and in the middle of the road. Definitely avoid.');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.22214908061195', '-84.36568378182682', 'critical', 
        'Very deep and covers almost half the road. Cracked 2 of my rims');
