/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.ui.IEditorPart;

/**
 * This class allows to run many callback when an editor is opened.
 * 
 * @author ymortier
 */
public class DelegatingCallBack implements CallBack {

    /** The callBacks. */
    private List<CallBack> callBacks = new ArrayList<CallBack>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.actions.CallBack#postOpeningRun(org.eclipse.sirius.viewpoint.DNavigationLink,
     *      org.eclipse.ui.IEditorPart)
     */
    public void postOpeningRun(final DNavigationLink link, final IEditorPart editor) {
        for (final CallBack callBack : callBacks) {
            callBack.postOpeningRun(link, editor);
        }
    }

    /**
     * Adds a callBack.
     * 
     * @param callBack
     *            the callBack to add.
     */
    public void addCallBack(final CallBack callBack) {
        this.callBacks.add(callBack);
    }

    /**
     * Removes a callBack.
     * 
     * @param callBack
     *            the callBack to remove.
     */
    public void removeCallBack(final CallBack callBack) {
        this.callBacks.remove(callBack);
    }

    /**
     * Creates a call back.
     * 
     * @param callBack
     *            the main call back.
     * @param anotherCallBack
     *            a call back that might be null.
     * @return the call back.
     */
    public static CallBack createCallBack(final CallBack callBack, final CallBack anotherCallBack) {
        CallBack ret = null;
        if (anotherCallBack == null) {
            ret = callBack;
        } else if (anotherCallBack instanceof DelegatingCallBack) {
            ((DelegatingCallBack) anotherCallBack).addCallBack(callBack);
            ret = anotherCallBack;
        } else {
            final DelegatingCallBack delegatingCallBack = new DelegatingCallBack();
            delegatingCallBack.addCallBack(anotherCallBack);
            delegatingCallBack.addCallBack(callBack);
        }
        return ret;
    }
}
