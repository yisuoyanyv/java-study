package coreJavaVolume2AdvancedFeatures.regex;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangjinglong
 * @date 2020-03-18-11:51 下午
 * <p>
 * This program displays all URLs in a web page by matching a regular expression
 * that describes the <a herf=...> HTML tag. Start the programs as
 * java HrefMatch URL
 */

public class HrefMatch {
    public static void main(String[] args) {
        try {
            //get URL string from command line or use default;
            String urlString;
            if (args.length > 0) urlString = args[0];
            else urlString = "http://www.baidu.com";

            //open reader for URL
            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream(), StandardCharsets.UTF_8);

            //read contents into string builder
            StringBuilder input = new StringBuilder();
            int ch;
            while ((ch = in.read()) != -1)
                input.append((char) ch);

            //search for all occurences of pattern
            String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String match = matcher.group();
                System.out.println(match);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

