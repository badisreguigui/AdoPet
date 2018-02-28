/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AutoComplete;
import Entities.Pet;
import Entities.Session;
import Services.ServicePet;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsWaypoint;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;

import org.controlsfx.control.textfield.TextFields;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import uk.me.jstott.jcoord.LatLng;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLGmapsController implements Initializable {
    
    @FXML
    private GoogleMapView googleMapView;
    private GoogleMap map;
    @FXML
    private TableView<Pet> tableAdo;
    @FXML
    private TableColumn<Pet, String> Cimage;
    @FXML
    private TableColumn<?, ?> Cinfo;
    private ObservableList<Pet> petdata;
    @FXML
    private JFXButton recenter;
    
    private int returnedId;
    @FXML
    private TextField field;
    @FXML
    private TextField radius;
    @FXML
    private JFXButton showCircle;
    
    private Circle c;
    
    private String distanceMiles;
    private String distanceMilesInt;
    private double distanceKM;
    
    private String duration;
    @FXML
    private JFXButton calcul;
    @FXML
    private Label distanceToTarget;
    
    private boolean check = true;
    
    private Polyline poly;
    
    private double radiusV;
    @FXML
    private TextField radiusFetch;
    @FXML
    private Button location;
    
    private double userAlt= 36.998392;
    private double userLng= 10.189732;
    
    private Marker marker2;
    
    Session session = Session.getInstance();
    @FXML
    private AnchorPane pane;
    
    Image image1;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        pane.setStyle("-fx-background-image: url('images/adopet3.jpg');");
        
        calcul.setStyle(
                
                "-fx-min-width: 140px; " +
                "-fx-min-height: 140px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        File file3 = new File("/Users/mac/Desktop/locateme.gif");
        //Image image = new Image(file.toURI().toString());
        String pt3 = file3.toURI().toString();
        Image image3 = new Image(pt3, 110 ,110, false, false);
        calcul.setGraphic(new ImageView(image3));
        calcul.setText("");
        
        
        System.out.println(session.IdSession());
        
        googleMapView.addMapInializedListener(() -> {
            try {
                configureMap();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLGmapsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        try {
            //load the table on start
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGmapsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            loadAuto();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGmapsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // delete table header from css file
        tableAdo.getStyleClass().add("noheader");
        // fkn hide the tableview horizontal scroll via css files
        tableAdo.getStyleClass().add("table-view");
        
    }  
    
    private void loadAuto() throws SQLException{
        AutoComplete ac = new AutoComplete();
        String[] a = ac.MotsAutoComplete();      
        TextFields.bindAutoCompletion(field, a);
    }
    
//    private void loadData() throws SQLException {
//        ServicePet sp = new ServicePet();
//       
//        List<Pet> pets;
//	pets= sp.displayAll();
//
//        // Display 2 data in 1 column   
//        // name + breed + age in governate(pet)
//        for (Pet pet: pets){
//            String a = pet.getName_pet() +"\n \n \n"+ pet.getBreed()+"\n \n \n"+ pet.getAge();
//            pet.setGovernate(a);
//        }
//
//        
//        //System.out.println(pets);
//	petdata = FXCollections.observableArrayList(pets);
//        
//
//
//        Cinfo.setCellValueFactory(new PropertyValueFactory<>("governate"));
//
//
//        Cimage.setCellValueFactory(new PropertyValueFactory<>("pet_image"));
//        
//        Cimage.setCellFactory(tc -> {
////            File file = new File("/Users/mac/Desktop/heart.png");
////            String pt = file.toURI().toString();
////            final Image activeImage = new Image(pt, 50,50,false, false);
//            
//            TableCell<Pet, String> cell = new TableCell<Pet, String>(){
//                private ImageView imageView = new ImageView();
//                
//                protected  void updateItem(String pet_image, boolean empty){
//                    super.updateItem(pet_image, empty);
//                    if(empty){
//                        setGraphic(null);
//                    } else {
//                        File file = new File(pet_image);
//                        String pt = file.toURI().toString();
//                        final Image activeImage = new Image(pt, 250, 200,false, false);
//                        imageView.setImage(activeImage);
//                    }
//                    setGraphic(imageView);
//                }
//            };
//            return cell;
//        });
//        tableAdo.setItems(null);
//        tableAdo.setItems(petdata); 
//    } 
    
    
    private void loadData() throws SQLException {
        ServicePet sp = new ServicePet();
       
        List<Pet> pets;
	pets= sp.displayAllAdo(session.IdSession());
//        pets= sp.displayAll();
        

        // Display 3 data in 1 column   
        // name + breed + age in governate(pet)
        for (Pet pet: pets){
            String a = pet.getName_pet() +"\n \n \n"+ pet.getBreed()+"\n \n \n"+ pet.getAge();
            pet.setGovernate(a);
        }

        
        //System.out.println(pets);
	petdata = FXCollections.observableArrayList(pets);
        


        Cinfo.setCellValueFactory(new PropertyValueFactory<>("governate"));


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
                        final Image activeImage = new Image(pt, 250, 200,false, false);
                        imageView.setImage(activeImage);
                    }
                    setGraphic(imageView);
                }
            };
            return cell;
        });
        tableAdo.setItems(null);
        tableAdo.setItems(petdata); 
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    protected void configureMap() throws SQLException {
        
        
        // initial position for user
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(userAlt, userLng))
//                .mapType(MapTypeIdEnum.ROADMAP)
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9)
                .styleString("[ { \"featureType\": \"all\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"saturation\": 36 }, { \"color\": \"#000000\" }, { \"lightness\": 40 } ] }, { \"featureType\": \"all\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"visibility\": \"on\" }, { \"color\": \"#000000\" }, { \"lightness\": 16 } ] }, { \"featureType\": \"all\", \"elementType\": \"labels.icon\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"administrative\", \"elementType\": \"geometry.fill\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 20 } ] }, { \"featureType\": \"administrative\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 17 }, { \"weight\": 1.2 } ] }, { \"featureType\": \"landscape\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 20 } ] }, { \"featureType\": \"poi\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 21 } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"geometry.fill\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 17 } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 29 }, { \"weight\": 0.2 } ] }, { \"featureType\": \"road.arterial\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 18 } ] }, { \"featureType\": \"road.local\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 16 } ] }, { \"featureType\": \"transit\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 19 } ] }, { \"featureType\": \"water\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" }, { \"lightness\": 17 } ] } ]")
//                .styleString("[ { \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#212121\" } ] }, { \"elementType\": \"labels.icon\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#757575\" } ] }, { \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#212121\" } ] }, { \"featureType\": \"administrative\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#757575\" } ] }, { \"featureType\": \"administrative.country\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#9e9e9e\" } ] }, { \"featureType\": \"administrative.land_parcel\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"administrative.locality\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#bdbdbd\" } ] }, { \"featureType\": \"poi\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#757575\" } ] }, { \"featureType\": \"poi.park\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#181818\" } ] }, { \"featureType\": \"poi.park\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#616161\" } ] }, { \"featureType\": \"poi.park\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#1b1b1b\" } ] }, { \"featureType\": \"road\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"road\", \"elementType\": \"geometry.fill\", \"stylers\": [ { \"color\": \"#2c2c2c\" } ] }, { \"featureType\": \"road\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#8a8a8a\" } ] }, { \"featureType\": \"road.arterial\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#373737\" } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#3c3c3c\" } ] }, { \"featureType\": \"road.highway.controlled_access\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#4e4e4e\" } ] }, { \"featureType\": \"road.local\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#616161\" } ] }, { \"featureType\": \"transit\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#757575\" } ] }, { \"featureType\": \"water\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#000000\" } ] }, { \"featureType\": \"water\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#3d3d3d\" } ] } ]")
                ;
        

//        mapOptions.center(new LatLong(36.8064948, 10.1815316))
//                .mapMarker(true)
//                .zoom(9)
//                .overviewMapControl(false)
//                .panControl(false)
//                .rotateControl(false)
//                .scaleControl(false)
//                .streetViewControl(false)
//                .zoomControl(false)
//                .mapType(MapTypeIdEnum.TERRAIN)
//                
//                .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
        map = googleMapView.createMap(mapOptions, false);

        // on map click
//        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
//            LatLong latLong = event.getLatLong();
//
//        });

        LatLong marker2LatLong = new LatLong(userAlt, userLng);
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.position(marker2LatLong)
                    .visible(Boolean.TRUE)
                    .icon("https://media.giphy.com/media/dIPGSJ6xN57oZkd0tB/giphy.gif")
                    .title("YOU");
            marker2 = new Marker(markerOptions2);
            map.addMarker(marker2);
            
            InfoWindowOptions infoWindowOptions2 = new InfoWindowOptions();
            map.addUIEventHandler(marker2 ,UIEventType.click, (i -> {

                System.out.println("a");
                infoWindowOptions2.content("<h2> This is you \n at "+ marker2LatLong );

                InfoWindow fredWilkeInfoWindow2 = new InfoWindow(infoWindowOptions2);
                fredWilkeInfoWindow2.open(map, marker2);
            }));
        
        // Adding markers
        ServicePet sp = new ServicePet();
        List<Pet> petsAdop = new ArrayList<>();
        petsAdop = sp.displayAllAdo(session.IdSession());
        for (Pet pet : petsAdop) {
            System.out.println(pet);
            System.out.println(pet.getLat());
            System.out.println(pet.getLng());
            
            System.out.println(pet.getTypePet());
            
            //Esprit position
//            LatLong marker2LatLong = new LatLong(userAlt, userLng);
//            MarkerOptions markerOptions2 = new MarkerOptions();
//            markerOptions2.position(new LatLong(36.898392,10.189732))
//                    .visible(Boolean.TRUE)
//                    .icon("https://media.giphy.com/media/dIPGSJ6xN57oZkd0tB/giphy.gif")
//                    .title("YOU");
//            Marker marker2 = new Marker(markerOptions2);
//            map.addMarker(marker2);
//            
//            InfoWindowOptions infoWindowOptions2 = new InfoWindowOptions();
//            map.addUIEventHandler(marker2 ,UIEventType.click, (i -> {
//                System.out.println(pet.getAge());
//                System.out.println("a");
//                infoWindowOptions2.content("<h2> This is you \n at "+ marker2LatLong );
//
//                InfoWindow fredWilkeInfoWindow2 = new InfoWindow(infoWindowOptions2);
//                fredWilkeInfoWindow2.open(map, marker2);
//            }));
            
            
            
            
            

            MarkerOptions markerOptions = new MarkerOptions();
            
            
            // check race to change the icon
            if (pet.getTypePet().equals("DOG")) {
                markerOptions.position(new LatLong(pet.getLat(), pet.getLng()))
                    .visible(Boolean.TRUE)
//                    .icon("https://s3-us-west-2.amazonaws.com/s.cdpn.io/123941/cheshire1-icon.png")
//                    .icon("https://www.lv.com/assets/gi/images/insurance/homepage-icons/icon-pet-50x50.png")
//                    .icon("https://image.ibb.co/jMzpJH/cheshire1_icon.png")
                    .icon("https://media.giphy.com/media/FDQydqKZKl9jMPlkLN/giphy.gif")
//                    .icon("https://media.giphy.com/media/1Ago0V6cWM52OT7JPO/giphy.gif")
                    .animation(Animation.BOUNCE)
                    .title(pet.getName_pet());
            }
            else {
                markerOptions.position(new LatLong(pet.getLat(), pet.getLng()))
                    .visible(Boolean.TRUE)
//                    .icon("https://s3-us-west-2.amazonaws.com/s.cdpn.io/123941/cheshire1-icon.png")
//                    .icon("https://www.lv.com/assets/gi/images/insurance/homepage-icons/icon-pet-50x50.png")
//                    .icon("https://image.ibb.co/jMzpJH/cheshire1_icon.png")
//                    .icon("https://media.giphy.com/media/FDQydqKZKl9jMPlkLN/giphy.gif")
                    .icon("https://media.giphy.com/media/1Ago0V6cWM52OT7JPO/giphy.gif")
                    .animation(Animation.BOUNCE)
                    .title(pet.getName_pet());
            }

//            markerOptions.position(new LatLong(pet.getLat(), pet.getLng()))
//                    .visible(Boolean.TRUE)
////                    .icon("https://s3-us-west-2.amazonaws.com/s.cdpn.io/123941/cheshire1-icon.png")
////                    .icon("https://www.lv.com/assets/gi/images/insurance/homepage-icons/icon-pet-50x50.png")
////                    .icon("https://image.ibb.co/jMzpJH/cheshire1_icon.png")
////                    .icon("https://media.giphy.com/media/FDQydqKZKl9jMPlkLN/giphy.gif")
//                    .icon("https://media.giphy.com/media/1Ago0V6cWM52OT7JPO/giphy.gif")
//                    .animation(Animation.BOUNCE)
//                    .title(pet.getName_pet());

            Marker marker = new Marker(markerOptions);

            map.addMarker(marker);
            
            
            // iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
            // position esprti
//            LatLong marker2LatLong = new LatLong(36.898392,10.189732);
//            MarkerOptions markerOptions2 = new MarkerOptions();
//            markerOptions2.position(new LatLong(36.898392,10.189732))
//                    .visible(Boolean.TRUE)
//                    .title("1");
//            Marker marker2 = new Marker(markerOptions2);
//            map.addMarker(marker2);
//            
//            MarkerOptions markerOptions3 = new MarkerOptions();
//            markerOptions3.position(new LatLong(36.4072574, 10.6224706))
//                    .visible(Boolean.TRUE)
//                    .title("2");
//            Marker marker3 = new Marker(markerOptions3);
//            map.addMarker(marker3);
//            
//            
//            
//            LatLong marker3LatLong = new LatLong(36.4072574, 10.6224706);
//            LatLong[] ary = new LatLong[]{marker2LatLong, marker3LatLong};
//            MVCArray mvc = new MVCArray(ary);
//
//            PolylineOptions polyOpts = new PolylineOptions()
//                    .path(mvc)
//                    .strokeColor("red")
//                    .strokeWeight(2);
//
//            Polyline poly = new Polyline(polyOpts);
//            map.addMapShape(poly);
            // iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
//            DirectionsRenderer renderer;
//            
//            DirectionsService ds = new DirectionsService();
//            renderer = new DirectionsRenderer(true, map, directions);
//
//            DirectionsWaypoint[] dw = new DirectionsWaypoint[2];
//            dw[0] = new DirectionsWaypoint("São Paulo - SP");
//            dw[1] = new DirectionsWaypoint("Juiz de Fora - MG");
//
//            DirectionsRequest dr = new DirectionsRequest(
//                    "Belo Horizonte - MG",
//                    "Rio de Janeiro - RJ",
//                    TravelModes.DRIVING,
//                    dw);
//            ds.getRoute(dr, this, renderer);
            // iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
            
            
            

            
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            // get click on marker event
            map.addUIEventHandler(marker ,UIEventType.click, (i -> {
                System.out.println(pet.getAge());
                System.out.println("a");
                infoWindowOptions.content("<h2> Pet name: "+ pet.getName_pet() +"</h2>"
                                    + "Race: " + pet.getBreed() +"<br>"
                                    + "Age: "+ pet.getAge() +" y.o" );

                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, marker);
            }) 
//            LatLong latLong = event.getLatLong();
      
            );
            
//            LatLong centerD = new LatLong(47.606189, -122.335842);
//            map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
//                System.out.println("clicked");
//                map.setCenter(centerD);
//            });

        }
        
        
        // create circle on start radius = 0 
        onStartCircle();

    }

    @FXML
    private void recenter(ActionEvent event) {
        LatLong centerD = new LatLong(userAlt,userLng);
        map.setCenter(centerD);
    }

    @FXML
    private void check(MouseEvent event) throws SQLException {
        Object selectedItems = tableAdo.getSelectionModel().getSelectedItems().get(0);
        
        //get the name from the selected line in the table mte3 affichage lkol
        //  10 = pet { name = 
        String first_Column = selectedItems.toString().split(",")[1].substring(10);
        
        //get the value of the selected column
        String idColumn = selectedItems.toString().split(",")[0].substring(11);
        System.out.println(idColumn);
        
        ServicePet sp = new ServicePet();
        returnedId = sp.getPetAdo(Integer.parseInt(idColumn)).getId_pet();
        
        File file = new File(sp.getPetAdo(Integer.parseInt(idColumn)).getPet_image());
        System.out.println(sp.getPetAdo(Integer.parseInt(idColumn)).getPet_image());
        image1 = new Image(file.toURI().toString());
        
        tableAdo.setOnMousePressed(new EventHandler<MouseEvent>() {
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
        
//        System.out.println(returnedId);

        
        
        double lat = sp.getPetAdo(Integer.parseInt(idColumn)).getLat();
        double lng = sp.getPetAdo(Integer.parseInt(idColumn)).getLng();
        
//        System.out.println(lat);
//        System.out.println(lng);

        LatLong goTo = new LatLong(lat, lng);
        
//        map.removeMapShape(poly);
        
        //draw line
            LatLong[] ary = new LatLong[]{new LatLong(userAlt,userLng), goTo};
            MVCArray mvc = new MVCArray(ary);

//            PolylineOptions polyOpts = new PolylineOptions()
//                    .path(mvc)
//                    .strokeColor("#75fffc")
//                    .strokeWeight(2);
//
//            
//            Polyline poly = new Polyline(polyOpts);
            onStartPolyline(goTo);
            poly.setPath(mvc);
            
            map.addMapShape(poly);
        
            calculateDistance(sp.getPetAdo(Integer.parseInt(idColumn)).getGovernate(), sp.getPetAdo(Integer.parseInt(idColumn)).getCity());
            
            distanceToTarget.setAlignment(Pos.CENTER);
            
            distanceToTarget.setText("Distance from Esprit Ghazela,tunisia to "+sp.getPetAdo(Integer.parseInt(idColumn)).getGovernate()+","+sp.getPetAdo(Integer.parseInt(idColumn)).getCity()+": "+distanceKM+" KM \n Time to get to your destination: " + duration);
        
        map.setCenter(goTo);
        
        
        
        
    }

    @FXML
    private void searchAction(ActionEvent event) {
        
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
                                    sortedData.comparatorProperty().bind(tableAdo.comparatorProperty());
                                    tableAdo.setItems(sortedData); 
                                                    }
                }
        });
    }

    @FXML
    private void showCircleR(ActionEvent event) {  
        
        System.out.println("ssssss");
        System.out.println(userAlt);
        System.out.println(userLng);
        System.out.println("sssss");
        
        radiusV = Integer.parseInt(radius.getText());
        c.setRadius(radiusV);
        
        c.getBounds();
        System.out.println(c.getBounds());
            
    }

//    @FXML
//    private void refresh(ActionEvent event) {
//        onStartCircle();
//
//    }
    
    public void onStartCircle (){
        //getLoc();
        System.out.println("ssssss");
        System.out.println(userAlt);
        System.out.println(userLng);
        System.out.println("sssss");
        
        LatLong centreC = new LatLong(userAlt, userLng);
        
            CircleOptions cOpts = new CircleOptions()
                    .center(centreC)
                    .radius(0)
                    .strokeColor("#ffcfcf")
                    .strokeOpacity(0.6)
                    .strokeWeight(2)
                    .fillColor("#ffcfcf")
                    .fillOpacity(0.1);

            c = new Circle(cOpts);

          
            map.addMapShape(c); 
        
    }
    
    public void onStartPolyline (LatLong goTo){

        while (check) {
        PolylineOptions polyOpts = new PolylineOptions()
//                    .path(mvc)
                    .strokeColor("#75fffc")
                    .strokeWeight(2);

            
        poly = new Polyline(polyOpts);
        check = false;
        }
    }
    

    
    
    public void calculateDistance (String gov, String city) {
        JSONParser parser = new JSONParser();

            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=tunisia+esprit&destinations="+gov+"+"+city+"&key=AIzaSyAy6X_fPTOR940RBWcDa4MuheBggCFhp7w");
                URLConnection connection = url.openConnection();
                InputStream in = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(new File("downloadeds.json"));
                byte[] buf = new byte[512];
                while (true) {
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    fos.write(buf, 0, len);
                }
                in.close();
                fos.flush();
                fos.close();

                Object obj = parser.parse(new FileReader(new File("downloadeds.json")));

                JSONObject jsonObject = (JSONObject) obj;

                JSONArray rows = (JSONArray) jsonObject.get("rows");
                Iterator<JSONObject> iterator = rows.iterator();
                while (iterator.hasNext()) {

//                    JSONObject row = (JSONObject) iterator.next().get("elements");
//                    JSONArray rows = (JSONArray) jsonObject.get("rows");
                    JSONArray row = (JSONArray) iterator.next().get("elements");
                    System.out.println(row);
                    Iterator<JSONObject> iterator1 = row.iterator();
                    while (iterator1.hasNext()) {
                        JSONObject row1 = (JSONObject) iterator1.next().get("distance");
                        
                        System.out.println(row1);
                        distanceMiles = (String) row1.get("text");
                        
                        System.out.println(distanceMiles);   
                    }
                    Iterator<JSONObject> iterator2 = row.iterator();
                    while (iterator2.hasNext()) {
                        JSONObject row2 = (JSONObject) iterator2.next().get("duration");
                        
                        System.out.println(row2);
                        duration = (String) row2.get("text");
                        
                        System.out.println(duration);
                    }
                    
              
                    
                    

//                    JSONObject rowObj = (JSONObject) row.get(0);
//
//                    a = (String) rowObj.get("text");
//                    System.out.println(rowObj.get("text"));
//                    b = (String) rowObj.get("lng");
                    

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }

                
            // Convert miles to KM
            // delete the mil from the string first then convert to int
            
            distanceKM = Double.parseDouble(distanceMiles.substring(0, distanceMiles.length() - 2));
            
            System.out.println(distanceKM);


    }

    @FXML
    private void calcul(ActionEvent event){
//        calculateDistance("tunisia", "tunis");
//        
//        System.out.println(distanceKM);


//        FilteredList<Pet> filteredData = new FilteredList<>(petdata, e-> true);
//        System.out.println("mellowel");
//        radius.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(pet -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                
//                double radiusValue = Double.parseDouble(newValue) / 1000;
//
//                if (calculateDistanceInt("tunisia", pet.getCity()) < radiusValue) {
//                    System.out.println("l dekhel");
//                    return true;
//                }      
//                System.out.println("not in");
//                return false; // Does not match.
//            });
//
//        });
//
//        SortedList<Pet> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableAdo.comparatorProperty());
//        tableAdo.setItems(sortedData);
    }
    
    
    
    
    
    public double calculateDistanceInt (String gov, String city) {
        JSONParser parser = new JSONParser();
        

            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=tunisia+esprit&destinations="+gov+"+"+city+"&key=AIzaSyAy6X_fPTOR940RBWcDa4MuheBggCFhp7w");
                URLConnection connection = url.openConnection();
                InputStream in = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(new File("downloadeds.json"));
                byte[] buf = new byte[512];
                while (true) {
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    fos.write(buf, 0, len);
                }
                in.close();
                fos.flush();
                fos.close();

                Object obj = parser.parse(new FileReader(new File("downloadeds.json")));

                JSONObject jsonObject = (JSONObject) obj;

                JSONArray rows = (JSONArray) jsonObject.get("rows");
                Iterator<JSONObject> iterator = rows.iterator();
                while (iterator.hasNext()) {

//                    JSONObject row = (JSONObject) iterator.next().get("elements");
//                    JSONArray rows = (JSONArray) jsonObject.get("rows");
                    JSONArray row = (JSONArray) iterator.next().get("elements");
                    Iterator<JSONObject> iterator1 = row.iterator();
                    while (iterator1.hasNext()) {
                        JSONObject row1 = (JSONObject) iterator1.next().get("distance");
                        
                        distanceMilesInt = (String) row1.get("text");
                        
                        
                        
                        
                        
                    }
//                    Iterator<JSONObject> iterator2 = row.iterator();
//                    while (iterator2.hasNext()) {
//                        JSONObject row2 = (JSONObject) iterator2.next().get("duration");
//                        
//                        System.out.println(row2);
//                        duration = (String) row2.get("text");
//                        
//                    }
                    
              
                    
                    

//                    JSONObject rowObj = (JSONObject) row.get(0);
//
//                    a = (String) rowObj.get("text");
//                    System.out.println(rowObj.get("text"));
//                    b = (String) rowObj.get("lng");
                    

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }

                
            // Convert miles to KM
            // delete the mil from the string first then convert to int
            
            double distanceKMInt;
            distanceKMInt = Double.parseDouble(distanceMilesInt.substring(0, distanceMilesInt.length() - 2));
            
            

            return distanceKMInt;
    }

    @FXML
    private void fetchOnRadius(ActionEvent event) {
        FilteredList<Pet> filteredData = new FilteredList<>(petdata, e-> true);
        System.out.println("start");
        
        radiusFetch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pet -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                double radiusValue = Double.parseDouble(newValue) / 1000;

                if (calculateDistanceInt("tunisia", pet.getCity()) < radiusValue) {
                    System.out.println("in");
                    return true;
                }      
                System.out.println("not in");
                return false; // Does not match.
            });

        });

        SortedList<Pet> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableAdo.comparatorProperty());
        tableAdo.setItems(sortedData);
    }

    @FXML
    private void getLocation(ActionEvent event) throws IOException, URISyntaxException, org.json.simple.parser.ParseException {
        System.out.println(userAlt);
        System.out.println(userLng);
//        Desktop.getDesktop().browse(new URI("http://localhost/rest_api/getMyCoords.php"));
//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//        webEngine.load("http://localhost/rest_api/getMyCoords.php");
//        VBox root = new VBox();
//        root.getChildren().addAll(webView);
//        Scene sc = new Scene(root);
//        Stage st = new Stage();
//        st.setScene(sc);
//        st.show();
        JSONParser parser = new JSONParser();
        try{
        Object obj = parser.parse(new FileReader(new File("coords.json")));
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject compose = (JSONObject) jsonObject.get("val");
	userAlt = (double) compose.get("alt");
	userLng = (double) compose.get("lng");
            System.out.println(userAlt);
            System.out.println(userLng);
            double a = (double) compose.get("alt");
            double b = (double) compose.get("lng");
            System.out.println("aaaaa");
            System.out.println(a);
            System.out.println(b);
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(userAlt);
        System.out.println(userLng);
        

    }
    
    public void getLoc () throws MalformedURLException, IOException, URISyntaxException{
        
        
        JSONParser parser = new JSONParser();
        try{
        Object obj = parser.parse(new FileReader(new File("coords.json")));
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject compose = (JSONObject) jsonObject.get("val");
	userAlt = (double) compose.get("alt");
	userLng = (double) compose.get("lng");
        double a = (double) compose.get("alt");
	double b = (double) compose.get("lng");
            System.out.println("aaaaa");
            System.out.println(a);
            System.out.println(b);
            System.out.println(userAlt);
            System.out.println(userLng);
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }       

       
    }

    
    
}
