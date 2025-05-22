package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import groovy.util.logging.Log;

public class LogListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("🔹 Test Started: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ Test Passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ Test Failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }
}

