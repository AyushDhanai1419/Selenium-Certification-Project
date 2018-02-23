package mercury_Tours;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfUtility {
//define a Pdf Document variable named doc
 private PDDocument doc;
 //function to write test results to pdf file with file name and list of test cases
 public void WriteTestResultToPdfFile(String fileName,List<String> testCase) throws IOException, COSVisitorException
 {
     try
  {
      //create a new  pdf document
    doc = new PDDocument();
    //create a new pdf page
      PDPage page = new PDPage();
      //set font for the pdf
      PDFont font=PDType1Font.HELVETICA;
      //set fontsize=12
    int  fontSize=12;
    //add page to the pdf document
      doc.addPage(page);
      //create a new content stream that will be written to the pdf file
      PDPageContentStream contentStream = new PDPageContentStream(doc, page);
      //define cursor position
         float x=10;
      float y=750;
      //if there is no test case string, return nothing    
          if (testCase.size() == 0) { return; }
          //get number of test case lines
          final int numberOfLines = testCase.size();
          //get fontHeight
          final float fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight()/1000 * fontSize;
          //set font to use in the content stream
          contentStream.setFont(font, fontSize);
          //set content stream begin
          contentStream.beginText();
          //move content stream cursor position to (x,y)
          contentStream.moveTextPositionByAmount( x, y);
          //add a new row with the fontHeight
          contentStream.appendRawCommands(fontHeight + " TL\n");
          //loop through all the lines in the test case list
          for (int i = 0; i < numberOfLines; i++) {
            contentStream.drawString(testCase.get(i).toString());
            //when current line is not end of the line, change the line, so content will be written to the next line
            if (i < numberOfLines - 1) {
              contentStream.appendRawCommands("T*\n");
            }
          }
          //end content stream
          contentStream.endText();
          //close content stream
         contentStream.close();
         //write the pdf file
      doc.save(fileName);
  }
  finally
  {
      //finally close the pdf document
    if (doc != null)
      {
          doc.close();
      }
  }
}
}