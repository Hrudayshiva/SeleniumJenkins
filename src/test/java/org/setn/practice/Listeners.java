package org.setn.practice;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BasicClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            captureScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
