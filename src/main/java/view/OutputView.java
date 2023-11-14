package view;

import java.text.DecimalFormat;
import java.util.List;

import domain.Customer;
import domain.DiscountEvent;
import domain.MenuItem;
import event.ChristmasDdayDiscountEvent;
import event.GiftEvent;
import event.WeekdayDiscountEvent;
import event.WeekendDiscountEvent;

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

	public static int printBeforeDiscountPrice(Customer customer) {
		System.out.println("\n<할인 전 총주문 금액>");
		int price = customer.calculateTotalPrice();
		System.out.println(formatNumber(price) + "원");
		return price;

	}

    
    public static void printEvents(List<DiscountEvent> events) {
        System.out.println("\n<혜택 내역>");
        for (DiscountEvent event : events) {
            System.out.println(event.getEventName() + ": " + formatNumber(event.getDiscountAmount()) + "원");
        }
        System.out.println();
    }


    public static void printTotalEventPrice(Customer customer) {
        int totalDiscount = DiscountEvent.calculateTotalDiscount(customer);
        System.out.println("<총혜택 금액>");
        System.out.println("-" + formatNumber(totalDiscount) + "원");
    }

    
    public static void printAfterDiscountPrice(Customer customer) {
    	
    }
    
    private static String formatNumber(int number) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(number);
    }
    
    
}
