/* 
 @ Classes: SpreadSheetDriver
 @ Author: Shane Slattery (19235046)
*/

public class SpreadSheetDriver {
    public static void runIntOverflow(SpreadSheetImpl mySpreadSheet) {
        for (int i = 0; i < Short.MAX_VALUE - 1; i++) {
            if (mySpreadSheet.add()) {
                mySpreadSheet.remove(1);
            }
        }

        // We are now on "Sheet-NUM"
        mySpreadSheet.add();

        for (int i = 0; i < Short.MAX_VALUE; i++) {
            if (mySpreadSheet.add()) {
                mySpreadSheet.remove(1);
            }
        }

        mySpreadSheet.add();
        mySpreadSheet.add();
        // We now have a dupe of Sheet1
        mySpreadSheet.Display();
    }

    public static void runArrayOOB(SpreadSheetImpl mySpreadSheet) {
        mySpreadSheet.add(); //Sheet2
        mySpreadSheet.add(); //Sheet3
        mySpreadSheet.add(); //Sheet4
        // Show sheets
        mySpreadSheet.Display();
        mySpreadSheet.move(3, 0, true); //lol we get an exception here for out of bounds :(
        mySpreadSheet.Display();
    }

    public static void main(String[] args) {
        SpreadSheetImpl mySpreadSheet = new SpreadSheetImpl();
        mySpreadSheet.Display();

        //runIntOverflow(mySpreadSheet);
        runArrayOOB(mySpreadSheet);
    }
}