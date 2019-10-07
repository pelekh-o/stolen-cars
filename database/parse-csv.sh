#!/bin/bash

read -r -p "Enter csv files path: " path

read -r -p "Enter db user: " user
read -r -s -p "Enter password: " password

for file in $path
    do
        printf "\nImporting file %s\n" "$file"

        mysql -e "USE stolen_cars;
            LOAD DATA LOCAL INFILE '$file'
            INTO TABLE car_info
            FIELDS TERMINATED BY ';'
            OPTIONALLY ENCLOSED BY '\"'
            LINES TERMINATED BY '\n'
            IGNORE 1 LINES
            (person, reg_addr_koatuu, operation_code, operation_name, regdate, 
            department_code, department_name, brand, model, make_year, color, 
            kind, body, purpose, fuel, capacity, own_weight, total_weight, 
            vechicle_number)" -u "$user" --password="$password" --local-infile

        printf "Completed importing %s\n" "$file"
done