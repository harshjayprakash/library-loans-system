package winchester.library.presentation.view;

import javafx.scene.layout.BorderPane;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.window.WindowBase;

public final class ViewsManager extends BorderPane {

    public ViewsManager() {
        super();
    }

    public void showView(Views view, WindowBase parentWindow, Employee employee, Item item) {
        this.setCenter(null);
        this.setCenter(
                switch (view) {
                    case HOME -> new HomeView(parentWindow, employee);
                    case NONE -> new NoneView(parentWindow);
                    case LOANS -> new LoansView(parentWindow);
                    case LOGIN -> new LoginView(parentWindow);
                    case USERS -> new UsersView(parentWindow);
                    case SETTINGS -> new SettingsView(parentWindow);
                    case ADD_ITEM -> new AddItemView(parentWindow);
                    case REGISTER -> new RegisterView(parentWindow);
                    case ADD_USER -> new AddUserView(parentWindow);
                    case CUSTOMERS -> new CustomerView(parentWindow);
                    case INVENTORY -> new InventoryView(parentWindow);
                    case LOANING_ITEMS -> new LoaningItemView(parentWindow);
                    case CHANGE_PASSWORD -> new ChangePasswordView(parentWindow, employee);
                    case INDIVIDUAL_ITEM -> new IndividualItemView(parentWindow);
                    case INDIVIDUAL_LOAN -> new IndividualLoanView(parentWindow);
                    case NONE_WITH_SIDEBAR -> new NoneSidePaneView(parentWindow);
                    case INDIVIDUAL_CUSTOMER -> new IndividualCustomerView(parentWindow);
                    case DATABASE_CONFIGURATION -> new DatabaseConfigurationView(parentWindow);
                }
        );
    }


}
