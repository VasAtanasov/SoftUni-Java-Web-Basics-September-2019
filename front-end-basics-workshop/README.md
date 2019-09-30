# Workshop – Web Fundamentals

Exam problems for the “JavaScript Advanced” course @ SoftUni. Submit your solutions in the SoftUni Judge system at https://judge.softuni.bg/Contests/1586/.

## Problem 1. SoftUni Courses

Use the given CSS files to create similar view and solve the problem.

![site overview]("../z_resources/front-end-basics/media/image1.png")

### Your task

Write the HTML and JavaScript code to make the SoftUni Courses functionality work as follows: 

This season SoftUni has focused solely on Java courses. That’s awesome, right? The 
available courses are:

- Java Fundamentals – 170 BGN
- Java Advanced - 180 BGN
- Java DB - 190 BGN
- Java Web - 490 BGN

The offered education forms are Onsite and Online.
The Java Fundamentals, Java Advanced and Java DB courses form a whole Module.
Fortunately, there are some cases where students receive a discount:

- If Java Advanced is combined with Java Fundamentals, the student receives a 10% discount on Java Advanced.
- If all three courses are selected (Java Fundamentals, Java Advanced and Java DB), students receive a module discount - 6% on the module's total price.
- If all available courses are selected, students get a bonus course - 'HTML and CSS'.
- Students also get a discount if their education form is online - 6% on all courses.

You need to CCC:

- Check which courses and education form are selected.
- Create a list item for every course and append it to the given unordered list.
- Calculate the total price of all courses the student has signed up for and update the cost.

![example]("../z_resources/front-end-basics/media/image2.png")

Example:

![example]("../z_resources/front-end-basics/media/image3.png")
![example]("../z_resources/front-end-basics/media/image4.png")

Hint

- A student can receive more than one discount.
- The courses’ cost needs to be rounded to the closest integer, followed by two zeros.
