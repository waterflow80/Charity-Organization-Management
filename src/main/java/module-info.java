module org.charityOrganization.organization {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens org.charityOrganization.organization to javafx.fxml;
    exports org.charityOrganization.organization;
}
