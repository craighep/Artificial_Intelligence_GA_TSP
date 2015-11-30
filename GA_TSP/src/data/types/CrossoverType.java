package data.types;

/**
 * Represents the different selection types.
 * Holds the name and text name of each selection method for use in the GUI.
 * @author Craig
 */
public enum CrossoverType {
    ORDERED("Ordered"),
    UNIFORM("Uniform");
    
    private String name = "";
    
    private CrossoverType(String name){
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
    public static CrossoverType fromString(String text) {
    if (text != null) {
      for (CrossoverType CT : CrossoverType.values()) {
        if (text.equalsIgnoreCase(CT.name)) {
          return CT;
        }
      }
    }
    return null;
  }
}
