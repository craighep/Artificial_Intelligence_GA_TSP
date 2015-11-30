package data.types;

/**
 * Represents the different selection types.
 * Holds the name and text name of each selection method for use in the GUI.
 * @author Craig
 */
public enum SelectionType {
    TOURNAMENT("Tournament"),
    ROULETTEWHEEL("Roulette Wheel"),
    RANK("Rank");
    
    private String name = "";
    
    private SelectionType(String name){
        this.name = name;
    }
    
    /**
     * Gets the text value name of the selection type. Used primarily for the GUI
     * drop down choice of selection type.
     * @return name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Returns a selection type based on the text value name from the GUI drop-
     * down.
     * @param text Text value name of the selection type
     * @return SelectionType
     */
    public static SelectionType fromString(String text) {
    if (text != null) {
      for (SelectionType ST : SelectionType.values()) {
        if (text.equalsIgnoreCase(ST.name)) {
          return ST;
        }
      }
    }
    return null;
  }
}
