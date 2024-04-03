
create sequence books_seq start with 1 increment by 50;

create sequence borrow_records_seq start with 1 increment by 50;

create sequence membership_requests_seq start with 1 increment by 50;

create sequence memberships_seq start with 1 increment by 50;

create sequence reading_groups_seq start with 1 increment by 50;

create sequence reviews_seq start with 1 increment by 50;

create sequence users_seq start with 1 increment by 50;

create table books (
                       book_key VARCHAR(40) UNIQUE NOT NULL,
                       hard_cover boolean,
                       height integer,
                       width integer,
                       due_date timestamp(6),
                       id bigint not null,
                       posted_by_id bigint,
                       genre varchar(64) not null,
                       name varchar(64),
                       author varchar(256) not null,
                       book_title varchar(256) not null,
                       description varchar(1024),
                       book_description varchar(255) not null,
                       book_status CHAR(3) CHECK(book_status in ('AVL','BRW','MBR')),
                       file_path varchar(255),
                       filetype varchar(255),
                       language CHAR(2) CHECK(language in ('EN','DE','FR','ES','IT','PT','NL','RU','PL','SV','NO','DA','FI','EL','CS','HU','RO','BG','SR','HR','BS','SK','SL')),
        primary key (id)
    );

create table borrow_records (
                                borrow_date timestamp(6),
                                borrowed_book_id bigint,
                                borrower_id bigint,
                                id bigint not null,
                                return_date timestamp(6),
                                primary key (id)
);

create table membership_requests (
                                     id bigint not null,
                                     request_date timestamp(6),
                                     requested_reading_group_id bigint,
                                     requesting_user_id bigint,
                                     request_status CHAR(1) CHECK(request_status in ('A','P','R')),
                                     primary key (id)
);

create table memberships (
                             id bigint not null,
                             join_date timestamp(6),
                             member_id bigint,
                             reading_group_id bigint,
                             primary key (id)
);

create table reading_groups (
                                created_by_id bigint,
                                creation_date timestamp(6),
                                id bigint not null,
                                description varchar(64) not null,
                                name varchar(64) not null,
                                primary key (id)
);

create table reviews (
                         rating float(53) not null,
                         id bigint not null,
                         review_date timestamp(6) not null,
                         reviewed_book_id bigint,
                         reviewer_id bigint,
                         comment varchar(255),
                         primary key (id)
);

create table users (
                       height integer,
                       width integer,
                       id bigint not null,
                       first_name varchar(32) not null,
                       email varchar(64),
                       last_name varchar(64) not null,
                       name varchar(64),
                       username varchar(64) not null,
                       password varchar(256) not null,
                       description varchar(1024),
                       city varchar(255),
                       country varchar(255),
                       file_path varchar(255),
                       filetype varchar(255),
                       gender CHAR(1) CHECK(gender in ('f','m','o','u')),
                       phone_number varchar(255),
                       postal_code varchar(255),
                       street varchar(255),
                       user_role CHAR(1) CHECK(user_role in ('A','S')) not null,
                       primary key (id)
);

alter table if exists books
    add constraint FK5j1u58f6bp7hov4y3msx8bltj
    foreign key (posted_by_id)
    references users;

alter table if exists borrow_records
    add constraint FKt4cfi96quyd8d8kk2t4trkg0d
    foreign key (borrowed_book_id)
    references books;

alter table if exists borrow_records
    add constraint FKiejlerch9mckg3dkl9vrlqn32
    foreign key (borrower_id)
    references users;

alter table if exists membership_requests
    add constraint FK1a1j4wgmjphsyt2j5igj9btpn
    foreign key (requested_reading_group_id)
    references reading_groups;

alter table if exists membership_requests
    add constraint FKemfc8uctj02akib2aar4xw63v
    foreign key (requesting_user_id)
    references users;

alter table if exists memberships
    add constraint FK30r1xtc6xt5bi04wqgf8q5ub
    foreign key (member_id)
    references users;

alter table if exists memberships
    add constraint FKhxmx9j5ndv10ixog30vo5qjhw
    foreign key (reading_group_id)
    references reading_groups;

alter table if exists reading_groups
    add constraint FK2h0ri53aocbwsmibwciepb51m
    foreign key (created_by_id)
    references users;

alter table if exists reviews
    add constraint FKe0y4tx8web9ajsibn9w61pu6w
    foreign key (reviewed_book_id)
    references books;

alter table if exists reviews
    add constraint FKd1isgfajhtdl8mgg29up6mofi
    foreign key (reviewer_id)
    references users;
