package ad.fh.albsig.de.$85224;



import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.jamesmurty.utils.XMLBuilder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * class XMLManager
 * @author JP
 *
 */
public class XMLManagerTest {
	@Mock
	private File file;
	private XMLBuilder xml;
	private Properties outputProperties;
	private String[] data;
	private int day;
	final static Logger log = Logger.getLogger(XMLManagerTest.class);
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	/***
	 * construcotr XMLManager
	 */
	public XMLManagerTest(){
		PropertyConfigurator.configure("log4j.properties");
		log.info("A new XMLManager was created");
        log.debug("Sample - XMLManager");
        log.error("Sample - XMLManager");
        log.fatal("Sample - XMLManager");
		try {
			this.xml = XMLBuilder.create("Weather");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			log.error("!Fehler:XMLManager ", e);
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			log.error("!Fehler:XMLManager ", e);
			e.printStackTrace();
		}
		this.outputProperties = null;
		this.day = 0;
		this.data = new String[5];
		for(int i = 0; i<5; i++)
			this.data[i]="err";
	}
	/***
	 * public method giveData
	 * @param pdata
	 */
	public void giveData(String[] pdata) {
		for(int i = 0; i<5; i++) {
			String nData = pdata[i];
			if(!nData.isEmpty()) {
				this.data[i] = nData;
			}else {
				this.data[i] = "unknown";
			}	
		}
	}
	/***
	 * public method writeInto
	 */
	@Test
	public void writeInto() {
		String tag = "";
		XMLBuilder out;
		switch(this.day) {
		case 0: tag += "aktuell"; break;
		case 1: tag += "morgen"; break;
		case 2: tag += "uebermorgen"; break;
		default: tag += "in" + this.day + "Tagen";
		}
		this.day++;
		out = xml.e(tag)
				.e("wetter")
			            .t(this.data[0])
			        .up()
			    .e("temperatur")
			    		.t(this.data[1])
			    	.up()
			    .e("luftfeuchtigkeit")
			            .t(this.data[2])
			        .up()
			    .e("windrichtung")
					   	.t(this.data[3])
					.up()
				.e("windgeschwindigkeit")
					    .t(this.data[4])
					.up()
				.up();	
		log.info(" XML sequenze written: " + out.toString());
	}
	/**
	 * public method flush
	 */
	@Test
	@SuppressFBWarnings
	public void flush() {
		this.file = new File("Weather.xml");
		PrintWriter writer;
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(this.file);
			writer = new PrintWriter(stream);
			xml.toWriter(writer, outputProperties);
			writer.flush();
			writer.close();
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("!Fehler:XMLManager:flush() ", e);
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			log.error("!Fehler:XMLManager:flush() ", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("!Fehler:XMLManager:flush() ", e);
			e.printStackTrace();
		}
	}
}
