package org.setn.practice;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowHandle extends BasicClass {

    private String button_PopupAlerts = "//a[normalize-space()='Pop Ups & Alerts']";
    private String button_TriggerPopUp = "//button[text()='Trigger Pop-up']";
    private String button_Alert = "//button[text()='Trigger Alert']";
    private String text_Window = "//p[contains(text(), 'This is a selenium popup window')]";
    public void navigateToPopupAlerts() throws InterruptedException {
        try {
            scrollToElement(button_PopupAlerts);
            waitUntilElementVisible(button_PopupAlerts);
            Thread.sleep(4000);
            driver.findElement(By.xpath(button_PopupAlerts)).click();
        } catch (Exception e) {
            System.err.println("Unable to navigate to Pop Up Alerts");
            throw e;
        }
    }
    @Test
    public void windowHandling() throws InterruptedException {
        navigateToPopupAlerts();
        clickElement(button_TriggerPopUp);
        String handle = driver.getWindowHandle();
        for(String w : driver.getWindowHandles()) {
            if(!w.equals(handle)) {
                driver.switchTo().window(w);
            }
        }
//        String text = getText(text_Window);
//        Assert.assertEquals(text, ExcelHandle.getExcelData(4, 0));
        driver.switchTo().window(handle);
        clickElement(button_Alert);
        acceptAlert();
    }
}
