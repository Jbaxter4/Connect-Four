/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 16070009
 */
public class ConnectFourTest {
    
    public ConnectFourTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class ConnectFour.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConnectFour.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAndFindWinner method, of class ConnectFour.
     */
    @Test
    public void testAddAndFindWinner() {
        System.out.println("addAndFindWinner");
        int player = 1;
        int col = 2;
        ConnectFour instance = new ConnectFour();
        int expResult = 2;
        int result = instance.addAndFindWinner(player, col);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of displayGrid method, of class ConnectFour.
     */
    @Test
    public void testDisplayGrid() {
        System.out.println("displayGrid");
        String title = "    Connect Four";
        ConnectFour instance = new ConnectFour();
        instance.displayGrid(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerName method, of class ConnectFour.
     */
    @Test
    public void testPlayerName() {
        System.out.println("playerName");
        int player = 1;
        ConnectFour instance = new ConnectFour();
        String expResult = "Red";
        String result = instance.playerName(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
