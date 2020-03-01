package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;

public class BudgetPlanner {
    public static void main(String[] args) {
        BudgetPlannerImporter importer = new BudgetPlannerImporter("C:\\Users\\11801613\\Desktop\\School2deSem\\PJavaAdvanced\\Budgetplanner\\src\\main\\resources\\account_payments.csv");
        //BudgetPlannerImporter.
        importer.readFile();

    }

}
