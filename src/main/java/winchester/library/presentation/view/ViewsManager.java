package winchester.library.presentation.view;

import javafx.scene.layout.BorderPane;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.window.WindowBase;

public class ViewsManager extends BorderPane {

    public ViewsManager() {
        super();
    }

    public void showView(Views view, WindowBase parentWindow, Employee employee) {
        this.setCenter(null);
        this.setCenter(
                switch (view) {
                    case HOME -> new HomeView();
                    case NONE -> new NoneView();
                    case LOANS -> new LoansView();
                    case LOGIN -> new LoginView(parentWindow);
                    case USERS -> new UsersView();
                    case ADD_ITEM -> new AddItemView();
                    case REGISTER -> new RegisterView(parentWindow);
                    case ADD_USER -> new AddUserView();
                    case CUSTOMERS -> new CustomerView();
                    case INVENTORY -> new InventoryView();
                    case LOANING_ITEMS -> new LoaningItemView();
                    case CHANGE_PASSWORD -> new ChangePasswordView();
                    case INDIVIDUAL_ITEM -> new IndividualItemView();
                    case INDIVIDUAL_LOAN -> new IndividualLoanView();
                    case NONE_WITH_SIDEBAR -> new NoneSidePaneView();
                    case INDIVIDUAL_CUSTOMER -> new IndividualCustomerView();
                    case DATABASE_INFORMATION -> new DatabaseInformationView();
                    case DATABASE_CONFIGURATION -> new DatabaseConfigurationView(parentWindow);
                }
        );
    }
}
