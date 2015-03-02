/*
 * Copyright (c) 2005 Meding Software Technik - All Rights Reserved.
 *
 * $Id$
 */

package com.uml.fx.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 *  HTML helper functions
 *
 *  @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class HtmlHelper implements TextConverter {

    private boolean	_bodyFound = false;
    private boolean	_inBody = false;
    private boolean	_isPre = false;
    private String	_href = "";

    private static final HtmlHelper helper;

    static {
	helper = new HtmlHelper();
    }

    /**
     *  Construct a html to text converter
     *  @param 
     *  @return 
     */
    private HtmlHelper() {
    }

    /**
     * Get the helper singleton
     * @return the singleton
     */
    public static HtmlHelper getInstance() {
	return(helper);
    }

    /**
     * Extract the text from an html string.
     *
     * @param  source is the source html text
     * @return the plain text
     */
    public String convert(String source) {

        StringBuffer result = new StringBuffer();
        StringBuffer result2 = new StringBuffer();
        StringReader input = new StringReader(source);

        try {
            String text = null;
            int c = input.read();

            while(c != -1) {
                // Convert until EOF

                text = "";
                if(c == '<') {
                    // It's a tag!!

                    String currentTag = getTag(input);
                    // Get the rest of the tag
                    text = convertTag(currentTag);
                }
                else if(c == '&') {
                    String specialchar = getSpecial(input);
                    if(specialchar.equals("lt;") || specialchar.equals("#60"))
                        text = "<";
                    else if(specialchar.equals("gt;") || specialchar.equals("#62"))
                        text = ">";
                    else if(specialchar.equals("amp;") || specialchar.equals("#38"))
                        text = "&";
                    else if(specialchar.equals("nbsp;"))
                        text = " ";
                    else if(specialchar.equals("quot;") || specialchar.equals("#34"))
                        text = "\"";
                    else if(specialchar.equals("copy;") || specialchar.equals("#169"))
                        text = "[Copyright]";
                    else if(specialchar.equals("reg;") || specialchar.equals("#174"))
                        text = "[Registered]";
                    else if(specialchar.equals("trade;") || specialchar.equals("#153"))
                        text = "[Trademark]";
                    else
                        text = "&" + specialchar;
                }
                else if(!_isPre && Character.isWhitespace((char) c)) {
                    StringBuffer s = _inBody ? result : result2;
                    if(s.length() > 0 && Character.isWhitespace(s.charAt(s.length() - 1)))
                        text = "";
                    else
                        text = " ";
                }
                else
                    text = "" + (char) c;

                StringBuffer s = _inBody ? result : result2;
                s.append(text);

                c = input.read();
            }
        } catch(Exception e) {
            input.close();
            throw new RuntimeException(e);
        }

        StringBuffer s = _bodyFound ? result : result2;
        return(s.toString().trim());
    }


    /**
     * Extract a tag from the input stream.
     *
     * @param  r is the input reader
     * @return  the tag
     */
    private String getTag(Reader r) throws Exception {
        StringBuffer result = new StringBuffer();
        int level = 1;

        result.append('<');
        while(level > 0) {
            int c = r.read();
            if(c == -1)
                break;
            // EOF
            result.append((char) c);
            if(c == '<')
                level++;
            else if(c == '>')
                level--;
        }

        return(result.toString());
    }


    /**
     * Read a character entity from the input stream.
     *
     * @param  r input reader
     * @return the char entity
     */
    private String getSpecial(Reader r) throws Exception {
        StringBuffer result = new StringBuffer();
        r.mark(1);
        //Mark the present position in the stream
        int c = r.read();

        while(Character.isLetter((char) c)) {
            result.append((char) c);
            r.mark(1);
            c = r.read();
        }

        if(c == ';')
            result.append(';');
        else
            r.reset();

        return(result.toString());
    }


    /**
     * Are we inside a tag?
     *
     * @param text is the html text
     * @param tag is the tag
     * @return true or false
     */
    private boolean isTag(String text, String tag) {
        text = text.toLowerCase();
        String t1 = "<" + tag.toLowerCase() + ">";
        String t2 = "<" + tag.toLowerCase() + " ";

        return(text.startsWith(t1) || text.startsWith(t2));
    }


    /**
     * Convert a tag into something plain text equivalent.
     *
     * @param  t is the tag
     */
    private String convertTag(String t) throws IOException {
        String result = "";

        if(isTag(t, "body")) {
            _inBody = true;
            _bodyFound = true;
        }
        else if(isTag(t, "/body")) {
            _inBody = false;
            result = "\n";
        }
        else if(isTag(t, "center")) {
            result = "\n";
        }
        else if(isTag(t, "/center")) {
            result = "\n";
        }
        else if(isTag(t, "pre")) {
            result = "\n";
            _isPre = true;
        }
        else if(isTag(t, "/pre")) {
            result = "\n";
            _isPre = false;
        }
        else if(isTag(t, "p"))
            result = "\n";
        else if(isTag(t, "br"))
            result = "\n";
        else if(isTag(t, "h1") || isTag(t, "h2") ||
                isTag(t, "h3") || isTag(t, "h4") || isTag(t, "h5") || isTag(t, "h6") ||
                isTag(t, "h7"))
            result = "\n";
        else if(isTag(t, "/h1") || isTag(t, "/h2") ||
                isTag(t, "/h3") || isTag(t, "/h4") || isTag(t, "/h5") || isTag(t, "/h6") ||
                isTag(t, "/h7"))
            result = "\n";
        else if(isTag(t, "/dl"))
            result = "\n";
        else if(isTag(t, "dd"))
            result = "\n* ";
        else if(isTag(t, "dt"))
            result = "      ";
        else if(isTag(t, "li"))
            result = "\n* ";
        else if(isTag(t, "/ul"))
            result = "\n";
        else if(isTag(t, "/ol"))
            result = "\n";
        else if(isTag(t, "hr"))
            result = "_________________________________________\n";
        else if(isTag(t, "table"))
            result = "\n";
        else if(isTag(t, "/table"))
            result = "\n";
        else if(isTag(t, "form"))
            result = "\n";
        else if(isTag(t, "/form"))
            result = "\n";
        else if(isTag(t, "b"))
            result = "*";
        else if(isTag(t, "/b"))
            result = "*";
        else if(isTag(t, "i"))
            result = "\"";
        else if(isTag(t, "/i"))
            result = "\"";
        else if(isTag(t, "img")) {
            int idx = t.indexOf("alt=\"");
            if(idx != -1) {
                idx += 5;
                int idx2 = t.indexOf("\"", idx);
                result = t.substring(idx, idx2);
            }
        }
        else if(isTag(t, "a")) {
            int idx = t.indexOf("href=\"");
            if(idx != -1) {
                idx += 6;
                int idx2 = t.indexOf("\"", idx);
                _href = t.substring(idx, idx2);
            }
            else
                _href = "";

        }
        else if(isTag(t, "/a")) {
            if(_href.length() > 0) {
                result = " [ " + _href + " ]";
                _href = "";
            }
        }

        return(result);
    }

//
//    /**
//     *  Description of the Method
//     *
//     * @param  argv           Description of the Parameter
//     * @exception  Exception  Description of the Exception
//     */
//    public static void main(String argv[]) throws Exception {
//        FileInputStream fis = null;
//        String s = null;
//
//        try {
//            File file;
//            if(argv[0] != null)
//                file = new File(argv[0]);
//            else
//                file = new File("xx.html");
//            fis = new FileInputStream(file);
//            byte buf[] = new byte[fis.available()];
//
//            fis.read(buf);
//            fis.close();
//            fis = null;
//            s = new String(buf);
//            HTML2Text h = new HTML2Text();
//            System.out.println(h.convert(s));
//        } catch(Exception e) {
//            if(fis != null)
//                fis.close();
//            throw e;
//        }
//    }
}
