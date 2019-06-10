package allTests;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class extractTextFromPdf {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		
		//If we have store the pdf in a separate folder inside project folder
//		PDDocument document = PDDocument.load(new File(System.getProperty("user.dir")+"\\pdfs\\sample.pdf"));
		
		//If the pdf is in the same path as the .java file
		URL path = extractTextFromPdf.class.getResource("sample.pdf");
		PDDocument document = PDDocument.load(new File(path.getFile()));
		
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        PDFTextStripper tStripper = new PDFTextStripper();
        String pdfFileInText = tStripper.getText(document);
        String lines[] = pdfFileInText.split("\\r?\\n");
        
        //To extract all lines in the pdf
        for (String line : lines) {
//            System.out.println(line);
        }
        
        Rectangle rect = new Rectangle( 0, 475, 300, 50 );
        stripper.addRegion( "areatExtract", rect );
        PDPage firstPage = document.getPage(0);
        stripper.extractRegions( firstPage );
        System.out.println( "Text in the area:" + rect );
        System.out.println( stripper.getTextForRegion( "areatExtract" ) );      
	}
}
