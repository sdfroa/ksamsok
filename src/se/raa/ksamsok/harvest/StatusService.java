package se.raa.ksamsok.harvest;

import java.util.Date;
import java.util.List;

/**
 * Tj�nst som hanterar status mm f�r sk�rdetj�nster (cron-jobb).
 */
public interface StatusService {

	/**
	 * Enum f�r de olika steg en tj�nst kan befinna sig i.
	 */
	public static enum Step { FETCH, STORE, EMPTYINDEX, INDEX, IDLE };
	
	/**
	 * �terst�ller status f�r tj�nsten s� att den �r redo f�r en ny k�rning.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 */
	void initStatus(HarvestService service, String message);

	/**
	 * Beg�r att en k�rande tj�nst ska avbryta sig sj�lv s� snart den kan.
	 * 
	 * @param service tj�nst
	 */
	void requestInterrupt(HarvestService service);

	/**
	 * Kollar och kastar exception om tj�nsten ska avbrytas. Anv�nds av tj�nsten
	 * f�r att kontrollera sin status.
	 * 
	 * @param service tj�nst
	 */
	void checkInterrupt(HarvestService service);

	/**
	 * S�tter statusmeddelande och l�gger ocks� till meddelandet i tj�nstens logg.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 */
	void setStatusTextAndLog(HarvestService service, String message);

	/**
	 * S�tter statusmeddelande utan att logga det.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 */
	void setStatusText(HarvestService service, String message);

	/**
	 * S�tter varningsmeddelande och l�gger ocks� till meddelandet i tj�nstens logg.
	 * Ett varningsmeddelande �r samma sak som ett statusmeddelande pss att tex
	 * {@linkplain #getStatusText(HarvestService)} ger senast satta varningsmeddelande.
	 * Den enda skillnaden �r att de lagras med en annan kod i databasen.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 */
	void setWarningTextAndLog(HarvestService service, String message);

	/**
	 * S�tter varningsmeddelande och l�gger ocks� till meddelandet i tj�nstens logg.
	 * Ett varningsmeddelande �r samma sak som ett statusmeddelande pss att tex
	 * {@linkplain #getStatusText(HarvestService)} ger senast satta varningsmeddelande.
	 * Den enda skillnaden �r att de lagras med en annan kod i databasen.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 * @param date tidsst�mpel att ge meddelandet
	 */
	void setWarningTextAndLog(HarvestService service, String message, Date date);

	/**
	 * H�mtar senast satta statusmeddelande f�r tj�nsten.
	 * 
	 * @param service tj�nst
	 * @return statusmeddelande eller null
	 */
	String getStatusText(HarvestService service);

	/**
	 * H�mtar loggmedelanden f�r senaste k�rning. Denna metod h�mtar bara
	 * meddelanden fr�n minnet s� om ett jobb ej k�rts efter uppstart kommer
	 * listan inte inneh�lla n�gra meddelanden.
	 * 
	 * @param service tj�nst
	 * @return lista med loggmeddelanden
	 */
	List<String> getStatusLog(HarvestService service);

	/**
	 * H�mtar en tj�nsts loggmeddelandehistorik.
	 * 
	 * @param service tj�nst
	 * @return lista med loggmeddelanden
	 */
	List<String> getStatusLogHistory(HarvestService service);

	/**
	 * H�mtar en tj�nsts problemloggmeddelandehistorik.
	 * 
	 * @param service tj�nst
	 * @param maxRows max antal loggrader
	 * @param sort sorteringskolumn
	 * @param sortDir sorteringriktning (asc/desc)
	 * @return lista med loggmeddelanden av typen varningar eller fel
	 */
	List<LogEvent> getProblemLogHistory(int maxRows, String sort, String sortDir);

	/**
	 * S�tter felmeddelande och l�gger till meddelandet i tj�nstens logg.
	 * 
	 * @param service tj�nst
	 * @param message meddelande
	 */
	void setErrorTextAndLog(HarvestService service, String message);

	/**
	 * H�mtar felmeddelande.
	 * 
	 * @param service tj�nst
	 * @return felmeddelande eller null
	 */
	String getErrorText(HarvestService service);

	/**
	 * H�mtar senaste starttid f�r tj�nsten.
	 * 
	 * @param service tj�nst
	 * @return senaste starttid som en str�ng, eller null
	 */
	String getLastStart(HarvestService service);

	/**
	 * H�mtar vilket steg tj�nsten befinner sig i.
	 * 
	 * @param service tj�nst
	 * @return aktuellt steg
	 */
	Step getStep(HarvestService service);

	/**
	 * S�tter vilket steg tj�nsten befinner sig i.
	 * 
	 * @param service tj�nst
	 * @param step steg
	 */
	void setStep(HarvestService service, Step step);

	/**
	 * H�mtar vilket steg tj�nsten ska b�rja k�ra ifr�n.
	 * 
	 * @param service tj�nst
	 * @return steg
	 */
	Step getStartStep(HarvestService service);

	/**
	 * S�tter vilket steg tj�nsten ska b�rja k�ra ifr�n.
	 * 
	 * @param service tj�nst
	 * @param step steg
	 */
	void setStartStep(HarvestService service, Step step);

	/**
	 * S�tter flagga som talar om att det finns rdf-parsningsfel
	 * @param service tj�nst
	 */
	void signalRDFError(HarvestService service);
	
	/**
	 * Ger sant om det finns rdf-parsningsfel f�r tj�nsten
	 * @param service tj�nst
	 * @return om rdf-parsningsfel har uppt�ckts
	 */
	boolean containsRDFErrors(HarvestService service);

}
