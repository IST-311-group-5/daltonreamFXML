package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Mealmodel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author daltonream
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) { // provided and from demo
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        Query query = manager.createNamedQuery("Mealmodel.findAll");
        List<Mealmodel> data = query.getResultList();

        for (Mealmodel s : data) {
            System.out.println(s.getId() + " " + s.getMealdescription() + " " + s.getCaloricintake() + " " + s.getDietrayrestictions());
        }
    }

    // Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) { // from demo
        // loading data from database
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("DaltonReamFXMLPU").createEntityManager();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button readMeal;

    @FXML
    private Button deleteMeal;
    
      @FXML
    private Button mealAndRestr;
      
    @FXML
    private Button calsAndID;
     
    @FXML
    void createMeal(ActionEvent event) { // from demo
        Scanner input = new Scanner(System.in);

        System.out.println("Enter ID");
        int id = input.nextInt();
        
        System.out.println("Enter Dietary Restrictions");
        String restrictions = input.next();
        
        System.out.println("Enter Caloric Intake");
        int intake = input.nextInt();
        
        System.out.println("Enter Meal Description");
        String description = input.next();
        
        Mealmodel meal = new Mealmodel();
        
        meal.setId(id);
        meal.setDietrayrestictions(restrictions);
        meal.setCaloricintake(intake);
        meal.setMealdescription(description);
        
        create(meal);

    }

    public void create(Mealmodel meal) { // from demo
        try {
            manager.getTransaction().begin();

            if (meal.getId() != null) {

                manager.persist(meal);

                manager.getTransaction().commit(); // I used this code from the demo

                System.out.println(meal.toString() + " is created");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
     public Mealmodel readById(int id){ // from demo
        Query query = manager.createNamedQuery("Mealmodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Mealmodel meal = (Mealmodel) query.getSingleResult();
        if (meal != null) {
            System.out.println(meal.getId() + " " + meal.getDietrayrestictions() + " "
                    + meal.getCaloricintake() + " " + meal.getMealdescription());
        }
                return meal;

     }

    @FXML
    void deleteMeal(ActionEvent event) { // from demo
        Scanner input = new Scanner(System.in);
        
         // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Mealmodel s = readById(id);
        System.out.println("we are deleting this meal: " + s.toString());
        delete(s);

    }
    public void delete(Mealmodel meal) { // from demo
        try {
            Mealmodel existingmeal = manager.find(Mealmodel.class, meal.getId());

            // sanity check
            if (existingmeal != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove meal
                manager.remove(existingmeal);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void readByID(ActionEvent event) { // from demo
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Mealmodel s = readById(id);
        System.out.println(s.toString());

    }
    
    @FXML
    void readMeal(ActionEvent event) { // from demo
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Meal Description:");
        String mealDesc = input.next();
        
        List<Mealmodel> s = readByMeal(mealDesc);
        System.out.println(s.toString());

    }
    public List<Mealmodel> readByMeal(String mealDescription){ // from demo
        Query query = manager.createNamedQuery("Mealmodel.findByMealdescription");

        // setting query parameter
        query.setParameter("mealdescription", mealDescription);

        // execute query
        List<Mealmodel> meal =  query.getResultList();
        for (Mealmodel otherMeal: meal) {
            System.out.println(otherMeal.getId() + " " + otherMeal.getDietrayrestictions() + " " + otherMeal.getCaloricintake() + " " + otherMeal.getMealdescription());
        }

        return meal;
    }  


    @FXML
    void updateMeal(ActionEvent event) { // from demo
         Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Dietaray Restrictions");
        String diet = input.next();
        
        System.out.println("Enter Caloric Intake:");
        int calories = input.nextInt();
        
        System.out.println("Enter Meal description:");
        String mealDesc = input.next();
        
        // create a meal instance
        Mealmodel meal = new Mealmodel();
        
        // set properties
        meal.setId(id);
        meal.setDietrayrestictions(diet);
        meal.setCaloricintake(calories);
        meal.setMealdescription(mealDesc);
        
        // save this meal to database by calling Create operation        
        update(meal);
    }
    public void update(Mealmodel model) {
        try {

            Mealmodel existingmeal = manager.find(Mealmodel.class, model.getId());

            if (existingmeal != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                // update all atttributes
                existingmeal.setDietrayrestictions(model.getDietrayrestictions());
                existingmeal.setCaloricintake(model.getCaloricintake());
                existingmeal.setMealdescription(model.getMealdescription());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void mealAndRestr(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Meal Description:");
        String mealDesc = input.next();
        System.out.println("Enter Dietary Restriction:");
        String dietRest = input.next();
        
        readByMealAndRestr(mealDesc,dietRest);

    }
    public void readByMealAndRestr(String mealDescription, String mealRestriction){ // from demo and my own
        Query query = manager.createNamedQuery("Mealmodel.findByDietRestAndMealDesc");

        // setting query parameter
        query.setParameter("mealdescription", mealDescription);
        query.setParameter("dietrayrestictions", mealRestriction);

        // execute query
        List<Mealmodel> meal =  query.getResultList();
        for (Mealmodel otherMeal: meal) {
            System.out.println(otherMeal.getId() + " " + otherMeal.getDietrayrestictions() + " " + otherMeal.getCaloricintake() + " " + otherMeal.getMealdescription());
        }

       
    }
    @FXML
    void calsAndID(ActionEvent event) {
        
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID: ");
        int id = input.nextInt();
        System.out.println("Enter Caloric Intake: ");
        int cals = input.nextInt();
        
        readByIDAndCals(id, cals);

    }
        public void readByIDAndCals (int id, int cals){ // from demo and my own
        Query query = manager.createNamedQuery("Mealmodel.findByIDAndCaloricIntake");

        // setting query parameter
        query.setParameter("id", id);
        query.setParameter("caloricintake", cals);

        // execute query
        List<Mealmodel> meal =  query.getResultList();
        for (Mealmodel otherMeal: meal) {
            System.out.println(otherMeal.getId() + " " + otherMeal.getDietrayrestictions() + " " + otherMeal.getCaloricintake() + " " + otherMeal.getMealdescription());
        }

       
    }

    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert readMeal != null : "fx:id=\"readMeal\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert deleteMeal != null : "fx:id=\"deleteMeal\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert mealAndRestr != null : "fx:id=\"mealAndRestr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert calsAndID != null : "fx:id=\"calsAndID\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }

}
