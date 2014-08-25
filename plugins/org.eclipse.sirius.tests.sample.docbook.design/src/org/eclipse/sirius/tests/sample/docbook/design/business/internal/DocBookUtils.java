/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook.design.business.internal;

import java.util.Iterator;

import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Sect2;

/**
 * @author lredor
 * 
 */
public class DocBookUtils {
    /**
     * @param book
     *            The book containing the chapter
     * @param chapterToMove
     *            The chapter to move
     * @param moveBeforeThis
     *            The chapter before which must be moved the chapter to move
     */
    public void moveChapterBeforeAnother(Book book, Chapter chapterToMove, Chapter moveBeforeThis) {
        // Search the position of the chapter moveBeforeThis
        int position = 0;
        boolean found = false;
        for (Iterator<Chapter> iterator = book.getChapter().iterator(); iterator.hasNext() && !found;) {
            Chapter chapter = iterator.next();
            if (chapter.equals(moveBeforeThis)) {
                found = true;
            } else {
                position++;
            }
        }
        // Move the chapter chapterToMove
        book.getChapter().move(position, chapterToMove);
    }

    /**
     * @param chapter
     *            The chapter containing the big sections
     * @param bigSectionToMove
     *            The big section to move
     * @param moveBeforeThis
     *            The big section before which must be moved the big section to
     *            move
     */
    public void moveBigSectionBeforeAnother(Chapter chapter, Sect1 bigSectionToMove, Sect1 moveBeforeThis) {
        // Search the position of the big section moveBeforeThis
        int position = 0;
        boolean found = false;
        for (Iterator<Sect1> iterator = chapter.getSect1().iterator(); iterator.hasNext() && !found;) {
            Sect1 bigSection = iterator.next();
            if (bigSection.equals(moveBeforeThis)) {
                found = true;
            } else {
                position++;
            }
        }
        // Move the big section bigSectionToMove
        chapter.getSect1().move(position, bigSectionToMove);
    }

    /**
     * @param bigSection
     *            The big section containing the medium sections
     * @param mediumSectionToMove
     *            The medium section to move
     * @param moveBeforeThis
     *            The medium section before which must be moved the medium
     *            section to move
     */
    public void moveMediumSectionBeforeAnother(Sect1 bigSection, Sect2 mediumSectionToMove, Sect2 moveBeforeThis) {
        // Search the position of the medium section moveBeforeThis
        int position = 0;
        boolean found = false;
        for (Iterator<Sect2> iterator = bigSection.getSect2().iterator(); iterator.hasNext() && !found;) {
            Sect2 mediumSection = iterator.next();
            if (mediumSection.equals(moveBeforeThis)) {
                found = true;
            } else {
                position++;
            }
        }
        // Move the medium section mediumSectionToMove
        bigSection.getSect2().move(position, mediumSectionToMove);
    }

    /**
     * @param paragraphe
     *            The paragraph to truncate
     * @param nbChars
     *            The maximum chars to return
     * @return the beginning of paragraph
     */
    public String getBeginningOfParagraph(Para paragraph, int nbChars) {
        String result = "";
        String paragraphContent = paragraph.getData();
        if (paragraphContent != null) {
            if (paragraphContent.length() < nbChars) {
                result = paragraphContent;
            } else {
                result = paragraphContent.substring(0, nbChars);
                result += "...";
            }
        }
        return result;
    }
}
