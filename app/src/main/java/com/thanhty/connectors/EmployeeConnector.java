package com.thanhty.connectors;

import com.thanhty.models.Employee;
import com.thanhty.models.ListEmployee;

public class EmployeeConnector
{
    public Employee login(String usr,String pwd)
    {
        ListEmployee le=new ListEmployee();
        le.gen_dataset();
        for (Employee emp : le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equalsIgnoreCase(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}
