/* 
 @ Classes: SpreadSheetImpl
 @ Author: Shane Slattery (19235046)
*/

import java.util.ArrayList;

public class SpreadSheetImpl {
    /* Private Member Variables */
    private ArrayList<String> m_Sheets;
    private int m_nextSheetNum;

    /* Private Functions */
    // Checks if the specific index is valid
    private boolean isValidIndex(int index) {
        return (index >= 0 && index < m_Sheets.size());
    }

    //Checks if a sheet name is valid; i.e. it's length > 1 and has only valid characters (a-z, A-Z, 0-9 and space)
    private boolean isValidSheetName(String sheetName) {
        if (sheetName.length() <= 1) { //Sheet names must be greater than 1 character
            return false;
        }

        for (int i=0; i < sheetName.length(); i++) {
            char ch = sheetName.charAt(i);
            if ((ch < '0' || ch > '9') && ch != ' ') { //if character isn't between 0-9 and not a space
                ch = (ch >= 'a' ? (char)(ch-0x20):ch); //if a lowercase letter or more, make it uppercase (-0x20)
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
        m_Sheets = new ArrayList<String>();
        //Add the default 3 sheets
        m_Sheets.add("Sheet1");
        m_Sheets.add("Sheet2");
        m_Sheets.add("Sheet3");
        m_nextSheetNum = 4;
    }

    //This function adds a new sheet to our list of sheets
    public boolean add() {
        if (m_Sheets.size() < 256) {
            m_Sheets.add("Sheet" + m_nextSheetNum);
            m_nextSheetNum++; //There's an integer overflow here
            return true;
        }

        return false;
    }

    //This function removes a specified sheet from our list of sheets, given the sheet's name
    public int remove(String sheetName) {
        if (m_Sheets.size() > 1) {
            int index = this.index(sheetName);
            if (this.isValidIndex(index)) {
               m_Sheets.remove(index);
               return index;
            }
        }

        return -1;
    }

    //This function removes a specified sheet from our list of sheets, given the sheet's index
    public String remove(int index) {
        if (this.isValidIndex(index)) {
            if (m_Sheets.size() > 1) { //Nested if, just for readability
                return m_Sheets.remove(index); //remove returns the removed element
            }
        }

        return "";
    }

    //This function moves a sheet from one position to another, given the sheet names
    public int move(String from, String to, boolean before) {
        int idxFrom = this.index(from);
        int idxTo = this.index(to);

        if (!from.equalsIgnoreCase(to) && this.isValidIndex(idxFrom) && this.isValidIndex(idxTo)) {
            String sheet = m_Sheets.remove(idxFrom); //remove returns the removed element
            idxTo += (before == true ? -1 : 1);
            if (this.isValidIndex(idxTo)) { //It can be -1 if the index of to is 0 and the 'before' boolean is set
                m_Sheets.add(idxTo, sheet);
                return idxTo;
            }
        }

        return -1;
    }

    //This function moves a sheet from one position to another, given the sheet indexes
    public String move(int from, int to, boolean before) {
        if (from != to && this.isValidIndex(from) && this.isValidIndex(to)) {
            String sheet = m_Sheets.remove(from); //remove returns the removed element
            int idxTo = to + (before == true ? -1 : 1);
            if (this.isValidIndex(idxTo)) { //It can be -1 if 'to' is 0 and the 'before' boolean is set
                m_Sheets.add(idxTo, sheet);
                return sheet;
            }
        }

        return "";
    }

    //This function moves a sheet to the end of our sheet list, using the sheet's index
    public String moveToEnd(int from) {
        if (this.isValidIndex(from)) {
            String sheet = m_Sheets.remove(from); //remove returns the removed element
            if (m_Sheets.add(sheet)) { //appends to end
                return sheet;
            }
        }

        return "";
    }

    //This function moves a sheet to the end of our sheet list, using the sheet's name
    public int moveToEnd(String from) {
        int index = this.index(from);

        if (this.isValidIndex(index)) {
            String sheet = m_Sheets.remove(index); //remove returns the removed element
            m_Sheets.add(sheet); //appends to end
            return m_Sheets.size();
        }

        return -1;
    }

    //This function allows to change the name of a sheet
    public int rename(String currentName, String newName) {
        if (this.isValidSheetName(newName)) {
            int index = this.index(currentName);
            if (this.isValidIndex(index) && (this.index(newName) == -1)) { //if currentName is valid and newName not found
                m_Sheets.set(index, newName);
                return index;
            }
        }

        return -1;
    }

    //returns index position of a name in our list; -1 if not found.
    public int index(String sheetName) {
        // Since names are case insensitive, we need to manually go through each string instead of using ArrayList.indexOf
        for (int i = 0; i < sheetName.length(); i++) {
            if (sheetName.equalsIgnoreCase(m_Sheets.get(i))) {
                return i;
            }
        }
        return -1;
    }

    //returns the name of the sheet at the specified index position; null string if not found.
    public String sheetName(int index) {
        if (this.isValidIndex(index)) {
            return m_Sheets.get(index);
        }

        return "";
    }

    //Displays all sheet names in our list.
    public void Display() {
        System.out.println("List of Sheets:");
        for (String sheet : m_Sheets) {
            System.out.println(sheet);
        }
    }

    //returns an integer value representing the number of items in our list.
    public int length() {
        return m_Sheets.size(); //ArrayList.size returns the size of our list
    }
}