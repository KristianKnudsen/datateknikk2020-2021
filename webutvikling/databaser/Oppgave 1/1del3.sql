#1
select ename, sal, comm from emp where comm > 0.25*sal;
#2 
select ename, sal, comm, comm/sal as "comm/sal" from emp where comm is not null order by comm/sal desc;
#3
select ename, sal, comm, 12*(sal+comm) as Årslønn from emp where comm is not null order by Årslønn desc;
#4
select avg(sal) from emp where job = "Clerk";
#5
select sum(sal), sum(comm) from emp where job = "Salesman";
#6
select count(comm) from emp where comm > 0;
#7
select count(distinct job) from emp where deptno = 3;
#8
select count(distinct empno) from emp where deptno = 3;
#9
select deptno, avg(sal) as "Avrege salary" from emp group by deptno;
#10
select CONCAT(dname, " - ", loc) as "Departments" from dept;
#11
select ename, job, case when job = "Clerk" then 1 when job = "Salesman" then 2 when job = "Manager" then 3 when job = "Analyst" then 4 else 5 end as job_class from emp;
#12
select substring(ename, 2, length(ename)) as name_part from emp;
#13
select curdate() as date;