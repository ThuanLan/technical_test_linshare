package cucumberOption;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/linShareResource",
		glue = "linShare.stepDefinitions",
		monochrome = true,
		dryRun = false,
		plugin = { "pretty",
				"html:target/site/cucumber-report-default",
				"json:target/site/linShare.json" },
		snippets = SnippetType.CAMELCASE,
		tags = "@loginandcopyfile")
public class LinShareTestRunner {
}
