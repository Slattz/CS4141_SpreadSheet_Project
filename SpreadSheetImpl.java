/* 
 @ Classes: SpreadSheetImpl
 @ Author: Shane Slattery (19235046)
*/

public class SpreadSheetImpl {
    /* Private Member Variables */
    private String[] m_Sheets;
    private int m_nextSheetNum;
    private int m_sheetAmount;

    /* Private Functions */
    // Checks if the specific index is valid
    private boolean isValidIndex(int index) {
        return (index >= 0 && index < m_Sheets.length);
    }

    private boolean sheetExists(String sheetName) {
        int idx = this.index(sheetName);
        return this.isValidIndex(idx) && (idx <= m_sheetAmount);
    }

    //Common function to remove from and fix our array of sheet names
    private boolean popSheet(int index) {
        if (!isValidIndex(index)) {
            return false;
        }

        m_Sheets[index] = null; //Mark for deletion
        for (int i = index; i < m_Sheets.length-1; i++) {
            m_Sheets[i] = m_Sheets[i+1];
            m_Sheets[i+1] = null; //Needed as otherwise we'd have a dupe of the last sheet we move
        }
        m_sheetAmount--;
        return true;
    }

    //Common function to add to and fix our array of sheet names
    private boolean pushSheet(String sheetName, int index) {
        if (index == -1) { // i.e. add to end
            m_Sheets[m_sheetAmount] = sheetName;
            m_sheetAmount++;
            return true;
        }

        if (isValidIndex(index)) {
            /* Moving to certain position, need to fix array*/
            for (int i = m_Sheets.length-2; i >= index; i--) { //move all indexs up by one to 'slot' our name in
                m_Sheets[i+1] = m_Sheets[i];
                m_Sheets[i] = null; // Needed as otherwise we'd have a dupe of the last sheet we move
            }
            m_Sheets[index] = sheetName;
            m_sheetAmount++;
            return true;
        }

        return false;
    }

    //Checks if a sheet name is valid; i.e. it's length > 1 and has only valid characters (a-z, A-Z, 0-9 and space)
    private boolean isValidSheetName(String sheetName) {
        if (sheetName.length() <= 1) { //Sheet names must be greater than 1 character
            return false;
        }

        for (int i=0; i < sheetName.length(); i++) {
            char ch = sheetName.charAt(i);
            if ((ch < '0' || ch > '9') && ch != ' ') { //if character isn't between 0-9 and not a space
                ch = Character.toUpperCase(ch);
                if (ch < 'A' || ch > 'Z') { //if character isn't between A-Z
                    return false;
                }
            }
        }

        return true;
    }

    /* Public Functions */

    //Ctor for the class to init variables
    public SpreadSheetImpl() {
        m_Sheets = new String[256];
        for (int i = 0; i < m_Sheets.length; i++) {
            m_Sheets[i] = null; //Set every index in array to null
        }

        //Add the default 3 sheets
        m_Sheets[0] = "Sheet1";
        m_Sheets[1] = "Sheet2";
        m_Sheets[2] = "Sheet3";
        m_nextSheetNum = 4;
        m_sheetAmount = 3;
    }

    //This function adds a new sheet to our list of sheets
    public boolean add() {
        if (m_sheetAmount < 256) {
            String nextName = "Sheet" + m_nextSheetNum;
            while (this.sheetExists(nextName)) { //To avoid dupes incase a user renames to what our next sheet will be called
                m_nextSheetNum++;
                nextName = "Sheet" + m_nextSheetNum;
            }

            if (this.pushSheet(nextName, -1)) {
                m_nextSheetNum++; //There's a theoretical integer overflow here
                return true;
            }
        }

        return false;
    }

    //This function removes a specified sheet from our list of sheets, given the sheet's name
    public int remove(String sheetName) {
        if (m_sheetAmount > 1) {
            int index = this.index(sheetName);
            if (this.popSheet(index)) { //Also checks if sheet is valid
                return index;
            }
        }

        return -1;
    }

    //This function removes a specified sheet from our list of sheets, given the sheet's index
    public String remove(int index) {
        String sheet = sheetName(index); //Get sheetname before removed
        if (m_sheetAmount > 1 && this.popSheet(index)) { // popSheet also checks if sheet is valid
            return sheet;
        }

        return "";
    }

    //This function moves a sheet from one position to another, given the sheet names
    public int move(String from, String to, boolean before) {
        int idxFrom = this.index(from);
        int idxTo = this.index(to);

        if (!from.equalsIgnoreCase(to) && this.isValidIndex(idxFrom) && this.isValidIndex(idxTo)) {
            if (this.popSheet(idxFrom)) {
                idxTo += (before == true ? -1 : 0);
                if (this.isValidIndex(idxTo)) { //It can be -1 if the index of to is 0 and the 'before' boolean is set
                    this.pushSheet(from, idxTo);
                    return idxTo;
                }
            }
        }

        return -1;
    }

    //This function moves a sheet from one position to another, given the sheet indexes
    public String move(int from, int to, boolean before) {
        if (from != to && this.isValidIndex(from) && this.isValidIndex(to)) {
            String sheet = this.sheetName(from);
            if (this.popSheet(from)) {
                int idxTo = to + (before == true ? -1 : 1);
                if (this.isValidIndex(idxTo)) { //It can be -1 if 'to' is 0 and the 'before' boolean is set
                    this.pushSheet(sheet, idxTo);
                    return sheet;
                }
            }
        }

        return "";
    }

    //This function moves a sheet to the end of our sheet list, using the sheet's index
    public String moveToEnd(int from) {
        if (this.isValidIndex(from)) {
            String sheet = this.sheetName(from);
            if (this.popSheet(from)) {
                if (this.pushSheet(sheet, -1)) {
                    return sheet;
                }
            }
        }

        return "";
    }

    //This function moves a sheet to the end of our sheet list, using the sheet's name
    public int moveToEnd(String from) {
        int index = this.index(from);

        if (this.isValidIndex(index)) {
            if (this.popSheet(index)) {
                if (this.pushSheet(from, -1)) {
                    return index;
                }
            }
        }

        return -1;
    }

    //This function allows to change the name of a sheet
    public int rename(String currentName, String newName) {
        if (this.isValidSheetName(newName)) {
            int index = this.index(currentName);
            if (this.isValidIndex(index) && (!this.sheetExists(newName))) { //if currentName is valid and newName not found
                m_Sheets[index] = newName;
                return index;
            }
        }

        return -1;
    }

    //returns index position of a name in our list; -1 if not found.
    public int index(String sheetName) {
        // Since names are case insensitive, we need to manually go through each string instead of using ArrayList.indexOf
        for (int i = 0; i < m_Sheets.length; i++) {
            if (sheetName.equalsIgnoreCase(m_Sheets[i])) {
                return i;
            }
        }
        return -1;
    }

    //returns the name of the sheet at the specified index position; null string if not found.
    public String sheetName(int index) {
        if (this.isValidIndex(index) && m_Sheets[index] != null) {
            return m_Sheets[index];
        }

        return "";
    }

    //Displays all sheet names in our list (along with their indexes)
    public void Display() {
        System.out.println("--List of Sheets--\nIndex\tName\n");
        for (int i = 0; i < m_Sheets.length; i++) {
            if (m_Sheets[i] != null) {
                System.out.printf("%03d:\t%s\n", i, m_Sheets[i]);
            }
        }
        System.out.println();
    }

    //returns an integer value representing the number of items in our list.
    public int length() {
        return m_sheetAmount;
    }
}