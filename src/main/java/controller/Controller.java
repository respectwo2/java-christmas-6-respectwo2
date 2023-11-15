package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.DiscountEvent;
import domain.MenuItem;
import view.InputView;
import view.OutputView;
import event.ChristmasDdayDiscountEvent;
import event.GiftEvent;
import event.WeekdayDiscountEvent;
import event.WeekendDiscountEvent;
import service.CustomerService;
import service.DiscountEventService;
import service.EventApplyService;

public class Controller {
    private final CustomerService customerService;
    private final DiscountEventService discountEventService;
    private final EventApplyService eventApplyService;

    public Controller(CustomerService customerService, DiscountEventService discountEventService, EventApplyService eventApplyService) {
        this.customerService = customerService;
        this.discountEventService = discountEventService;
        this.eventApplyService = eventApplyService;
    }
    
    public void play() {
        OutputView.printHello();
        EventApplyService eventApplyService = new EventApplyService(customerService);
        Customer customer = generateCustomer(eventApplyService);
        OutputView.printNotice(customer);
        OutputView.printMenu(customer);
        OutputView.printBeforeDiscountPrice(customer, customerService);
        OutputView.printGiftMenu(customer);
        OutputView.printEvents(customer.getEvents());
        OutputView.printTotalEventPrice(customer, discountEventService);
        OutputView.printAfterDiscountPrice(customer, discountEventService);
        OutputView.printEventBadge(customer, discountEventService);
    }

    private Customer generateCustomer(EventApplyService eventApplyService) {
        InputView input = new InputView();
        int visitDate = input.readDate();
        List<MenuItem> menus = input.readMenu();
        Customer customer = new Customer(visitDate, menus, new ArrayList<>());
        eventApplyService.eventApply(customer);
        return customer;
    }
}

