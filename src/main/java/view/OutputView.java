package view;

import domain.Customer;
import domain.MenuItem;

public class OutputView {
	private static final String ERROR_MESSAGE_TEXT = "[ERROR] %s\n";
	private static final String INPUTDATE_TEXT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
	private static final String INPUTMENU_TEXT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static void printErrorMessage(String errorMessage) {
        System.out.printf(ERROR_MESSAGE_TEXT, errorMessage);
    }
    

	public static void printVisitDateInputText() {
		System.out.println(INPUTDATE_TEXT);
	}

	public static void printMenuItemInputText() {
		System.out.println(INPUTMENU_TEXT);		
	}
	
    public static void printMenu(Customer customer) {
        System.out.println("<주문 메뉴>");	
        for (MenuItem menuItem : customer.getMenuItems()) {
            System.out.println(menuItem.getMenu() + " " + menuItem.getQuantity() + "개");
        }
    }

}
