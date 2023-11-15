package christmas;

import controller.Controller;
import service.CustomerService;
import service.DiscountEventService;
import service.EventApplyService;

public class Application {
    public static void main(String[] args) {
    	
        CustomerService customerService = new CustomerService();
        EventApplyService eventApplyService = new EventApplyService(customerService);
        DiscountEventService discountEventService = new DiscountEventService(customerService);

        Controller controller = new Controller(customerService, discountEventService, eventApplyService);
        controller.play();
    }
}