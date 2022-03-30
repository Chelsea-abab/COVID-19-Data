package comp3111.covid.view;

import java.util.ArrayList;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import javafx.beans.binding.ListExpression;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.chart.BubbleChart;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;



/**
 * Provide service for all the honor track activities
 */
public class HonorTask {
	
	/**
	 * Obtain the data required for task A3 from the normalized dataset
	 * @param feature1		The name of the first feature
	 * @param feature2		The name of the second feature	
	 * @param date			The date of interest in the standard form of the dataset
	 * @param countries		The list of countries of interest
	 * @return			The list of required data lists, containing list of feature1, feature2 and standardized
	 *  confirmed case data
	 */
	public static ArrayList<ArrayList<Double>> getFeaturesForA3(String feature1, String feature2, String date, ArrayList<String> countries) {
		ArrayList<ArrayList<Double>> featureData = new ArrayList<>();
		for (int i = 0; i < 3; i++)
			featureData.add(new ArrayList<>());
		for (int i = 0; i < countries.size(); i++) {
			featureData.get(0).add(comp3111.covid.DataAnalysis.getFeature("COVID_Dataset_v2.0.csv", feature1, countries.get(i), date));
			featureData.get(1).add(comp3111.covid.DataAnalysis.getFeature("COVID_Dataset_v2.0.csv", feature2, countries.get(i), date));
			featureData.get(2).add(comp3111.covid.DataAnalysis.getConfirmedCasesStandarded("COVID_Dataset_v2.0.csv",
					countries.get(i), date));
			System.out.println(i);
		}
		return featureData;
	}
	
	/**
	 * Obtain the data required for task C3 from the normalized dataset
	 * @param feature1		The name of the first feature
	 * @param feature2		The name of the second feature	
	 * @param date			The date of interest in the standard form of the dataset
	 * @param countries		The list of countries of interest
	 * @return			The list of required data lists, containing list of feature1, feature2 and standardized
	 *  vaccination rate data
	 */
	public static ArrayList<ArrayList<Double>> getFeaturesForC3(String feature1, String feature2, String date, ArrayList<String> countries) {
		ArrayList<ArrayList<Double>> featureData = new ArrayList<>();
		for (int i = 0; i < 3; i++)
			featureData.add(new ArrayList<>());
		for (int i = 0; i < countries.size(); i++) {
			featureData.get(0).add(comp3111.covid.DataAnalysis.getFeature("COVID_Dataset_v2.0.csv", feature1, countries.get(i), date));
			featureData.get(1).add(comp3111.covid.DataAnalysis.getFeature("COVID_Dataset_v2.0.csv", feature2, countries.get(i), date));
			featureData.get(2).add(comp3111.covid.DataAnalysis.getVaccinationRateStandarded("COVID_Dataset_v2.0.csv",
					countries.get(i), date));
			System.out.println(i);
		}
		return featureData;
	}
	
	
	/**
	 * Generate the bubble chart for task A3
	 * @param feature1			The name of the first feature
	 * @param feature2			The name of the second feature	
	 * @param date				The date of interest in the standard form of the dataset
	 * @param countries			The list of countries of interest
	 * @param feature1Data		The list of feature1 data to be displayed as x-aixs in the chart
	 * @param feature2Data		The list of feature2 data to be displayed as y-axis in the chart
	 * @param confirmedData		The list of confirmed case data to be displayed as the radius of the bubble in the chart
	 * @return		The bubble chart to be shown in task A3
	 */
	public static BubbleChart<Number, Number> generateChartForA3(String feature1, String feature2, String date, 
			ArrayList<String> countries, ArrayList<Double> feature1Data, ArrayList<Double> feature2Data, 
			ArrayList<Double> confirmedData) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(feature1);
        yAxis.setLabel(feature2);
		BubbleChart<Number, Number> chart = new BubbleChart<Number, Number>(xAxis,yAxis);
		chart.setTitle("Number of Confirmed COVID-19 Cases with respect to " + feature1 + " and " + feature2 + " on " + date);
        List<XYChart.Series> series = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
        	series.add(new XYChart.Series());
        	series.get(i).setName(countries.get(i));
        	series.get(i).getData().add(new XYChart.Data(feature1Data.get(i), feature2Data.get(i), confirmedData.get(i)));
        	System.out.println(feature1Data.get(i));
        	System.out.println(feature2Data.get(i));
        	System.out.println(confirmedData);            	
        	chart.getData().add(series.get(i));
        }
        return chart;

	}
	
	/**
	 * Generate the bubble chart for task C3
	 * @param feature1			The name of the first feature
	 * @param feature2			The name of the second feature	
	 * @param date				The date of interest in the standard form of the dataset
	 * @param countries			The list of countries of interest
	 * @param feature1Data		The list of feature1 data to be displayed as x-aixs in the chart
	 * @param feature2Data		The list of feature2 data to be displayed as y-axis in the chart
	 * @param vaccinationData		The list of vaccination rate data to be displayed as the radius of the bubble in the chart
	 * @return		The bubble chart to be shown in task C3
	 */
	public static BubbleChart<Number, Number> generateChartForC3(String feature1, String feature2, String date, 
			ArrayList<String> countries, ArrayList<Double> feature1Data, ArrayList<Double> feature2Data, 
			ArrayList<Double> vaccinationData) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(feature1);
        yAxis.setLabel(feature2);
		BubbleChart<Number, Number> chart = new BubbleChart<Number, Number>(xAxis,yAxis);
		chart.setTitle("Vaccination Rate with respect to " + feature1 + " and " + feature2 + " on " + date);
        List<XYChart.Series> series = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
        	series.add(new XYChart.Series());
        	series.get(i).setName(countries.get(i));
        	series.get(i).getData().add(new XYChart.Data(feature1Data.get(i), feature2Data.get(i), vaccinationData.get(i)));
        	System.out.println(feature1Data.get(i));
        	System.out.println(feature2Data.get(i));
        	System.out.println(vaccinationData);            	
        	chart.getData().add(series.get(i));
        }
        return chart;

	}
	
	/**
	 * Generate the Map for task A3 on a new stage by calling 
	 * {@link comp3111.covid.view.MapTask#GenerateMap(ArrayList<String>, ArrayList<? extends Number>
	 * , String, String)}
	 * @param countries		The list of countries of interest
	 * @param date			The date of interest in the standard form of the dataset
	 */
	public static void generateMapForA3(ArrayList<String> countries, String date) {
		ArrayList<Long> data = new ArrayList<>();
		for (int i = 0; i < countries.size(); i++)
			data.add(comp3111.covid.DataAnalysis.getConfirmedCasesa("COVID_Dataset_v1.0.csv",
					countries.get(i), date));
		comp3111.covid.view.MapTask.GenerateMap(countries, data, date, "Confirmed Cases");
	}
	
	/**
	 * Generate the Map for task C3 on a new stage by calling 
	 * {@link comp3111.covid.view.MapTask#GenerateMap(ArrayList<String>, ArrayList<? extends Number>
	 * , String, String)}
	 * @param countries		The list of countries of interest
	 * @param date			The date of interest in the standard form of the dataset
	 */
	public static void generateMapForC3(ArrayList<String> countries, String date) {
		ArrayList<Double> data = new ArrayList<>();
		for (int i = 0; i < countries.size(); i++)
			data.add(comp3111.covid.DataAnalysis.getVaccinationPer("COVID_Dataset_v1.0.csv",
					countries.get(i), date));
		comp3111.covid.view.MapTask.GenerateMap(countries, data, date, "Vaccination Rate (%)");
	}
	
	/**
	 * Generate the report on the Pearson's correlation between the features and the confirmed cases data in task A3
	 * @param feature1			Name of feature 1
	 * @param feature2			Name of feature 2
	 * @param date				The date of interest in the standard form of the dataset
	 * @param countries			The list of countries of interest
	 * @param feature1Data		The list of data of feature 1
	 * @param feature2Data		The list of data of feature 2s
	 * @param confirmedData		The list of data of confirmed cases
	 * @return		A label showning the conparison result of the two Pearson's correlations to be shown in task A3
	 */
	public static Label generateReportForA3(String feature1, String feature2, String date, 
			ArrayList<String> countries, ArrayList<Double> feature1Data, ArrayList<Double> feature2Data,
			ArrayList<Double> confirmedData) {
		Label result = new Label();
		var cor = new PearsonsCorrelation();
		var feature1Array = new double[feature1Data.size()];
		var feature2Array = new double[feature2Data.size()];
		var confirmedArray = new double[confirmedData.size()];
		for (int i = 0; i < feature1Data.size(); i++) {
			feature1Array[i] = feature1Data.get(i);
			feature2Array[i] = feature2Data.get(i);
			confirmedArray[i] = confirmedData.get(i);
		}
		double feature1Cor = cor.correlation(feature1Array, confirmedArray);
		double feature2Cor = cor.correlation(feature2Array, confirmedArray);
		System.out.println(feature1Cor);
		System.out.println(feature2Cor);
		if (Math.abs(feature1Cor) > Math.abs(feature2Cor))
			result.setText("The relationship between " + feature1 + " and total_cases is larger than the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		else if (Math.abs(feature1Cor) < Math.abs(feature2Cor))
			result.setText("The relationship between " + feature1 + " and total_cases is smaller than the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		else
			result.setText("The relationship between " + feature1 + " and total_cases is approximately the same as"
					+ " the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		return result;
	}

	/**
	 * Generate the report on the Pearson's correlation between the features and the confirmed cases data in task C3
	 * @param feature1			Name of feature 1
	 * @param feature2			Name of feature 2
	 * @param date				The date of interest in the standard form of the dataset
	 * @param countries			The list of countries of interest
	 * @param feature1Data		The list of data of feature 1
	 * @param feature2Data		The list of data of feature 2s
	 * @param vaccinationData		The list of data of vaccination rate
	 * @return		A label showning the conparison result of the two Pearson's correlations to be shown in task C3
	 */
	public static Label generateReportForC3(String feature1, String feature2, String date, 
			ArrayList<String> countries, ArrayList<Double> feature1Data, ArrayList<Double> feature2Data,
			ArrayList<Double> vaccinationData) {
		Label result = new Label();
		var cor = new PearsonsCorrelation();
		var feature1Array = new double[feature1Data.size()];
		var feature2Array = new double[feature2Data.size()];
		var vaccinationArray = new double[vaccinationData.size()];
		for (int i = 0; i < feature1Data.size(); i++) {
			feature1Array[i] = feature1Data.get(i);
			feature2Array[i] = feature2Data.get(i);
			vaccinationArray[i] = vaccinationData.get(i);
		}
		double feature1Cor = cor.correlation(feature1Array, vaccinationArray);
		double feature2Cor = cor.correlation(feature2Array, vaccinationArray);
		System.out.println(feature1Cor);
		System.out.println(feature2Cor);
		if (Math.abs(feature1Cor) > Math.abs(feature2Cor))
			result.setText("The relationship between " + feature1 + " and vaccination rate is larger than the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		else if (Math.abs(feature1Cor) < Math.abs(feature2Cor))
			result.setText("The relationship between " + feature1 + " and vaccination rate is smaller than the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		else
			result.setText("The relationship between " + feature1 + " and vaccination rate is approximately the same as"
					+ " the relationship between "
					+ feature2 + " and total_cases in these countries on " + date);
		return result;
	}
}


















