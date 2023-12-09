module com.example.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires junit;

    opens com.example.stickhero to javafx.fxml;
    exports com.example.stickhero;
}