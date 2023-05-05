package senarath_chami.river;

public class Agriculture extends LandArea {

    public Agriculture(int change) {
        name = "Agriculture";
        totalCost = 300;
        totalRevenue = 0;
        age = 0;
        lastChanged = change;
    }

    @Override
    public int nextMonth() {
        age++;
        int month = (age + lastChanged) % 12;
        int difference = 0;
        if (month == 4) {
            difference = -50;
            totalCost -= difference;
        } else if (month == 9 && age > 3) {
            difference = 65;
            totalRevenue += difference;
        }
        return difference;
    }

    @Override
    public String getChangeMonthInfo () {
        int time = age + lastChanged;
        int month = time % 12;
        int plus = 0;
        int neg = 0;
        if (age == 0) // first month creation
            neg = 300;
        else if (month == 4)
            neg = 50;
        else if (month == 9 && age > 3)
            plus = 65;
        return "-$" + neg + "k\n+$" + plus + "k";
    }

    @Override
    public String landInfoSection(int colNum, int rowNum) {
        int yearChange = lastChanged / 12;
        int monthChange = (lastChanged % 12) +1;
        int aYear = age / 12;
        int aMonth = (age % 12) + 1;
        return name + "\nLast changed: " + yearChange + "-" + monthChange + "\nAge: " + aYear + "-" + aMonth + "\nTotal Cost: $" + totalCost + "k\nTotal Revenue: $"+ totalRevenue + "kn\nX: " + rowNum + " Y: " + colNum;
    }
}
