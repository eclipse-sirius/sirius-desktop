/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.common.tools.api.util.FileUtil;
import org.junit.Test;

public class FileUtilTest {

    @Test
    public void checkValidity() {
        FileUtil util = new FileUtil("example.test");
        assertTrue(util.isValid());
    }

    @Test
    public void checkValidityWIthSlash() {
        FileUtil util = new FileUtil("example/.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthAntislash() {
        FileUtil util = new FileUtil("example\\.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthLeftTag() {
        FileUtil util = new FileUtil("example<.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthRightTag() {
        FileUtil util = new FileUtil("example>.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthColon() {
        FileUtil util = new FileUtil("example:.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthQuote() {
        FileUtil util = new FileUtil("example\".test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthStar() {
        FileUtil util = new FileUtil("example*.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthPipe() {
        FileUtil util = new FileUtil("example|.test");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthQuestionMark() {
        FileUtil util = new FileUtil("example?.aird");
        assertFalse(util.isValid());
    }

    @Test
    public void checkValidityWIthAWindowsReservedKeyword() {
        FileUtil util = new FileUtil("com2");
        assertFalse(util.isValid());
    }

    @Test
    public void getNoError() {
        FileUtil util = new FileUtil("example.test");
        util.isValid();
        assertEquals(FileUtil.InvalidFilenameError.NO_ERROR, util.getError());
    }

    @Test
    public void getIllegalCharError() {
        FileUtil util = new FileUtil("example/.test");
        util.isValid();
        assertEquals(FileUtil.InvalidFilenameError.ILLEGAL_CHARACTERS, util.getError());
    }

    @Test
    public void getReservedKeywordError() {
        FileUtil util = new FileUtil("com1");
        util.isValid();
        assertEquals(FileUtil.InvalidFilenameError.RESERVED_FILENAME, util.getError());
    }

    @Test
    public void identity() {
        FileUtil util = new FileUtil("example.aird");
        util.isValid();
        assertTrue(new FileUtil(util.getValidFilename()).isValid());
    }

    @Test
    public void getValidFilename() {
        FileUtil util = new FileUtil("/?:*aazz.aird");
        assertFalse(util.isValid());
        assertTrue(new FileUtil(util.getValidFilename()).isValid());
    }

    @Test
    public void getValidFilenameWithALongFilename() {
        FileUtil util = new FileUtil("/**********" + "******************************" + "*******************************" + "*******************************" + "*******************************"
                + "******************************** " + "********************************" + "********************************" + "*********************************"
                + "********************************" + "*********************************" + "**************hgffffffffffffftdddddf");
        assertFalse(util.isValid());
        assertTrue(new FileUtil(util.getValidFilename()).isValid());
    }

}
