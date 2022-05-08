module org.charityOrganization.organization {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;
    
    opens org.charityOrganization.organization to javafx.fxml;
    opens controllers to javafx.fxml;
    exports org.charityOrganization.organization;
    exports entities;
    
}
