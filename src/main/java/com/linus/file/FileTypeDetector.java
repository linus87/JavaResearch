package com.linus.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileTypeDetector {

    private static Map<String,String> head2FileType = new HashMap<String,String>();  
    static{  
	head2FileType.put("00000000", "txt");
	head2FileType.put("FFD8FFE0", "jpeg");
        head2FileType.put("FFD8FFE1", "jpg");
        head2FileType.put("89504E47", "png");  
        head2FileType.put("47494638 ", "gif");  
        head2FileType.put("49492A00", "tif");  
        head2FileType.put("424D", "bmp");  
        head2FileType.put("41433130", "dwg");  
        head2FileType.put("38425053 ", "psd");  
        head2FileType.put("7B5C727466", "rtf");  
        head2FileType.put("3C3F786D6C", "xml");  
        head2FileType.put("68746D6C3E ", "html");  
        head2FileType.put("44656C69766572792D646174", "eml");  
        head2FileType.put("CFAD12FEC5FD746F ", "dbx");  
        head2FileType.put("2142444E", "pst");  
        head2FileType.put("D0CF11E0", "xls/doc");  
        head2FileType.put("5374616E64617264204A", "mdb");  
        head2FileType.put("FF575043", "wpd");  
        head2FileType.put("252150532D41646F6265", "eps/ps");  
        head2FileType.put("255044462D312E", "pdf");  
        head2FileType.put("E3828596", "pwl");  
        head2FileType.put("504B0304", "zip/pptx/docs");
        head2FileType.put("52617221", "rar");  
        head2FileType.put("57415645", "wav");  
        head2FileType.put("41564920", "avi");  
        head2FileType.put("2E7261FD", "ram");  
        head2FileType.put("2E524D46", "rm");  
        head2FileType.put("000001BA", "mpg");  
        head2FileType.put("000001B3", "mpg");  
        head2FileType.put("6D6F6F76", "mov");  
        head2FileType.put("3026B2758E66CF11", "asf");  
        head2FileType.put("4D546864", "mid");
        head2FileType.put("2253414D", "csv");
        head2FileType.put("3C3F786D", "jnlp");
    }  
      
    private static String bytesToHexString(String fileName) throws IOException{  
        FileInputStream fis = null;  
        StringBuilder stringBuilder = new StringBuilder();  
        try{  
            fis = new FileInputStream(new File(fileName));  
            byte[] b = new byte[4];  
            fis.read(b, 0, b.length);  
  
            for (int i = 0; i < b.length; i++) {  
                int v = b[i] & 0xFF;  
                String hv = Integer.toHexString(v);  
                if (hv.length() < 2) {  
                    stringBuilder.append(0);  
                }
                stringBuilder.append(hv);  
            }
            System.out.println(stringBuilder.toString().toUpperCase());
        }finally{  
            if(fis != null)  
                fis.close();  
        }  
        return stringBuilder.toString().toUpperCase();  
    }  
      
    public static String fileType(String fileName) throws IOException{  
        String head = bytesToHexString(fileName);  
        return head2FileType.get(head);  
    }  
      
    public static void main(String[] args) throws IOException {  
        System.out.println(fileType("C://Linus/Tech/org-netbeans-modules-html-parser.jnlp"));  
    }  
}
