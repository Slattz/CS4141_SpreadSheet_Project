/* 
 @ Classes: SpreadSheetDriver
 @ Author: Shane Slattery (19235046)
*/

public class SpreadSheetDriver {

    /*
        Instance 1 Tests
        Show that all the functions in the spec are implemented and do what was asked
    */

    private static void addTest(SpreadSheetImpl spreadSheet) {
        int success = 0;
        System.out.printf("\n-- Add Test for Instance 1 --\n");
        System.out.printf("Let's add 7 new sheets\n");
        for (int i = 0; i < 7; i++) {
            if (spreadSheet.add()) {
                success++;
            }
        }

        System.out.printf("Added %d sheets successfully\n\n", success);
        spreadSheet.Display(); // Show spreadsheet now has 7 more sheets
    }

    private static void removeTest(SpreadSheetImpl spreadSheet) {
        System.out.printf("\n-- Remove Test for Instance 1 --\n");
        System.out.printf("Let's remove 2 sheets: The sheet at position 1 and \"Sheet2\"\n");

        int strFunc = spreadSheet.remove("ShEeT2"); //Show it works case insensitive
        String indexFunc = spreadSheet.remove(1);

        System.out.printf("Removed sheet %s by position 1 and sheet at pos %d by name \"Sheet2\"\n\n", indexFunc, strFunc);
        spreadSheet.Display(); // Show spreadsheet no longer has those sheets
    }

    private static void moveTest(SpreadSheetImpl spreadSheet) {
        System.out.printf("\n-- Move Test for Instance 1 --\n");
        System.out.printf("Let's move 2 sheets: The sheet at position 1 to before position 3 and \"Sheet5\" to after \"Sheet7\"\n");

        int strFunc = spreadSheet.move("Sheet5", "Sheet7", false);
        String indexFunc = spreadSheet.move(1, 3, true);

        System.out.printf("Moved sheet %s from position 1 to position 3 and \"Sheet5\" (position %d) to after \"Sheet7\"\n\n", 
                        indexFunc, strFunc);
        spreadSheet.Display(); // Show spreadsheet has changed since last display
    }

    private static void moveToEndTest(SpreadSheetImpl spreadSheet) {
        System.out.printf("\n-- Move To End Test for Instance 1 --\n");
        System.out.printf("Let's move 2 sheets to the end: The sheet at position 1 and \"Sheet5\"\n");

        String indexFunc = spreadSheet.moveToEnd(1);
        System.out.printf("Moved sheet %s from position 1 to end:\n",indexFunc);
        spreadSheet.Display(); // Show spreadsheet has changed since last display

        int strFunc = spreadSheet.moveToEnd("Sheet5");
        System.out.printf("Moved \"Sheet5\" from position %d to end:\n\n", strFunc);
        spreadSheet.Display(); // Show spreadsheet has changed since last display
    }

    private static void renameTest(SpreadSheetImpl spreadSheet) {
        System.out.printf("\n-- Rename Test for Instance 1 --\n");
        System.out.printf("Let's rename \"Sheet5\" to \"TimeTable\"\n");

        int index = spreadSheet.rename("Sheet5", "TimeTable");
        System.out.printf("Renamed \"Sheet5\" from position %d to \"TimeTable\"\n",index);
        spreadSheet.Display(); // Show spreadsheet has changed since last display
    }


    /*
        Instance 2 Tests
        Shows that bounds check for add and remove works as intended
        Also show that sheet name incrementing and current amount don't correlate
    */

    private static void instance2Tests(SpreadSheetImpl yaySpreadSheet) {
        System.out.printf("First, we'll add max amount of sheets and show we can't add more than the max (256)\n");
        //Show adding works and that we can have a max of 256 sheets
        for (int i = 0; i < 253; i++) {
            if (!yaySpreadSheet.add()) {
                System.out.printf("yaySpreadSheet.add() failed on sheet %d\n", i + 4);
            }
        }

        yaySpreadSheet.Display(); // Show spreadsheet has all 256 sheets
        boolean addFailed = yaySpreadSheet.add(); // Show can't add more than 256 sheets
        System.out.printf("Trying to add a 257th sheet (i.e. max+1); result from yaySpreadSheet.add() is: \"%s\" (expected value: false)\n\n",
                addFailed ? "true" : "false");


        System.out.printf("\n\nNow, we'll remove all sheets and show there must always be 1 sheet.\n");
        //Show removing works and that we can have a min of 1 sheet
        for (int i = 256; i > 1; i--) {
            if (yaySpreadSheet.remove(i) == "") {
                System.out.printf("Couldn't remove sheet %d", i);
            }
        }

        yaySpreadSheet.Display(); // Show spreadsheet now only has 1 sheet
        String removeFailed = yaySpreadSheet.remove(0);
        System.out.printf("We can't remove the last sheet using yaySpreadSheet.remove; Result is \"%s\" here (expected value: true)\n\n",
                removeFailed == "" ? "true" : "false");

        // Add a few more sheets
        for (int i = 0; i < 6; i++) {
            if (!yaySpreadSheet.add()) {
                System.out.printf("yaySpreadSheet.add() #2 failed on sheet %d", i+1);
            }
        }

        System.out.printf("\n\nWe will now show that sheet names do not correlate to the amount of sheets and their indiviual positions\n\n");
        yaySpreadSheet.Display(); // Show sheet names don't correlate to amount
        System.out.printf("As shown, there is no consistent correlation to the amount of sheets and their indiviual positions\n\n");
    }

    public static void main(String[] args) {
        SpreadSheetImpl mySpreadSheet = new SpreadSheetImpl();
        System.out.printf("\nInstance #1 Tests\nThere are initially 3 sheets:\n\n");
        mySpreadSheet.Display(); // Show spreadsheet has inital 3 sheets (and Display works)

        addTest(mySpreadSheet); //Show that adding works
        removeTest(mySpreadSheet); //Show removing by name and index works
        moveTest(mySpreadSheet); //Show moving by name and index works
        moveToEndTest(mySpreadSheet); //Show moving to end by name and index works
        renameTest(mySpreadSheet); //Show renaming works

        System.out.printf("\nCurrent Amount in SpreadSheet: %d\n", mySpreadSheet.length()); //Show getting amount of sheets works
        System.out.printf("\nSheetname of position 1: %s\n", mySpreadSheet.sheetName(1)); //Show getting sheet name by index works
        System.out.printf("\nPosition of Sheetname \"Sheet4\": %d\n\n", mySpreadSheet.index("Sheet4")); //Show getting index by sheet name works

        /* Instance 2 */
        SpreadSheetImpl yaySpreadSheet = new SpreadSheetImpl(); // Our 2nd instance of spreadsheets
        System.out.printf("\n\nInstance #2 Tests\nThere are initially 3 sheets:\n\n");
        yaySpreadSheet.Display(); // Show spreadsheet #2 has inital 3 sheets
        instance2Tests(yaySpreadSheet);

    }
}