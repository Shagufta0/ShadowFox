import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("John Doe");
    }

    @Test
    void testInitialBalance() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testDeposit() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    void testWithdraw() {
        account.deposit(1000);
        account.withdraw(400);
        assertEquals(600, account.getBalance());
    }

    @Test
    void testWithdrawInsufficient() {
        account.deposit(500);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600);
        });
        assertEquals("Insufficient balance", e.getMessage());
    }

    @Test
    void testTransactionHistory() {
        account.deposit(1000);
        account.withdraw(300);
        List<String> history = account.getTransactionHistory();
        assertTrue(history.contains("Deposited: ₹1000.0"));
        assertTrue(history.contains("Withdrew: ₹300.0"));
    }
}
