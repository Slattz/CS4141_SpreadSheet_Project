/* 
 @ Classes: SpreadSheetImpl
 @ Author: Shane Slattery (19235046)
*/

import java.util.ArrayList;

public class SpreadSheetImpl {
    /* Private Member Variables */
    private ArrayList<String> m_Sheets;
    private int nextSheetNum;

    /* Private Functions */
    // Checks if the specific index is valid
    private boolean isValidIndex(int index) {
        return (index >= 0 && index < m_Sheets.size());
    }

    /* Public Functions */

    //Ctor for the class to init variables
    public SpreadSheetImpl() {
        m_Sheets = new ArrayList<String>();
        m_Sheets.add("Sheet1"); //Spreadsheet must always have at least 1 sheet, so we add it at the start
        nextSheetNum = 2;
    }

    //This function adds a new sheet to our list of sheets
    public boolean add() {
        if (m_Sheets.size() < 256) {
            m_Sheets.add("Sheet" + nextSheetNum);
            if (nextSheetNum < Integer.MAX_VALUE) { //We don't want it to be able to wrap around and break things
                nextSheetNum++;
            }
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

    public int move(String from, String to, boolean before) {
        int idxFrom = this.index(from);
        int idxTo = this.index(to);

        if (!from.equalsIgnoreCase(to) && this.isValidIndex(idxFrom) && this.isValidIndex(idxTo)) {
            String sheet = m_Sheets.remove(idxFrom); //remove returns the removed element
            idxTo += (before == true ? -1 : 1);
            m_Sheets.add(idxTo, sheet); //if idxTo is 0 and we do before, then it's -1, which breaks things?
            return idxTo;
        }

        return -1;
    }

    public String move(int from, int to, boolean before) {
        if (from != to && this.isValidIndex(from) && this.isValidIndex(to)) {
            String sheet = m_Sheets.remove(from); //remove returns the removed element
            int idxTo = to + (before == true ? -1 : 1);
            m_Sheets.add(idxTo, sheet); //if idxTo is 0 and we do before, then it's -1, which breaks things?
            return sheet;
        }

        return "";
    }

    public String moveToEnd(int from) {
        if (this.isValidIndex(from)) {
            String sheet = m_Sheets.remove(from); //remove returns the removed element
            if (m_Sheets.add(sheet)) { //appends to end
                return sheet;
            }
        }

        return "";
    }

    public int moveToEnd(String from) {
        int index = this.index(from);

        if (this.isValidIndex(index)) {
            String sheet = m_Sheets.remove(index); //remove returns the removed element
            m_Sheets.add(sheet); //appends to end
            return m_Sheets.size();
        }

        return -1;
    }

    public int rename(String currentName, String newName) {
        int index = this.index(currentName);
        if (this.isValidIndex(index) && (this.index(newName) == -1)) { //if currentName is valid and newName not found
            m_Sheets.set(index, newName);
            return index;
        }

        return -1;
    }

    //returns index position of a name in our list; -1 if not found.
    public int index(String sheetName) {
        return m_Sheets.indexOf(sheetName); //indexOf returns index of sheet; if not found, returns -1.
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