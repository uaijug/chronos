--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
--insert into Member (id, name, email, ddd, phone_number) values (1, 'John Smith', 'john.smith@mailinator.com', '034', '2125551212') 
--insert into PostCategory (id, name, slug, description) values (1, 'Java', 'java', 'Java noano');
--insert into Post (id, post_category_id, title, content, member_id, tag) values (1, 1, 'post1', 'content1', 1, 'java');
--insert into Page (id, title, content, slug) values (1, 'Sobre', 'ssobrel', 'sobre');

--insert into uj_member_type (active, create_time, update_time, description, id) values ('Y', '2001-01-01', '2001-01-01', 'Associado', 1);
--insert into uj_member_type (active, create_time, update_time, description, id) values ('Y', '2001-01-01', '2001-01-01', 'Lider', 2);

--insert into uj_country (active, create_time, update_time, description, id) values ('Y', '2001-01-01', '2001-01-01', 'Brasil', 1);    
--insert into uj_state (active, create_time, update_time, country_id, description, id) values ('Y', '2001-01-01', '2001-01-01', 1, 'Minas Gerais',1);
--insert into uj_city (active, create_time, update_time, description, state_id, id) values ('Y', '2001-01-01', '2001-01-01', 'Uberlandia', 1, 1);

--insert into uj_address (active, create_time, update_time, city_id, street, id) values ('Y', '2001-01-01', '2001-01-01', 1,'Afonso pena', 1);
--insert into uj_address (active, create_time, update_time, city_id, street, id) values ('Y', '2001-01-01', '2001-01-01', 1,'Floriano Peixoto', 1);
--insert into uj_address (active, create_time, update_time, city_id, street, id) values ('Y', '2001-01-01', '2001-01-01', 1,'Getulio Vargas', 1);
--insert into uj_address (active, create_time, update_time, city_id, street, id) values ('Y', '2001-01-01', '2001-01-01', 1,'Rodon Pacheco', 1);

insert into uj_admin_profile (id, acronym, description) values (1, 'ADMIN', 'ADMIN');

insert into uj_admin_user (id, name, email, username, password, profile_id, website, location) values (1, 'rogerioz', 'fontes@sk.c', 'rogerioz', '12345', 1, 'www', 'lo');