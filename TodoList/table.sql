create table tasklist(id serial, taskSummary varchar(256), taskPriority int2, taskCreation varchar(256),taskStatus int2);
alter table tasklist add column taskCreateDate date;
update tasklist set taskcreatedate = pg_catalog.date(substring(taskcreation,0,strpos(taskcreation,' GMT')))
