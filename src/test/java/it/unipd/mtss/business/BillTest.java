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
        itemsOrdered.add(new EItem(EItem.item.Scheda_Madre, "Asrock", 267.35));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Intel", 156.43));
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "Razer", 98.99));

        assertEquals(522.77, bill.getOrderPrice(itemsOrdered, user), 1e-4);
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
            bill.getOrderPrice(itemsOrdered, null);
        }catch (BillException exc){
            assertEquals("L'utente inserito è uguale a null", exc.getMessage());
        }
    }

    @Test
    public void sconto5Processori() throws BillException{
        itemsOrdered.add(new EItem(EItem.item.Processore, "Intel Core i5-9500", 192.40));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Amd 7452", 2552.30));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Amd Ryzen Box 3500", 166.20));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Amd Ryzen 5 5500", 134.10));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Intel Box Core i5 5-12600KF", 262.40));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Amd Ryzen 9 5950X", 516));

        assertEquals(3756.35, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void giftCheapestMouse() throws BillException{
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Roccat Burst Core", 34));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Razer Viper 8K", 69.42));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Corsair Dark Core RGB Pro Wireless", 110.50));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Steelseries Rival 5", 44.20));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Razer Basilisk V3", 62.42));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Razer Basilisk X Hyperspeed", 50));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Corsair Sabre Pro", 44.90));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Logitech G305 Lightspeed", 33.40));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Cooler Master MM720", 32));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Razer Viper Ultimate", 120.50));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Asus Rog Spatha", 132));

        assertEquals(701.34, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }


    @Test
    public void scontoUgualeMouseTastiere() throws BillException{
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Roccat Burst Core", 34));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Razer Viper 8K", 69.42));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Corsair Dark Core RGB Pro Wireless", 110.50));
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "MSI Vigor GK20", 24));
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "Cooler Master CK530 V2", 86.30));
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "Logitech G915 LIGHTSPEED TKL", 269.40));

        assertEquals(569.62, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void scontoSu1000Euro() throws BillException{
        itemsOrdered.add(new EItem(EItem.item.Tastiera, "Logitech G915 LIGHTSPEED TKL", 269.40));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Intel Box Core i5 5-12600KF", 262.40));
        itemsOrdered.add(new EItem(EItem.item.Processore, "Amd 7452", 2552.30));
        assertEquals(2775.69, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }
}