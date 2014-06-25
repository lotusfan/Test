package test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class changC {

	static float PDF_VERESION = 1.5f;
	static String PDF_AUTHOR = "duxk";
	static String PDF_CREATER = "santa";
	static String PDF_PRODUCER = "skylight";
	static int PDF_X_PIXELS = 595;
	static int PDF_Y_PIXELS = 841;
	//version
	static String PDF_HEADER = "%%PDF-%1.1f\n";

	//index version pageObjectIndex
	static String PDF_CATALOG = "%d 0 obj\n<<\n/Type /Catalog\n/Version /%1.1f\n/Pages %d 0 R\n>>\nendobj\n";

	//index author creator producer
	static String PDF_INFO = "%d 0 obj\n<<\n/Author (%s)\n/Creator (%s)\n/Producer (%s)\n>>\nendobj\n";

	//index kids count
	static String PDF_PAGES = "%d 0 obj\n<<\n/Type /Pages\n/Kids [%s]\n/Count %d\n>>\nendobj\n";

	//index parentIndex contensIndex xobjectID xobjectIndex
	static String PDF_PAGES_KID = "%d 0 obj\n<<\n/Type /Page\n/Parent %d 0 R\n/MediaBox [0 0 595.276 841.890]\n/CropBox [0 0 595.276 841.890]\n/Contents %d 0 R\n/Resources <<\n/XObject <<\n/%s %d 0 R >>\n/ProcSet [/ImageC]\n>>\n>>\nendobj\n";

	//index lengthIndex width height xoffset yoffset xobjectID
	static String PDF_CONTENTS = "%d 0 obj\n<</Length %d 0 R\n>>\nstream\nq  %3.3f 0.0000 0.0000 %3.3f %3.3f %3.3f cm /%s Do Q\nendstream\nendobj\n";

	//index value
	static String PDF_LENGTH = "%d 0 obj\n%d\nendobj\n";

	//objectCount objectInfo size rootIndex InfoIndex totaoSize
	static String PDF_XREF = "xref\r\n0 %d\r\n%s\r\ntrailer\r\n<<\r\n/Size %d\r\n/Root %d 0 R\r\n/Info %d 0 R\r\n/ID[<2900000023480000FF180000FF670000><2900000023480000FF180000FF670000>]\r\n>>\r\nstartxref\r\n%d\r\n%%%%EOF";

	//index dataLen xobjectID width height BitsPerComponent
	static String PDF_JPG_DATA_HEADER = "%d 0 obj\n<<\n/Length %d\n/Type /XObject\n/Subtype /Image\n/Name /%s\n/Width %d\n/Height %d\n/BitsPerComponent %d\n/ColorSpace /DeviceRGB\n/Filter /DCTDecode >>\nstream\n";

	static String PDF_JPG_DATA_ENDER = "\nendstream\nendobj\n";

	public static void MCombineJPG2PDF(String[] jpgPath, String pdfPath) {

		File outFile = null;
		File jpgFile = null;
		File tmpFile = null;
		int i = 0;
		int j = 0;
		long fileLen = 0L;
		int pdfCount = 0;
		int count = jpgPath.length;
		StringBuffer imgID = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		StringBuffer buf = new StringBuffer();
		StringBuffer tmp = new StringBuffer();
		int objectOffset[] = new int[1024];
		int totalLen = 0, objectCount = 1;
		int width = 0, height = 0;
		double scale = 1.0;
		outFile = new File(pdfPath);
		try {
			outFile.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// write pdf header
		sprintf(tmp, PDF_HEADER, PDF_VERESION);
		totalLen += tmp.length();
		buffer.append(tmp);
		tmp = new StringBuffer();
		// write pdf catalog
		sprintf(tmp, PDF_CATALOG, objectCount, PDF_VERESION, objectCount + 2);
		objectOffset[objectCount++] = totalLen;
		totalLen += tmp.length();
		buffer.append(tmp);
		tmp = new StringBuffer();
		// write pdf info
		sprintf(tmp, PDF_INFO, objectCount, PDF_AUTHOR, PDF_CREATER, PDF_PRODUCER);
		objectOffset[objectCount++] = totalLen;
		totalLen += tmp.length();
		buffer.append(tmp);
		tmp = new StringBuffer();

		List<String> flist = new ArrayList<String>();
		List<String> tlist = new ArrayList<String>();
		List<String> dlist = new ArrayList<String>();
		//鍦ㄥ悎鎴愪箣鍓嶏紝jpgPath涓墍鏈夌殑pdf杩涜jpg杞崲鍚庝繚瀛樺埌List鍒楄〃涓鐢�
		for (i = 0; i < count; i++) {
			tmpFile = new File(jpgPath[i]);
			if (getMIMEType(tmpFile).equals("application/pdf")) {
				tlist = JPDF2JPG(tmpFile.getAbsolutePath());
				for (j = 0; j < tlist.size(); j++) {
					flist.add(tlist.get(j));
					dlist.add(tlist.get(j));
				}
			} else if (getMIMEType(tmpFile).equals("image/*")) {
				flist.add(tmpFile.getAbsolutePath());
			} else {
				System.out.println("Can't combine!");
			}
		}

		// write pdf page
		pdfCount = flist.size();
		for (j = 0; j < pdfCount; j++) {
			sprintf(buf, "%d 0 R ", j * 4 + 4);
		}
		sprintf(tmp, PDF_PAGES, objectCount, buf, pdfCount);
		objectOffset[objectCount++] = totalLen;
		totalLen += tmp.length();
		buffer.append(tmp);
		tmp = new StringBuffer();
		buf = new StringBuffer();

		for (j = 0; j < pdfCount; j++) {
			jpgFile = new File(flist.get(j));
			if (!jpgFile.exists()) {
				System.out.println("jpg file is no exist\n");
			} else {
				/*
				 * if(Util.getMIMEType(jpgFile).equals("application/pdf")){ //String jpgtmp =
				 * jpgFile.getAbsolutePath().substring(0,jpgFile.getAbsolutePath().lastIndexOf("."))+".jpg"; //System.out.println("jpgtmp="+jpgtmp);
				 * flist = JPDF2JPG(jpgFile.getAbsolutePath()); count += flist.size()-1; System.out.println(jpgtmp+" is creat ...");
				 * jpgPath[j]=jpgtmp; flist.add(jpgPath[j]); }
				 */
				sprintf(imgID, "Im%d", j + 1);
				// write pdf kids
				sprintf(tmp, PDF_PAGES_KID, objectCount, 3, objectCount + 1, imgID, objectCount + 3);
				objectOffset[objectCount++] = totalLen;
				totalLen += tmp.length();
				buffer.append(tmp);
				tmp = new StringBuffer();
				Map map = getWidthAndHeight(flist.get(j));
				width = (Integer) map.get("width");
				height = (Integer) map.get("height");
				if (width > PDF_X_PIXELS || height > PDF_Y_PIXELS) {
					if (PDF_X_PIXELS * 1.0 / width < PDF_Y_PIXELS * 1.0 / height) {
						scale = PDF_X_PIXELS * 1.0 / width;
					} else {
						scale = PDF_Y_PIXELS * 1.0 / height;
					}
				} else {
					scale = getScale(height, width);
				}

				// write pdf contents
				sprintf(tmp, PDF_CONTENTS, objectCount, objectCount + 1, width * scale, height * scale, (PDF_X_PIXELS - width * scale) / 2,
						(PDF_Y_PIXELS - height * scale) / 2, imgID);
				objectOffset[objectCount++] = totalLen;
				totalLen += tmp.length();
				buffer.append(tmp);
				tmp = new StringBuffer();

				// write pdf object length
				// PDF_LENGTH "%d 0 obj\n%d\nendobj\n"
				sprintf(tmp, PDF_LENGTH, objectCount, 60);
				objectOffset[objectCount++] = totalLen;
				totalLen += tmp.length();
				buffer.append(tmp);
				tmp = new StringBuffer();
				fileLen = jpgFile.length();

				// write data header
				sprintf(tmp, PDF_JPG_DATA_HEADER, objectCount, fileLen, imgID, width, height, 8);
				objectOffset[objectCount++] = totalLen;
				totalLen += tmp.length();
				buffer.append(tmp);
				tmp = new StringBuffer();
				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();
				imgID = new StringBuffer();
				FileInputStream fis;
				try {
					fis = new FileInputStream(flist.get(j));
					byte[] bytes = new byte[fis.available()];
					totalLen += bytes.length;
					fis.read(bytes);
					fis.close();
					appendMethodB(pdfPath, bytes);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				/**************************************************************/
				// write data ender
				sprintf(tmp, PDF_JPG_DATA_ENDER);
				totalLen += tmp.length();
				appendMethodA(pdfPath, tmp.toString());
				tmp = new StringBuffer();
			}
		}
		sprintf(buf, "%010d 65535 f\r\n", objectOffset[0], 0);

		for (i = 1; i < objectCount; i++) {
			sprintf(buf, "%010d %05d n\r\n", objectOffset[i], 0);
		}

		// write pdf xref
		sprintf(tmp, PDF_XREF, objectCount, buf, objectCount, 1, 2, totalLen);
		objectOffset[objectCount++] = totalLen;
		totalLen += tmp.length();
		appendMethodA(pdfPath, tmp.toString());
		//娓呴櫎涓存椂鐢熸垚鐨勫浘鐗�
		/*
		 * for(int k = 0; k <dlist.size(); k++){ File f = new File(dlist.get(k).toString()); f.delete();
		 * System.out.println(dlist.get(k).toString()+" is deleting ..."); }
		 */
		tmp = new StringBuffer();
		buf = new StringBuffer();
		System.out.println("CombineJPG2PDF:->" + pdfPath);
	}

	public static boolean CombineJPGToPDF(String[] jpgPath, String pdfPath, int pdfCount) {

		boolean flag = false;
		File outFile = null;
		File jpgFile = null;
		int i = 0;
		int j = 0;
		long fileLen = 0L;
		StringBuffer imgID = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		int objectOffset[] = new int[1024];
		int temp, totalLen = 0, objectCount = 1;
		int width = 0, height = 0, offset;
		StringBuffer buf = new StringBuffer();
		double scale = 1.0;
		InputStream in = null;
		outFile = new File(pdfPath);
		try {
			outFile.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		//write pdf header
		sprintf(buffer, PDF_HEADER, PDF_VERESION);
		totalLen += buffer.length();
		appendMethodA(pdfPath, buffer.toString());
		buffer = new StringBuffer();
		//write pdf catalog
		//PDF_CATALOG     "%d 0 obj\n<<\n/Type /Catalog\n/Version /%1.1f\n/Pages %d 0 R\n>>\nendobj\n"
		sprintf(buffer, PDF_CATALOG, objectCount, PDF_VERESION, objectCount + 2);
		objectOffset[objectCount++] = totalLen;
		totalLen += buffer.length();
		appendMethodA(pdfPath, buffer.toString());
		buffer = new StringBuffer();
		//write pdf info
		//PDF_INFO        "%d 0 obj\n<<\n/Author (%s)\n/Creator (%s)\n/Producer (%s)\n>>\nendobj\n"
		sprintf(buffer, PDF_INFO, objectCount, PDF_AUTHOR, PDF_CREATER, PDF_PRODUCER);
		objectOffset[objectCount++] = totalLen;
		totalLen += buffer.length();
		appendMethodA(pdfPath, buffer.toString());
		buffer = new StringBuffer();
		//write pdf page
		for (j = 0; j < pdfCount; j++) {
			sprintf(buf, "%d 0 R ", j * 4 + 4);
		}
		//PDF_PAGES       "%d 0 obj\n<<\n/Type /Pages\n/Kids [%s]\n/Count %d\n>>\nendobj\n"
		sprintf(buffer, PDF_PAGES, objectCount, buf, pdfCount);
		objectOffset[objectCount++] = totalLen;
		totalLen += buffer.length();
		appendMethodA(pdfPath, buffer.toString());
		buffer = new StringBuffer();
		buf = new StringBuffer();

		for (j = 0; j < pdfCount; j++) {
			jpgFile = new File(jpgPath[j]);
			if (!jpgFile.exists()) {
				System.out.println("jpg file is no exist\n");
			} else {
				//GetImageResolutions(jpgPath[0], &width, &height);
				sprintf(imgID, "Im%d", j + 1);

				//write pdf kids
				//PDF_PAGES_KID   "%d 0 obj\n<<\n/Type /Page\n/Parent %d 0 R\n/MediaBox [0 0 595.276 841.890]\n/CropBox [0 0 595.276 841.890]\n/Contents %d 0 R\n/Resources <<\n/XObject <<\n/%s %d 0 R >>\n/ProcSet [/ImageC]\n>>\n>>\nendobj\n"
				sprintf(buffer, PDF_PAGES_KID, objectCount, 3, objectCount + 1, imgID, objectCount + 3);
				objectOffset[objectCount++] = totalLen;
				totalLen += buffer.length();
				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();
				Map map = getWidthAndHeight(jpgPath[j]);
				width = (Integer) map.get("width");
				height = (Integer) map.get("height");

				//System.out.println("Width : Height->"+width+" : "+height);
				//scale = getPercent2(height,width);
				if (width > PDF_X_PIXELS || height > PDF_Y_PIXELS) {
					if (PDF_X_PIXELS * 1.0 / width < PDF_Y_PIXELS * 1.0 / height) {
						scale = PDF_X_PIXELS * 1.0 / width;
					} else {
						scale = PDF_Y_PIXELS * 1.0 / height;
					}
				} else {
					scale = getScale(height, width);
				}
				System.out.println("scale = " + scale);
				//write pdf contents
				//PDF_CONTENTS    "%d 0 obj\n<<\n/Length %d 0 R\n>>\nstream\nq  %3.4f 0.0000 0.0000 %3.4f %3.4f %3.4f cm /%s Do Q\nendstream\nendobj\n"
				System.out.println(width * scale + " 0.0000 0.0000 " + height * scale + " " + (PDF_X_PIXELS - width * scale) / 2 + " "
						+ (PDF_Y_PIXELS - height * scale) / 2);
				System.out.println("x = " + (width * scale * width + 0 * height + (PDF_X_PIXELS - width * scale) / 2) + " y = "
						+ (0 * width + height * scale * height + (PDF_Y_PIXELS - height * scale) / 2));
				sprintf(buffer, PDF_CONTENTS, objectCount, objectCount + 1, width * scale, height * scale, (PDF_X_PIXELS - width * scale) / 2,
						(PDF_Y_PIXELS - height * scale) / 2, imgID);
				objectOffset[objectCount++] = totalLen;
				totalLen += buffer.length();

				//System.out.println("PDF_CONTENTS->"+buffer.toString());

				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();

				//write pdf object length
				//PDF_LENGTH      "%d 0 obj\n%d\nendobj\n"
				sprintf(buffer, PDF_LENGTH, objectCount, 60);
				objectOffset[objectCount++] = totalLen;
				totalLen += buffer.length();
				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();
				fileLen = jpgFile.length();

				//write data header
				//PDF_JPG_DATA_HEADER "%d 0 obj\n<<\n/Length %l\n/Type /XObject\n/Subtype /Image\n/Name /%s\n/Width %d\n/Height %d\n/BitsPerComponent %d\n/ColorSpace /DeviceRGB\n/Filter /DCTDecode >>\nstream\n"

				sprintf(buffer, PDF_JPG_DATA_HEADER, objectCount, fileLen, imgID, width, height, 8);
				objectOffset[objectCount++] = totalLen;
				totalLen += buffer.length();

				//System.out.println("PDF_JPG_DATA_HEADER->"+buffer.toString());

				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();
				imgID = new StringBuffer();

				/**********************************************************/
				//鍐欐斁鏂囦欢娴�

				/*
				 * in = new FileInputStream(path); byte[] bytes = new byte[in.available()]; in.read(bytes); in.close();
				 */

				/*
				 * FileInputStream fis = new FileInputStream("D:\\test.bmp"); FileOutputStream fos = new FileOutputStream("D:\\test.bmp");
				 * BufferedImage img = ImageIO.read(fis); Graphics g = img.getGraphics(); g.drawLine(0, 0, 60, 60); //浣犵殑鍏跺畠缁樺浘浠ｇ爜... img.flush();
				 * g.dispose(); ImageIO.write(img, "BMP", fos);
				 */
				//bufferStream = new ByteArrayInputStream(buffer.toByteArray());

				/*
				 * 锛氭妸杈撳叆娴佽浆鎹yte[] public static byte[] InputStreamTOByte(InputStream in) throws IOException{ ByteArrayOutputStream outStream = new
				 * ByteArrayOutputStream(); byte[] data = new byte[BUFFER_SIZE]; int count = -1; while((count = in.read(data,0,BUFFER_SIZE)) != -1)
				 * outStream.write(data, 0, count); //data = null; return outStream.toByteArray(); }
				 */

				// 鎶婃枃浠舵祦杞崲鎴愭枃浠�
				/*
				 * public static void inputstreamtofile(InputStream ins,File file){ OutputStream os=null; try { os = new FileOutputStream(file); int
				 * bytesRead = 0; byte[] buffer = new byte[8192]; while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) { os.write(buffer, 0,
				 * bytesRead); } os.close(); ins.close(); } catch (FileNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace();
				 * } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
				 */

				/*
				 * 閲嶅啓鍥剧墖鏂囦欢.
				 * 
				 * File source = new File("E://1.jpg"); String type = "gif"; BufferedImage inImg = ImageIO.read(source); int wideth =
				 * inImg.getWidth(null); int height = inImg.getHeight(null); BufferedImage bimage = new
				 * BufferedImage(wideth,height,BufferedImage.SCALE_SMOOTH); bimage.getGraphics().drawImage(inImg,0,0,wideth,height,null); File outfile
				 * = new File("E://2.gif"); OutputStream out = new FileOutputStream(outfile); ImageIO.write(bimage,type,out); source.delete();
				 * out.close();
				 */

				FileInputStream fis;
				//FileOutputStream fos;
				try {
					//System.out.println("jpg"+j+"->"+jpgPath[j]);
					fis = new FileInputStream(jpgPath[j]);
					//fos = new FileOutputStream(pdfPath);
					byte[] bytes = new byte[fis.available()];
					totalLen += bytes.length;
					fis.read(bytes);
					fis.close();

					appendMethodB(pdfPath, bytes);
					//writer.write(new String(bytes));
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				/**************************************************************/
				//write data ender
				//PDF_JPG_DATA_ENDER  "\nendstream\nendobj\n"
				sprintf(buffer, PDF_JPG_DATA_ENDER);
				totalLen += buffer.length();
				appendMethodA(pdfPath, buffer.toString());
				buffer = new StringBuffer();
			}
		}
		sprintf(buf, "%010d 65535 f\r\n", objectOffset[0], 0);

		for (i = 1; i < objectCount; i++) {
			sprintf(buf, "%010d %05d n\r\n", objectOffset[i], 0);
		}

		//write pdf xref
		// PDF_XREF        "xref\r\n0 %d\r\n%s\r\ntrailer\r\n<<\r\n/Size %d\r\n/Root %d 0 R\r\n/Info %d 0 R\r\n/ID[<2900000023480000FF180000FF670000><2900000023480000FF180000FF670000>]\r\n>>\r\nstartxref\r\n%d\r\n%%%%EOF"
		sprintf(buffer, PDF_XREF, objectCount, buf, objectCount, 1, 2, totalLen);
		objectOffset[objectCount++] = totalLen;
		totalLen += buffer.length();
		appendMethodA(pdfPath, buffer.toString());
		buffer = new StringBuffer();
		buf = new StringBuffer();
		return flag;
	}

	public static boolean ParsePDFImage(String pdfPath, String jpgPath) {

		boolean result = false;
		File pdf = new File(pdfPath);
		if (!pdf.exists()) {
			System.out.println("file is not exist!");
		}
		try {
			FileReader fr = new FileReader(pdf);

			BufferedReader bf = new BufferedReader(fr);

			String context = null;
			String s[] = new String[2];
			int total = 0;
			int count = 0;
			do {
				context = bf.readLine();
				total += context.length() + 1;
				if (context.startsWith("/Length")) {
					s = context.split(" ");
					//System.out.println(context+"-->"+s[0]+" - "+s[1]);
				}
				if (context.startsWith("stream")) {
					if (count++ != 0) {
						break;
					}
				}
				//System.out.println(context +"  -->  "+total);
			} while (context != null);
			FileInputStream fis = new FileInputStream(pdfPath);
			//RandomAccessFile randomFile = new RandomAccessFile(pdfPath, "rw");
			RandomAccessFile jpgFile = new RandomAccessFile(jpgPath, "rw");
			System.out.println("s[1]-->" + s[1] + "--" + Integer.parseInt(s[1]));
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			fis.close();
			// 灏嗗啓鏂囦欢鎸囬拡绉诲埌鎸囧畾浣嶇疆
			jpgFile.write(bytes, total, Integer.parseInt(s[1]));
			jpgFile.close();
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * try { Scanner scner = new Scanner(new File("C:\\900dpi_2.pdf")); } catch (FileNotFoundException e) { e.printStackTrace(); }
		 */

		/*
		 * File file=new File("text.txt"); RandomAccessFile fileAccess=new RandomAccessFile(file,"rw"); fileAccess.seek(1);//灏嗘枃浠舵寚閽堟寚鍚戠涓�釜瀛楃
		 * System.out.println((char)fileAccess.read());//璇诲彇绗簩涓瓧鑺備綅缃笂鐨勫瓧绗� 缁撴灉:8 fileAccess.seek(1);//鍐嶅皢鏂囦欢鎸囬拡鎸囧悜绗竴涓瓧绗�
		 * fileAccess.write("5".getBytes());//鍐欏叆涓�釜瀛楃骞惰鐩栧垰鎵嶅瓧绗� fileAccess.seek(0);//灏嗘枃浠舵寚閽堟寚鍚戦涓瓧绗�
		 * System.out.println(fileAccess.readLine());//璇诲彇涓�鐩村埌閬囧埌鎹㈣绗� 缁撴灉:95624
		 */
		/*
		 * RandomAccessFile r = new RandomAccessFile(new File("c:/1.txt", "r"));//鍙鏂瑰紡鎵撳紑鏂囦欢 r.seek(100);//鎸囧畾涓嬩竴娆＄殑寮�浣嶇疆 byte[] bs = new
		 * byte[1024]; r.read(bs); r.readChar(); r.readInt();//璇诲彇涓�釜鏁存暟
		 */

		return result;
	}

	//灏嗗椤祊df杞崲涓簀pg
	public static List MPDF2JPG(String pdfPath) {

		List list = new ArrayList();
		File pdf = new File(pdfPath);
		if (!pdf.exists()) {
			System.out.println("file is not exist!");
		}
		try {
			FileReader fr = new FileReader(pdf);
			BufferedReader bf = new BufferedReader(fr);
			String context = null;
			String s[] = new String[2];
			int total = 0;
			int scount = 0;
			int ecount = 0;
			int count = 0;
			boolean sflag = false;
			boolean eflag = false;
			FileInputStream fis = new FileInputStream(pdfPath);
			byte[] bytes = new byte[fis.available()];
			//灏唒df鏁版嵁鍏ㄩ儴璇诲叆types.
			fis.read(bytes);
			fis.close();
			do {
				context = bf.readLine();
				if (context != null) {
					//System.out.println("total->"+total);
					//鑾峰埌鍥剧墖鐨勯暱搴�
					if (context.startsWith("/Length")) {
						s = context.split(" ");
					}
					//璇诲彇pdf寮�ご閮ㄥ垎瀛楃淇℃伅鐩村埌閬囧埌绗簩涓猻tream鍋滄
					if (!sflag) {
						total += context.length() + 1;
					}

					//pdf淇℃伅涓叡鏈変袱涓猻tream,鎴戜滑闇�浠庣浜屼釜缁撴潫鍚庡紑濮嬭鍙栨暟鎹�
					if (context.startsWith("stream")) {
						if (scount++ % 2 != 0) {
							sflag = true;
							eflag = false;
							RandomAccessFile jpgFile = new RandomAccessFile("D://img" + scount / 2 + ".jpg", "rw");
							//灏哹ytes涓殑鏁版嵁浠巘otal寮�,闀垮害涓簊[1]鐨勫唴瀹瑰啓鍏pgFile.
							System.out.println("total=" + total + "  s[1]=" + s[1]);
							if (count++ == 0) {
								jpgFile.write(bytes, total, Integer.parseInt(s[1]));
							} else {
								//姣忚鍙栦竴涓枃浠舵寚閽堢殑浣嶇疆灏变細鍋滅暀鍦╯tream涔嬪墠锛屾墍浠ョ疮绉捣鏉ュ氨涓�*(scount/2-1)锛屽彟澶栧洖杞︿负scount/2-1
								jpgFile.write(bytes, total + 7 * (scount / 2 - 1) + scount / 2 - 1, Integer.parseInt(s[1]) + 1);
							}
							//System.out.println("s[1]"+s[1]);
							jpgFile.close();
							//鍋舵暟浣嶄笂鐨剆tream寮�璇诲彇浜岃繘鍒舵枃浠讹紝闀垮害闇�姞涓婁簩杩涘埗鏂囦欢鐨勯暱搴︼紟
							total += Integer.parseInt(s[1]);
							System.out.println("PDF2JPG:" + pdfPath + "->" + "D://img" + scount / 2 + ".jpg");
						}
					}
					//璇诲彇瀹屼竴涓簩杩涘埗鏂囦欢鍚庯紝鍋氫竴涓爣蹇楋紝寮�鎸夎璇诲彇瀛楃璁版暟
					if (context.startsWith("endstream")) {
						if (ecount++ % 2 != 0) {
							eflag = true;
						}
					}
					//鎸夎璇诲彇瀛楃璁版暟寮�
					if (eflag) {
						total += context.length() + 1;
					}
				}
			} while (context != null);
			/**/
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//灏嗗椤祊df杞崲涓簀pg鏁寸悊閫昏緫
	public static List<String> JPDF2JPG(String pdfPath) {

		List<String> list = new ArrayList<String>();
		File pdf = new File(pdfPath);
		if (!pdf.exists()) {
			System.out.println("file is not exist!");
		}
		try {
			FileReader fr = new FileReader(pdf);
			BufferedReader bf = new BufferedReader(fr);
			String context = null;
			String s[] = new String[2];
			int total = 0;
			int scount = 0;
			int ecount = 0;
			int count = 0;
			boolean sflag = false;
			boolean eflag = false;
			FileInputStream fis = new FileInputStream(pdfPath);
			byte[] bytes = new byte[fis.available()];
			//灏唒df鏁版嵁鍏ㄩ儴璇诲叆types.
			fis.read(bytes);
			fis.close();
			do {
				context = bf.readLine();
				if (context != null) {
					//System.out.println("total->"+total);
					//鑾峰埌鍥剧墖鐨勯暱搴�
					if (context.startsWith("/Length")) {
						s = context.split(" ");
					}
					//璇诲彇pdf寮�ご閮ㄥ垎瀛楃淇℃伅鐩村埌閬囧埌绗簩涓猻tream鍋滄
					if (!sflag) {
						total += context.length() + 1;
					}
					//pdf淇℃伅涓叡鏈変袱涓猻tream,鎴戜滑闇�浠庣浜屼釜缁撴潫鍚庡紑濮嬭鍙栨暟鎹�
					if (context.startsWith("stream")) {
						if (scount++ % 2 != 0) {
							//閬囧埌鍋舵暟stream鏃讹紝鎶妔tream鍜屽洖杞︾殑闀垮害鍔犲叆杩涙潵
							//if(sflag){
							//	total += context.length()+1;
							//}
							//鏍囧織寮�璇诲彇鏂囦欢
							sflag = true;
							//
							eflag = false;
							RandomAccessFile jpgFile = new RandomAccessFile("C:\\img" + scount / 2 + ".jpg", "rw");
							list.add("C:\\img" + scount / 2 + ".jpg");
							//灏哹ytes涓殑鏁版嵁浠巘otal寮�,闀垮害涓簊[1]鐨勫唴瀹瑰啓鍏pgFile.
							System.out.println("total=" + total + "  s[1]=" + s[1]);
							if (count++ == 0) {
								jpgFile.write(bytes, total, Integer.parseInt(s[1]));
							} else {
								//姣忚鍙栦竴涓枃浠舵寚閽堢殑浣嶇疆灏变細鍋滅暀鍦╯tream涔嬪墠锛屾墍浠ョ疮绉捣鏉ュ氨涓�*(scount/2-1)锛屽彟澶栧洖杞︿负scount/2-1
								jpgFile.write(bytes, total + 7 * (scount / 2 - 1) + scount / 2 - 1, Integer.parseInt(s[1]) + 1);
							}
							//System.out.println("s[1]"+s[1]);
							jpgFile.close();
							//鍋舵暟浣嶄笂鐨剆tream寮�璇诲彇浜岃繘鍒舵枃浠讹紝闀垮害闇�姞涓婁簩杩涘埗鏂囦欢鐨勯暱搴︼紟
							total += Integer.parseInt(s[1]);
							System.out.println("PDF2JPG:" + pdfPath + "->" + "C:\\img" + scount / 2 + ".jpg");
						}
					}
					//璇诲彇瀹屼竴涓簩杩涘埗鏂囦欢鍚庯紝鍋氫竴涓爣蹇楋紝寮�鎸夎璇诲彇瀛楃璁版暟
					if (context.startsWith("endstream")) {
						if (ecount++ % 2 != 0) {
							eflag = true;
						}
					}
					//鎸夎璇诲彇瀛楃璁版暟寮�
					if (eflag) {
						total += context.length() + 1;
					}
				}
			} while (context != null);
			/**/
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

		/*
		 * StringBuffer sb=new StringBuffer(); sprintf(sb, "%d, %s, asdfasdf, %s", new Object[]{1,"浣�,"濂�}); System.out.println(sb.toString());
		 */
		/*
		 * String[] jpgPath =
		 * {"C:\\0.jpg","C:\\1.jpg","C:\\2.jpg","C:\\3.jpg","C:\\4.jpg","C:\\5.jpg","C:\\6.jpg","C:\\7.jpg","C:\\8.jpg","C:\\9.jpg"}; String pdfPath =
		 * "C:\\pdfcombine09.pdf"; int pdfCount =9; CombineJPGToPDF(jpgPath,pdfPath,pdfCount);
		 */

		String[] jpgPath = {"D://aa.jpg"};
		String pdfPath = "D://cc.pdf";
		//MCombineJPG2PDF(jpgPath, pdfPath);

		MPDF2JPG("D://cc.pdf");
		//JPDF2JPG("C:\\pdfcombine09.pdf");
		/*
		 * File f = new File("C:\\sd.jpg"); ParsePDFImage("C:\\900dpi_2.pdf","C:\\sd.jpg");
		 */
		/*
		 * try { FileRead("C:\\900dpi_2.pdf"); } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	private static void sprintf(StringBuffer sb, String formats, Object... replaceString) {

		Object[] objects = new Object[replaceString.length];
		for (int i = 0; i < replaceString.length; i++) {
			objects[i] = (Object) replaceString[i];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(out);
		System.out.printf(formats, objects);
		byte[] byteArray = baos.toByteArray();
		System.setOut(old);
		sb.append(new String(byteArray));
	}

	public static void appendMethodA(String fileName, String content) {

		try {
			// 鎵撳紑涓�釜闅忔満璁块棶鏂囦欢娴侊紝鎸夎鍐欐柟寮�
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 鏂囦欢闀垮害锛屽瓧鑺傛暟
			long fileLength = randomFile.length();
			// 灏嗗啓鏂囦欢鎸囬拡绉诲埌鏂囦欢灏俱�
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendMethodB(String fileName, byte content[]) {

		try {
			// 鎵撳紑涓�釜闅忔満璁块棶鏂囦欢娴侊紝鎸夎鍐欐柟寮�
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 鏂囦欢闀垮害锛屽瓧鑺傛暟
			long fileLength = randomFile.length();
			// 灏嗗啓鏂囦欢鎸囬拡绉诲埌鏂囦欢灏俱�
			randomFile.seek(fileLength);
			randomFile.write(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map getWidthAndHeight(String imgpath) {

		Map map = new HashMap();
		File source = new File(imgpath);
		BufferedImage inImg = null;
		try {
			inImg = ImageIO.read(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = inImg.getWidth(null);
		int height = inImg.getHeight(null);
		map.put("width", width);
		map.put("height", height);
		System.out.println("width : height->" + width + " : " + height);
		return map;
	}

	//鑾峰緱鏂囦欢绫诲瀷
	public static String getMIMEType(File file) {

		// TODO Auto-generated method stub
		String type = "";
		String fName = file.getName();
		String endName1 = (String) fName.subSequence(fName.lastIndexOf(".") + 1, fName.length());
		String endName = endName1.toLowerCase();
		if (!endName.equals("apk")) {
			if (endName.equals("mp3") || endName.equals("m4a") || endName.equals("mid") || endName.equals("xmf") || endName.equals("ogg")
					|| endName.equals("wav")) {
				type = "audio";
			} else if (endName.equals("3gp") || endName.equals("mp4")) {
				type = "video";
			} else if (endName.equals("jpg") || endName.equals("gif") || endName.equals("png") || endName.equals("jpeg") || endName.equals("bmp")) {
				type = "image";
			} else if (endName.equals("pdf")) {
				type = "application/pdf";
				return type;
			} else {
				type = "*";
			}
			type += "/*";
		} else {
			type = "application/vnd.android.package-archive";
		}

		return type;
	}

	static int FileRead(String path) throws IOException {

		//System.out.println("璇ユ枃浠剁殑鍐呭濡備笅锛�);

		File file = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] cFis = new byte[1024];

			int n = fis.read(cFis, 0, 1024);

			while (n != -1) {

				System.out.println(new String(cFis, 0, n, "gbk"));
				n = fis.read(cFis, 0, n);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) fis.close();
		}

		return 0;
	}

	public static void testReadFile() {

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("c:/out.txt", "r");
			//璺宠繃瀛楄妭鏁�
			raf.skipBytes(32);
			//璇诲彇涓�釜鏁板瓧
			int num = raf.readInt();
			System.out.println(num);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getPercent2(float h, float w) {

		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}

	public static int getPercent(float h, float w) {

		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	public static int getScale(int h, int w) {

		int p = 0;
		float p2 = 0.0f;
		p2 = PDF_X_PIXELS / w;
		p = Math.round(p2);
		return p;
	}
}
