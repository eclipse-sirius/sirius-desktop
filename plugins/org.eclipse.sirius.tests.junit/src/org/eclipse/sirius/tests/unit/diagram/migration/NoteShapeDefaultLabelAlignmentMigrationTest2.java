/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.sirius.diagram.ui.business.internal.migration.NoteShapeDefaultLabelAlignmentMigrationParticipant;

/**
 * Test for {@link NoteShapeDefaultLabelAlignmentMigrationParticipant} updated in Sirius 6.5.1. Same test than
 * {@link NoteShapeDefaultLabelAlignmentMigrationTest} but with the new use case data corresponding to bugzilla 574133.
 * The use case can not exist in pure Sirius environment. It has been created in collaborative environment.
 * 
 * @author Laurent Redor
 */
public class NoteShapeDefaultLabelAlignmentMigrationTest2 extends NoteShapeDefaultLabelAlignmentMigrationTest {

    public NoteShapeDefaultLabelAlignmentMigrationTest2() {
        dataPath = "data/unit/migration/do_not_migrate/noteAttachmentAlignment2/";
        errorMessage = "One note should be found in this session to check the migration of a Note with the problem of bugzilla 574133.";
    }

}
