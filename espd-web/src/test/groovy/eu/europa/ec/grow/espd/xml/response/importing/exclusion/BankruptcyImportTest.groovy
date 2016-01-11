package eu.europa.ec.grow.espd.xml.response.importing.exclusion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.BankruptcyCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 9:26 AM.
 */
class BankruptcyImportTest extends AbstractXmlFileImport {

    def "10. should import all fields of 'Bankruptcy'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/bankruptcy_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.bankruptcy.exists == true
        espd.bankruptcy.description == "Bankruptcy description"
        espd.bankruptcy.reason == "We lost all our money at poker."

        then: "info electronically"
        espd.bankruptcy.availableElectronically.exists == true
        espd.bankruptcy.availableElectronically.url == "www.hodor.com"
        espd.bankruptcy.availableElectronically.code == "INTERNATIONAL"

    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true, description: "Bankruptcy description",
                reason: "We lost all our money at poker.",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "INTERNATIONAL")))

        expect:
        1 == 1
    }

}