package ad.fh.albsig.de.$85224;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class SteuerungTest {
	private XMLManagerTest xmlManager;
	private RandomGeneratorTest random;
	
	final static Logger log = Logger.getLogger(SteuerungTest.class);
	
	public SteuerungTest() {	
        PropertyConfigurator.configure("log4j.properties");
        
        log.info("A new Steuerung was created");
        
        log.debug("Sample - Steuerung");
        log.error("Sample - Steuernug");
        log.fatal("Sample - Steuerung");

		
		 this.print(">---------- SimpleWeather ----------<");
		 this.print(" Ihr Wetter wird erstellt...");
		 this.print(" Bitte Warten...");
			
		 this.xmlManager = new XMLManagerTest();
		 this.random = new RandomGeneratorTest();	
		 this.work();
		 
		 this.print(" Ihre Wettervorhersage ist fertig.");
		 this.print(" Sie finden sie als Weather.xml vor.");
		 this.print(">-----------------------------------<");
		 
		}
		
		@Test
		public void work() {
			String temp = "";			//Temperatur
			String humid = "";			//Luftfeuchtigkeit
			String windR = "";			//wind Richtung
			String windG = "";			//wind Geschwindigkeit
			String zustand = "";		//Wetterzustand "Bewölkt" oder "Sonnig" etc.
			
			
			for(int i = 0; i < 10; i++) {
				temp = this.genTemp();
				humid = this.genHumid();
				windR = this.genWindR();
				windG = this.genWindG();
				zustand = this.genZustand();
				
				String[] data = new String[5];
				data[0] = zustand;
				data[1] = temp;
				data[2] = humid;
				data[3] = windR;
				data[4] = windG;
				
				this.xmlManager.giveData(data);
				this.xmlManager.writeInto();
				this.pause();
			}
			this.xmlManager.flush();
		}
		
		
		private String genTemp() {
			int tempInt = this.random.getInt(50);
			String temp = "" + tempInt + "°C";
			return temp;
		}
		
		
		private String genHumid() {
			int humidInt = this.random.getInt(90);
			String humid = "" + humidInt + "%";
			return humid;
		}
		
		
		private String genWindR() {
			int windRInt = this.random.getInt(8);
			String windR = "";
			
			switch(windRInt) {
			case 1: windR += "N"; break;
			case 2: windR += "NO"; break;
			case 3: windR += "O"; break;
			case 4: windR += "SO"; break; 
			case 5: windR += "S"; break;
			case 6: windR += "SW"; break;
			case 7: windR += "W"; break;
			case 8: windR += "NW"; break;
			default: windR += "OSWN";
			}
			
			return windR;
		}
		
		
		private String genWindG() {
			int windGInt = this.random.getInt(120);
			String windG = "" +  windGInt + "km/h";
			return windG;
		}
		
		
		private String genZustand() {
			int zustandInt = this.random.getInt(9);
			String zustand = "";
			
			switch(zustandInt) {
			case 1: zustand += "Sönnig"; break;
			case 2: zustand += "Bewölkt"; break;
			case 3: zustand += "Regen"; break;
			case 4: zustand += "Schnee"; break;
			case 5: zustand += "Neblig"; break;
			case 6: zustand += "Gewitter"; break;
			case 7: zustand += "Hagel"; break;
			case 8: zustand += "Leicht Bewölkt"; break;
			case 9: zustand += "Niesel"; break;
			default: zustand += "Blizzard"; 		
			}
			
			return zustand;
		}
		
		private void print(String pString) {
			System.out.println(pString);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.error("!Fehler:Steuerung:print() ", e);
				e.printStackTrace();
			}
			
		}
		@Test
		public void pause() {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.error("!Fehler:SteuerungTest:work() ", e);
				e.printStackTrace();
			}
		}
	}