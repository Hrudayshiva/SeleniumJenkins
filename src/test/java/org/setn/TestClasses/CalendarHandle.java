package org.setn.TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.setn.MainClass.BasicClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarHandle extends BasicClass {

    private String button_DatePicker = "//a[normalize-space()='Date Picker']";
    private String label_CurrentYear = "//input[@class='numInput cur-year']";
    private String text_Hour = "//input[@class='numInput flatpickr-hour']";
    private String text_Minute = "//input[@class='numInput flatpickr-minute']";
    private String label_CurrentMonth = "(//div[contains(@class, 'flatpickr-current-month')]/span)[1]";
    private String button_Calendar = "//input[@id='basicDate']";
    private String button_Prev = "(//span[@class='flatpickr-prev-month'])[1]";
    private String button_Next = "(//span[@class='flatpickr-next-month'])[1]";
    private String button_HourUp = "(//div[@class='flatpickr-time']//span[@class='arrowUp'])[1]";
    private String button_MinUp = "(//div[@class='flatpickr-time']//span[@class='arrowUp'])[2]";

    public void navigateToDatePicker() throws InterruptedException {
        try {
            scrollToElement(button_DatePicker);
            waitUntilElementVisible(button_DatePicker);
            Thread.sleep(4000);
            driver.findElement(By.xpath(button_DatePicker)).click();
        } catch (Exception e) {
            System.err.println("Unable to navigate to Select");
            throw e;
        }
    }

    @Test
    public void datePicker() throws ParseException, InterruptedException {
        actions = new Actions(driver);
        String date = "16/12/2024";

        navigateToDatePicker();
        clickElement(button_Calendar);
        Calendar calendar = Calendar.getInstance();

        String Month = getText(label_CurrentMonth);
        String Year = driver.findElement(By.xpath(label_CurrentYear)).getAttribute("value");

        calendar.setTime(new SimpleDateFormat("MMMM yyyy").parse(Month + " " + Year));

        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        while (month < currentMonth || year < currentYear) {
            clickElement(button_Prev);
            waitUntilElementVisible(label_CurrentMonth);
            Month = getText(label_CurrentMonth);
            Year = driver.findElement(By.xpath(label_CurrentYear)).getAttribute("value");
            calendar.setTime(new SimpleDateFormat("MMMM yyyy").parse(Month + " " + Year));
            currentMonth = calendar.get(Calendar.MONTH);
            currentYear = calendar.get(Calendar.YEAR);
        }

        while (month > currentMonth || year > currentYear) {
            clickElement(button_Next);
            waitUntilElementVisible(label_CurrentMonth);
            Month = getText(label_CurrentMonth);
            Year = driver.findElement(By.xpath(label_CurrentYear)).getAttribute("value");
            calendar.setTime(new SimpleDateFormat("MMMM yyyy").parse(Month + " " + Year));
            currentMonth = calendar.get(Calendar.MONTH);
            currentYear = calendar.get(Calendar.YEAR);
        }

        driver.findElement(By.xpath("//span[@class='flatpickr-day ' and text()='" + day + "']")).click();

        int setHour = 02;
        int setMinute = 00;

        int hour = Integer.parseInt(driver.findElement(By.xpath(text_Hour)).getAttribute("value"));
        int min = Integer.parseInt(driver.findElement(By.xpath(text_Minute)).getAttribute("value"));

        while(setHour!=hour) {
            clickElement(button_HourUp);
            hour = Integer.parseInt(driver.findElement(By.xpath(text_Hour)).getAttribute("value"));
            if(hour==setHour) {
                break;
            }
        }

        while(setMinute!=min) {
            clickElement(button_MinUp);
            min = Integer.parseInt(driver.findElement(By.xpath(text_Hour)).getAttribute("value"));
            if(min==setMinute) {
                break;
            }
        }

        actions.keyDown(Keys.ESCAPE);
        actions.keyUp(Keys.ESCAPE);

        System.out.println(driver.findElement(By.xpath(button_Calendar)).getAttribute("value"));


    }
}
