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
public class PHomePageControllerTest {
    
    public PHomePageControllerTest() {
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
     * Test of initialize method, of class PHomePageController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        PHomePageController instance = new PHomePageController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onClickListener method, of class PHomePageController.
     */
    @Test
    public void testOnClickListener() {
        System.out.println("onClickListener");
        ActionEvent event = null;
        PHomePageController instance = new PHomePageController();
        instance.onClickListener(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFname_static method, of class PHomePageController.
     */
    @Test
    public void testGetFname_static() {
        System.out.println("getFname_static");
        String expResult = "";
        String result = PHomePageController.getFname_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFname_static method, of class PHomePageController.
     */
    @Test
    public void testSetFname_static() {
        System.out.println("setFname_static");
        String aFname_static = "";
        PHomePageController.setFname_static(aFname_static);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
