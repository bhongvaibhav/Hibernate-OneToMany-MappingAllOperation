package com.tybsc.hibernate5_OneToManyAllop;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.Collection;
//import org.hibernate.sql.Update;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
	private static SessionFactory factory;
	
	
    public static void main( String[] args )
    {
       factory = new Configuration().configure().buildSessionFactory();
       
       HashSet<Certificate>set1 = new HashSet<Certificate>();
       Certificate cert1 = new Certificate("MCA");
       addCertificate(cert1);
       Certificate cert2 = new Certificate("MBA");
       addCertificate(cert2);
       Certificate cert3 = new Certificate("PHP");
       addCertificate(cert3);
       
       set1.add(cert1);
       set1.add(cert2);
       set1.add(cert3);
       
       Integer empID1 = addEmployee("Manoj","Kumar",4000,set1);

       HashSet<Certificate>set2 = new HashSet<Certificate>();
       Certificate cert4 = new Certificate("MCA");
       addCertificate(cert4);
       Certificate cert5 = new Certificate("MBA");
       addCertificate(cert5);
       
       set2.add(cert4);
       set2.add(cert5);
       
       Integer empID2 = addEmployee("Dilip","Kumar",3000,set2);
       
       listEmployees();
       
       updateEmployee(empID1,15000);
       
       //deleteEmployee(empID1);
       
       listEmployees();
    
    }
       public static void addCertificate(Certificate cert)
       {
    	   Session session = factory.openSession();
    	   Transaction tx = null;
    	   tx = session.beginTransaction();
    	   session.save(cert);
    	   System.out.println("****** Certificate added "+cert+"****\n");
    	   tx.commit();
       }
       
       public static Integer addEmployee(String fname,String lname,int salary,HashSet cert)
       {
    	   Session session = factory.openSession();
    	   Transaction tx = null;
    	   Integer employeeID = null;
    	   tx = session.beginTransaction();
    	   Employee employee = new Employee(fname,lname,salary,cert);
    	   employeeID = (Integer)session.save(employee);
    	   tx.commit();
    	   System.out.println("******Employee Added*****"+employee+"*****\n");
    	   return employeeID;
    			   
       }
       public static void listEmployees()
       {
    	   Session session = factory.openSession();
    	   Transaction tx = null;
    	   tx = session.beginTransaction();
    	   List employees = session.createQuery("FROM Employee").list();
    	   
    	   System.out.println("\n**** List of all Employee***");
    	   
    	   for(Iterator iterator1 = employees.iterator();iterator1.hasNext();)
    	   {
    		   System.out.println();
    		   Employee employee = (Employee)iterator1.next();
    		   System.out.println("First Name:"+employee.getFirstName());
    		   System.out.println("Last Name:"+employee.getLastName());
    		   System.out.println("Salary:"+employee.getSalary());
    		   Set certificates = employee.getCertificates();
    		   for(Iterator iterator2 = certificates.iterator();iterator2.hasNext();)
    		   {
    			   Certificate certName = (Certificate)iterator2.next();
    			   System.out.println("Certificate:"+certName.getName());
    		   
    		   }  
    	   }
    	   System.out.println();
    	   tx.commit();
       }
       
       public static void updateEmployee(Integer EmployeeID,int salary1)
       {
    	   Session session = factory.openSession();
    	   Transaction tx = null;
    	   tx = session.beginTransaction();
    	   Employee employee = (Employee)session.get(Employee.class, EmployeeID);
    	   employee.setSalary(salary1);
    	   session.update(employee);
    	   System.out.println("****Employee updated"+employee+"***\n");
    	   tx.commit();
       }
       public static void deleteEmployee(Integer EmployeeID)
       {
    	   Session session = factory.openSession();
    	   Transaction tx = null;
    	   tx = session.beginTransaction();
    	   Employee employee = (Employee)session.get(Employee.class, EmployeeID);
    	   //employee.setSalary(salary);
    	   session.delete(employee);
    	   System.out.println("****Employee Deleted"+employee+"***\n");
    	   tx.commit();
       }
       

    
}

	