package winchester.library.presentation.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

import java.util.ArrayList;

public final class LoansView extends View {

    private VBox loansList;
    private ScrollPane scrollPane;

    public LoansView(WindowBase parentWindow) {
        super(parentWindow, Views.LOANS.toString());
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.loansList = new VBox();
        this.loansList.setId("background-primary");
        this.loansList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.setId("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    @Override
    protected void initialiseControls() {
        ArrayList<Loan> loan = DatabaseInteraction.getInstance().getLoans();
    }

    @Override
    protected void addComponentsToView() {
        this.scrollPane.setContent(this.loansList);
        this.getChildren().add(this.scrollPane);
    }
}
