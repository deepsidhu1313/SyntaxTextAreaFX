/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx.langs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import in.co.s13.syntaxtextareafx.meta.Language;
import java.util.Collections;

/**
 *
 * @author nika
 */
public class Sparql implements Language {

    String BOOLEANS[] = new String[]{"true", "false"};
    String A[] = new String[]{"a"};
    String DATATYPES[] = new String[]{"xsd:ID", "xsd:IDREF", "xsd:NCName", "xsd:NMTOKEN", "xsd:Name", "xsd:anyAtomicType", "xsd:anyURI", "xsd:base64Binary", "xsd:boolean", "xsd:byte", "xsd:date", "xsd:dateTime", "xsd:dayTimeDuration", "xsd:decimal", "xsd:double", "xsd:duration", "xsd:float", "xsd:gDay", "xsd:gMonth", "xsd:gMonthDay", "xsd:gYear", "xsd:gYearMonth", "xsd:hexBinary", "xsd:int", "xsd:integer", "xsd:language", "xsd:long", "xsd:negativeInteger", "xsd:nonNegativeInteger", "xsd:nonPositiveInteger", "xsd:normalizedString", "xsd:precisionDecimal", "xsd:positiveInteger", "xsd:short", "xsd:string", "xsd:time", "xsd:token", "xsd:unsignedByte", "xsd:unsignedInt", "xsd:unsignedLong", "xsd:unsignedShort", "xsd:yearMonthDuration", "dcterms:Box", "dcterms:ISO3166", "dcterms:ISO639-2", "dcterms:ISO639-3", "dcterms:Period", "dcterms:Point", "dcterms:RFC1766", "dcterms:RFC3066", "dcterms:RFC4646", "dcterms:URI", "dcterms:W3CDTF"};
    String CLASSES[] = new String[]{"rdf:Property", "rdf:Statement", "rdf:Bag", "rdf:Seq", "rdf:Alt", "rdf:List", "rdf:XMLLiteral", "rdfs:Resource", "rdfs:Class", "rdfs:Literal", "rdfs:Container", "rdfs:ContainerMembershipProperty", "rdfs:Datatype", "owl:Class", "owl:Thing", "owl:Nothing", "owl:AllDifferent", "owl:Restriction", "owl:ObjectProperty", "owl:DatatypeProperty", "owl:TransitiveProperty", "owl:SymmetricProperty", "owl:FunctionalProperty", "owl:InverseFunctionalProperty", "owl:AnnotationProperty", "owl:Ontology", "owl:OntologyProperty", "owl:DeprecatedClass", "owl:DeprecatedProperty", "owl:DataRange", "skos:Collection", "skos:Concept", "skos:ConceptScheme", "skos:OrderedCollection", "dcam:VocabularyEncodingScheme", "dcterms:Agent", "dcterms:AgentClass", "dcterms:BibliographicResource", "dcterms:FileFormat", "dcterms:Frequency", "dcterms:Jurisdiction", "dcterms:LicenseDocument", "dcterms:LinguisticSystem", "dcterms:Location", "dcterms:LocationPeriodOrJurisdiction", "dcterms:MediaType", "dcterms:MediaTypeOrExtent", "dcterms:MethodOfAccrual", "dcterms:MethodOfInstruction", "dcterms:PeriodOfTime", "dcterms:PhysicalMedium", "dcterms:PhysicalResource", "dcterms:Policy", "dcterms:ProvenanceStatement", "dcterms:RightsStatement", "dcterms:SizeOrDuration", "dcterms:Standard"};
    String PREDICATES[] = new String[]{"rdf:type", "rdf:subject", "rdf:predicate", "rdf:object", "rdf:value", "rdf:first", "rdf:rest", "rdfs:subClassOf", "rdfs:subPropertyOf", "rdfs:comment", "rdfs:label", "rdfs:domain", "rdfs:range", "rdfs:seeAlso", "rdfs:isDefinedBy", "rdfs:member", "owl:equivalentClass", "owl:disjointWith", "owl:equivalentProperty", "owl:sameAs", "owl:differentFrom", "owl:distinctMembers", "owl:unionOf", "owl:intersectionOf", "owl:complementOf", "owl:oneOf", "owl:onProperty", "owl:allValuesFrom", "owl:hasValue", "owl:someValuesFrom", "owl:minCardinality", "owl:maxCardinality", "owl:cardinality", "owl:inverseOf", "owl:imports", "owl:versionInfo", "owl:priorVersion", "owl:backwardCompatibleWith", "owl:incompatibleWith", "skos:altLabel", "skos:broadMatch", "skos:broader", "skos:broaderTransitive", "skos:changeNote", "skos:closeMatch", "skos:definition", "skos:editorialNote", "skos:exactMatch", "skos:example", "skos:hasTopConcept", "skos:hiddenLabel", "skos:historyNote", "skos:inScheme", "skos:mappingRelation", "skos:member", "skos:memberList", "skos:narrowMatch", "skos:narrower", "skos:narrowerTransitive", "skos:notation", "skos:note", "skos:prefLabel", "skos:related", "skos:relatedMatch", "skos:scopeNote", "skos:semanticRelation", "skos:topConceptOf", "dc:contributor", "dc:coverage", "dc:creator", "dc:date", "dc:description", "dc:format", "dc:identifier", "dc:language", "dc:publisher", "dc:relation", "dc:rights", "dc:source", "dc:subject", "dc:title", "dc:type", "dcam:memberOf", "dcterms:abstract", "dcterms:accessRights", "dcterms:accrualMethod", "dcterms:accrualPeriodicity", "dcterms:accrualPolicy", "dcterms:alternative", "dcterms:audience", "dcterms:available", "dcterms:bibliographicCitation", "dcterms:conformsTo", "dcterms:contributor", "dcterms:coverage", "dcterms:created", "dcterms:creator", "dcterms:date", "dcterms:dateAccepted", "dcterms:dateCopyrighted", "dcterms:dateSubmitted", "dcterms:description", "dcterms:educationLevel", "dcterms:extent", "dcterms:format", "dcterms:hasFormat", "dcterms:hasPart", "dcterms:hasVersion", "dcterms:identifier", "dcterms:instructionalMethod", "dcterms:isFormatOf", "dcterms:isPartOf", "dcterms:isReferencedBy", "dcterms:isReplacedBy", "dcterms:isRequiredBy", "dcterms:issued", "dcterms:isVersionOf", "dcterms:language", "dcterms:license", "dcterms:mediator", "dcterms:medium", "dcterms:modified", "dcterms:provenance", "dcterms:publisher", "dcterms:references", "dcterms:relation", "dcterms:replaces", "dcterms:requires", "dcterms:rights", "dcterms:rightsHolder", "dcterms:source", "dcterms:spatial", "dcterms:subject", "dcterms:tableOfContents", "dcterms:temporal", "dcterms:title", "dcterms:type", "dcterms:valid"};
    String INDIVIDUALS[] = new String[]{"rdf:nil", "dcterms:DCMIType", "dcterms:DDC", "dcterms:IMT", "dcterms:LCC", "dcterms:LCSH", "dcterms:MESH", "dcterms:NLM", "dcterms:TGN", "dcterms:UDC"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String BOOLEANS_PATTERN = "\\b(" + String.join("|", BOOLEANS) + ")\\b";
        String A_PATTERN = "\\b(" + String.join("|", A) + ")\\b";
        String DATATYPES_PATTERN = "\\b(" + String.join("|", DATATYPES) + ")\\b";
        String CLASSES_PATTERN = "\\b(" + String.join("|", CLASSES) + ")\\b";
        String PREDICATES_PATTERN = "\\b(" + String.join("|", PREDICATES) + ")\\b";
        String INDIVIDUALS_PATTERN = "\\b(" + String.join("|", INDIVIDUALS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BOOLEANS>" + BOOLEANS_PATTERN + ")"
                + "|(?<A>" + A_PATTERN + ")"
                + "|(?<DATATYPES>" + DATATYPES_PATTERN + ")"
                + "|(?<CLASSES>" + CLASSES_PATTERN + ")"
                + "|(?<PREDICATES>" + PREDICATES_PATTERN + ")"
                + "|(?<INDIVIDUALS>" + INDIVIDUALS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BOOLEANS") != null ? "booleans"
                : matcher.group("A") != null ? "a"
                : matcher.group("DATATYPES") != null ? "datatypes"
                : matcher.group("CLASSES") != null ? "classes"
                : matcher.group("PREDICATES") != null ? "predicates"
                : matcher.group("INDIVIDUALS") != null ? "individuals"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BOOLEANS));
        keywordList.addAll(Arrays.asList(A));
        keywordList.addAll(Arrays.asList(DATATYPES));
        keywordList.addAll(Arrays.asList(CLASSES));
        keywordList.addAll(Arrays.asList(PREDICATES));
        keywordList.addAll(Arrays.asList(INDIVIDUALS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
