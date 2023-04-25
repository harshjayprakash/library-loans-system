package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.component.Banner;
import winchester.library.presentation.component.card.UserCard;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public class UsersView extends View {

    private ScrollPane scrollPane;
    private VBox employeeList;
    private ArrayList<UserCard> customersCardList;
    private Banner banner;

    public UsersView(WindowBase parentWindow) {
        super(parentWindow, Views.USERS.toString());
        this.setSpacing(20);
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.employeeList = new VBox();
        this.employeeList.setId("background-primary");
        this.employeeList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.setId("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    @Override
    protected void initialiseControls() {
        ArrayList<Employee> employees = DatabaseInteraction.getInstance().getEmployees();
        if (employees.isEmpty()) {
            this.banner = new Banner(
                    "No Employees",
                    ""
            );
            return;
        }
        this.customersCardList = new ArrayList<>();
        for (Employee employee : employees) {
            this.customersCardList.add(new UserCard(employee));
        }
    }

    @Override
    protected void addComponentsToView() {
        if (this.banner != null) {
            this.getChildren().add(this.banner);
            return;
        }
        for (UserCard card : this.customersCardList) {
            this.employeeList.getChildren().add(card);
        }
        this.scrollPane.setContent(this.employeeList);
        this.getChildren().add(this.scrollPane);
    }

}
