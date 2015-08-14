/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.parts;

/**
 *
 *
 */
public class DocbookViewsRepository {

    public static final int SWT_KIND = 0;

    public static final int FORM_KIND = 1;

    /**
     * Book view descriptor
     *
     */
    public static class Book {
        public static class Properties {

            public static String chapter = "docbook::Book::properties::chapter";

            public static String id = "docbook::Book::properties::id";

            public static String lang = "docbook::Book::properties::lang";

            public static String version = "docbook::Book::properties::version";

        }

    }

    /**
     * Info view descriptor
     *
     */
    public static class Info {
        public static class Properties {

            public static String author = "docbook::Info::properties::author";

            public static String date = "docbook::Info::properties::date";

            public static String pubdate = "docbook::Info::properties::pubdate";

        }

    }

    /**
     * Author view descriptor
     *
     */
    public static class Author {
        public static class Properties {

            public static String email = "docbook::Author::properties::email";

            public static String personname = "docbook::Author::properties::personname";

            public static String address = "docbook::Author::properties::address";

        }

    }

    /**
     * Chapter view descriptor
     *
     */
    public static class Chapter {
        public static class Properties {

            public static String para = "docbook::Chapter::properties::para";

            public static String sect1 = "docbook::Chapter::properties::sect1";

            public static String id = "docbook::Chapter::properties::id";

        }

    }

    /**
     * Title view descriptor
     *
     */
    public static class Title_ {
        public static class Properties {

            public static String data = "docbook::Title::properties::data";

        }

    }

    /**
     * Para view descriptor
     *
     */
    public static class Para {
        public static class Properties {

            public static String data = "docbook::Para::properties::data";

        }

    }

    /**
     * SimpleList view descriptor
     *
     */
    public static class SimpleList {
    }

    /**
     * ItemizedList view descriptor
     *
     */
    public static class ItemizedList {
        public static class Properties {

            public static String mark = "docbook::ItemizedList::properties::mark";

            public static String listitem = "docbook::ItemizedList::properties::listitem";

        }

    }

    /**
     * OrderedList view descriptor
     *
     */
    public static class OrderedList {
        public static class Properties {

            public static String numeration = "docbook::OrderedList::properties::numeration";

        }

    }

    /**
     * Sect1 view descriptor
     *
     */
    public static class Sect1 {
        public static class Properties {

            public static String para = "docbook::Sect1::properties::para";

            public static String id = "docbook::Sect1::properties::id";

            public static String sect2 = "docbook::Sect1::properties::sect2";

        }

    }

    /**
     * Sect2 view descriptor
     *
     */
    public static class Sect2 {
        public static class Properties {

            public static String para = "docbook::Sect2::properties::para";

            public static String id = "docbook::Sect2::properties::id";

            public static String sect3 = "docbook::Sect2::properties::sect3";

        }

    }

    /**
     * Emphasis view descriptor
     *
     */
    public static class Emphasis {
        public static class Properties {

            public static String remap = "docbook::Emphasis::properties::remap";

        }

    }

    /**
     * ULink view descriptor
     *
     */
    public static class ULink {
        public static class Properties {

            public static String url = "docbook::ULink::properties::url";

            public static String data = "docbook::ULink::properties::data";

        }

    }

    /**
     * Link view descriptor
     *
     */
    public static class Link {
    }

    /**
     * XRef view descriptor
     *
     */
    public static class XRef {
        public static class Properties {

            public static String linkend = "docbook::XRef::properties::linkend";

        }

    }

    /**
     * Example view descriptor
     *
     */
    public static class Example {
        public static class Properties {

            public static String para = "docbook::Example::properties::para";

        }

    }

    /**
     * Sect3 view descriptor
     *
     */
    public static class Sect3 {
        public static class Properties {

            public static String para = "docbook::Sect3::properties::para";

            public static String id = "docbook::Sect3::properties::id";

        }

    }

    /**
     * ListItem view descriptor
     *
     */
    public static class ListItem {
    }

}
