package listener;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporterListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try (FileWriter writer = new FileWriter(outputDirectory + "/custom-report.html")) {
            writer.write("<html><head><title>Custom Test Report</title></head><body>");
            writer.write("<h1>Test Execution Summary</h1>");
            for (ISuite suite : suites) {
                suite.getResults().forEach((testName, result) -> {
                    result.getTestContext().getPassedTests().getAllResults().forEach(testResult -> {
                        try {
                            writer.write("<p style='color:green;'>PASSED: " + testResult.getMethod().getMethodName()
                                    + "</p>");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    result.getTestContext().getFailedTests().getAllResults().forEach(testResult -> {
                        try {
                            writer.write(
                                    "<p style='color:red;'>FAILED: " + testResult.getMethod().getMethodName() + "</p>");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
            }
            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
