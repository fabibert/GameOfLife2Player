//package UI;
//
//
//import javafx.application.Application;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static javafx.application.Application.launch;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class GolUITest extends ApplicationTest {
//    GolUI ui;
//
//    @BeforeEach
//    public void setUp(){
//        launch();
//    }
//
//    @Override
//    public void start(Stage primaryStage)  {
//        this.ui = new GolUIImpl(primaryStage);
//        this.requestPlayerName();
//    }
//
//    @Test
//    void requestPlayerName() {
//        String playerName = this.ui.requestPlayerName();
//        assertEquals("Fabio", playerName);
//    }
////
////
////    public GolUITest(Stage stage){
////        this.stage = stage;
////    }
//
////    public void start(Stage stage) throws Exception {
////        GolUITest uiTest = new GolUITest(stage);
////        this.ui = new GolUI(stage);
////        }
//
//
//
////    @Test
////    void displayState() {
////        //pass board to be implemented
////        this.ui.displayState();
////        //board.setCellToPlayer(3,3, "Fabio");
////    }
////
//
//
//    @Test
//    void getPlayerCellCreation() {
//        //get Board and clicked on cell
//    }
//
//    @Test
//    void getPlayerCellDeletion() {
//    }
//
//    @Test
//    void testTextField(){
//        //TextFieldGetData.main();
//    }
//}