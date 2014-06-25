package test.invoice;

import java.util.List;
import java.util.Scanner;

import test.invoice.util.Test_Invoice;

public class BeiJingGS {
	public static void main(String[] args) throws Exception {

		Test_Invoice beijingGS = new Test_Invoice();
		String codeIP = "http://www.bjtax.gov.cn/ptfp/turnyzm.jsp";
		List cookielist = beijingGS.getCode(codeIP);

		Scanner scan = new Scanner(System.in);
		String validateCode = scan.next();
		//String invoiceIP = "http://www.bjtax.gov.cn/ptfp/turna.jsp?valiNum=" + validateCode + "&fpdm=111001175021&fphm=21069446&sfzh=&fpmm=81230737&ip=10.1.100.18&kpri=2013-11-11&nsr=&isFrist=1";
		String invoiceIP = "http://www.bjtax.gov.cn/ptfp/turna.jsp?valiNum=" + validateCode + "&fpdm=111001374001&fphm=30689939&sfzh=&fpmm=33714803&ip=10.1.100.18&kpri=&nsr=&isFrist=1";
		beijingGS.getInformation(invoiceIP, cookielist);
	}
}
