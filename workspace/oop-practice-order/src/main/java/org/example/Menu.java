package org.example;

import java.util.List;
import java.util.Objects;

public class Menu {


    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
        return this.menuItems.stream().filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 이름 입니다."));
        //return new MenuItem("돈까스", 5000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(menuItems, menu.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItems);
    }
}
