/*
 * Dalton Ream
 * Quiz 4 
 * IST 311
 */
package Controller;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Model.Mealmodel;
import java.awt.event.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 *
 * @author daltonream
 */
public class DetailedModelViewController implements Initializable {
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private ImageView mealImage;
    
       @FXML
    private Text id;

    @FXML
    private Text meal;
    
    @FXML
    private ImageView image;
    
      @FXML
    private Text setID;

    @FXML
    private Text setMeal;


    
    Mealmodel selectedModel;
    Scene previousScene;
    
    @FXML
    void backButton(ActionEvent event) {
        //setting the stage to previous scene if back button is hit
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }
    
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Mealmodel model) {
        //setting text and pic in the detailed view
        selectedModel = model;
        setID.setText(model.getId().toString());
        setMeal.setText(model.getMealdescription());

        try {
            System.out.println(model.getMealdescription());
            String imagename = "/resources/" +  model.getMealdescription() + ".jpg";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert meal != null : "fx:id=\"meal\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert setID != null : "fx:id=\"setID\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert setMeal != null : "fx:id=\"setMeal\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailedModelView.fxml'.";


        backButton.setDisable(true);

    }
}