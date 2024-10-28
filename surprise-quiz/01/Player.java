import java.util.ArrayList;

public class Player implements ISaveable {
    private String name;
    private int hitPoints;

    public Player(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }

    @Override
    public ArrayList<String> write() {
        ArrayList<String> values = new ArrayList<>();
        values.add(this.name);
        values.add("" + this.hitPoints);
        return values;
    }

    @Override
    public void read(ArrayList<String> savedValues) {
        if (savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));
        }
    }

    @Override
    public String toString() {
        return "Player { name:'" + name + "', hitPoints: " + hitPoints + " }";
    }
}
