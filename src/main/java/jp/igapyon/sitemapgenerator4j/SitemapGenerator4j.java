/*
 * Copyright 2020 Toshiki Iga
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.igapyon.sitemapgenerator4j;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Sitemap Generator for Java.
 * 
 * @see https://www.sitemaps.org/protocol.html
 * @author Toshiki Iga
 */
public class SitemapGenerator4j {
    public void write(SitemapInfo entry, File fileWrite) throws IOException {
        try {
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();

            Element eleRoot = document.createElement("urlset");
            document.appendChild(eleRoot);
            eleRoot.setAttribute("xmlns", "http://www.sitemaps.org/schemas/sitemap/0.9");

            for (SitemapInfoUrl url : entry.getUrlList()) {
                {
                    Element eleUrl = document.createElement("url");
                    eleRoot.appendChild(eleUrl);

                    {
                        Element eleLoc = document.createElement("loc");
                        eleUrl.appendChild(eleLoc);
                        eleLoc.appendChild(document.createTextNode(url.getLoc()));
                    }

                    if (url.getLastmod() != null) {
                        Element eleLoc = document.createElement("lastmod");
                        eleUrl.appendChild(eleLoc);

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                        eleLoc.appendChild(document.createTextNode(df.format(url.getLastmod())));
                    }

                    if (url.getChangefreq() != null) {
                        Element eleLoc = document.createElement("changefreq");
                        eleUrl.appendChild(eleLoc);
                        eleLoc.appendChild(document.createTextNode(url.getChangefreq()));
                    }

                    if (url.getPriority() != null) {
                        Element eleLoc = document.createElement("priority");
                        eleUrl.appendChild(eleLoc);
                        eleLoc.appendChild(document.createTextNode(url.getPriority()));
                    }
                }
            }

            {
                // write xml
                final Transformer transformer = TransformerFactory.newInstance().newTransformer();
                final DOMSource source = new DOMSource(document);
                final OutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileWrite));
                final StreamResult target = new StreamResult(outStream);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, target);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        } catch (TransformerFactoryConfigurationError ex) {
            ex.printStackTrace();
        }
    }
}