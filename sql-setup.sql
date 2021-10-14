drop table if exists request;

drop table if exists employee;

create table employee(
employee_id serial primary key,
username varchar(100),
passwd varchar(50),
fname varchar(50),
mname varchar(50),
lname varchar(50),
pemail varchar(100),
pphone varchar(10),
wphone varchar(10),
hire_date date default now(),
employee_role varchar(10)
);

create table request(
request_id serial primary key,
amount decimal(15,2),
category varchar(10),
status varchar(10),
req_date timestamp default now(),
dec_date timestamp,
req_emp_id integer references employee(employee_id),
dec_manager_id integer references employee(employee_id)
);

insert into employee(username,passwd,fname,mname,lname,pemail,employee_role)
values('prime.mover','IAmAlpha','Prime','','Mover','alpha@primemover.not','MANAGER');

insert into employee(username,passwd,fname,mname,lname,pemail,employee_role)
values('shawn.mao','ChangeMeNow!','Shawn','','Mao','shawn.mao@primemover.not','MANAGER');

insert into employee(username,passwd,fname,mname,lname,pemail,employee_role)
values('alpha.romero','ChangeMeNow!','Alpha','','Romero','alpha.romero@primemover.not','ASSOCIATE');

insert into employee(username,passwd,fname,mname,lname,pemail,employee_role)
values('dr.who','ChangeMeNow!','Dr','','Who','thedoctor@primemover.not','MANAGER');

insert into employee(username,passwd,fname,mname,lname,pemail,employee_role)
values('henry.potter','ChangeMeNow!','Henry','','Potter','not.harry@primemover.not','ASSOCIATE');




