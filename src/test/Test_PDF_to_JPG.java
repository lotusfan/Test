package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Test_PDF_to_JPG {

	public static void main(String[] args) {

		PDDocument doc = null;
		FileOutputStream out = null;
		try {

			doc = PDDocument.load(new File("D://cc.pdf"));

			PDPage pd = (PDPage) doc.getDocumentCatalog().getAllPages().get(0);
			COSDictionary cos = pd.getCOSDictionary();
			System.out.println(cos = (COSDictionary) cos.getDictionaryObject("Resources"));
			System.out.println(cos = (COSDictionary) cos.getDictionaryObject("Font"));
			System.out.println(cos = (COSDictionary) cos.getDictionaryObject("F11"));
			cos.setName("BaseFont", "hhh");
			System.out.println(cos.getNameAsString("BaseFont"));
			Set set = cos.entrySet();
			Iterator it = set.iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry entry = (Entry) it.next();
				i++;
				System.out.println(entry.getKey() + "-----" + entry.getValue() + i);
			}

			/*
			 * int pageCount = doc.getNumberOfPages();
			 * 
			 * List pages = doc.getDocumentCatalog().getAllPages(); for (int i = 0; i < pages.size(); i++) { PDPage page = (PDPage) pages.get(i);
			 * BufferedImage image = page.convertToImage(); Iterator iter = ImageIO.getImageWritersBySuffix("jpg"); ImageWriter writer = (ImageWriter)
			 * iter.next(); File outFile = new File("D://aa.jpg"); out = new FileOutputStream(outFile); ImageOutputStream outImage =
			 * ImageIO.createImageOutputStream(out); writer.setOutput(outImage); writer.write(new IIOImage(image, null, null)); }
			 */
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (doc != null) doc.close();
				if (out != null) out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
