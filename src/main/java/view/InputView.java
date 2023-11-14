package view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import domain.MenuItemsList;
import domain.MenuItem;

public class InputView {
	
	private static final String ERROR_ILLEGALARGUMENT_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
	private static final String ERROR_ILLEGALARGUMENT_MESSAGE_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
	
	public int readDate() {
		while (true) {
			try {
				OutputView.printVisitDateInputText();
				String input = Console.readLine();
				validateDateInputNumber(input);
				int inputDate = Integer.parseInt(input);
				return inputDate;
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
	    List<MenuItem> menuItems = new ArrayList<>();
	    String input = Console.readLine();
	    String[] menuAndQuantity = input.split(",");

	    for (String entry : menuAndQuantity) {
	        String[] split = entry.split("-");
	        String menu = split[0].trim();
	        validateMenu(menu);
	        validateInputNumber(split[1].trim());
	        int quantity = Integer.parseInt(split[1].trim());
	        menuItems.add(new MenuItem(menu, quantity));
	    }

	    return menuItems;
	}
	
	private void validateMenu(String input) {
		for (MenuItemsList menuItem : MenuItemsList.values()) {
			if (menuItem.getName().equalsIgnoreCase(input)) {
				return;
			}
		}
		throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
	}
	
    private static void validateInputNumber(String input) {
        if (input.matches("\\d*")) 
        	return;
        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE);
    }

    private static void validateDateInputNumber(String input) {
        if (input.matches("\\d*")) 
        	return;
        throw new IllegalArgumentException(ERROR_ILLEGALARGUMENT_MESSAGE_DATE);
    }
    
}