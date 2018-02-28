/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AutoComplete;
import Entities.Matching;
import Entities.Pet;
import Entities.Rateme;
import Entities.Session;
import Services.ServiceMatching;
import Services.ServicePet;
import Services.ServiceRating;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLDisplayAllController implements Initializable {


    @FXML
    private TableView<Pet> tableUser;
    //@FXML
    //private TableView<?> tableUser;
    @FXML
    private TableColumn<?, ?> Cname;
    @FXML
    private TableColumn<?, ?> Cbreed;
    @FXML
    private TableColumn<?, ?> Cage;
    @FXML
    private TableColumn<Pet, String> Csex;
    @FXML
    private TableColumn<?, ?> Cgovernate;
    @FXML
    private TableColumn<Pet, String> Cimage;
    @FXML
    private Button btnLoad;
    
    private ObservableList<Pet> data;
    private ObservableList<Pet> petdata;
    
    String nameSelected = "";
//    private Label nameLabel;
//    private Label breedLabel;
//    private JFXButton match;
//    private ImageView imageview;
    
    private Image image1;
    
    private int a=1;

    private Button btn;
    @FXML
    private ButtonBar matching;

    
    
    private int returnedId;
//    private Rating ratingBar;
//    private PieChart pieChart;


    @FXML
    private JFXButton clearSearch;
    @FXML
    private JFXButton profile;
    @FXML
    private TextField field;
    @FXML
    private Button likeBtn;
    @FXML
    private Button dislike;
    
    Session session = Session.getInstance();
    @FXML
    private AnchorPane pane;
  
    //private ObservableList items;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        pane.setStyle("-fx-background-image: url('images/adopet1.jpg');");
        
//        System.out.println(session.IdSession());
        //load table data from start
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        // load autocomplete database values (related to autocomplete.java class)
        try {
        loadAuto();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        profile.setStyle(
                
                "-fx-min-width: 140px; " +
                "-fx-min-height: 140px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        File file3 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\pets.gif");
        //Image image = new Image(file.toURI().toString());
        String pt3 = file3.toURI().toString();
        Image image3 = new Image(pt3, 110 ,110, false, false);
        profile.setGraphic(new ImageView(image3));
        profile.setText("");
        
        likeBtn.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 140px; " +
                "-fx-min-height: 140px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\heart.gif");
        //Image image = new Image(file.toURI().toString());
        String pt = file.toURI().toString();
        Image image = new Image(pt, 110 ,110, false, false);
        likeBtn.setGraphic(new ImageView(image));
        likeBtn.setText("");
        
        
        dislike.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 140px; " +
                "-fx-min-height: 140px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file2 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\cross.gif");
        //Image image = new Image(file.toURI().toString());
        String pt2 = file2.toURI().toString();
        Image image2 = new Image(pt2, 110 ,110, false, false);
        dislike.setGraphic(new ImageView(image2));
        dislike.setText("");

        //likeBtn.getStyleClass().add("closebutton");
        
        

        // delete table header from css file
        tableUser.getStyleClass().add("noheader");
        // fkn hide the tableview horizontal scroll via css files
        tableUser.getStyleClass().add("table-view");
        //tableUser.getStyleClass().add("scroll-pane");
//        ratingBar.setRating(0);   

        
    }  
    
    //load autocomplete database values (related to autocomplete.java class)
    private void loadAuto() throws SQLException{
        AutoComplete ac = new AutoComplete();
        String[] a = ac.MotsAutoComplete();      
        TextFields.bindAutoCompletion(field, a);
    }

    
    private void loadData() throws SQLException {   
//        System.out.println(session.IdSession());
        ServicePet sp = new ServicePet();
       
        List<Pet> pets;
	pets= sp.displayAll();

        // Display 2 data in 1 column   
        // name + breed + age in governate(pet)
        for (Pet pet: pets){
            String a = pet.getName_pet() +"\n \n \n"+ pet.getBreed()+"\n \n \n"+ pet.getAge();
            pet.setGovernate(a);
        }

        
        //System.out.println(pets);
	petdata = FXCollections.observableArrayList(pets);
//        final ObservableList<Pet> petdata = FXCollections.observableArrayList(pets);
   
        Cname.setCellValueFactory(new PropertyValueFactory<>("name_pet"));
        Cbreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        Cage.setCellValueFactory(new PropertyValueFactory<>("age"));
//        Csex.setCellValueFactory(new PropertyValueFactory<>("sex"));
//        Ccolor.setCellValueFactory(new PropertyValueFactory<>("color"));
        Cgovernate.setCellValueFactory(new PropertyValueFactory<>("governate"));
//        Ccity.setCellValueFactory(new PropertyValueFactory<>("city"));
//        Czip.setCellValueFactory(new PropertyValueFactory<>("zip"));
//        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));

        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));
        
        Cimage.setCellFactory(tc -> {
//            File file = new File("/Users/mac/Desktop/heart.png");
//            String pt = file.toURI().toString();
//            final Image activeImage = new Image(pt, 50,50,false, false);
            
            TableCell<Pet, String> cell = new TableCell<Pet, String>(){
                private ImageView imageView = new ImageView();
                
                protected  void updateItem(String pet_image, boolean empty){
                    super.updateItem(pet_image, empty);
                    if(empty){
                        setGraphic(null);
                    } else {
                        File file = new File(pet_image);
                        String pt = file.toURI().toString();
                        final Image activeImage = new Image(pt, 300, 300,false, false);
                        imageView.setImage(activeImage);
                    }
                    setGraphic(imageView);
                }
            };
            return cell;
        });
        
        
        Csex.setCellValueFactory(new PropertyValueFactory<>("sex"));     
        Csex.setCellFactory(tc -> {
            TableCell<Pet, String> cell = new TableCell<Pet, String>(){
                
                protected  void updateItem(String sex, boolean empty){
                    super.updateItem(sex, empty);
                    
                    TableRow<Pet> currentRow = getTableRow();
                    
                    if (!isEmpty()) {
                    if(sex.equals("MALE")) 
                        currentRow.setStyle("-fx-background-color:#e6ffff");
                    else
                        currentRow.setStyle("-fx-background-color:#ffe6ff");
                    }
                }      
            };
            return cell;
        });
       
//        isActiveColumn.setCellFactory(tc -> {
//        final Image activeImage = new Image(...);
//        final Image passiveImage = new Image(...);
//        TableCell<User, Boolean> cell = new TableCell<User, Boolean>() {
//            private ImageView imageView = new ImageView();
//            @Override
//            protected void updateItem(Boolean active, boolean empty) {
//                super.updateItem(active, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    if (active) {
//                        imageView.setImage(activeImage);
//                    } else {
//                        imageView.setImage(passiveImage);
//                    }
//                    setGraphic(imageView);
//                }
//            }
//        };
//        return cell ;
//        });

        









//
//    Cimage.setCellValueFactory(new Callback<CellDataFeatures<Pet, Image>, ObservableValue<Image>>() {
//    public ObservableValue<Image> call(CellDataFeatures<Pet, Image> p) {
//         Pet w = p.getValue();
//      
//         Image image = new Image("file:/Users/mac/Desktop/031.png");
//         return new SimpleObjectProperty<>(image);      
//     }
//    });
//    
//    Cimage.setCellFactory(new Callback<TableColumn<Pet, Image>, TableCell<Pet, Image>>(){
//        public TableCell<Pet, Image> call(TableColumn<Pet, Image> p) {
//            return new TableCell<>() {
//                   public void updateItem(Image image, boolean empty) {
//                      setText(null);
//                      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                      ImageView imageView = (image == null || empty) ? null :  ImageViewBuilder.create().image(image).build();
//                      setGraphic(imageView);
//                   }
//            };
//        }
//    }):



//    Cimage.setCellFactory(new Callback<TableColumn<Pet,Image>,TableCell<Pet,Image>>(){        
//	@Override
//	public TableCell<Pet,Image> call(TableColumn<Pet,Image> param) {                
//		TableCell<Pet,Image> cell = new TableCell<Pet,Image>(){
//			ImageView imageview = new ImageView();
//                        
//			public void updateItem(Pet item, boolean empty) {                        
//				if(item!=null){                            
//					HBox box= new HBox();
//					box.setSpacing(10) ;
//					VBox vbox = new VBox();
//					vbox.getChildren().add(new Label(item.getPet_image()));
//					vbox.getChildren().add(new Label(item.getName_pet())); 
//
//					
//					imageview.setFitHeight(50);
//					imageview.setFitWidth(50);
//					imageview.setImage(new Image("file:/Users/mac/Desktop/031.png")); 
//
//					box.getChildren().addAll(imageview,vbox); 
//					//SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//					setGraphic(box);
//				}
//			}
//		};
//		System.out.println(cell.getIndex());               
//		return cell;
//	}
//
//});



//        Cimage.setCellFactory(new Callback<TableColumn<Pet, Image>, TableCell<Pet, Image>>() {
//            public TableCell<Pet, Image> call(TableColumn<Pet, Image> param) {
//
//                final ImageView imageView = new ImageView();
//                imageView.setFitHeight(50);
//                imageView.setFitWidth(50);
//
//                    TableCell<Pet, Image> cell = new TableCell<Pet, Image>(){
//
//                        @Override
//                        protected void updateItem(Image item, boolean empty) {
//                            if(item != null)
//                              imageView.setImage(new Image("file:/Users/mac/Desktop/031.png"));
//                        }
//
//                    };
//                    cell.setGraphic(imageView);
//                    return cell;
//            }
//        });
//        Cimage.setCellValueFactory(new PropertyValueFactory<Pet, Image>("image"));





        
        // disable horizental scroll
        //tableUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableUser.setItems(null);
        tableUser.setItems(petdata); 
    }
    
    @FXML
    private void loadDataFromDatabase(ActionEvent event) throws SQLException {
                ServicePet sp = new ServicePet();
       
        List<Pet> pets;
	pets= sp.displayAll();

        // Display 2 data in 1 column   
        // name + breed + age in governate(pet)
        for (Pet pet: pets){
            String a = pet.getName_pet() +"\n \n \n"+ pet.getBreed()+"\n \n \n"+ pet.getAge();
            pet.setGovernate(a);
        }

        
        //System.out.println(pets);
	final ObservableList<Pet> petdata = FXCollections.observableArrayList(pets);
   
        Cname.setCellValueFactory(new PropertyValueFactory<>("name_pet"));
        Cbreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        Cage.setCellValueFactory(new PropertyValueFactory<>("age"));
//        Csex.setCellValueFactory(new PropertyValueFactory<>("sex"));
//        Ccolor.setCellValueFactory(new PropertyValueFactory<>("color"));
        Cgovernate.setCellValueFactory(new PropertyValueFactory<>("governate"));
//        Ccity.setCellValueFactory(new PropertyValueFactory<>("city"));
//        Czip.setCellValueFactory(new PropertyValueFactory<>("zip"));
//        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));

        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));
        
        Cimage.setCellFactory(tc -> {
//            File file = new File("/Users/mac/Desktop/heart.png");
//            String pt = file.toURI().toString();
//            final Image activeImage = new Image(pt, 50,50,false, false);
            
            TableCell<Pet, String> cell = new TableCell<Pet, String>(){
                private ImageView imageView = new ImageView();
                
                protected  void updateItem(String pet_image, boolean empty){
                    super.updateItem(pet_image, empty);
                    if(empty){
                        setGraphic(null);
                    } else {
                        File file = new File(pet_image);
                        String pt = file.toURI().toString();
                        final Image activeImage = new Image(pt, 300, 300,false, false);
                        imageView.setImage(activeImage);
                    }
                    setGraphic(imageView);
                }
            };
            return cell;
        });
        
        
        Csex.setCellValueFactory(new PropertyValueFactory<>("sex"));     
        Csex.setCellFactory(tc -> {
            TableCell<Pet, String> cell = new TableCell<Pet, String>(){
                
                protected  void updateItem(String sex, boolean empty){
                    super.updateItem(sex, empty);
                    
                    TableRow<Pet> currentRow = getTableRow();
                    
                    if (!isEmpty()) {
                    if(sex.equals("MALE")) 
                        currentRow.setStyle("-fx-background-color:#e6ffff");
                    else
                        currentRow.setStyle("-fx-background-color:#ffe6ff");
                    }
                }      
            };
            return cell;
        });
       
//        isActiveColumn.setCellFactory(tc -> {
//        final Image activeImage = new Image(...);
//        final Image passiveImage = new Image(...);
//        TableCell<User, Boolean> cell = new TableCell<User, Boolean>() {
//            private ImageView imageView = new ImageView();
//            @Override
//            protected void updateItem(Boolean active, boolean empty) {
//                super.updateItem(active, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    if (active) {
//                        imageView.setImage(activeImage);
//                    } else {
//                        imageView.setImage(passiveImage);
//                    }
//                    setGraphic(imageView);
//                }
//            }
//        };
//        return cell ;
//        });

        









//
//    Cimage.setCellValueFactory(new Callback<CellDataFeatures<Pet, Image>, ObservableValue<Image>>() {
//    public ObservableValue<Image> call(CellDataFeatures<Pet, Image> p) {
//         Pet w = p.getValue();
//      
//         Image image = new Image("file:/Users/mac/Desktop/031.png");
//         return new SimpleObjectProperty<>(image);      
//     }
//    });
//    
//    Cimage.setCellFactory(new Callback<TableColumn<Pet, Image>, TableCell<Pet, Image>>(){
//        public TableCell<Pet, Image> call(TableColumn<Pet, Image> p) {
//            return new TableCell<>() {
//                   public void updateItem(Image image, boolean empty) {
//                      setText(null);
//                      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                      ImageView imageView = (image == null || empty) ? null :  ImageViewBuilder.create().image(image).build();
//                      setGraphic(imageView);
//                   }
//            };
//        }
//    }):



//    Cimage.setCellFactory(new Callback<TableColumn<Pet,Image>,TableCell<Pet,Image>>(){        
//	@Override
//	public TableCell<Pet,Image> call(TableColumn<Pet,Image> param) {                
//		TableCell<Pet,Image> cell = new TableCell<Pet,Image>(){
//			ImageView imageview = new ImageView();
//                        
//			public void updateItem(Pet item, boolean empty) {                        
//				if(item!=null){                            
//					HBox box= new HBox();
//					box.setSpacing(10) ;
//					VBox vbox = new VBox();
//					vbox.getChildren().add(new Label(item.getPet_image()));
//					vbox.getChildren().add(new Label(item.getName_pet())); 
//
//					
//					imageview.setFitHeight(50);
//					imageview.setFitWidth(50);
//					imageview.setImage(new Image("file:/Users/mac/Desktop/031.png")); 
//
//					box.getChildren().addAll(imageview,vbox); 
//					//SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//					setGraphic(box);
//				}
//			}
//		};
//		System.out.println(cell.getIndex());               
//		return cell;
//	}
//
//});



//        Cimage.setCellFactory(new Callback<TableColumn<Pet, Image>, TableCell<Pet, Image>>() {
//            public TableCell<Pet, Image> call(TableColumn<Pet, Image> param) {
//
//                final ImageView imageView = new ImageView();
//                imageView.setFitHeight(50);
//                imageView.setFitWidth(50);
//
//                    TableCell<Pet, Image> cell = new TableCell<Pet, Image>(){
//
//                        @Override
//                        protected void updateItem(Image item, boolean empty) {
//                            if(item != null)
//                              imageView.setImage(new Image("file:/Users/mac/Desktop/031.png"));
//                        }
//
//                    };
//                    cell.setGraphic(imageView);
//                    return cell;
//            }
//        });
//        Cimage.setCellValueFactory(new PropertyValueFactory<Pet, Image>("image"));





        
        // disable horizental scroll
        //tableUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableUser.setItems(null);
        tableUser.setItems(petdata); 
    }
    
//    @FXML
//    private void loadDataFromDatabase(ActionEvent event) throws SQLException {
//        ServicePet sp = new ServicePet();
//       
//        List<Pet> pets;
//	pets= sp.displayAll();
//        
//        //System.out.println(pets);
//	final ObservableList<Pet> petdata = FXCollections.observableArrayList(pets);
//   
//        Cname.setCellValueFactory(new PropertyValueFactory<>("name_pet"));
//        Cbreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
//        Cage.setCellValueFactory(new PropertyValueFactory<>("age"));
////        Csex.setCellValueFactory(new PropertyValueFactory<>("sex"));
////        Ccolor.setCellValueFactory(new PropertyValueFactory<>("color"));
////        Cgovernate.setCellValueFactory(new PropertyValueFactory<>("governate"));
////        Ccity.setCellValueFactory(new PropertyValueFactory<>("city"));
////        Czip.setCellValueFactory(new PropertyValueFactory<>("zip"));
////        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
////        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));
//        
//        tableUser.setItems(null);
//        tableUser.setItems(petdata); 
//    }

    
    //select from database using table data
    @FXML
    private void check(MouseEvent event) throws SQLException, IOException {     
        Object selectedItems = tableUser.getSelectionModel().getSelectedItems().get(0);
        
        //get the name from the selected line in the table mte3 affichage lkol
        //  10 = pet { name = 
        String first_Column = selectedItems.toString().split(",")[1].substring(10);
        
        //get the value of the selected column
        String idColumn = selectedItems.toString().split(",")[0].substring(11);
        System.out.println(idColumn);
        
        System.out.println(first_Column);
        
        ServicePet sp = new ServicePet();
        returnedId = sp.getPet(first_Column).getId_pet();
        String name = sp.getPet(first_Column).getName_pet();
        String breed = sp.getPet(first_Column).getBreed();
        
//        nameLabel.setText(name);
//        breedLabel.setText(breed);
        
        System.out.println(returnedId);
        //System.out.println(sp.getPet(first_Column).getPet_image());
//        File file = new File(sp.getPet(spc.getVal()).getPet_image());
//        image1 = new Image(file.toURI().toString());
//        spc.setImageview(image1);

        File file = new File(sp.getPet(first_Column).getPet_image());
        System.out.println(sp.getPet(first_Column).getPet_image());
        image1 = new Image(file.toURI().toString());
        //System.out.println(image1);
//        imageview.setImage(image1);
        //System.out.println(imageview);
        
//        loadPage();

        // load page on image clik 
        
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayProfilCoupling.fxml"));
//        Parent root5 = loader.load();
//        Scene sc = new Scene(root5);
//        Stage st = new Stage();
//        st.setScene(sc);
//        st.show();

//        tableUser.setRowFactory(tv -> {
//        TableRow<Pet> row = new TableRow<>();
//        row.setOnMouseClicked(event2 -> {
//            if (event2.getClickCount() == 2 && (! row.isEmpty()) ) {
//                System.out.println("ssssss 2 clicks");
//            }
//        });
//        return row ;
//        });

        tableUser.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent ee) {
               if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
                   try {      
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayProfilCoupling.fxml"));
                        Parent root5 = loader.load();
                        FXMLDisplayProfilCouplingController spc = loader.getController();
                        
                        ServicePet sp1 = new ServicePet();
                        System.out.println(returnedId);
                        
                        spc.setReturnedId(returnedId);
                        spc.setNameLabel(sp.getPetMatching(returnedId).getName_pet());
                        spc.setBreedLabel(sp.getPetMatching(returnedId).getBreed());
                        spc.setImageView(image1);
                        
                        System.out.println(sp.getPetMatching(returnedId).getPet_image());
                        
                        
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show(); 
                   } catch (IOException ex) {
                       Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
         });
          

    }
    
//    public void loadPage() throws IOException{
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayProfilCoupling.fxml"));
//       Parent root5 = loader.load();
//       Scene sc = new Scene(root5);
//       Stage st = new Stage();
//       st.setScene(sc);
//       st.show(); 
//    }



//    private void match(ActionEvent event) throws SQLException {
//        //add in database accouplement after check
//        
//        
////        File file = new File(sp.getPet(spc.getVal()).getPet_image());
////        image1 = new Image(file.toURI().toString());
////        spc.setImageview(image1);
//
//        Matching m = new Matching(2, returnedId);
//        System.out.println(m);
//        ServiceMatching sm = new ServiceMatching();
//        sm.AjouterMatching(m);
//        
//        File file = new File("/Users/mac/Desktop/heart.png");
//        //Image image = new Image(file.toURI().toString());
//        String pt = file.toURI().toString();
//        Image image = new Image(pt, 10 ,10, false, false);
//        match.setDisable(true);
//        match.setGraphic(new ImageView(image));
//        match.setText("MATCHED");
//        
//        // after matching
////        if (a == 0) {
////            
////        }
//    }
//
//
//    private void rateme(MouseEvent event) throws SQLException {
//        double ratingvalue = ratingBar.getRating();
////        System.out.println(ratingvalue);
//        
//        Rateme r = new Rateme(ratingvalue, returnedId, 2);
//        
//        ServiceRating sr = new ServiceRating();
//        sr.AjouterRating(r);
//        
//        ratingBar.setDisable(true);
//        
//        
//        // displaying the rating after rateme method
////        System.out.println(returnedId);
////        int n = sr.nbr5stars(returnedId);
////        System.out.println(n);
//        
//        ObservableList<PieChart.Data> pieChartData = 
//                FXCollections.observableArrayList(
//                        new PieChart.Data("5 stars", sr.nbr5stars(returnedId)),
//                        new PieChart.Data("4 stars", sr.nbr4stars(returnedId)),
//                        new PieChart.Data("3 stars", sr.nbr3stars(returnedId)),
//                        new PieChart.Data("2 stars", sr.nbr2stars(returnedId)),
//                        new PieChart.Data("I stars", sr.nbr1stars(returnedId))
//                );
//        
//        pieChart.setData(pieChartData);
//    }

    @FXML
    private void clearSearch(ActionEvent event) {
        field.clear();
    }
    
    public HBox createPage() throws SQLException{

            HBox tilePane = new HBox();
            tilePane.setPadding(new javafx.geometry.Insets(50, 5, 5, 5));
            ServicePet sp = new ServicePet();
            // show profile of user id = 2
//            ResultSet res = sp.showMyProfiles(2);
            ResultSet res = sp.showMyProfiles(session.IdSession());
            while(res.next()){
                System.out.println("ook");
                image1 = new Image("file:///"+res.getString(11));
                ImageView imageView = new ImageView(image1);
                imageView.setFitHeight(70);
                imageView.setFitWidth(100);
                Label label1 = new Label();
                label1.setGraphic(imageView);
                Label label2 = new Label();
                label2.setText(res.getString(2));
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setAlignment(Pos.CENTER);
                label2.setTranslateY(10);
                int id =res.getInt(1);
                
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayMyPetProfile.fxml"));
                        Parent root5 = loader.load();
                        FXMLDisplayMyPetProfileController spc = loader.getController();
                        spc.setReturnedId(id);
                        spc.setName(sp.getPetById(id).getName_pet());
                        spc.setBreed(sp.getPetById(id).getBreed());
                        spc.setAge(""+sp.getPetById(id).getAge());
                        spc.setSex(sp.getPetById(id).getSex());
                        spc.setColor(sp.getPetById(id).getColor());
                        spc.setCity(sp.getPetById(id).getGovernate()+", "+sp.getPetById(id).getCity());
                        spc.setDescription(sp.getPetById(id).getDescription());
                        spc.setType(sp.getPetById(id).getType());
                        spc.setTypePet(sp.getPetById(id).getTypePet());
                        //image path
                        String pathImg = sp.getPetById(id).getPet_image();
                        spc.setImageView(new Image(("file:///"+pathImg)));

                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                        
                        
                        event.consume();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDisplayAllController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                VBox vbox1 = new VBox();
                vbox1.setPadding(new javafx.geometry.Insets(10));
                vbox1.setSpacing(8);
                vbox1.getChildren().addAll(label1,label2);
                
                String cssLayout = "-fx-border-color: #0e2600;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: solid;\n";
  
                vbox1.setStyle(cssLayout);
                
                tilePane.getChildren().add(vbox1);
            }
            return tilePane;
    }    
   


    @FXML
    private void showProfile(ActionEvent event) throws SQLException {
        PopOver pop = new PopOver();
        HBox hbox = new HBox();  
        hbox = createPage();
        hbox.setPadding(new Insets(10));
        pop.setContentNode(hbox);
        // show on profile button click 
        pop.show(profile);
    }

    @FXML
    private void searchFor(KeyEvent event) {

        field.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            
        // methode bch n desabli el enter 5ater enter traja3li display l 3adi, confli m3a enter mte3 autocomplete    
        @Override
        public void handle(KeyEvent event1){
                if (!event1.getCode().equals(KeyCode.ENTER)){
                                    FilteredList<Pet> filteredData = new FilteredList<>(petdata, e-> true);
        
                                    field.textProperty().addListener((observable, oldValue, newValue) -> {
                                        filteredData.setPredicate(pet -> {
                                            if (newValue == null || newValue.isEmpty()) {
                                                return true;
                                            }

                                            String lowerCaseFilter = newValue.toLowerCase();     


                                            if (Integer.toString(pet.getAge()) == newValue) {
                                                return true;
                                            } else if (pet.getName_pet().toLowerCase().contains(lowerCaseFilter)) {
                                                return true;
                                            } else if (pet.getBreed().toLowerCase().contains(lowerCaseFilter)) {
                                                return true;
                                            }
                                            return false; // Does not match.


                                        });

                                    });

                                    SortedList<Pet> sortedData = new SortedList<>(filteredData);
                                    sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
                                    tableUser.setItems(sortedData); 
                                                    }
                }
        });

        
//        FilteredList<Pet> filteredData = new FilteredList<>(petdata, e-> true);
//        
//        field.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(pet -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//        
//                String lowerCaseFilter = newValue.toLowerCase();     
//
//                
//                if (Integer.toString(pet.getAge()) == newValue) {
//                    return true;
//                } else if (pet.getName_pet().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (pet.getBreed().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                }
//                return false; // Does not match.
//                
//                
//            });
//            
//        });
//
//        SortedList<Pet> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
//        tableUser.setItems(sortedData); 
        
        
    }

    @FXML
    private void searchAction(ActionEvent event) {
//
//        
//        
//        FilteredList<Pet> filteredData = new FilteredList<>(petdata, e-> true);
//        
//        field.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(pet -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//        
//                String lowerCaseFilter = newValue.toLowerCase();     
//
//                
//                if (Integer.toString(pet.getAge()) == newValue) {
//                    return true;
//                } else if (pet.getName_pet().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (pet.getBreed().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                }
//                return false; // Does not match.
//                
//                
//            });
//            
//        });
//
//        SortedList<Pet> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
//        tableUser.setItems(sortedData); 
//        
    }

    @FXML
    private void matchAction(ActionEvent event) throws SQLException {
        ServicePet sp = new ServicePet();
        ServiceMatching sm = new ServiceMatching();
        
        System.out.println(sp.getPetMatching(returnedId).getId_pet());
        System.out.println(sp.getPetMatching(returnedId).getUser_id());
        
        // 2 for connected user id 
//        Matching m = new Matching(2, sp.getPetMatching(returnedId).getId_pet(), 0);
        Matching m = new Matching(session.IdSession(), sp.getPetMatching(returnedId).getId_pet(), 0);
        // 1 for matching
        m.setMatching(1);
        
        int id = sp.returnPetsUser(returnedId);
        
        sm.AjouterMatching(m);
        
        
        //play music 
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\1.mp3");
        
        Media musicfile = new Media(file.toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(musicfile);
        mediaplayer.setAutoPlay(true);
        mediaplayer.setVolume(0.7);
        
        
        
        System.out.println(id);
        System.out.println("name peeeeeeeeeeeeeeeeeet");
        
        
        
        System.out.println("pets of connected user");
        // static user
//        List<Integer> listPets =  sp.returnUsersPets(2);
        List<Integer> listPets =  sp.returnUsersPets(session.IdSession());
        for (int i = 0; i < listPets.size(); i++) {
//            int value = listPets.get(i);
//            System.out.println("Element: " + value);
            System.out.println(listPets.get(i));
        }
        
        
        System.out.println("matched pets by user "+ id);
        List<Integer> listPetsMatched = new ArrayList<>();
        listPetsMatched.clear();
                
        listPetsMatched =  sm.returnMatchedPetsOfUser(id);
        for (int i = 0; i < listPetsMatched.size(); i++) {
//            int value = listPets.get(i);
//            System.out.println("Element: " + value);
            System.out.println(listPetsMatched.get(i));
        }
//        System.out.println(sm.returnMatchedPetsOfUser(id));
        
        // returnedid = selected pet id 
        // id = owner of selected pet
        
        //check if listpets contains listpetsmatched
        
//        Set<Integer> similar = new HashSet<Integer>(listPets);
//        Set<Integer> different = new HashSet<Integer>();
//        different.addAll(listPets);
//        different.addAll(listPetsMatched);
//        
//        similar.retainAll(listPets);
//        different.removeAll( similar );
//        
//        System.out.println(similar);
//        System.out.println(different);

        List<Integer> common = new ArrayList<Integer>(listPets);
        common.retainAll(listPetsMatched);
        
        System.out.println(common);
        if(common.size() > 0){
//        if (listPets.contains(listPetsMatched)) {
            try {
			// Construct data
			String apiKey = "apikey=" + "vXGAhGaxnys-7cF2ZGxgFHxS1Lut0t2HTz1otc2VrJ";
			String message = "&message=" + "You matched pet: " + sp.getPetNameById(returnedId);
			String sender = "&sender=" + "AdoPet";
			String numbers = "&numbers=" + "0021625400017";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
                        System.out.println("sms sent");
                        
//                        File file2 = new File("/Users/mac/Desktop/bg/3.mp3");
//                        Media musicfile2 = new Media(file2.toURI().toString());
//                        MediaPlayer mediaplayer2 = new MediaPlayer(musicfile2);
//                        mediaplayer2.setAutoPlay(true);
//                        mediaplayer2.setVolume(0.3);
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			//return "Error "+e;
		}

            
            mediaplayer.pause();

            File file2 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\3.mp3");
            Media musicfile2 = new Media(file2.toURI().toString());
            MediaPlayer mediaplayer2 = new MediaPlayer(musicfile2);
            mediaplayer2.setAutoPlay(true);
            mediaplayer2.setVolume(0.3);
            System.out.println("fehaaaaaaaaa");
            
        }
        
//        System.out.println(listPets.toString());
        // reload table
        field.clear();
        loadData();
        
    }

    @FXML
    private void dislikeAction(ActionEvent event) throws SQLException {
        ServicePet sp = new ServicePet();
        ServiceMatching sm = new ServiceMatching();
        
        System.out.println(sp.getPetMatching(returnedId).getId_pet());
        System.out.println(sp.getPetMatching(returnedId).getUser_id());
        
        // 2 for connected user id 
//        Matching m = new Matching(2, sp.getPetMatching(returnedId).getId_pet(), 0);
        Matching m = new Matching(session.IdSession(), sp.getPetMatching(returnedId).getId_pet(), 0);
        // 1 for matching
        m.setMatching(0);
        
        sm.AjouterMatching(m);
        
        //play music on click 

        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\2.mp3");
        Media musicfile = new Media(file.toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(musicfile);
        mediaplayer.setAutoPlay(true);
        mediaplayer.setVolume(0.7);

        // reload table
        field.clear();
        loadData();
    }



    
}
