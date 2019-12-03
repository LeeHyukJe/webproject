DROP TABLE IF EXISTS CARREGISTER;

create table CARREGISTER (
                             productsTitle varchar2 not null,
                             Price int not null ,
                             description varchar2 not null,
                             modelYear varchar2 not null ,
                             carMileage varchar2 not null,
                             smokingOptions varchar2 not null
);

create table tbl_member_roles(
                                 fno varchar2 not null,
                                 roleName varchar2 not null
);

create table tbl_members(
    uid varchar2 not null,
    upw varchar2 not null,
    uname varchar2 not null,
    regdate date default systimestamp ,
    updatedate date default systimestamp ,
    fno varchar2 ,
    constraint fk_user foreign key (fno) references tbl_member_roles(fno)

);

insert into tbl_member_roles values('85','NORMAL');
insert into tbl_member_roles values('1','MANAGER');
insert into tbl_members(uid,upw,uname,fno) values('user1','1234','사용자1','85');
insert into tbl_members(uid,upw,uname,fno) values('user2','1234','관리','1');
