/*
 * Copyright (c) 2007 Meding Software Technik -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * A very simple CSV reader.
 *
 * @author <a href="mailto:meding@yahoo.com">Uwe B. Meding</a>
 */
public class CSVReader implements AutoCloseable {

	private final LineNumberReader br;
	private final char separator;
	private final char quotechar;
	private final int skipLines;

	private boolean linesSkiped;
	private boolean hasNext = true;

	/**
	 * Construct a csv reader with sensable defaults
	 * @param reader is the reader
	 */
	public CSVReader(Reader reader) {
		this(reader, ',', '"', 0);
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 *
	 * @param reader the reader to an underlying CSV source.
	 * @param separator the delimiter to use for separating entries
	 * @param quotechar the character to use for quoted elements
	 * @param line the line number to skip for start reading
	 */
	public CSVReader(Reader reader, char separator, char quotechar, int line) {
		this.br = new LineNumberReader(reader);
		this.separator = separator;
		this.quotechar = quotechar;
		this.skipLines = line;
	}

	/**
	 * Reads the next line from the buffer and converts to a string array.
	 *
	 * @return a string array with each comma-separated element as a separate
	 *         entry.
	 *
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public String[] readNext() throws IOException {

		String nextLine = getNextLine();
		return hasNext ? parseLine(nextLine) : null;
	}
	
	public int getLineNumber() {
		return br.getLineNumber();
	}

	/**
	 * Reads the next line from the file.
	 *
	 * @return the next line from the file without trailing newline
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	private String getNextLine() throws IOException {
		if (!this.linesSkiped) {
			for (int i = 0; i < skipLines; i++) {
				br.readLine();
			}
			this.linesSkiped = true;
		}
		String nextLine = br.readLine();
		if (nextLine == null) {
			hasNext = false;
		}
		return hasNext ? nextLine : null;
	}

	/**
	 * Parses an incoming String and returns an array of elements.
	 *
	 * @param nextLine
	 *            the string to parse
	 * @return the comma-tokenized list of elements, or null if nextLine is null
	 * @throws IOException if bad things happen during the read
	 */
	@SuppressWarnings("unchecked")
	private String[] parseLine(String nextLine) throws IOException {

		if (nextLine == null) {
			return null;
		}

		List tokensOnThisLine = new ArrayList();
		StringBuffer sb = new StringBuffer();
		boolean inQuotes = false;
		do {

			if (inQuotes) {
				// continuing a quoted section, reappend newline
				sb.append("\n");
				nextLine = getNextLine();
				if (nextLine == null) {
					break;
				}
			}

			for (int i = 0; i < nextLine.length(); i++) {

				char c = nextLine.charAt(i);
				if (c == quotechar) {
					// this gets complex... the quote may end a quoted
					// block, or escape another quote.  do a 1-char
					// lookahead:
					if (inQuotes
							&& nextLine.length() > (i + 1)
							&& nextLine.charAt(i + 1) == quotechar) {
						// we have two quote chars in a row == one
						// quote char, so consume them both and put
						// one on the token. we do *not* exit the
						// quoted text.
						sb.append(nextLine.charAt(i + 1));
						i++;
					}
					if (inQuotes
							&& nextLine.length() > (i + 1)
							&& nextLine.charAt(i + 1) != this.separator) {
						// we have a stray quote inside a quoted area,
						// for example: "blah .25"  "
						// consume current char, do *not* exit the
						// quoted text
						sb.append(c);

					} else {
						inQuotes = !inQuotes;
						// the tricky case of an embedded quote in the
						// middle: a,bc"d"ef,g
						if (i > 2
								&& nextLine.charAt(i - 1) != this.separator
								&& nextLine.length() > (i + 1)
								&& nextLine.charAt(i + 1) != this.separator) {
							sb.append(c);
						}
					}

				} else if (c == separator && !inQuotes) {
					tokensOnThisLine.add(StringUtils.collapseWhitespace(sb.toString()));
					sb = new StringBuffer(); // start work on next token
				} else {
					sb.append(c);
				}
			}

		} while (inQuotes);

		tokensOnThisLine.add(sb.toString());
		return (String[]) tokensOnThisLine.toArray(new String[0]);

	}

	/**
	 * Closes the underlying reader.
	 *
	 * @throws IOException if the close fails
	 */
	public void close() throws IOException {
		if (br != null) {
			br.close();
		}
	}
}
