package senarath_chami.river;

public class Unused extends LandArea {

    /**
     * Constructor for Unused
     */
    public Unused(int change) {
        name = "Unused";
        totalCost = 0;
        totalRevenue = 0;
        age = 0;
        lastChanged = change;
    }

    @Override
    // This is a method that returns a string that contains the name of the land, the last time it was changed, the age of
    // the land, the total cost, the total revenue, and the coordinates of the land.
    public String landInfoSection(int colNum, int rowNum) {
        int yearChange = lastChanged / 12;
        int monthChange = (lastChanged % 12) +1;
        int aYear = age / 12;
        int aMonth = (age % 12) + 1;
        return name + "\nLast changed: " + yearChange + "-" + monthChange + "\nAge: " + aYear + "-" + aMonth + "\nTotal Cost: $0k\nTotal Revenue: $0k\nX: " + rowNum + " Y: " + colNum;
    }
}
