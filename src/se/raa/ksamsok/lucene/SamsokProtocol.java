package se.raa.ksamsok.lucene;

import java.net.URI;


public class SamsokProtocol {

	static final String uriPrefix = "http://kulturarvsdata.se/";
	static final String uriPrefixKSamsok = uriPrefix + "ksamsok#";

	static final URI uri_rdfType = URI.create("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
	static final URI uri_samsokEntity = URI.create(uriPrefixKSamsok + "Entity");

	// protokollversion
	static final URI uri_rKsamsokVersion = URI.create(uriPrefixKSamsok + "ksamsokVersion");

	// cidoc-crm
	static final String uriPrefix_cidoc_crm = "http://www.cidoc-crm.org/rdfs/cidoc-crm#";
	static final URI uri_cidoc_P94B_was_created_by = URI.create(uriPrefix_cidoc_crm + "P94B.was_created_by");
	static final URI uri_cidoc_P94F_has_created = URI.create(uriPrefix_cidoc_crm + "P94F.has_created");
	static final URI uri_cidoc_P51F_has_former_or_current_owner = URI.create(uriPrefix_cidoc_crm + "P51F.has_former_or_current_owner");
	static final URI uri_cidoc_P51B_is_former_or_current_owner_of = URI.create(uriPrefix_cidoc_crm + "P51B.is_former_or_current_owner_of");
	static final URI uri_cidoc_P49F_has_former_or_current_keeper = URI.create(uriPrefix_cidoc_crm + "P49F.has_former_or_current_keeper");
	static final URI uri_cidoc_P49B_is_former_or_current_keeper_of = URI.create(uriPrefix_cidoc_crm + "P49B.is_former_or_current_keeper_of");
	static final URI uri_cidoc_P105F_right_held_by = URI.create(uriPrefix_cidoc_crm + "P105F.right_held_by");
	static final URI uri_cidoc_P105B_has_right_on = URI.create(uriPrefix_cidoc_crm + "P105B.has_right_on");
	static final URI uri_cidoc_P11F_had_participant = URI.create(uriPrefix_cidoc_crm + "P11F.had_participant");
	static final URI uri_cidoc_P11B_participated_in = URI.create(uriPrefix_cidoc_crm + "P11B.participated_in");
	static final URI uri_cidoc_P12B_was_present_at = URI.create(uriPrefix_cidoc_crm + "P12B.was_present_at");
	static final URI uri_cidoc_P12F_occurred_in_the_presence_of = URI.create(uriPrefix_cidoc_crm + "P12F.occurred_in_the_presence_of");

	// bio
	static final String uriPrefix_bio = "http://purl.org/vocab/bio/0.1/";
	static final URI uri_bio_child = URI.create(uriPrefix_bio + "child");
	static final URI uri_bio_parent = URI.create(uriPrefix_bio + "parent");
	static final URI uri_bio_mother = URI.create(uriPrefix_bio + "mother");
	static final URI uri_bio_father = URI.create(uriPrefix_bio + "father");

	// "tagnamn"
	static final URI uri_rServiceName = URI.create(uriPrefixKSamsok + "serviceName");
	static final URI uri_rServiceOrganization = URI.create(uriPrefixKSamsok + "serviceOrganization");
	static final URI uri_rCreatedDate = URI.create(uriPrefixKSamsok + "createdDate");
	static final URI uri_rLastChangedDate = URI.create(uriPrefixKSamsok + "lastChangedDate");
	static final URI uri_rItemTitle = URI.create(uriPrefixKSamsok + "itemTitle");
	static final URI uri_rItemLabel = URI.create(uriPrefixKSamsok + "itemLabel");
	static final URI uri_rItemType = URI.create(uriPrefixKSamsok + "itemType");
	static final URI uri_rItemSuperType = URI.create(uriPrefixKSamsok + "itemSuperType");
	static final URI uri_rItemClass = URI.create(uriPrefixKSamsok + "itemClass");
	static final URI uri_rItemClassName = URI.create(uriPrefixKSamsok + "itemClassName");
	static final URI uri_rItemName = URI.create(uriPrefixKSamsok + "itemName");
	static final URI uri_rItemSpecification = URI.create(uriPrefixKSamsok + "itemSpecification");
	static final URI uri_rItemKeyWord = URI.create(uriPrefixKSamsok + "itemKeyWord");
	static final URI uri_rItemMotiveWord = URI.create(uriPrefixKSamsok + "itemMotiveWord");
	static final URI uri_rItemMaterial = URI.create(uriPrefixKSamsok + "itemMaterial");
	static final URI uri_rItemTechnique = URI.create(uriPrefixKSamsok + "itemTechnique");
	static final URI uri_rItemStyle = URI.create(uriPrefixKSamsok + "itemStyle");
	static final URI uri_rItemColor = URI.create(uriPrefixKSamsok + "itemColor");
	static final URI uri_rItemNumber = URI.create(uriPrefixKSamsok + "itemNumber");
	static final URI uri_rItemDescription = URI.create(uriPrefixKSamsok + "itemDescription");
	static final URI uri_rItemLicense = URI.create(uriPrefixKSamsok + "itemLicense");
	static final URI uri_rSubject = URI.create(uriPrefixKSamsok + "subject");
	static final URI uri_rCollection = URI.create(uriPrefixKSamsok + "collection");
	static final URI uri_rDataQuality = URI.create(uriPrefixKSamsok + "dataQuality");
	static final URI uri_rMediaType = URI.create(uriPrefixKSamsok + "mediaType");
	static final URI uri_r__Desc = URI.create(uriPrefixKSamsok + "desc");
	static final URI uri_r__Name = URI.create(uriPrefixKSamsok + "name");
	static final URI uri_r__Spec = URI.create(uriPrefixKSamsok + "spec");
	static final URI uri_rMaterial = URI.create(uriPrefixKSamsok + "material");
	static final URI uri_rNumber = URI.create(uriPrefixKSamsok + "number");
	static final URI uri_rPres = URI.create(uriPrefixKSamsok + "presentation");
	static final URI uri_rContext = URI.create(uriPrefixKSamsok + "context");
	static final URI uri_rContextType = URI.create(uriPrefixKSamsok + "contextType");
	static final URI uri_rContextSuperType = URI.create(uriPrefixKSamsok + "contextSuperType");
	static final URI uri_rContextLabel = URI.create(uriPrefixKSamsok + "contextLabel");
	static final URI uri_rURL = URI.create(uriPrefixKSamsok + "url");
	static final URI uri_rMuseumdatURL = URI.create(uriPrefixKSamsok + "museumdatUrl");
	static final URI uri_rTheme = URI.create(uriPrefixKSamsok + "theme");

	// special
	static final URI uri_rItemForIndexing = URI.create(uriPrefixKSamsok + "itemForIndexing");

	// relationer
	static final URI uri_rContainsInformationAbout = URI.create(uriPrefixKSamsok + "containsInformationAbout");
	static final URI uri_rContainsObject = URI.create(uriPrefixKSamsok + "containsObject");
	static final URI uri_rHasBeenUsedIn = URI.create(uriPrefixKSamsok + "hasBeenUsedIn");
	static final URI uri_rHasChild = URI.create(uriPrefixKSamsok + "hasChild");
	static final URI uri_rHasFind = URI.create(uriPrefixKSamsok + "hasFind");
	static final URI uri_rHasImage = URI.create(uriPrefixKSamsok + "hasImage");
	static final URI uri_rHasObjectExample = URI.create(uriPrefixKSamsok + "hasObjectExample");
	static final URI uri_rHasParent = URI.create(uriPrefixKSamsok + "hasParent");
	static final URI uri_rHasPart = URI.create(uriPrefixKSamsok + "hasPart");
	static final URI uri_rIsDescribedBy = URI.create(uriPrefixKSamsok + "isDescribedBy");
	static final URI uri_rIsFoundIn = URI.create(uriPrefixKSamsok + "isFoundIn");
	static final URI uri_rIsPartOf = URI.create(uriPrefixKSamsok + "isPartOf");
	static final URI uri_rIsRelatedTo = URI.create(uriPrefixKSamsok + "isRelatedTo");
	static final URI uri_rIsVisualizedBy = URI.create(uriPrefixKSamsok + "isVisualizedBy");
	static final URI uri_rSameAs = URI.create("http://www.w3.org/2002/07/owl#sameAs"); // obs, owl
	static final URI uri_rVisualizes = URI.create(uriPrefixKSamsok + "visualizes");

	// var-kontext
	static final URI uri_rContinentName = URI.create(uriPrefixKSamsok + "continentName");
	static final URI uri_rCountryName = URI.create(uriPrefixKSamsok + "countryName");
	static final URI uri_rPlaceName = URI.create(uriPrefixKSamsok + "placeName");
	static final URI uri_rCadastralUnit = URI.create(uriPrefixKSamsok + "cadastralUnit");
	static final URI uri_rPlaceTermId = URI.create(uriPrefixKSamsok + "placeTermId");
	static final URI uri_rPlaceTermAuth = URI.create(uriPrefixKSamsok + "placeTermAuth");
	static final URI uri_rCountyName = URI.create(uriPrefixKSamsok + "countyName");
	static final URI uri_rMunicipalityName = URI.create(uriPrefixKSamsok + "municipalityName");
	static final URI uri_rProvinceName = URI.create(uriPrefixKSamsok + "provinceName");
	static final URI uri_rParishName = URI.create(uriPrefixKSamsok + "parishName");
	static final URI uri_rCountry = URI.create("http://www.mindswap.org/2003/owl/geo/geoFeatures20040307.owl#country");
	static final URI uri_rCounty = URI.create("http://www.mindswap.org/2003/owl/geo/geoFeatures20040307.owl#county");
	static final URI uri_rMunicipality = URI.create("http://www.mindswap.org/2003/owl/geo/geoFeatures20040307.owl#municipality");
	static final URI uri_rProvince = URI.create("http://www.mindswap.org/2003/owl/geo/geoFeatures20040307.owl#province");
	static final URI uri_rParish = URI.create("http://www.mindswap.org/2003/owl/geo/geoFeatures20040307.owl#parish");
	static final URI uri_rCoordinates = URI.create(uriPrefixKSamsok + "coordinates");

	// vem-kontext
	static final URI uri_rFirstName = URI.create("http://xmlns.com/foaf/0.1/#firstName");
	static final URI uri_rSurname = URI.create("http://xmlns.com/foaf/0.1/#surname");
	static final URI uri_rFullName = URI.create("http://xmlns.com/foaf/0.1/#fullName");
	static final URI uri_rName = URI.create("http://xmlns.com/foaf/0.1/#name");
	static final URI uri_rGender = URI.create("http://xmlns.com/foaf/0.1/#gender");
	static final URI uri_rOrganization = URI.create("http://xmlns.com/foaf/0.1/#organization");
	static final URI uri_rTitle = URI.create("http://xmlns.com/foaf/0.1/#title");
	static final URI uri_rNameId = URI.create(uriPrefixKSamsok + "nameId");
	static final URI uri_rNameAuth = URI.create(uriPrefixKSamsok + "nameAuth");

	// n�r-kontext
	static final URI uri_rFromTime = URI.create(uriPrefixKSamsok + "fromTime");
	static final URI uri_rToTime = URI.create(uriPrefixKSamsok + "toTime");
	static final URI uri_rFromPeriodName = URI.create(uriPrefixKSamsok + "fromPeriodName");
	static final URI uri_rToPeriodName = URI.create(uriPrefixKSamsok + "toPeriodName");
	static final URI uri_rFromPeriodId = URI.create(uriPrefixKSamsok + "fromPeriodId");
	static final URI uri_rToPeriodId = URI.create(uriPrefixKSamsok + "toPeriodId");
	static final URI uri_rPeriodAuth = URI.create(uriPrefixKSamsok + "periodAuth");
	static final URI uri_rEventName = URI.create(uriPrefixKSamsok + "eventName");
	static final URI uri_rEventAuth = URI.create(uriPrefixKSamsok + "eventAuth");
	//static final URI uri_rTimeText = URI.create(uriPrefixKSamsok + "timeText");

	// �vriga
	static final URI uri_rThumbnail = URI.create(uriPrefixKSamsok + "thumbnail");
	static final URI uri_rImage = URI.create(uriPrefixKSamsok + "image");
	static final URI uri_rMediaLicense = URI.create(uriPrefixKSamsok + "mediaLicense");
	static final URI uri_rMediaMotiveWord = URI.create(uriPrefixKSamsok + "mediaMotiveWord");

	// geo
	static final String aukt_country_pre = uriPrefix + "resurser/aukt/geo/country#";
	static final String aukt_county_pre = uriPrefix + "resurser/aukt/geo/county#";
	static final String aukt_municipality_pre = uriPrefix + "resurser/aukt/geo/municipality#";
	static final String aukt_province_pre = uriPrefix + "resurser/aukt/geo/province#";
	static final String aukt_parish_pre = uriPrefix + "resurser/aukt/geo/parish#";

	// context
	static final String context_pre = uriPrefix + "resurser/ContextType#";
	static final String contextsuper_pre = uriPrefix + "resurser/ContextSuperType#";

}
