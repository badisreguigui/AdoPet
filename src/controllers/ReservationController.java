/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.LocalTimeTextField;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class ReservationController implements Initializable {
    @FXML
    private AnchorPane reservation;
    @FXML
    private Label ddtitre;
    @FXML
    private Label dftitre;
    @FXML
    private Label hdtitre;
    @FXML
    private JFXButton res;
    @FXML
    private Label title;
    @FXML
    private TextField cin;
   
    @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField adresse;
    @FXML
    private Label adresset;

    /**
     * Initializes the controller class.
     */
    Document doc = new Document();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void genererPDF(ActionEvent event) {
        try{
            doc.addTitle("Protocole d'Accord entre les soussigné(e)s :");
            PdfWriter.getInstance(doc ,new FileOutputStream("contract.pdf"));
            doc.open();
            Paragraph p = new Paragraph("**…………………………………………………………………………………………………, Propriétaire de l'animal,\n" +
"demeurant à (adresse) :  …………………………………………………………………………………..…………………………….…………………………,\n" +
"\n" +
"Téléphone : ….-….-….-….-….\n" +
"\n" +
"et\n" +
"\n" + "**"+nom.getText() +
", Petsitter\n" +
"* demeurant à (adresse): " + adresse.getText() +"\n" +
"\n" +
"Téléphone : "+ tel.getText() +"\n"+ "CIN : " + cin.getText() + "\n" +
"\n" +
" \n" +
"\n" +
"Il a été convenu ce qui suit :\n" +
"\n" +
"Définition du service rendu par le PetSitter\n" +
"\n" +
"Le Propriétaire sollicite le Petsitter en\n" +
"\n" +
" à compter du ………………………………. jusqu’au …………………………….\n" +
"\n" +
" \n" +
"\n" +
"Le PetSitter devra prendre soin de l'animal nommé ci-après :\n" +
" \n" +
"\n" +
"………………………………………………..\n" +
 
" \n" +
"\n" +
" \n" +
"\n" +
"La garde aura lieu (cocher l'option choisie) :\n" +
"* Au domicile du Propriétaire de l'animal\n" +
"* Au domicile du PetSitter\n" +
"* Autre lieu (indiquez l'adresse): ………………………………………………………………………………..\n" +
" \n" +
"\n" +
"Dans le cadre de sa prise en charge de l’animal, le Petsitter exercera les actions suivantes :\n" +
"\n" +
"NB : Le rôle du PetSitter est de prendre en charge l’animal, de subvenir à ses besoins, de veiller à son bienêtre et de s’assurer qu’il ne reste pas seul (ou le moins possible en accord avec son propriétaire). Le propriétaire de l’animal peut ici indiquer tous ce qui concerne son animal (soins médicaux, habitudes alimentaires, habitudes de vie, …) :\n" +
"\n" +
" \n" +
"\n" +
"Personne de contact en cas d’urgence (nom, prénom, tel) : ……………………………………………………………………………………………………………………………..\n" +
"\n" +
"Vétérinaire de l’animal  (nom, prénom, tel, adresse) : ……………………………………………………………………………………………………………………………..\n" +
"\n" +
"Habitudes alimentaires de l’animal : ……………………………………………………………………………………………………………………………..\n" +
"\n" +
"…………………………………………………………………………………………………..…………………………\n" +
"\n" +
"……………………………………………………………………………………………………………………………….\n" +
"\n" +
"Habitudes de vie de l’animal : ……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"Problèmes de santé éventuels de l’animal : …………………………………………………………………………………………………..…………………………\n" +
"\n" +
"……………………………………………………………………………………………………………………………….\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"Autres consignes : ……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"…………………………………………………………………………………………………..…………………………\n" +
"\n" +
"……………………………………………………………………………………………………………………………….\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
" \n" +
"\n" +
"Conditions Générale de réalisation de la Garde\n" +
" il est convenu que le propriétaire de l’animal dédommage le PetSitter de son service de garde, en lui versant la somme forfaitaire de ………….…………Dinar tunisiens, (montant en lettres : ………….………………………………….… ).\n" +
"\n" +
"\n" +
"En cas d’empêchement de réalisation de sa garde, le PetSitter sera tenu d’en aviser le Propriétaire dans un délai de ………...jours (indiquez le nombre de jours souhaités ) précédant le début de la garde.\n" +
"\n" +
"En cas d’urgence vétérinaire :\n" +
"\n" +
"Le PetSitter est habilité à conduire l’animal malade auprès du vétérinaire recommandé par le propriétaire de l’animal (voir plus haut - si aucun vétérinaire n’a été préalablement conseillé, il pourra être choisi par l’intervenant lui-même). Le PetSitter est donc habilité à engager les frais vétérinaires qu'il estimera nécessaire pour la bonne santé et la sauvegarde de l’animal.\n" +
"Les frais vétérinaires et médicaux seront intégralement remboursés au PetSitter par le propriétaire de l’animal.\n" +
"\n" +
"Le propriétaire de l’animal déclare être joignable téléphoniquement aux numéros suivants, durant la réalisation de la garde: ….-….-….-….-…. ou ….-….-….-….-…. . Le PetSitter devra obtenir un accord téléphonique du propriétaire de l’animal avant d’engager des frais médicaux (exception faite d’un danger grave encouru par l’animal, nécessitant une intervention médicale immédiate). Le propriétaire de l’animal s’engage au remboursement des frais médicaux engagés par le PetSitter.\n" +
"Le propriétaire de l’animal déclare ne pas être joignable durant la réalisation de la garde, mais confie la décision à (prénom+nom) joignable téléphoniquement aux numéros suivants : : ….-….-….-….-…. ou ….-….-….-….-…. . Le PetSitter devra obtenir un accord téléphonique du propriétaire de l’animal avant d’engager des frais médicaux (exception faite d’un danger grave encouru par l’animal, nécessitant une intervention médicale immédiate). Le propriétaire de l’animal s’engage au remboursement des frais médicaux engagés par le PetSitter.\n" +
"Autres consignes spécifiques du Propriétaire en cas d’urgence vétérinaire :\n" +
"\n" +
" \n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"……………………………………………………………………………………………………………………………..\n" +
"\n" +
"…………………………………………………………………………………………………..………………………………………………………………………………………………………………………………………………………..\n" +
"\n" +
" \n" +
"\n" +
" \n" +
"\n" +
"Durée du présent contrat de Petsitting.\n" +
" \n" +
"\n" +
"Ce présent contrat est conclu pour une période déterminée : celle-ci est précisée dans le chapitre « Définition du service rendu par le Petsitter». Le présent conttat n’est valable que pour les parties mentionnées, et est soumis au droit du pays où la garde se réalise.\n" +
"\n" +
" \n" +
"\n" +
"Fait le ………………………………… à ……………………………..,\n" +
"\n" +
" \n" +
"\n" +
"Le propriétaire de l’animal :                                                                                                      Le Petsitter :\n" +
"\n \n \n \n" +
"Faire précéder les signatures de vos noms et de la mention « lu et approuvé » rédigée à la main.\n" +
"\n" +
" ");
            doc.add(p);
        }catch(Exception e){
            System.out.println(e);
        }
        doc.close();
    }
    
}
