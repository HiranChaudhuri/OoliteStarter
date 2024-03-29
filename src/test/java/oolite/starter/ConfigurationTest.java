/*
 */

package oolite.starter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import oolite.starter.model.Installation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
 
/**
 *
 * @author hiran
 */
public class ConfigurationTest {
    private static final Logger log = LogManager.getLogger();

     public ConfigurationTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of testGetOoliteCommand method, of class Configuration.
     */
    @Test
    public void testDefaultConfiguration() throws MalformedURLException {
        Configuration c = new Configuration();
        assertNull(c.getActiveInstallation());
    }

    /**
     * Test of testGetOoliteCommand2 method, of class Configuration.
     */
    @Test
    public void testDefaultConfiguration2() throws MalformedURLException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        try {
            new Configuration(new File("src/test/resources/testConfig.properties"));
            fail("expected exception");
        } catch (SAXParseException e) {
            assertEquals("Content is not allowed in prolog.", e.getMessage());
            log.debug("caught expected exception", e);
        }
    }

    /**
     * Test of testGetOoliteCommand3 method, of class Configuration.
     */
    @Test
    public void testDefaultConfiguration3() throws MalformedURLException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        Configuration c = new Configuration(new File("src/test/resources/testConfig.xml"));
        List<File> addonDirs = c.getAddonDirs();
        assertEquals(4, addonDirs.size());
        assertEquals("/home/user/GNUstep/Applications/Oolite/DeactivatedAddOns", String.valueOf(c.getDeactivatedAddonsDir()));
        assertEquals("[https://addons.oolite.space/api/1.0/overview, http://addons.oolite.org/api/1.0/overview]", String.valueOf(c.getExpansionManagerURLs()));
        assertEquals("/home/user/GNUstep/Library/ApplicationSupport/Oolite/ManagedAddOns", String.valueOf(c.getManagedAddonsDir()));
        assertEquals("/home/user/GNUstep/Applications/Oolite/oolite.app/oolite-wrapper", c.getOoliteCommand());
        assertEquals("/home/user/oolite-saves", String.valueOf(c.getSaveGameDir()));
    }

    @Test
    public void testActivateInstallation_String() throws MalformedURLException {
        log.info("testActivateInstallation_String");
        Configuration c = new Configuration();
        try {
            c.activateInstallation((String)null);
            fail("expected exception");
        } catch (UnsupportedOperationException e) {
            assertEquals("not yet implemented", e.getMessage());
            log.debug("caught expected exception");
        }
    }

    @Test
    public void testActivateInstallation_Installation() throws MalformedURLException {
        log.info("testActivateInstallation_Installation");
        Configuration c = new Configuration();
        Installation i = new Installation();
        c.getInstallations().add(i);
        c.activateInstallation(i);
        
        assertEquals(i, c.getActiveInstallation());
        c.activateInstallation((Installation)null);
        assertNull(c.getActiveInstallation());
    }
    
    @Test
    public void testGetUpdateCheckInterval() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        log.info("testGetUpdateCheckInterval");
        
        Configuration c = new Configuration(new File("src/test/resources/testConfig.xml"));
        assertEquals(Duration.ofDays(7), c.getUpdateCheckInterval());

    }
}