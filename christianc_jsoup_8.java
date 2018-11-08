/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package christianc_jsoup_8;

/import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

public class christianc_jsoup_8 {

/**
* @param args the command line arguments
*/
public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

//https://docs.oracle.com/cd/E19906-01/820-4916/geygn/index.html

// System.setProperty("javax.net.ssl.trustStore","/home/ddavis/Downloads");
// keytool -import -trustcacerts -alias somealias -keystore clienttrust.jks -file cert.cer

System.setProperty("javax.net.ssl.trustStore","/home/ChristianC/ChristianC_jsoup_8/saisd.jks");
//javax.net.ssl.trustStore=trustStoreFile
//javax.net.ssl.trustStorePassword=trustStorePassword
// Validate.isTrue(args.length == 1, "usage: supply url to fetch");
// String url = "http://d22.ninja/";
// SSLContext sslContext = SSLContext.getInstance("TLS");
String url = "https://news.ycombinator.com/";
//String url = "http://d22.ninja/";
// String url = args[0];
print("Fetching %s...", url);

Document doc = Jsoup.connect(url).get();
Elements links = doc.select("a[href]");
Elements media = doc.select("[src]");
Elements imports = doc.select("link[href]");

print("\nMedia: (%d)", media.size());
for (Element src : media) {
if (src.tagName().equals("img"))
print(" * %s: <%s> %sx%s (%s)",
src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
trim(src.attr("alt"), 20));
else
print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
}

print("\nImports: (%d)", imports.size());
for (Element link : imports) {
print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
}

print("\nLinks: (%d)", links.size());
for (Element link : links) {
print(" * a: <%s> (%s)", link.attr("abs:href"), trim(link.text(), 35));
}
}

private static void print(String msg, Object... args) {
System.out.println(String.format(msg, args));
}

private static String trim(String s, int width) {
if (s.length() > width)
return s.substring(0, width-1) + ".";
else
return s;
}
}
