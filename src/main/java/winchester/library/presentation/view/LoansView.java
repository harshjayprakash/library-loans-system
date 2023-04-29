package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.component.card.LoanCard;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

public final class LoansView extends View {

    private VBox loansList;
    private ScrollPane scrollPane;
    private ArrayList<LoanCard> loanCards;

    public LoansView(WindowBase parentWindow) {
        super(parentWindow, Views.LOANS.toString());
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.loansList = new VBox();
        this.loansList.getStyleClass().add("background-primary");
        this.loansList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.getStyleClass().add("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    @Override
    protected void initialiseControls() {
        ArrayList<Loan> loans = DataPersistenceManager.getInstance().getLoans();
        this.loanCards = new ArrayList<>();
        if (loans.isEmpty()) { return; }
        for (Loan loan : loans) {
            this.loanCards.add(new LoanCard(loan));
        }
    }

    @Override
    protected void addComponentsToView() {
        for (LoanCard card : this.loanCards) {
            this.loansList.getChildren().add(card);
        }
        this.scrollPane.setContent(this.loansList);
        this.getChildren().add(this.scrollPane);
    }
}
