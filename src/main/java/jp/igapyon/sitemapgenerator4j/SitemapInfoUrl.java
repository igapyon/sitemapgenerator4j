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

import java.util.Date;

/**
 * Sitemap URL information.
 * 
 * @see https://www.sitemaps.org/protocol.html
 */
public class SitemapInfoUrl {
    private String loc = "http://example.com/";

    /**
     * Updated date.
     */
    private Date lastmod = null;

    /**
     * always
     * hourly
     * daily
     * weekly
     * monthly
     * yearly
     * never
     */
    private Changefreq changefreq = null;

    /**
     * 0.8
     */
    private String priority = null;

    public Changefreq getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(Changefreq changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Date getLastmod() {
        return lastmod;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public static enum Changefreq {
        Always, Hourly, Daily, Weekly, Monthly, Yearly, Never;
    }
}
