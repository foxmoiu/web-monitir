//�ع���1.�ܲ����������ʱ��ͻ����Ҫ��õ���Ϣ����Ϊԭʼ�Ƚϵ��ַ���
//     2.��д��


//flow : ���Լ�һ�ݳ��ͣ�������ʵ�ֶ��˷��ʼ������ֱ仯���б仯�˲ŷ��ʼ�
package sendEmail;

//import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.io.IOException;

//import javax.mail.*;

//import javax.mail.internet.*;
//import java.io.UnsupportedEncodingException;
//import javax.mail.Message;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
//import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.*;

//import javax.activation.DataHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeBodyPart;



//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.safety.Whitelist;

//import java.io.File;
/**
 * JavaMail �汾: 1.6.0
 * JDK �汾: JDK 1.7 ���ϣ����룩
 */
public class SendEmail {

    // �����˵� ���� �� ���루�滻Ϊ�Լ�����������룩
    // PS: ĳЩ���������Ϊ���������䱾������İ�ȫ�ԣ��� SMTP �ͻ��������˶������루�е������Ϊ����Ȩ�롱��, 
    //     ���ڿ����˶������������, ����������������ʹ������������루��Ȩ�룩��
    public static String myEmailAccount = "13609119670@163.com";
    public static String myEmailPassword = "53363222zZ";

    // ����������� SMTP ��������ַ, ����׼ȷ, ��ͬ�ʼ���������ַ��ͬ, һ��(ֻ��һ��, ���Ǿ���)��ʽΪ: smtp.xxx.com
    // ����163����� SMTP ��������ַΪ: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";//smtp.163.com

    // �ռ������䣨�滻Ϊ�Լ�֪������Ч���䣩
    public static String receiveMailAccount = "1172765646@qq.com";
    public static String conservelineText = "";
    public static String detectedChangeSV = "";
   
    public static void main(String[] args) throws Exception {
    //Boolean	openFlag = false;
    Boolean initiateFlag = true;//��ʼ����һ�ζ�ȡ������
    //int testStep = 0;
    
    while(true) {
    goonto:
    while(true) {
    	Thread.sleep(3000); 
    	//ץȡ��ҳ�����ݱ��浽������
    	try {  
            //��������  
            //URL url = new URL("http://blog.csdn.net/HLK_1135");
   		 	File writename = new File(".\\output2333333333333.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�
   		 	writename.createNewFile(); // �������ļ�
   		 	BufferedWriter out = new BufferedWriter(new FileWriter(writename));
   		 	
   		 	URL url = new URL("http://dean.xjtu.edu.cn/index.htm"); 
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //��ȡ������  
            InputStream input = httpUrlConn.getInputStream();
            //���ֽ�������ת��Ϊ�ַ�������
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //Ϊ�ַ���������ӻ���
            BufferedReader br = new BufferedReader(read);  
            // ��ȡ���ؽ��  
            String data = br.readLine();
            while(data!=null)  {
            	out.write(data+"\r\n"); // \r\n��Ϊ����      д����ҳԴ�뵽�ļ���
                //System.out.println(data);
                data=br.readLine();
                out.flush(); // �ѻ���������ѹ���ļ�
            }  
            // �ͷ���Դ  
            br.close();  
            read.close();  
            input.close();  
            httpUrlConn.disconnect();
            out.close(); // ���ǵùر��ļ�
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
    		e.printStackTrace();
    	}

   	 //��ȡ�ղ�ץ�����ݣ�ָ�����֣����б仯���򣩱����仯�Ĳ���
   		try { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw
	   	 
			
			String pathname = ".\\output2333333333333.txt"; // ����·�������·�������ԣ������Ǿ���·����д���ļ�ʱ��ʾ���·��
			File filename = new File(pathname); // Ҫ��ȡ����·����input��txt�ļ�
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(filename)); // ����һ������������reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			String lineText = "";
			
			lineText = br.readLine();
			Boolean flag = true;
			stop:
			while (lineText != null && flag) {
				
				Pattern pattern=Pattern.compile("(<UL class=\"xstz_list_ul\">)");//������ʽ ƥ��
				Matcher matcher= pattern.matcher(lineText);
				while(matcher.find()){
					detectedChangeSV = lineText;
					System.out.println(lineText);
					System.out.println("&&&&" + detectedChangeSV);
					flag = false;
					break  stop;
				}
				//
				lineText = br.readLine(); // һ�ζ���һ������	
			}
			
			br.close();
			
			if(initiateFlag) {
				
				conservelineText = detectedChangeSV ;
				initiateFlag = false;	
			}
			
			if(detectedChangeSV.equals(conservelineText)) {
				System.out.println("@@#����%����&&����%��#��&����%��#"+"detectedChangeSV.equals(conservelineText)"+detectedChangeSV.equals(conservelineText));
				continue;
				//break gotoon;
			}
			
			conservelineText = detectedChangeSV;
			//openFlag = true;
			
			break goonto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   	 //���������䴫�������
   	 
        // 1. ������������, ���������ʼ��������Ĳ�������
        Properties props = new Properties();                    // ��������
        props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤

        // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
        //     ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
        //     ������ /* ... */ ֮���ע�ʹ���, ���� SSL ��ȫ���ӡ�
        /*
        // SMTP �������Ķ˿� (�� SSL ���ӵĶ˿�һ��Ĭ��Ϊ 25, ���Բ����, ��������� SSL ����,
        //                  ��Ҫ��Ϊ��Ӧ����� SMTP �������Ķ˿�, ����ɲ鿴��Ӧ�������İ���,
        //                  QQ�����SMTP(SLL)�˿�Ϊ465��587, ������������ȥ�鿴)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. �������ô����Ự����, ���ں��ʼ�����������
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log

        // 3. ����һ���ʼ�
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. ���� Session ��ȡ�ʼ��������
        Transport transport = session.getTransport();

        // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
        // 
        //    PS_01: �ɰܵ��жϹؼ��ڴ�һ��, ������ӷ�����ʧ��, �����ڿ���̨�����Ӧʧ��ԭ��� log,
        //           ��ϸ�鿴ʧ��ԭ��, ��Щ����������᷵�ش������鿴�������͵�����, ���ݸ����Ĵ���
        //           ���͵���Ӧ�ʼ��������İ�����վ�ϲ鿴����ʧ��ԭ��
        //
        //    PS_02: ����ʧ�ܵ�ԭ��ͨ��Ϊ���¼���, ��ϸ������:
        //           (1) ����û�п��� SMTP ����;
        //           (2) �����������, ����ĳЩ���俪���˶�������;
        //           (3) ���������Ҫ�����Ҫʹ�� SSL ��ȫ����;
        //           (4) �������Ƶ��������ԭ��, ���ʼ��������ܾ�����;
        //           (5) ������ϼ��㶼ȷ������, ���ʼ���������վ���Ұ�����
        //
        //    PS_03: ��ϸ��log, ���濴log, ����log, ����ԭ����log��˵����
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
        transport.sendMessage(message, message.getAllRecipients());

        // 7. �ر�����
        transport.close();
    }
    }
    /**
     * ����һ��ֻ�����ı��ļ��ʼ�
     *
     * @param session �ͷ����������ĻỰ
     * @param sendMail ����������
     * @param receiveMail �ռ�������
     * @return
     * @throws Exception
     */
    
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: �����ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
        message.setFrom(new InternetAddress(sendMail, "mmpmmp", "UTF-8"));
        

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        /**
        * �����ռ��˵�ַ���������Ӷ���ռ��ˡ����͡����ͣ�����������һ�д�����д����
        * MimeMessage.RecipientType.TO:����
        * MimeMessage.RecipientType.CC������
        * MimeMessage.RecipientType.BCC������
        */
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "hhy", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(myEmailAccount, "hhy", "UTF-8"));//���Լ�һ�����ͣ��������  554 DT:SPM smtp12
        
        // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
        message.setSubject("����", "UTF-8");

        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
        //String a = new String(conservelineText);
        message.setContent(conservelineText, "text/html;charset=UTF-8");
//        System.out.println("mmp"+conservelineText+"mmp");
        // 6. ���÷���ʱ��
        message.setSentDate(new Date());

        // 7. ��������
        message.saveChanges();

        return message;
    
    }

}
