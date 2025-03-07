package org.setn.TestClasses;

import org.openqa.selenium.By;
import org.setn.DataUtil.ExcelHandle;
import org.setn.MainClass.BasicClass;
import org.testng.Assert;
import org.testng.annotations.*;

@Ignore
public class FramesClass extends BasicClass {
    private String button_Frames = "//a[normalize-space()='iFrames']";
    private String button_Toggle = "//a[contains(normalize-space(), 'Toggle')]";
    private String dropdown_HYR1 = "//select[@id='selectnav1']";
    private String link_DotNet = "//a[normalize-space()='Convert Dataset and Datareader to List']";
    private String text_FrameOut = "//input[@class='frmTextBox']";
    private String Iframe = "//iframe[@id='frm1']";

    public void navigateToFrames() throws InterruptedException {
        try {
            scrollToElement(button_Frames);
            waitUntilElementVisible(button_Frames);
            Thread.sleep(4000);
            driver.findElement(By.xpath(button_Frames)).click();
        } catch (Exception e) {
            System.err.println("Unable to navigate to Frames");
            throw e;
        }
    }

    @Test
    public void frameUsingNum() throws InterruptedException {
        navigateToFrames();
        switchToFrame(0);
        clickElement(button_Toggle);
        navigateToFrames();
        switchToDefaultContent();
    }

    @Test
    public void frameUsingId() throws InterruptedException {
        switchToFrame(driver.findElement(By.xpath(Iframe)));
        selectDropdownByText(dropdown_HYR1, "- Dot Net");
        Thread.sleep(5000);
        waitUntilElementVisible(link_DotNet);
        String text = getText(link_DotNet);
        Assert.assertEquals(text, ExcelHandle.getExcelData(5, 0));
        switchToDefaultContent();
        scrollToElement(text_FrameOut);
        enterText(text_FrameOut, "Outside frame");
    }
}
