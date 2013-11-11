package se.raa.ksamsok.api.method;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;

import com.sun.syndication.io.FeedException;

import se.raa.ksamsok.api.APIServiceProvider;
import se.raa.ksamsok.api.exception.BadParameterException;
import se.raa.ksamsok.api.exception.DiagnosticException;
import se.raa.ksamsok.api.exception.MissingParameterException;
import se.raa.ksamsok.api.method.APIMethod.Format;

/**
 * Basklass för api-metoder.
 *
 */
public abstract class AbstractAPIMethod implements APIMethod {

	protected static final Logger logger = Logger.getLogger(AbstractAPIMethod.class);

	protected APIServiceProvider serviceProvider;
	protected Map<String, String> params;
	protected OutputStream out;
	protected String stylesheet;
	protected Document doc;
	protected Format format = Format.XML;
	protected boolean prettyPrint = false;
	
	
	/**
	 * Skapar ny instans.
	 * @param serviceProvider tillhandahåller tjänster etc
	 * @param writer writer
	 * @param params parametrar
	 * @throws ParserConfigurationException 
	 */
	protected AbstractAPIMethod(APIServiceProvider serviceProvider, OutputStream out, Map<String, String> params) throws ParserConfigurationException {
		this.serviceProvider = serviceProvider;
		this.params = params;
		this.stylesheet = params.get("stylesheet");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();;
		this.doc = docBuilder.newDocument(); 
		this.out=out;
	}

	@Override
	public void performMethod() throws MissingParameterException,
			BadParameterException, DiagnosticException, TransformerException, JSONException, FeedException, ParserConfigurationException, SAXException, IOException {
		// läs ut parametrar och kasta ex vid problem
		extractParameters();
		// utför operationen
		performMethodLogic();
		generateDocument();
		try {
			writeResult();
		} catch (IOException e) {
			logger.error("writeXmlResult: "+e.getMessage());
			throw new DiagnosticException(e.getMessage(),AbstractAPIMethod.class.getName(),e.getCause().getMessage(),false);
		}
	}

	/**
	 * Skriver resultat av metod.
	 * @throws DiagnosticException vid fel
	 * @throws IOException 
	 * @throws TransformerConfigurationException 
	 * @throws TransformerException 
	 * @throws JSONException 
	 * @throws FeedException 
	 */
	protected void writeResult() throws IOException, TransformerException, JSONException, DiagnosticException, FeedException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transform = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult strResult;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (format == Format.JSON_LD){
			strResult = new StreamResult(baos);
		} else {
			strResult = new StreamResult(out);
		}
		transform.transform(source, strResult);
		if (format == Format.JSON_LD){
			String json;
			if (prettyPrint){
				json=XML.toJSONObject(baos.toString("UTF-8")).toString(indentFactor);
			} else {
				json=XML.toJSONObject(baos.toString("UTF-8")).toString();
			}
			out.write(json.getBytes("UTF-8"));
		}
	}

	/**
	 * Tar ut och kontrollerar parametrar.
	 * @throws MissingParameterException om parameter saknas
	 * @throws BadParameterException om parameter är felaktig
	 */
	protected void extractParameters() throws MissingParameterException, BadParameterException{
		//Check if the response should be in pretty print
		if (params.get("prettyPrint") != null && params.get("prettyPrint").equalsIgnoreCase("true")){
			prettyPrint=true;
		}
	}

	/**
	 * Utför metodens logik.
	 * @throws DiagnosticException vid problem
	 */
	abstract protected void performMethodLogic() throws DiagnosticException;

	/**
	 * Denna metod genererar xml dokumentet som är grund för api-svaret
	 * @throws ParserConfigurationException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	abstract protected void generateDocument() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException;
	
	protected Element generateBaseDocument(){
		//Root element
		Element result = doc.createElement("result");
		doc.appendChild(result);
		// Stylesheet
		if(stylesheet!=null && stylesheet.trim().length()>0){
			ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\""+ stylesheet +"\"");
			doc.insertBefore(pi, result);
		}
		//Version
		Element version = doc.createElement("version");
		version.appendChild(doc.createTextNode(API_VERSION));
		result.appendChild(version);
		return result;
	}

	/**
	 * Returnerar query-strängen eller kastar ett exception om värdet var null
	 * @param queryString query-sträng
	 * @return queryString
	 * @throws MissingParameterException om strängen är null eller tomma strängen
	 */
	public String getQueryString(String queryString) throws MissingParameterException {
		if (queryString == null || queryString.trim().length() < 1) {
			throw new MissingParameterException("parametern query saknas eller är tom", "APIMethodFactory.getQueryString", null, false);
		}
		return queryString;
	}

	/**
	 * Returnerar en index-map där indexen får samma värde, det som är inskickat i value.
	 *
	 * @param indexString sträng med indexnamn separerade av {@linkplain #DELIMITER}
	 * @param value värde för index
	 * @return index-map med indexnamn som nyckel och inskickat värde som värde, aldrig null men kan vara tom
	 * @throws MissingParameterException om index-strängen är null eller "tom".
	 */
	public Map<String,String> getIndexMapSingleValue(String indexString,
			String value)  throws MissingParameterException {
		Map<String,String> indexMap = new HashMap<String,String>();
		if (indexString == null || indexString.trim().length() < 1) 	{
			throw new MissingParameterException("parametern index saknas eller är tom", "APIMethodFactory.getIndexMapSingleValue", null, false);
		}
		StringTokenizer indexTokenizer = new StringTokenizer(indexString, DELIMITER);
		while (indexTokenizer.hasMoreTokens()) {
			indexMap.put(indexTokenizer.nextToken(), value);
		}
		return indexMap;
	}

	protected String getMandatoryParameterValue(String key, String infoClassName, String infoDetails,
			boolean logIfMissing) throws MissingParameterException {
		return getParameterValue(key, true, infoClassName, infoDetails, logIfMissing);
	}
	protected String getOptionalParameterValue(String key, String infoClassName, String infoDetails,
			boolean logIfMissing) throws MissingParameterException {
		return getParameterValue(key, false, infoClassName, infoDetails, logIfMissing);
	}

	protected String getParameterValue(String key, boolean isMandatory, String infoClassName, String infoDetails,
			boolean logIfMissing) throws MissingParameterException {
		String value = StringUtils.trimToNull(params.get(key));
		if (isMandatory && value == null) {
			throw new MissingParameterException("Parametern " + key + " saknas eller är tom",
					infoClassName, infoDetails, logIfMissing);
		}
		return value;
	}
	
	public void setFormat(Format format){
		this.format=format;
	}
	
	public void setPrettyPrint(Boolean prettyPrint){
		this.prettyPrint=prettyPrint;
	}
}
