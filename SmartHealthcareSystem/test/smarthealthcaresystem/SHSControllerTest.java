/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
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

    @Test
    public void testfetchName() throws Exception {
        System.out.println("SHSController.fetchName()");
        SHSController instance = new SHSController();
        assertEquals("Udit Pant", instance.fetchName("9643121619"));
        assertEquals(null, instance.fetchName("8574541256"));
        assertEquals(null, instance.fetchName("8574741226"));
    }
    
    @Test
    public void testfetchPCount() throws Exception {
        System.out.println("SHSController.fetchPCount()");
        SHSController instance = new SHSController();
        assertEquals(1, instance.fetchPCount());
        //assertEquals(null, instance.fetchPCount());
        //assertEquals(null, instance.fetchPCount());
    }
 
}
