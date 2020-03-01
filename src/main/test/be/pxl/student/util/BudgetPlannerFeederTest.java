package be.pxl.student.util;
import be.pxl.student.BudgetPlanner;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import be.pxl.student.entity.Payment;
import be.pxl.student.entity.Account;


public class BudgetPlannerFeederTest {
    BudgetPlannerImporter budgetPlannerImporter = new BudgetPlannerImporter("C:\\Users\\11801613\\Desktop\\School2deSem\\PJavaAdvanced\\Budgetplanner\\src\\main\\resources\\account_payments.csv");

    @Test
    public void accountShouldHave3payments(){
        budgetPlannerImporter.readFile();
        Account account = budgetPlannerImporter.getAccounts().get(0);
        int totalPayments = account.getPayments().size();

        assertEquals(totalPayments, 3);
    }
}
