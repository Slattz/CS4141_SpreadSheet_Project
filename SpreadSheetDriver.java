/* 
 @ Classes: SpreadSheetDriver
 @ Author: Shane Slattery (19235046)
*/

public class SpreadSheetDriver {
    private static void addTest(SpreadSheetImpl mySpreadSheet) {
        int success = 0;
        System.out.printf("\n-- Add Test for Instance 1 --\n");
        System.out.printf("Let's add 7 new sheets\n");
        for (int i = 0; i < 7; i++) {
            if (mySpreadSheet.add()) {
                success++;
            }
        }

        System.out.printf("Added %d sheets successfully\n\n", success);
        mySpreadSheet.Display(); // Show spreadsheet now has 7 more sheets
    }

    private static void removeTest(SpreadSheetImpl mySpreadSheet) {
        System.out.printf("\n-- Remove Test for Instance 1 --\n");
        System.out.printf("Let's remove 2 sheets: The sheet at index 0 and \"Sheet2\"\n");

        String indexFunc = mySpreadSheet.remove(0);
        int strFunc = mySpreadSheet.remove("Sheet2");

        System.out.printf("Removed sheet %s by index 0 and sheet index %d by name \"Sheet2\"\n\n", indexFunc, strFunc);
        mySpreadSheet.Display(); // Show spreadsheet no longer has those sheets
    }

    private static void moveTest(SpreadSheetImpl mySpreadSheet) {
        System.out.printf("\n-- Move Test for Instance 1 --\n");
        System.out.printf("Let's move 2 sheets: The sheet at index 0 to before index 2 and \"Sheet5\" to after \"Sheet6\"\n");

        String indexFunc = mySpreadSheet.move(0, 2, true);
        int strFunc = mySpreadSheet.move("Sheet5", "Sheet6", false);

        System.out.printf("Moved sheet %s from index 0 to before 2 and \"Sheet5\" (index %d) to after \"Sheet6\"\n\n", 
                        indexFunc, strFunc);
        mySpreadSheet.Display(); // Show spreadsheet has changed since last display
    }

    private static void moveToEndTest(SpreadSheetImpl mySpreadSheet) {
        System.out.printf("\n-- Move To End Test for Instance 1 --\n");
        System.out.printf("Let's move 2 sheets to the end: The sheet at index 0 and \"Sheet5\"\n");

        String indexFunc = mySpreadSheet.moveToEnd(0);
        System.out.printf("Moved sheet %s from index 0 to end:\n",indexFunc);
        mySpreadSheet.Display(); // Show spreadsheet has changed since last display

        int strFunc = mySpreadSheet.moveToEnd("Sheet5");
        System.out.printf("Moved \"Sheet5\" from index %d to end:\n\n", strFunc);
        mySpreadSheet.Display(); // Show spreadsheet has changed since last display
    }

    private static void renameTest(SpreadSheetImpl mySpreadSheet) {
        System.out.printf("\n-- Rename Test for Instance 1 --\n");
        System.out.printf("Let's rename \"Sheet5\" to \"JavaIsEw\"\n");

        int index = mySpreadSheet.rename("Sheet5", "JavaIsEw");
        System.out.printf("Renamed \"Sheet5\" from index %d to \"JavaIsEw\"\n",index);
        mySpreadSheet.Display(); // Show spreadsheet has changed since last display
    }

    public static void main(String[] args) {
        SpreadSheetImpl mySpreadSheet = new SpreadSheetImpl();
        mySpreadSheet.Display(); // Show spreadsheet has inital 3 sheets

        addTest(mySpreadSheet); //Show that adding works
        removeTest(mySpreadSheet); //Show removing by name and index works
        moveTest(mySpreadSheet); //Show moving by name and index works
        moveToEndTest(mySpreadSheet); //Show moving to end by name and index works
        renameTest(mySpreadSheet); //Show renaming works

        System.out.printf("\nCurrent Amount in SpreadSheet: %d\n", mySpreadSheet.length());
        System.out.printf("\nSheetname of index 0: %s\n", mySpreadSheet.sheetName(0));
        System.out.printf("\nIndex of Sheetname \"Sheet4\": %d\n", mySpreadSheet.index("Sheet4"));
    }
}