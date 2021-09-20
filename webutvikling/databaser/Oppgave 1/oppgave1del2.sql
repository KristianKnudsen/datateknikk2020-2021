#Oppgave 1
select * from emp where deptno = 1 and not (job = "Manager" or job = "Clerk");

#Oppgave 2
select * from emp where sal between 1200 and 1300;

#Oppgave 3
select ename, job, deptno from emp where job in ( "Clerk","Analyst","Salesman");

#Oppgave 4
select ename, job, sal from emp where not sal between 1200 and 1400;

#Oppgave 5
select ename, job, deptno from emp where not job in ("Clerk", "Analyst", "Salesman");

#Oppgave 6
select ename,job,deptno from emp where ename like "M%";

#Oppgave 7
select ename,job,deptno from emp where ename like "Al%%n";

#Oppgave 8
select sal, ename, deptno from emp where deptno = 3 order by sal;

#Oppgave 9
select sal, ename, deptno from emp where deptno = 3 order by sal desc;

#Oppgave 10
select ename,job,sal from emp order by job, sal desc;

#Oppgave 11
select ename, loc from emp inner join dept on emp.deptno = dept.deptno where ename = "Allen";

#Oppgave 12
select dept.deptno, dname, job, ename from dept left join emp on emp.deptno = dept.deptno where dept.deptno in (3,4);

#Oppgave 13
select distinct dept.deptno, dname, loc from dept left join emp on emp.deptno = dept.deptno where empno is null;

