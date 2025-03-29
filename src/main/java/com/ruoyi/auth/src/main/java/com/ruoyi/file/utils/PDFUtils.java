package com.ruoyi.file.utils;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayInputStream;
import java.io.File;


/**
 * @author: zhaojf
 * @Description TODO
 * @date 2022/3/30
 */
public class PDFUtils {
    private static String separator = File.separator;//文件夹路径分格符


    public static void wordToPdf(File file) {
        Document document = null;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License license = new License();
            license.setLicense(is);
//            市场局172环境字体目录 /usr/share/fontconfig/unif
//            星云平台环境字体目录 /usr/share/fonts/unif
//            FontSettings.setFontsFolder("/usr/share/fonts/unif", true);
            String fileName = file.getPath();
            System.out.println(fileName);
            document = new Document(fileName);
            String outFileName = fileName.replace(".docx", ".pdf");
            outFileName = outFileName.replace(".doc", ".pdf");
            document.save(outFileName, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        wordToPdf(new File("D://test//2227019118_林陈鹏_毕业论文(2).docx"));
        wordToPdf(new File("E:\\download\\report_1725961753431.doc"));
    }

}
