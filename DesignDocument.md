# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)
```mermaid
---
title: Payroll UML
---
classDiagram
    PayrollGenerator --> FileUtil
    PayrollGenerator --> Builder
    Builder --> IEmployee
    Builder --> ITimeCard
    class IEmployee{
        <<interface>>
        +getName() String
        +getID() String
        +getPayRate() Double
        +getEmployeeType() String
        +getYTDEarnings() Double
        +getYTDTaxesPaid() Double
        +getPretaxDeductions() Double
        +runPayroll(hoursWorked: Double) IPayStub
        +toCSV() String
    }
    
    class IPayStub{
        <<interface>>
        +getPay() Double
        +getTaxesPaid() Double
        +toCSV() String
    }
    
    class ITimeCard{
        <<interface>>
        +getEmployeeID() String
        +getHoursWorked() Double
    }
    
    class PayrollGenerator{
        -DEFAULT_EMPLOYEE_FILE: String
        -DEFAULT_PAYROLL_FILE: String
        -DEFAULT_TIME_CARD_FILE: String
        +main(String[] args): void
    }
    
    class FileUtil{
        +EMPLOYEE_HEADER: String
        +PAY_STUB_HEADER: String
        +readFileToList(file: String) List~String~
        +writeFile(outFile: String, lines: List~String~) void
    }
    
    class Builder{
        +buildEmployeeFromCSV(csv: String) IEmployee
        +buildTimeCardFromCSV(csv: String) ITimeCard
    }
```


## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. Test that the `Employee` class properly returns `payRate` from `getPayRate()`
4. Test that the `Employee` class properly returns `YTDEarnings` from `getYTDEarnings()`
5. Test that the `Employee` class properly returns `YTDTaxesPaid` from `getYTDTaxesPaid()`
6. Test that the `Employee` class properly returns `preTaxDeductions` from `getPretaxDeductions()`
7. Test that the `Employee` class properly generates `CSV` string format from `toCSV()`
8. Test that the `Employee` class throws exception when constructed with negative values
9. Test that the `TimeCard` class properly returns `employeeID` from `getEmployeeID()`
10. Test that the `TimeCard` class properly returns `hoursWorked` from `getHoursWorked()`
11. Test that the `TimeCard` class throws exception when constructed with negative hours
12. Test that the `PayStub` class properly returns the net `pay` amount from `getPay()`
13. Test that the `PayStub` class properly returns `taxesPaid` from `getTaxesPaid()`
14. Test that the `PayStub` class properly generates `CSV` string format from `toCSV()`


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.

```mermaid
---
title: Payroll UML
---
classDiagram
    PayrollGenerator --> FileUtil
    PayrollGenerator --> Builder
    Builder --> IEmployee
    Builder --> ITimeCard
    Builder --> SalaryEmployee
    Builder --> HourlyEmployee
    Builder --> TimeCard
    Employee --|> IEmployee
    HourlyEmployee --|> IEmployee
    SalaryEmployee --|> IEmployee
    TimeCard ..|> ITimeCard
    PayStub ..|> IPayStub
    
    class IEmployee{
        <<interface>>
        +getName() String
        +getID() String
        +getPayRate() Double
        +getEmployeeType() String
        +getYTDEarnings() Double
        +getYTDTaxesPaid() Double
        +getPretaxDeductions() Double
        +runPayroll(hoursWorked: Double) IPayStub
        +toCSV() String
    }
    
    class Employee{
        <<abstract>>
        -name: String
        -id: String
        -payRate: Double
        -ytdTaxesPaid: Double
        -pretaxDeductions: Double
        +Employee(name: String, id: String, payRate: Double, ytdEarnings: Double, ytdTaxesPaid: Double, pretaxDeductions: Double)
        +getName() String
        +getID() String
        +getPayRate() Double
        +getYTDEarnings() Double
        +getYTDTaxesPaid() Double
        +getPretaxDeductions() Double
        +runPayroll(hoursWorked: Double) IPayStub
        #calculateGrossPay(hoursWorked: Double)* Double
        #roundValue(val: Double) Double
        +toCSV()* String
        +getEmployeeType()* String
    }
    
    class HourlyEmployee{
        +HourlyEmployee(name: String, id: String, payRate: Double, ytdEarnings: Double, ytdTaxesPaid: Double, pretaxDeductions: Double)
        +calculateGrossPay(hoursWorked: Double) Double
        +toCSV() String
        +getEmployeeType() String
    }
    
    class SalaryEmployee{
        +SalaryEmployee(name: String, id: String, payRate: Double, ytdEarnings: Double, ytdTaxesPaid: Double, pretaxDeductions: Double)
        +calculateGrossPay(hoursWorked: Double) Double
        +toCSV() String
        +getEmployeeType() String
    }
    
    
    class IPayStub{
        <<interface>>
        +getPay() Double
        +getTaxesPaid() Double
        +toCSV() String
    }
    
    class PayStub{
        -employee: IEmployee
        -netPay: Double
        -taxes: Double
        +PayStub(employee: IEmployee, netPay: Double, taxes: Double)
        +getPay() Double
        +getTaxesPaid() Double
        +toCSV() String
    }
    
    class ITimeCard{
        <<interface>>
        +getEmployeeID() String
        +getHoursWorked() Double
    }
    
    class TimeCard{
        -employeeID: Sting
        -hoursWorked: Double
        +TimeCard(employeeID: String, hoursWorked: Double)
        +getEmployeeID() String
        +getHoursWorked() Double
    }
    
    class PayrollGenerator{
        -DEFAULT_EMPLOYEE_FILE: String
        -DEFAULT_PAYROLL_FILE: String
        -DEFAULT_TIME_CARD_FILE: String
        +main(String[] args): void
    }
    
    class FileUtil{
        +EMPLOYEE_HEADER: String
        +PAY_STUB_HEADER: String
        +readFileToList(file: String) List~String~
        +writeFile(outFile: String, lines: List~String~) void
    }
    
    class Builder{
        +buildEmployeeFromCSV(csv: String) IEmployee
        +buildTimeCardFromCSV(csv: String) ITimeCard
    }
```



## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

    Through this process, I learned the importance of modular design and data consistency. Additionally, I would spend more time designing a clear data flow diagram start coding.
    I realize that the most crucial aspects is deeply understanding the core concepts behind the problem. One key takeaway from this process is that I need to invest more time in fully understanding the underlying concepts before diving into implementation. 
    My payroll system had fundamental issues in handling calculations, particularly in ensuring that YTD earnings and YTD taxes were not incorrectly accumulated multiple times. Next time, I will prioritize conceptual understanding and rigorous test-driven development to create a more stable and maintainable system from the outset.
