package comp3111.covid.view;

import javafx.scene.control.Alert;

/**
 * Provide the pop up services to be used by other classes (controller)
 */
public class UIService {
	private static final String INVALID_DATE_ALERT_CONTENT_TEXT = "Please follow the format: "
			+ "Jul 20, 2021";
	private static final String INVALID_DATE_ALERT_TITLE = "Invalid Input";
	
	/**
	 * Generate alert indicating the input date is invalid
	 */
	public static void showDateInvalidDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR, INVALID_DATE_ALERT_CONTENT_TEXT);
        alert.setTitle(INVALID_DATE_ALERT_TITLE);
        alert.setHeaderText("Error");
        alert.showAndWait();
	}
	
	
	
	private static final String INVALID_PERIOD_ALERT_CONTENT_TEXT = "Please follow the format: "
			+ "from Mar 1, 2020 to Jul 20, 2021";
	private static final String INVALID_PERIOD_ALERT_TITLE = "Invalid Input";
	
	/**
	 * Generate alert indicating the input period is invalid
	 */
	public static void showPeriodInvalidDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR, INVALID_PERIOD_ALERT_CONTENT_TEXT);
        alert.setTitle(INVALID_PERIOD_ALERT_TITLE);
        alert.setHeaderText("Error");
        alert.showAndWait();
	}
	
	
	
	private static final String DATE_OUT_OF_RANGE_ALERT_CONTENT_TEXT = "Please enter a date within the legal range: "
			+ "\nMar 1, 2020 - Jul 20, 2021.";
	private static final String DATE_OUT_OF_RANGE_ALERT_TITLE = "Date out of range";
	
	/**
	 * Generate alert indicating the input date is out of range
	 */
	public static void showDateOutOfRangeDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR, DATE_OUT_OF_RANGE_ALERT_CONTENT_TEXT);
        alert.setTitle(DATE_OUT_OF_RANGE_ALERT_TITLE);
        alert.setHeaderText("Error");
        alert.showAndWait();
	}
	
	
	
	private static final String START_LATER_THAN_END_ALERT_CONTENT_TEXT = "Please make sure the start date is no later than end date.";
	private static final String START_LATER_THAN_END_ALERT_TITLE = "Invalid Period";
	
	/**
	 * Generate alert indicating the input period has the starting date later than ending date
	 */
	public static void showStartLaterThanEndDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR, START_LATER_THAN_END_ALERT_CONTENT_TEXT);
        alert.setTitle(START_LATER_THAN_END_ALERT_TITLE);
        alert.setHeaderText("Error");
        alert.showAndWait();
	}
	
	
	private static final String NO_COUNTRY_SPECIFIED_ALERT_CONTENT_TEXT = "Please choose at least one country.";
	private static final String NO_COUNTRY_SPECIFIED_ALERT_TITLE = "No country specified";
	
	/**
	 * Generate alert indicating no country is chosen
	 */
	public static void showCountryNotSpecifiedDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING, NO_COUNTRY_SPECIFIED_ALERT_CONTENT_TEXT);
        alert.setTitle(NO_COUNTRY_SPECIFIED_ALERT_TITLE);
        alert.setHeaderText("Warning");
        alert.showAndWait();
	}
	
	private static final String SAME_FEATURE_ALERT_CONTENT_TEXT = "Please choose two different features.";
	private static final String SAME_FEATURE_ALERT_TITLE = "Same feature is chosen";
	
	/**
	 * Generate alert indicating the same feature is choosen
	 */
	public static void showSameFeatureDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING, SAME_FEATURE_ALERT_CONTENT_TEXT);
        alert.setTitle(SAME_FEATURE_ALERT_TITLE);
        alert.setHeaderText("Warning");
        alert.showAndWait();
	}
	
	private static final String LESS_THAN_TWO_COUNTRIES_ALERT_CONTENT_TEXT = "Please choose two different features.";
	private static final String LESS_THAN_TWO_COUNTRIES_ALERT_TITLE = "Same feature is chosen";
	
	/**
	 * Generate alert indicating less than 2 countries is chosen
	 */
	public static void showLessThanTwoCountriesDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING, LESS_THAN_TWO_COUNTRIES_ALERT_CONTENT_TEXT);
        alert.setTitle(LESS_THAN_TWO_COUNTRIES_ALERT_TITLE);
        alert.setHeaderText("Warning");
        alert.showAndWait();
	}
	
}
