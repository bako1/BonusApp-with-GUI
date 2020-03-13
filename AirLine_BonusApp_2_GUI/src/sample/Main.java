package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Optional;

//import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;

public class Main extends Application implements EventHandler {
    private MemberArchive memberArchive = new MemberArchive();
    ObservableList<TableClass> personalObservableList = FXCollections.observableArrayList();


    TextArea memberType = new TextArea();
    TextField pointsTextF;
    //TextField name,  ;
    MenuItem seeDetail;
    MenuItem deleteMember;
    MenuItem registerPoint;
    MenuItem update;
    ContextMenu contextMenu;
    TextInputDialog inputDialog;
    TableView<TableClass> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {



        /**
         * Table*/

//table column for first name
        TableColumn<TableClass, String> firstName = new TableColumn<>("firstName");
        firstName.setMaxWidth(200);
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //table column for surname

        TableColumn<TableClass, String> surname = new TableColumn<>("Surname");
        surname.setMaxWidth(100);
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        //table column for Member number
        TableColumn<TableClass, Integer> memberNo = new TableColumn<>("Member.No");
        memberNo.setMaxWidth(100);
        memberNo.setCellValueFactory(new PropertyValueFactory<>("memberNo"));

       //
        //tableView.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        // labelBox.setBackground(new Background(new BackgroundFill(Color.BISQUE,CornerRadii.EMPTY,Insets.EMPTY)));

        /**
         *
         *
         *
         * **/

        Button addNew = new Button("Add");

        Image image = new Image(getClass().getResourceAsStream("addNewMember.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        addNew.setGraphic(imageView);
        addNew.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        /***
         * Add set onAction
         */
       addNew.setOnAction(add->{
         onAddClicked();


       });


        Button deleteM = new Button("Remove");
        Button registerPoints = new Button("Register \nPoints");
        Button showDetails  = new Button("Details");

        Image image2 = new Image(getClass().getResourceAsStream("removeMember.png"));
        ImageView image2View = new ImageView(image2);
        image2View.setFitHeight(25);
        image2View.setFitWidth(25);
        deleteM.setGraphic(image2View);


        deleteM.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));

        deleteM.setOnAction(del->onDeleteClicked());

        Image image3 = new Image(getClass().getResourceAsStream("viewDetails.png"));
        ImageView image3View = new ImageView(image3);
        image3View.setFitHeight(25);
        image3View.setFitWidth(25);
        showDetails.setGraphic(image3View);


        showDetails.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));

        registerPoints.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));

        showDetails.setOnAction(show->{onSeeDetail();});


        addNew.setPrefSize(100,70);
        addNew.setStyle("-fx-border-color: red");
        deleteM.setPrefSize(100,70);
        deleteM.setStyle("-fx-border-color: red");

        showDetails.setPrefSize(100,70);
        showDetails.setStyle("-fx-border-color: red");

        registerPoints.setPrefSize(100,70);
        registerPoints.setStyle("-fx-border-color: red");


        Image image4 = new Image(getClass().getResourceAsStream("registerP.png"));
        ImageView image4View = new ImageView(image4);
        image4View.setFitHeight(25);
        image4View.setFitWidth(25);
        registerPoints.setGraphic(image4View);

        HBox adddshdel = new HBox(10);


        registerPoints.setOnAction(registerPoint->onRegisterClicked());

        adddshdel.setOpaqueInsets(new Insets(10,10,10,10));
        adddshdel.setBackground( new Background(new BackgroundFill(Color.PALEGOLDENROD,CornerRadii.EMPTY,Insets.EMPTY)));
        adddshdel.getChildren().addAll(addNew,deleteM,showDetails,registerPoints);

        /**
         * Table view
         *
         *
         */

        tableView.getOnScroll();
        tableView.getColumns().addAll(surname, firstName,memberNo);
        tableView.setItems(initiate());
        tableView.setTableMenuButtonVisible(true);


        String styles =
               // "-fx-background-color: red;"+
              "-fx-border-color:  yellow" ;

        tableView.setStyle(styles);



        Label label = new Label("Member List");
        label.setFont(Font.font("verdana", FontWeight.BOLD, 30));


        label.setMinWidth(300);
        // label.setBackground(new Background(  new  BackgroundFill(Color.BISQUE, CornerRadii.EMPTY,Insets.EMPTY)));
        HBox labelBox = new HBox(10);
        labelBox.getChildren().add(label);
        labelBox.setOpaqueInsets(new Insets(10, 10, 10, 10));
        labelBox.setFillHeight(true);


        VBox root = new VBox();
        root.setStyle(styles);
        // labelBox.setBackground(new Background(new BackgroundFill(Color.BISQUE,CornerRadii.EMPTY,Insets.EMPTY)));
        root.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        tableView.setBackground(new Background(new BackgroundFill[100]));

VBox labelTabel=new VBox(10);
//pane.setAlignment(Pos.CENTER_RIGHT);
        labelTabel.setPadding(new Insets(50,10,10,10));
labelTabel.getChildren().addAll(adddshdel);


       // primaryStage.getIcons().add(new Image("/local/icon8-airport-96.png"));

   // Image i=    new Image(getResourceAsStream("/AirLine App.png"));
  //  primaryStage.getIcons().add(i);
        root.getChildren().addAll(labelTabel,tableView, memberType); //Add tableView to VBox
/**
 * Button show
 * */

        /**
         *
         * Delete Button
         * */

        //Delete Button
        Button delete = new Button("Delete");
        //set on action
        delete.setOnAction(d ->
        {
            onDeleteClicked();
        });

        HBox hBox = new HBox();
/***
 * MENU
 * */
        seeDetail = new MenuItem("See Detail");

        deleteMember = new MenuItem(" Remove");
        registerPoint = new MenuItem("Register Point");
        update = new MenuItem("Update");
        contextMenu = new ContextMenu();

        contextMenu.getItems().addAll(seeDetail, deleteMember, registerPoint);


        surname.setContextMenu(contextMenu);

        tableView.setOnMouseClicked(click -> {
            if (click.getButton() == MouseButton.PRIMARY) {
                TableClass selectedItem = tableView.getSelectionModel().getSelectedItem();
                try {

                    if (!selectedItem.surname.equals("")) {

                        contextMenu.show(tableView, click.getSceneX(), click.getSceneY());
                        deleteMember.setOnAction(remove -> onDeleteClicked());
                        registerPoint.setOnAction(registerPoint -> onRegisterClicked());
                    seeDetail.setOnAction(see->onSeeDetail());
                        System.out.println(memberArchive.displayMembers());


                    }

                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });


/****
 * Register Points to existing members


 */

        /**
         * set Up stage and scene
         *
         * */
       // hBox.setOpaqueInsets(new Insets(100,10,10,10));
        root.setSpacing(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(hBox);

        primaryStage.setTitle("AirLine Bonus App");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();


        /*********
         *
         *
         *
         *
         */


        Label usernameL = new Label("Username:  ");
        Label passL = new Label("Password:  ");
        Button log =new Button("Log");


        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField pass = new PasswordField();
        pass.setPromptText("********");
        GridPane gridPane = new GridPane();

        gridPane.add(usernameL,0,0);
        gridPane.add(username,1,0);


        gridPane.add(passL,0,4);

        gridPane.add(pass,1,4);
        gridPane.add(log,1,5);



        gridPane.setVgap(10);
        HBox rootLog = new HBox(10);
        rootLog.getChildren().addAll(gridPane);
       rootLog.setBackground(new Background(new BackgroundFill(Color.PALEGOLDENROD,CornerRadii.EMPTY,Insets.EMPTY)));
    rootLog.setPadding(new Insets(100,10,10,10));

    rootLog.setAlignment(Pos.CENTER);

        Scene scene = new Scene(rootLog,500,500);
        Stage stageLog = new Stage();
        stageLog.setTitle("Login page");

        stageLog.setScene(scene);
        rootLog.setBackground(new Background(new BackgroundFill(Color.AZURE,CornerRadii.EMPTY,Insets.EMPTY
        )));


        primaryStage.close();
log.setOnAction(e->{

    if(username.getText().equals("bako") && pass.getText().equals("45548979")){
    primaryStage.show();
    stageLog.close();

    usernameL.setFont(Font.font("Arial",20));
        passL.setFont(Font.font("Times New Roman",25));

    }else {

        pass.setText("password");

        Text message = new Text();
        username.setStyle("-fx-border-color: red");
        pass.setStyle("-fx-border-color: red");;
        message.setText(" Username and/or password is incorrect");
        message.setStroke(Color.RED);
        message.setFont(Font.font("Times New Roman",20));
        gridPane.add(message,1,6);
        message.setLineSpacing(10);

    }
});
        stageLog.show();











    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {


    }

    /*****
     *
     *
     * ON REGISTER
     *
     * ***/












    public void onRegisterClicked() {

        //selected Item from a table


        TableClass selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem!=null) {
          for(BonusMember bm : memberArchive.displayMembers().values()) {
              if (selectedItem.memberNo == bm.getMemberNo()) {

                  inputDialog = new TextInputDialog();
                  inputDialog.setHeaderText("Register Point Dialog");
                  inputDialog.setTitle("Point registration");

                  Optional<String> response = inputDialog.showAndWait();


                  if (response.isPresent()) {


                      try {

                          int points = Integer.parseInt(response.get());
                          if (points > 0) {
                              memberArchive.checkMembers(LocalDate.now());
                              memberArchive.registerPoints(selectedItem.memberNo, points);
                              System.out.println(memberArchive.displayMembers());
                          }

                      } catch (NumberFormatException e) {
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setContentText("Invalid Input Only numbers Allowed");
                          alert.show();

                      }


                  }


              }
          }
        }
    }


    /**
     * Delete clicked
     * */
public void onDeleteClicked(){
    TableClass selectedItem = tableView.getSelectionModel().getSelectedItem();



    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);

    if(  selectedItem!=null){

        alert.showAndWait();
        if (alert.getResult()== ButtonType.OK) {
            //Remove selected Item from the table
            tableView.getItems().remove(selectedItem);
            //Remove yhe selected item from the collection(HashMap)
            memberArchive.displayMembers().remove(selectedItem.memberNo);
            Alert alert1=new Alert(Alert.AlertType.INFORMATION,
                    "You have "+" Successfully removed  "+selectedItem.surname)  ;

            alert1.show();}}


}
/****
 * SeeDetails
 * **/

public void onSeeDetail(){

    TableClass selectedItem=tableView.getSelectionModel().getSelectedItem();
    if((selectedItem!=null)){

        for (BonusMember bm:memberArchive.displayMembers().values()) {

          if(selectedItem.memberNo == bm.getMemberNo()){

                memberArchive.checkMembers(LocalDate.now());
                memberType.copy();

              String type = bm.getClass().getName().substring(7).replace("Member"," ");

                memberType.setText("Member Type: \t"+type+"\n"+"Full Name:  " +
                        "  \t"+bm.getPersonals().getSurname()+" , "+bm.getPersonals().getFirstName()+"\n"
                        +"MemberNo:    \t"+bm.getMemberNo()+"\n"+
                       "Earned Points: \t" +bm.getBonusPoints()+"\n");
                memberType.setEditable(false);




                  }


        }




                }


            



                }

public void onAddClicked()  {

    Stage stage = new Stage();
    stage.setTitle("Adding New Members");

    Label firstNameL = new Label("First Name: ");
    Label surnameL = new Label("Surname: ");
    Label e_mailL = new Label("E-mail:" );
    Label passL = new Label("Password: ");
    Label date  = new Label("Enrolled Date: ");
    DatePicker datePicker = new DatePicker();




   TextField firstName = new TextField();
   firstName.setPromptText("firstname");
   TextField surname = new TextField();
   surname.setPromptText("Surname");
    TextField e_mail = new TextField();
    e_mail.setPromptText("E-mail");
    PasswordField pass=new PasswordField();
    pass.setPromptText("*********");
   GridPane root =new GridPane();
   Button save = new Button("Save");
   Button cancel = new Button("Cancel");

   VBox vBox=new VBox();



   root.add(firstNameL,0,0);
   root.add(firstName,1,0);
   root.add(surnameL,0, 1);
   root.add(surname,1, 1);//col 1 row 1
   root.add(e_mailL,0,2);
   root.add(e_mail,1,2);
   root.add(passL,0,3);
   root.add(pass,1,3);
   root.add(date,0,4);
   root.add(datePicker,1,4);
   root.add(save,1,5);
   root.add(cancel, 2,5);
   root.setVgap(10);



   vBox.getChildren().addAll(root);
   vBox.setBackground(new Background(new BackgroundFill(Color.BISQUE,CornerRadii.EMPTY,Insets.EMPTY)));
   vBox.setAlignment(Pos.CENTER_RIGHT);
   root.setHgap(50);
   root.setPadding(new Insets(10,30,20,20));
   Scene scene = new Scene(vBox,500,500);

   //TableClass tableClass=new TableClass(11,new Personals("a",
           //"b","c","d"),LocalDate.now(),10);

save.setOnAction(toSave->{


  Personals personals=new Personals(firstName.getText(),surname.getText(),
          e_mail.getText(),pass.getText());

 int memberNumb =  memberArchive.addMember(personals,datePicker.getValue());
 TableClass tableClass = new TableClass(memberNumb, personals,datePicker.getValue(),0);
personalObservableList.removeAll(tableClass); //removes what already available in the table,helps to refresh the table
personalObservableList.addAll(tableClass);  //Adds up to dated value to the table


});

    System.out.println(memberArchive.displayMembers());

    stage.initModality((Modality.APPLICATION_MODAL));
    stage.setScene(scene);

    stage.show();

}



















public boolean isInt(TextInputDialog textField){
    try {

        return true;
    }catch (NumberFormatException nfe){
        return false;
    }

}

    public ObservableList<TableClass> initiate() {

        Personals p1= new Personals("Martin",
                "Hansen","mar@gmail.com",
                "ghhh45");


    int memberNum1=  memberArchive.addMember(p1,LocalDate.of(2020,6,8));

        Personals p2= new Personals("Abdi",
                "Bako","bako@gmail.com",
                "1234");
      int memberNum2 =  memberArchive.addMember(p2,LocalDate.of(2020,1,8));

        Personals p3= new Personals("Ola",
                "Tomas","tom@gmail.com",
                "4333h45");
     int memberNum3 =   memberArchive.addMember(p3,LocalDate.of(2019,6,8));

        Personals p4= new Personals("Hanna",
                "Hansen","han@gmail.com",
                "ghhh45");
     int memberNum4 =   memberArchive.addMember(p4,LocalDate.of(2018,11,8));

        TableClass tableClass1=new TableClass(memberNum1,p1,LocalDate.of(2020,6,8),5050);
        TableClass tableClass2=new TableClass(memberNum2,p2,LocalDate.of(2020,4,2),3050);
        TableClass tableClass3=  new TableClass(memberNum3,p3,LocalDate.of(2019,6,8),50050);
        TableClass tableClass4=  new TableClass(memberNum4,p4,LocalDate.of(2018,11,8),75000);


        personalObservableList.addAll(tableClass1,tableClass2,tableClass3,tableClass4);


        System.out.println(memberArchive.displayMembers());
        return personalObservableList;
    }




}
