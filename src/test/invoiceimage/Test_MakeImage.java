package test.invoiceimage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

public class Test_MakeImage {

	private Font font = new Font("sans-serif", Font.BOLD, 12);// 添加字体的属性设置sans-serif

	private Graphics2D g = null;

	/**
	 * 修改图片,返回修改后的图片缓冲区
	 */
	public BufferedImage modifyImage(BufferedImage img, JSONObject json) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			System.out.println("宽=" + w + "，长=" + h);
			g = img.createGraphics();
			//g.setBackground(Color.WHITE);
			g.setColor(Color.black);//设置字体颜色
			g.setStroke(new BasicStroke(15.5F));

			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

			if (this.font != null) g.setFont(this.font);
			Set es = json.entrySet();
			Iterator it = es.iterator();
			JSONObject jsonv = null;
			while (it.hasNext()) {
				Entry en = (Entry) it.next();
				jsonv = (JSONObject) en.getValue();
				if (!jsonv.containsKey("content")) continue;
				System.out.println(jsonv.get("content") + "--" + jsonv.getInt("width") + "---" + jsonv.getInt("high"));
				g.drawString(jsonv.getString("content"), jsonv.getInt("width"), jsonv.getInt("high") + 11);
			}
			g.setColor(new Color(153, 6, 10));
			JSONObject totaljson = json.getJSONObject("totaljson");
			g.drawString(totaljson.getString("content"), totaljson.getInt("width"), totaljson.getInt("high") + 11);
			//g.drawString("asdf", 50, 50);
			g.dispose();
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}

	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {

		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {

		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws Exception {

		Test_MakeImage mi = new Test_MakeImage();
		BufferedImage d = mi.loadImageLocal("D:\\22.jpg");
		//			BufferedImage b = tt
		//					.loadImageLocal("E:\\文件(word,excel,pdf,ppt.txt)\\zte-logo.png");
		Test_jsoup js = new Test_jsoup();
		JSONObject json = js.parseHTML();
		mi.writeImageLocal("D:\\ccd.jpg", mi.modifyImage(d, json));
	}
}
