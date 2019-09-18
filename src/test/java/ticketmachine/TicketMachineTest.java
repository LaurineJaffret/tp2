package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        //S3 ne pas imprimer le tiquer si montant insuffisant
        public void noImprimeSiNoMontant(){
            
            assertFalse("imprime un montant alors que ça devrais pas",machine.printTicket());
        }
        
        @Test
        //S4
        public void imprimeSiMontant(){
            machine.insertMoney(50);
            assertTrue("imprime pas montant alors que ça devrais",machine.printTicket());
        }
        
        @Test
        //S5
        public void decrementeBalance(){
            machine.insertMoney(50);
            machine.printTicket();
            assertEquals("ne décrémente pas",0,machine.getBalance());
        }
        
        @Test
        //S6
        public void majMontantCollecte(){
            int a=machine.getTotal();
            machine.insertMoney(50);
            assertEquals(a,machine.getTotal());
            machine.printTicket();
            assertEquals(a+50,machine.getTotal());
        }
        
        @Test
        //S7 et S8
        public void rendMoney(){
            machine.insertMoney(20);
            assertEquals(20,machine.refund());
            assertEquals(0,machine.getBalance());
        }
        
        @Test(expected=IllegalArgumentException.class)
        //S9
        public void montantNegatif(){
            machine.insertMoney(-1);
        }
        
        @Test(expected=IllegalArgumentException.class)
        //S10
        public void ticketsNegatif(){
            machine = new TicketMachine(-1);
        }
        

}
