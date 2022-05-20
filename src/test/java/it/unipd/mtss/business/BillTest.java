////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillTest {
    BillImplemented bill = null;
    User user = null;
    List<EItem> itemsOrdered = null;

    @Before
    public void initialize(){
        bill = new BillImplemented();
        itemsOrdered = new ArrayList<EItem>();
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, 29));
    }

    @Test
    public void testSomma() throws BillException{
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Logitech", 60.50));
        itemsOrdered.add(new EItem(EItem.item.Scheda_Madre, "Asrock", 267.35));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Intel", 156.43));
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "Razer", 98.99));

        assertEquals(583.27, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }


    @Test
    public void testUnElementoNull(){
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Logitech", 60.50));
        itemsOrdered.add(null);
        itemsOrdered.add(new EItem(EItem.item.Scheda_Madre, "Asrock", 267.35));


        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch (BillException exc){
            assertEquals("La lista degli item ordinati contiene un valore null", exc.getMessage());
        }
    }

    @Test
    public void testNullNellaLista() throws  BillException{
        itemsOrdered = null;

        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch(BillException exc){
            assertEquals("La lista degli item ordinati è uguale a null", exc.getMessage());
        }
    }

    @Test
    public void testUserNull(){
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Logitech", 60.50));
        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch (BillException exc){
            assertEquals("L'utente inserito è uguale a null", exc.getMessage());
        }

    }
}