package org.setn.TestClasses;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.setn.DataUtil.ExcelHandle;
import org.setn.DataUtil.JSONUtil;
import org.setn.MainClass.BasicClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ActionsClass extends BasicClass {

    private SelectClass selectClass;

    private String button_Actions = "//a[normalize-space()='Actions']";
    private String button_DragMe = "//div/p[@id='dragtarget']";
    private String area_DropTarget = "(//div[@class='droptarget'])[2]";
    private String button_ClickHold = "//div[@id='click-box']";
    private String text_DragDrop = "//p[@id='demo' and text()='The p element was dropped into an accepted rectangle']";
    private String text_ClickHold = "//div[contains(text(), 'Keep holding down!')]";
    private String button_DoubleClick = "//div[@id='doubleClickArea']";
    private String text_DoubleClick = "//div[@id='doubleClickArea']/p";
    private String button_ClickArea = "(//div[@id='doubleClickArea'])[2]";
    private String link_Animals = "//a[contains(text(), 'Animals')]";
    private String link_Mouse = "//a[contains(text(), 'Mouse')]";

    JSONObject jsonObject = new JSONObject(JSONUtil.jsonConverter());

    //Constructor
    public ActionsClass() {
        selectClass = new SelectClass();
    }

    public void navigateToActions() throws InterruptedException {
        try {
            waitUntilElementVisible(button_Actions);
            Thread.sleep(4000);
            driver.findElement(By.xpath(button_Actions)).click();
        } catch (Exception e) {
            System.err.println("Unable to navigate to Actions");
            throw e;
        }
    }

    @Test(priority = 1)
    public void dragAndDropElement() throws InterruptedException, IOException {
        navigateToActions();
        waitUntilElementVisible(button_DragMe);
        dragAndDrop(button_DragMe, area_DropTarget);
        waitUntilElementVisible(text_DragDrop);
        String text = getText(text_DragDrop);
        Assert.assertEquals(text, jsonObject.getJSONObject("actions").getString("draganddropmessage"));
    }

    @Test(priority = 2)
    public void clickAndHoldElement() throws InterruptedException, IOException {
        navigateToActions();
        waitUntilElementVisible(button_ClickHold);
        clickAndHold(button_ClickHold);
        waitUntilElementVisible(text_ClickHold);
        String text = getText(text_ClickHold);
        Assert.assertEquals(text, ExcelHandle.getExcelData(1, 0));
    }

    @Test(priority = 3)
    public void doubleClickElement() throws InterruptedException, IOException {
        navigateToActions();
        waitUntilElementVisible(button_DoubleClick);
        doubleClick(button_DoubleClick);
        waitUntilElementVisible(text_DoubleClick);
        String text = getText(text_DoubleClick);
        Assert.assertEquals(text, ExcelHandle.getExcelData(2, 0));
    }

    @Test(priority = 4, enabled = false)
    public void clickKey() throws InterruptedException, IOException {
        actions = new Actions(driver);
        navigateToActions();
        waitUntilElementVisible(button_ClickArea);
        actions.keyDown(Keys.SHIFT);
        clickElement(button_ClickArea);
        acceptAlert();
    }
    @Test(priority = 5)
    public void hoverOverElement() throws InterruptedException {
        selectClass.navigateToSelect();
        hoverOverElement(link_Animals);
        Assert.assertTrue(elementIsVisible(link_Mouse));

    }


}
