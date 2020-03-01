package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private Path path;
    private List<Account> accounts;
    private static Logger logger;

    public BudgetPlannerImporter(String filename){
        logger = LogManager.getLogger(BudgetPlannerImporter.class);
        accounts = new ArrayList<>();
        this.path = Paths.get(filename);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void readFile(){

        try(BufferedReader reader = Files.newBufferedReader(path)) {
            logger.info("Reading file");
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null){
                createAccount(line);
                line = reader.readLine();
            }
            logger.info("Data imported");

        } catch (IOException ex) {
            logger.error(ex.getMessage());

        }


    }

    private Payment createPayment(String[] line) {
/*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Eee Mmm dd HH:mm:ss zzz yyyy");
        LocalDateTime date = LocalDateTime.parse(line[3], formatter).atZone();

 */
        logger.info("Creating payment");
        String[] arrayDate = line[3].split(" ");
        String[] arrayHour = arrayDate[3].split(":");

        LocalDateTime date = LocalDateTime.of(Integer.parseInt(arrayDate[5]), Month.valueOf(arrayDate[1]).getValue(), Integer.parseInt(arrayDate[2]), Integer.parseInt(arrayHour[0]), Integer.parseInt(arrayHour[1]), Integer.parseInt(arrayHour[2]));

        //LocalDateTime date = LocalDateTime.parse(line[3]);
        float amount = Float.parseFloat(line[4]);
        String currency = line[5];
        String detail = line[6];

        Payment payment = new Payment(date, amount, currency, detail);
        logger.debug("New payement: " + payment.toString());

        return payment;
    }

    private void createAccount(String line) {

        Account account = new Account();
        String[] dataArray = line.split(",");
        account.setName(dataArray[0]);
        account.setIBAN(dataArray[1]);

        if (accounts.contains(account)){

            int index = accounts.indexOf(account);
            logger.debug("New payment for: " + account.getName());
            accounts.get(index).getPayments().add(createPayment(dataArray));

        } else {
            logger.info("Creating new account");
            List<Payment> payments = new ArrayList<>();
            payments.add(createPayment(dataArray));
            account.setPayments(payments);
            logger.debug("New account created: " + account.toString());
            accounts.add(account);

        }

    }






}
