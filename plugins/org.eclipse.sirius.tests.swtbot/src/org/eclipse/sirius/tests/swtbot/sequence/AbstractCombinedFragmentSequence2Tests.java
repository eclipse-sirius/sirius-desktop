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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

/**
 * Abstract test class for combinedfragments.
 * 
 * @author edugueperoux
 */
public abstract class AbstractCombinedFragmentSequence2Tests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "combinedFragments2/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample #1";

    private static final String MODEL = "combinedFragments.interactions";

    private static final String SESSION_FILE = "combinedFragments.aird";

    private static final String TYPES_FILE = "types.ecore";

    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

}
