package utility;

import com.aventstack.extentreports.Status;
import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class ADAScanningUtility {

    private static final URL axeScript = ADAScanningUtility.class.getResource("/axe.min.js");
    private static final String REPORT_DIR = System.getProperty("user.dir") + "/reports/ada/";

    public static void runAccessibilityScan(String pageName, org.openqa.selenium.WebDriver driver) {
        JSONObject response = new AXE.Builder(driver, axeScript).analyze();
        JSONArray violations = response.getJSONArray("violations");

        if (violations.length() == 0) {
            System.out.println("No accessibility issues found on: " + pageName);
        } else {
            System.out.println(violations.length() + " accessibility violations found on: " + pageName);
            generateHtmlReport(pageName, violations);

            for (int i = 0; i < violations.length(); i++) {
                JSONObject v = violations.getJSONObject(i);
                System.out.println("\nRule: " + v.getString("id"));
                System.out.println("Impact: " + v.optString("impact", "N/A"));
                System.out.println("Description: " + v.getString("description"));
                System.out.println("Help: " + v.getString("helpUrl"));

                JSONArray nodes = v.getJSONArray("nodes");
                for (int j = 0; j < nodes.length(); j++) {
                    JSONObject node = nodes.getJSONObject(j);
                    System.out.println("  -> Element: " + node.optString("html", "N/A"));
                    System.out.println("     Selector: " + node.optJSONArray("target"));
                }
            }

            Assert.fail("Accessibility violations detected on " + pageName + ". Check ADA report for details.");
        }
    }

    private static void generateHtmlReport(String pageName, JSONArray violations) {
        File dir = new File(REPORT_DIR);
        if (!dir.exists()) dir.mkdirs();

        String filePath = REPORT_DIR + "ada-report-" + pageName.toLowerCase().replace(" ", "-") + ".html";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<html><head><title>ADA Report - " + pageName + "</title></head><body>");
            writer.write("<h2>Accessibility Scan Report for: " + pageName + "</h2>");

            for (int i = 0; i < violations.length(); i++) {
                JSONObject v = violations.getJSONObject(i);
                writer.write("<div style='border:1px solid #ddd;padding:10px;margin:10px 0'>");
                writer.write("<b>Rule:</b> " + v.getString("id") + "<br>");
                writer.write("<b>Impact:</b> " + v.optString("impact", "N/A") + "<br>");
                writer.write("<b>Description:</b> " + v.getString("description") + "<br>");
                writer.write("<b>Help:</b> <a href='" + v.getString("helpUrl") + "' target='_blank'>Learn More</a><br><br>");

                JSONArray nodes = v.getJSONArray("nodes");
                for (int j = 0; j < nodes.length(); j++) {
                    JSONObject node = nodes.getJSONObject(j);
                    writer.write("<b>Element:</b> <code>" + node.optString("html", "N/A")
                            .replace("<", "&lt;").replace(">", "&gt;") + "</code><br>");
                    writer.write("<b>Selector:</b> " + node.optJSONArray("target") + "<br><br>");
                }
                writer.write("</div>");
            }

            writer.write("</body></html>");
            System.out.println("\nDetailed ADA report saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
