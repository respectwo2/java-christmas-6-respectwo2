package view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import domain.MenuItemsList;
import domain.MenuItem;

public class InputView {

	private static final String ERROR_ILLEGALARGUMENT_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
	private static final String ERROR_ILLEGALARGUMENT_MESSAGE_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

	private static final int MIN_DATE = 1;
	private static final int MAX_DATE = 31;
	private static final int MAX_ORDER_QUANTITY = 20;

	public int readDate() {
		while (true) {
			try {
				OutputView.printVisitDateInputText();
				String input = Console.readLine();
				validateDate(input);
				int date = Integer.parseInt(input);
				return date;
			} catch (IllegalArgumentException e) {
				OutputView.printErrorMessage(e.getMessage());
			}
		}
	}

	public List<MenuItem> readMenu() {
		while (true) {
			try {
				OutputView.printMenuItemInputText();
				List<MenuItem> menuItems = readMenuItems();
                return menuItems;
			} catch (IllegalArgumentException e) {
				OutputView.printErrorMessage(e.getMessage());
			}
		}
	}

	private List<MenuItem> readMenuItems() {
	    String input = Console.readLine();
	    String[] menuAndQuantity = input.split(",");
	    validateMenuAndQuantity(menuAndQuantity);
	    List<MenuItem> menuItems = createMenuItems(menuAndQuantity);

	    if (!validateBeverageOrder(menuItems)) {
	        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
	    }

	    return menuItems;
	}

	private List<MenuItem> createMenuItems(String[] menuAndQuantity) {
		List<MenuItem> menuItems = new ArrayList<>();
		
		for (String entry : menuAndQuantity) {
			String[] split = entry.split("-");
			validateMenuItem(split[0].trim());
			int quantity = Integer.parseInt(split[1].trim());
			validateQuantity(quantity);
			menuItems.add(new MenuItem(split[0].trim(), quantity));
		}
		return menuItems;
	}


	private void validateMenuAndQuantity(String[] menuAndQuantity) {
	    for (String entry : menuAndQuantity) {
	        String[] split = entry.split("-");
	        String menu = split[0].trim();
	        validateMenuItem(menu);

	        String quantityStr = split[1].trim();
	        validateParseInt(quantityStr);
	    }
	}

	private void validateParseInt(String input) {
	    if (!input.matches("\\d+")) {
	        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
	    }
	}

	private void validateDate(String input) {
	    if (!input.matches("\\d+")) {
	        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE_DATE);
	    }
	    int date = Integer.parseInt(input);
	    if (date < MIN_DATE || date > MAX_DATE) {
	        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE_DATE);
	    }
	}

    private void validateMenuItem(String input) {
        for (MenuItemsList menuItem : MenuItemsList.values()) {
            if (menuItem.getName().equalsIgnoreCase(input)) {
                return;
            }
        }
        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
    }
    

    private void validateQuantity(int quantity) {
        if (quantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
        }
    }
    
    
    private boolean validateBeverageOrder(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            MenuItemsList menuItemsList = MenuItemsList.valueOf(menuItem.getMenu());
            if (!menuItemsList.getType().equals("음료")) {
                return true;
            }
        }
        return false;
    }

}