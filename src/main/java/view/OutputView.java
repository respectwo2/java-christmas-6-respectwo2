package view;

import java.text.DecimalFormat;
import java.util.List;

import domain.Customer;
import domain.DiscountEvent;
import domain.MenuItem;
import event.GiftEvent;

public class OutputView {

	private static final String ERROR_MESSAGE_TEXT = "[ERROR] %s\n";
	private static final String MONTH_TEXT = "12월";
	private static final String NONE_TEXT = "없음";

	private static final String INPUTDATE_TEXT = "중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
	private static final String INPUTMENU_TEXT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
	private static final String EVENT_NOTICE_MESSAGE = "%d일에 우테코에서 받을 이벤트 혜택 미리 보기!";

	public static void printErrorMessage(String errorMessage) {
		System.out.printf(ERROR_MESSAGE_TEXT, errorMessage);
	}

	public static void printHello() {
		System.out.println("안녕하세요! 우테코 식당 " + MONTH_TEXT + " 이벤트 플래너입니다.");

	}

	public static void printVisitDateInputText() {
		System.out.println(MONTH_TEXT + " " + INPUTDATE_TEXT);
	}

	public static void printMenuItemInputText() {
		System.out.println(INPUTMENU_TEXT);
	}

	public static void printNotice(Customer customer) {
		int date = customer.getVisitDate();
		System.out.println(MONTH_TEXT + " " + String.format(EVENT_NOTICE_MESSAGE, date));
	}

	public static void printMenu(Customer customer) {
		System.out.println("\n<주문 메뉴>");
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
		if (events.isEmpty()) {
			System.out.println(NONE_TEXT);
			return;
		}
		for (DiscountEvent event : events) {
			System.out.println(event.getEventName() + ": " + formatNumber(-(event.getDiscountAmount())) + "원");
		}
	}

	public static void printTotalEventPrice(Customer customer) {
		int totalDiscount = DiscountEvent.calculateTotalDiscount(customer);
		System.out.println("\n<총혜택 금액>");
		System.out.println(formatNumber(-totalDiscount) + "원");
	}

	private static String formatNumber(int number) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(number);
	}

	public static void printAfterDiscountPrice(Customer customer) {
		int price = customer.calculateTotalPrice();
		int totalDiscount = DiscountEvent.calculateTotalDiscount(customer);

		System.out.println("\n<할인 후 예상 결제 금액>");
		System.out.println(formatNumber(price - totalDiscount) + "원");
	}

	public static void printGiftMenu(Customer customer) {
		List<DiscountEvent> events = customer.getEvents();
		String giftItems = getGiftTarget(events);

		System.out.println("\n<증정 메뉴>");

		if (giftItems != null) {
			System.out.println(giftItems + "개");
			return;
		}
		System.out.println(NONE_TEXT);
	}

	public static void printEventBadge(Customer customer) {
		System.out.println("\n<" + MONTH_TEXT + " 이벤트 배지>");
		String badge = customer.getBadge();
		if (badge == null) {
			System.out.println(NONE_TEXT);
			return;
		}
		System.out.println(badge);
	}

	private static String getGiftTarget(List<DiscountEvent> events) {
		for (DiscountEvent event : events) {
			if (event instanceof GiftEvent) {
				return ((GiftEvent) event).getGiftTarget();
			}
		}
		return null;
	}

}