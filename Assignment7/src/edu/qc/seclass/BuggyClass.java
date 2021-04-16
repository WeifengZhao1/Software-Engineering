package edu.qc.seclass;

public class BuggyClass{
    /*
        I'm not sure why IntelliJ isn't showing the correct coverages and I'm think my solutions are correct.
        So I include an explanations of why I think my solutions are correct. I have used Eclipse to check my coverage
        and it returns the output of what I expected.
     */


    /* Task 1
    (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault
        if x,y = 5, it will go through all lines in buggyMethod1, therefore it will achieve 100% statement coverage with no fault.

    (2) it is possible to create a test suite that achieves less than 50% statement coverage and reveals the fault.
        if x = 0, y = 3; it will not run the lines in (y==5) and (x>0) and return y/x and throws a zero fault. Therefore the statement coverage is less than 50%.
    */
    public int buggyMethod1(int x, int y) {
    	  if (y == 5) {
	       y = y + 1;
	       y = y - 1;
		 }
		  
		  if (x > 0) {
		  x = x + 1;
		  x = x + 1;
		  x = x + 1;
		  x = x - 1;
		  x = x - 1;
		  x = x -1;
		  y = y + 1;
		  y = y - 1;
	      
	  }
	  return y / x;
	  }


    /* Task 2
    (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault.
        if x = 1, y = 5; It will go through every lines in buggyMethod2, therefore it will achieve 100% statement coverage with no fault.
   
    (2) every test suite that achieves more than 50% branch coverage reveals the fault.
        If x = 0, y =1; buggyMethod2 will execute 3 branch out of 4 and reveals the fault. Therefore, it achieves more than 50% branch coverage and reveals the fault.
 
     */
    public int buggyMethod2(int x, int y) {
        if (y == 5){
            y = y + 1;
            y = y - 1;
        }
        if (x > -1){
           x = x + 1;
           x = x - 1;
        }
        if (y > -1) {
            y = y * 2;
            y = y / 2;
        }
        return y / x;
    }


    /* Task 3
    (1) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault.
        If x,y = 1; It will execute all conditional statement, but it won't execute two false output, so branch coverage will not reach to 100% and doesn't reveal the fault.
        So the goal is achieved.

    (2) it is possible to create a test suite that achieves 100% statement coverage, does not achieve 100% branch coverage, and reveals the fault.
        If x = 0, y= 5, it will run all code and reveal fault as result. So, the goal is achieved
     */
        public int buggyMethod3(int x,int y) {
            if (y > -1) {
                if (x > 100) {
                    y = y + 1;
                    y = y - 1;
                }
                else {
                    y = y + 1 ;
                    y = y - 1;
                }
            }

            if (x > -1){
                if (y > 100){
                    y = y * 2;
                    y = y / 2;
                 }
                else {
                      x = x + 1;
                      x = x - 1;
                  }
            }
            return y/x;
    }


    /* Task 4
     (1) every test suite that achieves 100% statement coverage reveals the fault.
        if x = 0, y = 5. It execute all lines and reveals the fault at last line. So 100% statement coverage is achieved.
     (2) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault.
        if x,y = 1. It execute all branches and reveal no fault. So 100% branch coverage will not fault goal is achieved.
     */
    public int buggyMethod4(int x, int y) {
        if (y > -1) {
            y = y + 1;
            y = y - 1;
        }
        if (x > -1){
            x = x + 1;
            x = x - 1;
        }
        return y / x;
    }


    /* Task 5
       (1) it is possible to create a test suite that achieves 100% statement coverage
       (2) the division by zero fault at line 4 cannot be revealed by any test suite.
    */
    public void buggyMethod5 (int i) {
		/*
          It is not possible to create such method, since we do not want to execute line 4 which cause a zero fault,
          we need to use a condition statement, so it can skip the line 4.
          However, on task 5,we were not allow to use any condition testing,
		  therefore,we couldn't avoid execute line 4.
		 */
    }
}