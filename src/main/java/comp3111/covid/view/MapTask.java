package comp3111.covid.view;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;

/**
 * Provide map generating service for the honor track tasks
 */
public class MapTask {
	
	/**
	 * Generate the map on a new stage for a given list of countries and data
	 * @param countries		The list of countries of interest in this task
	 * @param data			The list of data to be used to generate map
	 * @param date			The date of interest in the standard form of the dataset
	 * @param dataType		The type name of the data to be shown on the map
	 */
	public static void GenerateMap(ArrayList<String> countries, ArrayList<? extends Number> data, String date, String dataType) {
		Stage anotherStage = new Stage();
		anotherStage.setTitle("World Map of COVID-19 Confirmed Cases on " + date);
        WebView webView = new WebView();
		
		String content = "<html>\r\n"
        		+ "  <head>\r\n"
        		+ "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
        		+ "    <script type=\"text/javascript\">\r\n"
        		+ "      google.charts.load('current', {\r\n"
        		+ "        'packages':['geochart'],\r\n"
        		+ "      });\r\n"
        		+ "      google.charts.setOnLoadCallback(drawRegionsMap);\r\n"
        		+ "\r\n"
        		+ "      function drawRegionsMap() {\r\n"
        		+ "        var data = google.visualization.arrayToDataTable([\r\n"
        		+ "          ['Country', '" + dataType + "'],\r\n";
		for (int i = 0; i < countries.size(); i++)
		{       
			content = content + "          ['" + countries.get(i) + "', " + data.get(i) + "],\r\n";
		}
			content = content
        		+ "        ]);\r\n"
        		+ "\r\n"
        		+ "        var options = {};\r\n"
        		+ "\r\n"
        		+ "        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));\r\n"
        		+ "\r\n"
        		+ "        chart.draw(data, options);\r\n"
        		+ "      }\r\n"
        		+ "    </script>\r\n"
        		+ "  </head>\r\n"
        		+ "  <body>\r\n"
        		+ "    <div id=\"regions_div\" style=\"width: 900px; height: 500px;\"></div>\r\n"
        		+ "  </body>\r\n"
        		+ "</html>";

        webView.getEngine().loadContent(content);

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        anotherStage.setScene(scene);
		anotherStage.show();
	}
}
