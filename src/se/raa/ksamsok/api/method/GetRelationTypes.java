package se.raa.ksamsok.api.method;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import se.raa.ksamsok.api.APIServiceProvider;
import se.raa.ksamsok.api.exception.BadParameterException;
import se.raa.ksamsok.api.exception.DiagnosticException;
import se.raa.ksamsok.api.exception.MissingParameterException;
import se.raa.ksamsok.lucene.ContentHelper;
import se.raa.ksamsok.lucene.ContentHelper.Index;

public class GetRelationTypes extends AbstractAPIMethod {

	/** Metodnamn */
	public static final String METHOD_NAME = "getRelationTypes";

	/** Parameternamn för relationstyp */
	public static final String RELATION_PARAMETER = "relation";
	/** Parametervärde för att ange alla relationstyper */
	public static final String RELATION_ALL = "all";

	private Map<String, String> relationTypes = Collections.emptyMap();

	// datavariabler
	private String relation;
	// hjälpvariabler
	private boolean isAll;

	/**
	 * Skapa ny instans.
	 * @param serviceProvider tjänstetillhandahållare
	 * @param out writer
	 * @param params parametrar
	 * @throws ParserConfigurationException 
	 */
	public GetRelationTypes(APIServiceProvider serviceProvider, OutputStream out, Map<String, String> params) throws ParserConfigurationException {
		super(serviceProvider, out, params);
	}

	@Override
	protected void extractParameters() throws MissingParameterException,
			BadParameterException {
		relation = getMandatoryParameterValue(RELATION_PARAMETER, "GetRelations.extractParameters", null, false);
		isAll = RELATION_ALL.equals(relation);
		if (!isAll && !GetRelations.relationXlate.containsKey(relation)) {
			throw new BadParameterException("Värdet för parametern " + RELATION_PARAMETER + " är ogiltigt",
					"GetRelations.extractParameters", null, false);
		}
	}

	@Override
	protected void performMethodLogic() throws DiagnosticException {
		if (isAll) {
			relationTypes = GetRelations.relationXlate;
		} else {
			relationTypes = Collections.singletonMap(relation,
					GetRelations.relationXlate.get(relation));
		}
	}

	@Override
	protected void generateDocument() {
		Element result = super.generateBaseDocument();
		// relation types
		Element relationTypesEl = doc.createElement("relationTypes");
		relationTypesEl.setAttribute("count", Integer.toString(relationTypes.size(), 10));
		result.appendChild(relationTypesEl);
		Index index;
		String indexTitle;
		for (Entry<String, String> rel: relationTypes.entrySet()) {
			index = ContentHelper.getIndex(rel.getKey());
			if (index != null) {
				indexTitle = index.getTitle();
			} else {
				index = ContentHelper.getIndex(rel.getValue());
				if (index != null) {
					// TODO: hämta rätt sträng, antingen som ovan när/om det blir
					//       index av inverserna eller på annat sätt
					indexTitle = "Är/var " + StringUtils.lowerCase(index.getTitle()) + " för";
				} else {
					// ska inte hända
					indexTitle = rel.getKey();
				}
			}
			Element relationTypeEl = doc.createElement("relationType");
			relationTypeEl.setAttribute("name", rel.getKey());
			relationTypeEl.setAttribute("title", indexTitle);
			relationTypeEl.setAttribute("reverse", rel.getValue());
			relationTypesEl.appendChild(relationTypeEl);
		}
	}

}
