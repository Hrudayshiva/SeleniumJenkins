package org.setn.TestClasses;

import org.openqa.selenium.By;
import org.setn.DataUtil.ExcelHandle;
import org.setn.MainClass.BasicClass;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class SelectClass extends BasicClass {

    private String button_Select = "//a[normalize-space()='DropDown Checkbox Radio']";
    private String button_Radio = "//label[@for='demo-priority-high']";
    private String button_RedCheckbox = "//label[@for='cb_red']";
    private String button_GreenCheckbox = "//label[@for='cb_green']";
    private String dropdown_Cars = "//select[@id='cars']";

    public void navigateToSelect() throws InterruptedException {
        try {
            waitUntilElementVisible(button_Select);
            Thread.sleep(4000);
            driver.findElement(By.xpath(button_Select)).click();
        } catch (Exception e) {
            System.err.println("Unable to navigate to Select");
            throw e;
        }
    }

    @Test
    public void clickRadioCheckboxButton() throws InterruptedException {
        navigateToSelect();
        waitUntilElementVisible(button_Radio);
        clickElement(button_Radio);
        clickElement(button_RedCheckbox);
        clickElement(button_GreenCheckbox);
    }

    @Test
    public void selectDropdown() throws InterruptedException {
        navigateToSelect();
        waitUntilElementVisible(dropdown_Cars);
        selectDropdownByIndex(dropdown_Cars, 2);
        Assert.assertEquals(getDropdownSelectedOption(dropdown_Cars), ExcelHandle.getExcelData(3, 0));
    }

    @Test
    public void getDropdownOptions() throws InterruptedException {
        navigateToSelect();
        waitUntilElementVisible(dropdown_Cars);
        selectDropdownByText(dropdown_Cars, "Mercedes");
//        Assert.assertEquals(getDropdownSelectedOption(dropdown_Cars), ExcelHandle.getExcelData(3, 0));
        List<String> dropdownOptions = new ArrayList<>(getAllOptions(dropdown_Cars));
        System.out.println(dropdownOptions);
    }


}
