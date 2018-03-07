package swedbank.it.academy.app;

import swedbank.it.academy.domain.Loan;

/**
 * Created by p998feq on 2018.03.06.
 */
public interface DomainInitializer {
    Loan[] initializeLoans();
}
