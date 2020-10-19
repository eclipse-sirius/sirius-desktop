/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusDragEditPartsTrackerEx;
import org.eclipse.sirius.ext.base.Option;

/**
 * Specific implementation of {@link org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusDragEditPartsTrackerEx}.
 * <br/>
 * 
 * @author nlepine
 */
public class SequenceDragEditPartsTrackerEx extends SiriusDragEditPartsTrackerEx {

    /**
     * Constructor.
     * 
     * @param sourceEditPart
     *            Edit part.
     */
    public SequenceDragEditPartsTrackerEx(final EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        SequenceCacheDragTrackerHelper.handleButtonUp((IGraphicalEditPart) getSourceEditPart());
        return super.handleButtonUp(button);
    }

    @Override
    protected boolean handleButtonDown(int button) {
        boolean handleButtonDown = super.handleButtonDown(button);
        SequenceCacheDragTrackerHelper.handleButtonDown((IGraphicalEditPart) getSourceEditPart());
        return handleButtonDown;
    }

    /**
     * Helper to share cache activation and deactivation among all Sequence drag trackers.
     */
    public static final class SequenceCacheDragTrackerHelper {

        /**
         * Private constructor.
         */
        private SequenceCacheDragTrackerHelper() {
            // prevent instantiation
        }

        /**
         * Disable and clear the caches used during the drag.
         * 
         * @param smep
         *            the drag tracker source/owner edit part.
         */
        public static void handleButtonUp(IGraphicalEditPart smep) {
            SequenceDiagram diagram = getSequenceDiagram(smep);
            if (diagram != null) {
                diagram.useCache(false);
                diagram.clearAllCaches();
            }

            CacheHelper.clearCaches();
        }

        /**
         * Enable the caches to use during drag.
         * 
         * @param smep
         *            the drag tracker source/owner edit part.
         */
        public static void handleButtonDown(IGraphicalEditPart smep) {
            CacheHelper.initCaches();

            SequenceDiagram diagram = getSequenceDiagram(smep);
            if (diagram != null) {
                diagram.useCache(true);
            }
        }

        private static SequenceDiagram getSequenceDiagram(IGraphicalEditPart smep) {
            SequenceDiagram diagram = null;
            if (smep != null) {
                View notationView = smep.getNotationView();
                Option<ISequenceElement> iSequenceElement = ISequenceElementAccessor.getISequenceElement(notationView);
                if (iSequenceElement.some()) {
                    diagram = iSequenceElement.get().getDiagram();
                }
            }
            return diagram;
        }
    }
}
