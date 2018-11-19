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
public class SHSControllerTest {
    
    public SHSControllerTest() {
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
     * Test of initialize method, of class SHSController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        SHSController instance = new SHSController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of onClickListener method, of class SHSController.
     */
    @Test
    public void testOnClickListener() {
        System.out.println("onClickListener");
        ActionEvent event = null;
        SHSController instance = new SHSController();
        instance.onClickListener(event);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUname_static method, of class SHSController.
     */
    @Test
    public void testGetUname_static() {
        System.out.println("getUname_static");
        String expResult = "";
        String result = SHSController.getUname_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUname_static method, of class SHSController.
     */
    @Test
    public void testSetUname_static() {
        System.out.println("setUname_static");
        String aUname_static = "";
        SHSController.setUname_static(aUname_static);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDid_static method, of class SHSController.
     */
    @Test
    public void testGetDid_static() {
        System.out.println("getDid_static");
        String expResult = "";
        String result = SHSController.getDid_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDid_static method, of class SHSController.
     */
    @Test
    public void testSetDid_static() {
        System.out.println("setDid_static");
        String aDid_static = "";
        SHSController.setDid_static(aDid_static);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDname_static method, of class SHSController.
     */
    @Test
    public void testGetDname_static() {
        System.out.println("getDname_static");
        String expResult = "";
        String result = SHSController.getDname_static();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDname_static method, of class SHSController.
     */
    @Test
    public void testSetDname_static() {
        System.out.println("setDname_static");
        String aDname_static = "";
        SHSController.setDname_static(aDname_static);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
