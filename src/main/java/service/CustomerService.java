package service;

import java.util.List;

import domain.Customer;
import domain.MenuItem;
import domain.MenuItemsList;

public class CustomerService {

    public int calculateTotalPrice(Customer customer) {
        int total = 0;
        List<MenuItem> menuItems = customer.getMenuItems();
        for (MenuItem menuItem : menuItems) {
            total += MenuItemsList.valueOf(menuItem.getMenu()).getPrice() * menuItem.getQuantity();
        }
        return total;
    }
}