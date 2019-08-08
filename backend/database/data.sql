-- Password for this user is 'greatwall'
INSERT INTO users ("username", "password", "salt", "role") VALUES
(
'user',
'FjZDm+sndmsdEDwNtfr6NA==',
'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
'user');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.193684289509065', '-84.32208179208072', 'moderate', 
        'Not too bad. Located on the left side of the road.');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.22214908061195', '-84.36568378182682', 'severe', 
        'Fairly deep and in the middle of the road. Definitely avoid.');

INSERT INTO markers ("user_id", "latitude", "longitude", "rating", "comments") 
VALUES (1, '39.22214908061195', '-84.36568378182682', 'critical', 
        'Very deep and covers almost half the road. Cracked 2 of my rims');
