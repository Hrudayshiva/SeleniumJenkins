package org.setn.MainClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicClass {

    public static WebDriver driver;
    public static Actions actions;
    public static WebDriverWait wait;
    public static Alert alert;
    public static Select select;
    public static JavascriptExecutor jsExecutor;
    public static FluentWait wait1;
    public final int TIMEOUT = 40;
    public static ChromeOptions chromeOptions;
    public static FirefoxOptions firefoxOptions;
    public static EdgeOptions edgeOptions;

    @BeforeMethod
    @Parameters({"browser", "url1"})
    public void beforeTest(String browser, String url) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--headless");
//                firefoxOptions.addArguments("--disable-gpu");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments("--headless=new");
//                edgeOptions.addArguments("--disable-gpu");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.err.println("Enter valid browser name");
                break;
        }
        driver.get(url);
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterTest() {
        driver.quit();
    }


    public void captureScreenshot(String filename) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File file = takeScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./FailureScreenshots/" + filename + "-" + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date()) + ".jpg");
        FileUtils.copyFile(file, destFile);
    }

    public void scrollToElement(String xpath) {
        try {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(xpath)));
        } catch (Exception e) {
            System.err.println("Unable to scroll to element");
            throw e;
        }
    }

    public String getText(String xpath) {
        String text = "";
        try {
            text = driver.findElement(By.xpath(xpath)).getText().trim();
            return text;
        } catch (Exception e) {
            System.err.println("Unable to get text");
            throw e;
        }
    }

    public void waitUntilElementVisible(String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public void enterText(String xpath, String text) {
        try {
            driver.findElement(By.xpath(xpath)).sendKeys(text);
            System.out.println("Entered text "+text);
        } catch (Exception e) {
            System.err.println("unable to enter text");
            throw e;
        }
    }

    public void hoverOverElement(String xpath) {
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
    }

    public void dragAndDrop(String source, String target) {
        actions = new Actions(driver);
        try {
            actions.dragAndDrop(driver.findElement(By.xpath(source)), driver.findElement(By.xpath(target))).build().perform();
        } catch (Exception e) {
            System.err.println("Unable to drag and drop element");
            throw e;
        }
    }

    public void clickAndHold(String xpath) {
        actions = new Actions(driver);
        try {
            actions.clickAndHold(driver.findElement(By.xpath(xpath))).build().perform();
        } catch (Exception e) {
            System.err.println("Unable to click and hold element");
            throw e;
        }
    }

    public void doubleClick(String xpath) {
        actions = new Actions(driver);
        try {
            actions.doubleClick(driver.findElement(By.xpath(xpath))).build().perform();
        } catch (Exception e) {
            System.err.println("Unable to double click element");
            throw e;
        }
    }

    public void clickElement(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
            System.out.println("Clicked element");
        } catch (Exception e) {
            System.err.println("Unable to click element");
            throw e;
        }
    }

    public void acceptAlert() {
        alert = driver.switchTo().alert();
        alert.accept();
        System.out.println("Accepted alert");
    }

    public boolean elementIsVisible(String xpath) {
        boolean a = false;
        try {
            if(driver.findElement(By.xpath(xpath)).isDisplayed()) {
                a = true;
                return a;
            }
        } catch (Exception e) {
            System.err.println("Element is not visible");
            throw e;
        }
        return a;
    }

    public void selectDropdownByIndex(String xpath, int i) {
        try {
            select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByIndex(i);
        } catch (Exception e) {
            System.err.println("Unable to select dropdown");
            throw e;
        }
    }

    public void selectDropdownByText(String xpath, String text) {
        try {
            select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByVisibleText(text);
        } catch (Exception e) {
            System.err.println("Unable to select dropdown");
            throw e;
        }
    }

    public void selectDropdownByValue(String xpath, String value) {
        try {
            select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByValue(value);
        } catch (Exception e) {
            System.err.println("Unable to select dropdown");
            throw e;
        }
    }

    public String getDropdownSelectedOption(String xpath) {
        String option = "";
        try {
            select = new Select(driver.findElement(By.xpath(xpath)));
            option = select.getFirstSelectedOption().getText().trim();
            return option;
        } catch (Exception e) {
            System.err.println("Unable to get selected option");
            throw e;
        }
    }

    public List<String> getAllOptions(String xpath) {
        List<String> options = new ArrayList<>();
        try {
            select = new Select(driver.findElement(By.xpath(xpath)));
            for(WebElement element : select.getOptions()) {
                options.add(element.getText().trim());
            }
            return options;
        } catch (Exception e) {
            System.err.println("Unable to get options");
            throw e;
        }
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void switchToFrame(int i) {
        try {
            driver.switchTo().frame(i);
        } catch (Exception e) {
            System.err.println("Unable to switch to frame");
            throw e;
        }
    }

    public void switchToFrame(String id) {
        try {
            driver.switchTo().frame(id);
        } catch (Exception e) {
            System.err.println("Unable to switch to frame");
            throw e;
        }
    }

    public void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            System.err.println("Unable to switch to frame");
            throw e;
        }
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void waitFluentlyForVisibility(String xpath, int poll, Class<? extends Throwable> exception) {
        wait1 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(TIMEOUT)).pollingEvery(Duration.ofSeconds(poll)).ignoring(exception);
        wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

}
