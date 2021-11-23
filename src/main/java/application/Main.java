package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application{
    @Override
    public void start(Stage myStage) throws FileNotFoundException, Exception {
        myStage = FXMLLoader.load(this.getClass().getResource("/GUI/insert.fxml"));
        //myStage.show();

        DBConnection DbC = new DBConnection();


        /* Create tables, ma abbiamo errore sintassi */
//        CreateTable createTable = new CreateTable();
//        List<String> lines = Files.readAllLines(Paths.get(this.getClass().getResource("/Creation/makeTables.sql").toString()));
//        System.out.println(lines.get(2));
     /*
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Creation/makeTables.sql")));
            createTable.runScript(DbC.getConnection(), br);
        } catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
    */
        /* Funziona tutto ma dobbiamo produrre nuovi dati */
        InsertValues insertValues = new InsertValues();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Insert/insertion.sql")));
            insertValues.runScript(DbC.getConnection(), br);
        } catch (FileNotFoundException fe){
            fe.printStackTrace();
        }

        /* executing queries */
        TellMe tellMe = new TellMe(DbC);
        System.out.println(tellMe.actors());
        //TODO
        // Per come abbiamo modellato il DB george lucas non puo essere sia produttore che sceneggiatore
        System.out.println(tellMe.troupeMembers());

        InsertNew insertNew = new InsertNew(DbC);
        insertNew.sponsor("29218600223", "BHo SPA");
        //TODO
        // Aggiungo le row ad DB ma non mi risulta nulla sul mio DB in
        // "locale" dove si connette ??
        System.out.println(tellMe.sponsors());


        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

