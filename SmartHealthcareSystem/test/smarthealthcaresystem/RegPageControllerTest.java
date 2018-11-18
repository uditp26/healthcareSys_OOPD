/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author protagonist26
 */
public class RegPageControllerTest {
    
    public RegPageControllerTest() {
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
     * Test of initialize method, of class RegPageController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        RegPageController instance = new RegPageController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onClickListener method, of class RegPageController.
     */
    @Test
    public void testOnClickListener() {
        System.out.println("onClickListener");
        ActionEvent event = null;
        RegPageController instance = new RegPageController();
        instance.onClickListener(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setfname_static method, of class RegPageController.
     */
    @Test
    public void testSetfname_static() {
        System.out.println("setfname_static");
        String fname = "";
        RegPageController instance = new RegPageController();
        instance.setfname_static(fname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getfname_static method, of class RegPageController.
     */
    @Test
    public void testGetfname_static() {
        System.out.println("getfname_static");
        RegPageController instance = new RegPageController();
        String expResult = "";
        String result = instance.getfname_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone_static method, of class RegPageController.
     */
    @Test
    public void testGetPhone_static() {
        System.out.println("getPhone_static");
        String expResult = "";
        String result = RegPageController.getPhone_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhone_static method, of class RegPageController.
     */
    @Test
    public void testSetPhone_static() {
        System.out.println("setPhone_static");
        String aPhone_static = "";
        RegPageController.setPhone_static(aPhone_static);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
