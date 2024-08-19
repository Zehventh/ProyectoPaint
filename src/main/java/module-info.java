module com.example.proyectopaint {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectopaint to javafx.fxml;
    exports com.example.proyectopaint;
}