package test;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PdfToJpgTest {

	public static void main(String[] args) throws Exception {

		setup("D://cc.pdf", "D://adf.jpg");
	}

	public static void setup(String pdfFile, String jpgFile) throws IOException {

		// load a pdf from a byte buffer
		File file = new File(pdfFile);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);

		System.out.println("页数： " + pdffile.getNumPages());

		for (int i = 1; i <= pdffile.getNumPages(); i++) {
			// draw the first page to an image
			PDFPage page = pdffile.getPage(i);

			// get the width and height for the doc at the default zoom
			Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());

			// generate the image
			Image img = page.getImage(rect.width, rect.height, // width &
																// height
					rect, // clip rect
					null, // null for the ImageObserver
					true, // fill background with white
					true // block until drawing is done
					);

			BufferedImage tag = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, null);
			FileOutputStream out = new FileOutputStream(jpgFile + i + ".jpg"); // 输出到文件流
			ImageIO.write(tag, "jpg", out);

			out.close();
		}

		// show the image in a frame
		// JFrame frame = new JFrame("PDF Test");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(new JLabel(new ImageIcon(img)));
		// frame.pack();
		// frame.setVisible(true);
	}
}
