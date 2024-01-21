import org.example.Cook;
import org.example.Cooking;
import org.example.Menu;
import org.example.MenuItem;

public class Customer {
    public void order(String menuName, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(menuName);
        Cook cook = cooking.makeCook(menuItem);

    };
}
