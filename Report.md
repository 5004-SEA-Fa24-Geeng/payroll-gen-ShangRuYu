# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 

   A CSV (comma-separated values) file is a text file that has a specific format which allows data to be saved in a table structured format.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

   Because it allows code achieved polymorphism. `IEmployee` is an interface, which means that `List<IEmployee>` can hold both `HourlyEmployee` and `SalaryEmployee` objects.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

   has-a.

4. Can you provide an example of a has-a relationship in your code (if one exists)?

   In PatStub class has a IEmployee, the Employee is a component part of the PayStub

5. Can you provide an example of an is-a relationship in your code (if one exists)?

   In HourlyEmployee and SalaryEmployee extends Employee, both are is-a relationship

6. What is the difference between an interface and an abstract class?

    Interface specifies methods that a class must implement. Abstract class can have both implemented and abstract methods.

7. What is the advantage of using an interface over an abstract class?

   Java allows a class to implement multiple interfaces but only extend one abstract class.    

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

   Nor valid, because Java Generics do not except primitive types like int, double, or char. `List<Integer> numbers = new ArrayList<Integer>();`


9. Which class/method is described as the "driver" for your application? 

   `class PayrollGenerator` and `public static void main(String[] args)`

10. How do you create a temporary folder for JUnit Testing?

    The @TempDir annotation in JUnit 5 can be used to create a temporary directory. It can be applied as a method parameter or as a field within the test class.

## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

      All over the world, women have restricted access to quality education, especially in Science, Technology, Engineering, and Mathematics (STEM)[^1]. 
      In the fields with the lowest concentration of women (computer science and engineering), gender wage gaps persist even after controlling for observed characteristics.[^2]

      We can add new data fields such as gender, age and level or other related issue. With this method we can track salary growth and ensure fairness. We can also add method to compare employees with similar experience and job levels, looking for any significant disparities.

[^1] Sharma, M. (2023). STEM education and gender income parity in USA, 2019. Social Sciences & Humanities Open, 8(1), 100541.
[^2] Michelmore, K., & Sassler, S. (2016). Explaining the gender wage gap in STEM: does field sex composition matter?. RSF: The Russell Sage Foundation Journal of the Social Sciences, 2(4), 194-215.