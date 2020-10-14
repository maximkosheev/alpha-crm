create or replace function alpha.trigger_set_create_date() returns trigger
 language plpgsql
 security definer
as $function$
begin
  new.create_date := now();
return new;
end
$function$
;

-- тарифный план
create table alpha.op_tariff (
  id int4 not null,
  name varchar(100) not null,
  create_date timestamp not null,
  constraint pk_tariff primary key(id)
);

comment on table alpha.op_tariff is 'Тарифный план';
comment on column alpha.op_tariff.name is 'Название плана';

create trigger tbi_op_tariff before insert on
    alpha.op_tariff for each row execute procedure alpha.trigger_set_create_date();

-- организация
create table alpha.op_organization (
  id bigserial not null,
  name varchar(100) not null,
  tariff_id int4 not null,
  create_date timestamp not null,
  constraint pk_organization primary key (id),
  constraint fk_organization_tariff foreign key(tariff_id) references alpha.op_tariff(id)
);

comment on table alpha.op_organization is 'Организация, на которую зарегистрирован аккаунт';
comment on column alpha.op_organization.name is 'Название организации';
comment on column alpha.op_organization.tariff_id is 'Идентификатор тарифа';

create trigger tbi_op_organization before insert on
    alpha.op_organization for each row execute procedure alpha.trigger_set_create_date();

create sequence alpha.subject_id_seq;

create table alpha.op_role (
  id int4 not null,
  role varchar(15) not null,
  subject_id bigint not null default nextval('alpha.subject_id_seq'),
  description varchar(30),
  constraint pk_role primary key (id),
  constraint uk_role_role unique (role),
  constraint uk_role_subject_id unique (subject_id)
);

-- Пользователь системы
create table alpha.op_user (
  id bigserial not null,
  firstname text not null,
  lastname text not null,
  middlename text,
  org_id bigint not null,
  email varchar(50) not null,
  password varchar(255) not null,
  phone varchar(20),
  role_id int4 not null,
  subject_id bigint not null default nextval('alpha.subject_id_seq'),
  create_date timestamp not null,

  constraint pk_user primary key(id),
  constraint fk_user_organization foreign key(org_id) references alpha.op_organization(id),
  constraint uk_user_email unique (email),
  constraint fk_user_role foreign key(role_id) references alpha.op_role(id),
  constraint uk_user_subject_id unique (subject_id)
);

comment on table alpha.op_user is 'Пользователь системы';
comment on column alpha.op_user.id is 'Идентификатор';
comment on column alpha.op_user.firstname is 'Имя';
comment on column alpha.op_user.lastname is 'Фамилия';
comment on column alpha.op_user.middlename is 'Отчество';
comment on column alpha.op_user.org_id is 'Организация, к которой привязан пользователь';
comment on column alpha.op_user.email is 'Почта он же логин';
comment on column alpha.op_user.role_id is 'Роль';
comment on column alpha.op_user.subject_id is 'Идентификатор субъекта безопасности';

create trigger tbi_op_user before insert on
    alpha.op_user for each row execute procedure alpha.trigger_set_create_date();

-- Типы папок
create table alpha.ref_folder_type (
  id int2 not null,
  name varchar(15),

  constraint pk_folder_type primary key (id),
  constraint uk_folder_type_name unique (name)
);

comment on table alpha.ref_folder_type is 'Типы папок в системе';
comment on column alpha.ref_folder_type.id is 'Идентификатор';
comment on column alpha.ref_folder_type.name is 'Имя типа';

-- Папки
create table alpha.op_folder (
  id bigserial not null,
  owner_id bigint not null,
  folder_type_id int2 not null,
  name text not null,
  parent_id bigint null,

  constraint pk_folder primary key (id),
  constraint fk_folder_user foreign key (owner_id) references alpha.op_user(id),
  constraint fk_folder_folder_type foreign key (folder_type_id) references alpha.ref_folder_type(id),
  constraint fk_folder_folder foreign key (parent_id) references alpha.op_folder(id)
);

comment on table alpha.op_folder is 'Папка с объектами системы определенного типа';
comment on column alpha.op_folder.id is 'Идентификатор';
comment on column alpha.op_folder.owner_id is 'Пользователь - владелец папки';
comment on column alpha.op_folder.folder_type_id is 'Тип объектов, хранящихся в папке';
comment on column alpha.op_folder.name is 'Имя папки';
comment on column alpha.op_folder.parent_id is 'Идентификатор родительской папки';
