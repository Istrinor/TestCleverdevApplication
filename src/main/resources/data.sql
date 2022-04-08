INSERT INTO second_level_status (id, name)
values (1, 'Active');

INSERT INTO patient_status (id, name, second_level_status_id)
values (200, 'First', 1),
       (210, 'Second', 1),
       (230, 'Third', 1);

INSERT INTO patient_profile (id, first_name, last_name, patient_status_id)
values (1, 'Mmohn', 'Mhoms', 200),
       (2, 'Jmoh', 'Rhomrds', 200);

INSERT INTO old_client_guid (old_client_guid, patient_id)
values ('17DD0E94-6A57-49C8-E933-CC480D1AC3FB', 1),
       ('A661E400-8CD0-6336-D0C2-2E8012903819', 2);

INSERT INTO company_user (id, login)
values (1, 'someLogin');

INSERT INTO patient_note (id, old_system_note_guid, created_date_time, last_modified_date_time, created_by_user_id,
                          last_modified_by_user_id, note, pateint_id)
values (1, 'Test guid', '2022-04-06 00:00:00.000000', '2022-04-06 00:00:00.000000', 1, 1, 'Test note', 1);

