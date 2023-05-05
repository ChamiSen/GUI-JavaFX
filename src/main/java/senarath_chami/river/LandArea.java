package senarath_chami.river;

public abstract class LandArea {
    protected String name;
    protected int totalCost;
    protected int totalRevenue;
    protected int age;
    protected int lastChanged;

    public LandArea() {}

    /**
     * The function returns a string that contains the name of the company, the total cost, and the total revenue
     *
     * @return The name of the company, the total cost, and the total revenue.
     */
    public String toString() {
        return "-" + name.charAt(0) + "-" + "\n-$" + totalCost + "k\n+$" + totalRevenue + "k";
    }

    /**
     * This function increments the age of the animal by one month.
     *
     * @return The age of the person.
     */
    public int nextMonth() {
        age++;
        return 0;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * > This function returns a string that contains the change in revenue and change in expenses for the current month
     *
     * @return The string "-$0k\n+$0k"
     */
    public String getChangeMonthInfo() {
        return "-$0k\n+$0k";
    }

    /**
     * This function returns a string that contains the name of the land
     *
     * @param colNum The column number of the land.
     * @param rowNum The row number of the land.
     * @return The name of the land.
     */
    public String landInfoSection(int colNum, int rowNum) {
        return "\n" + getName() + "\n";
    }
}
