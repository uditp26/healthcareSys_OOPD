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
public class RegPage2ControllerTest {
    
    public RegPage2ControllerTest() {
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
    
    @Test
    public void testcheckURecords() throws Exception {
        System.out.println("RegPageController.checkURecords()");
        RegPage2Controller instance = new RegPage2Controller();
        assertEquals(true, instance.checkURecords("Udit", "Pant"));
        assertEquals(false, instance.checkURecords("Mark", "Colloway"));
        //assertEquals(false, instance.checkURecords("8574741226"));
    }
    
}
