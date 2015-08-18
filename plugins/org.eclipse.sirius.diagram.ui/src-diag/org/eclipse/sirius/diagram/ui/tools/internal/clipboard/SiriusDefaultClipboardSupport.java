/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.clipboard;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.OverrideCopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.OverridePasteChildOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.PasteChildOperation;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Implementation of copy/paste wich disallow copy/paste operations of semantic elements and keep the standard behavior for GMF notes.
 * 
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public final class SiriusDefaultClipboardSupport extends AbstractClipboardSupport {

    // The shared instance of the default clipboard support.
    private static final SiriusDefaultClipboardSupport INSTANCE = new SiriusDefaultClipboardSupport();

    /**
     * Constructor.
     */
    private SiriusDefaultClipboardSupport() {
        super();
    }

    /**
     * Obtains the singleton instance of this class.
     * 
     * @return my instance
     */
    public static SiriusDefaultClipboardSupport getInstance() {
        return INSTANCE;
    }

    /**
     * Return always all objects. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport#getExcludedCopyObjects(java.util.Set)
     */
    @Override
    public Collection getExcludedCopyObjects(final Set eObjects) {
        return Collections.EMPTY_SET;
    }

    /**
     * Return always true. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport#shouldOverrideCopyOperation(java.util.Collection,
     *      java.util.Map)
     */
    @Override
    public boolean shouldOverrideCopyOperation(final Collection eObjects, final Map hintMap) {
        return true;
    }

    /**
     * Return always true. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport#shouldOverrideChildPasteOperation(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean shouldOverrideChildPasteOperation(final EObject parentElement, final EObject childEObject) {
        return false;
    }

    /**
     * Return null to disallow copy operation. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport#getOverrideChildPasteOperation(org.eclipse.gmf.runtime.emf.clipboard.core.PasteChildOperation)
     */
    @Override
    public OverridePasteChildOperation getOverrideChildPasteOperation(final PasteChildOperation overriddenChildPasteOperation) {
        return null;
    }

    /**
     * Return null to disallow paste operation. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport#getOverrideCopyOperation(org.eclipse.gmf.runtime.emf.clipboard.core.CopyOperation)
     */
    @Override
    public OverrideCopyOperation getOverrideCopyOperation(final CopyOperation overriddenCopyOperation) {
        return new OverrideCopyOperation(overriddenCopyOperation) {
            @Override
            public String copy() throws Exception {
                keepNotes();
                if (getEObjects() == null || getEObjects().isEmpty())
                    return ""; //$NON-NLS-1$
                return doCopy();
            }

            /**
             * Keep only notes and non view elements.
             */
            private void keepNotes() {
                boolean noteFound = false;
                for (View view : Lists.newArrayList(Iterables.filter(getEObjects(), View.class))) {
                    if (!isNote(view)) {
                        getEObjects().remove(view);
                    } else {
                        noteFound = true;
                    }
                }

                if (!noteFound) {
                    getEObjects().clear();
                }
            }

            private boolean isNote(View view) {
                return view instanceof Node && GMFNotationHelper.isNote((Node) view);
            }
        };
    }
}
