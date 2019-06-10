# PDF text extract for test automation

* Apache pdfbox is being used for this purpose
* Dependancy to be added,
    <!-- https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox -->
    <dependency>
        <groupId>org.apache.pdfbox</groupId>
        <artifactId>pdfbox</artifactId>
        <version>2.0.15</version>
    </dependency>

* To extract all lines of the PDF,
```java
PDFTextStripperByArea stripper = new PDFTextStripperByArea();
stripper.setSortByPosition(true);
PDFTextStripper tStripper = new PDFTextStripper();
String pdfFileInText = tStripper.getText(document);
    String lines[] = pdfFileInText.split("\\r?\\n");
    for (String line : lines) {
        System.out.println(line);
    }
```

* To extract a part of the pdf
    * We will have to find out the rectangle coordinates of the pdf area from where the text has to be extracted
    * For this, download and install GIMP image editor
    * In Gimp > File > Open Location of pdf > Import as Image (In dialog)
    * As we hover over different parts of the PDF(image) with the mouse, the bottom left corner displays the x,y coordinates. 
    * View > Filp vertically (By default, the point (0,0) is located at the top, left. If you want to have the x,y coordinates start at the bottom left, you can flip the image vertically)
    * Find the coordinates from where the text need to be extractd
```java
//(x,y,width,height)
Rectangle rect = new Rectangle( 0, 475, 300, 50 );
stripper.addRegion( "areatExtract", rect );
PDPage firstPage = document.getPage(0);
stripper.extractRegions( firstPage );
System.out.println( "Text in the area:" + rect );
System.out.println( stripper.getTextForRegion( "areatExtract" ) );   
```