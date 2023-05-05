package senarath_chami.river;

public class Recreation extends LandArea {

    /**
     * Constructor for Recreation
     */
    public Recreation(int change) {
        name = "Recreation";
        totalCost = 10;
        totalRevenue = 0;
        age = 0;
        lastChanged = change;
    }

    @Override
    // A method that is called every month to update the age of the land area.
    public int nextMonth() {
        age++;
        int month = (age + lastChanged) % 12;
        int difference = 0;
        if (month == 11) {
            difference = -50;
            totalCost += difference;
        }
        return difference;
    }

    @Override
    // Returning a string that shows the change in cost and revenue for the month.
    public String getChangeMonthInfo() {
        int time = age + lastChanged;
        int month = time % 12;
        int plus = 0;
        int neg = 0;

        if (age == 0)
            neg = 10;
        else if (month == 11)
            plus = 5;
        return "-$" + neg + "k\n+$" + plus + "k";
    }

    @Override
    // This method is used to display the information about the land area.
    public String landInfoSection(int colNum, int rowNum) {
        int yearChange = lastChanged / 12;
        int monthChange = (lastChanged % 12) +1;
        int aYear = age / 12;
        int aMonth = (age % 12) + 1;
        return name + "\nLast changed: " + yearChange + "-" + monthChange + "\nAge: " + aYear + "-" + aMonth + "\nTotal Cost: $" + totalCost + "k\nTotal Revenue: $"+ totalRevenue +"kn\nX: " + rowNum + " Y: " + colNum;
    }
}
