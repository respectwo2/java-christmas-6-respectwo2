package view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import domain.MenuItem;

public class InputView {
	public int readDate() {
		System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

		String input = Console.readLine();

		int inputDate = Integer.parseInt(input);

		return inputDate;
	}

	public List<MenuItem> readMenu() {
		System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

		List<MenuItem> menuItems = new ArrayList<>();

		String input = Console.readLine();

		String[] menuAndQuantitiy = input.split(",");

		for (String entry : menuAndQuantitiy) {
			String[] split = entry.split("-");
			String menu = split[0].trim();
			int quantity = Integer.parseInt(split[1].trim());

			menuItems.add(new MenuItem(menu, quantity));
		}
		return menuItems;
	}
}
