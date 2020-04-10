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
package org.eclipse.sirius.ui.business.internal.dialect;

import java.text.MessageFormat;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * A function that transforms a {@link Throwable} into the corresponding message to display. It is currently used mainly
 * to deal with {@link LockedInstanceException} in context of {@link LogThroughActiveDialectEditorLogListener}.
 * 
 * @author Laurent Redor
 */
public class ErrorMessageProvider implements Function<Throwable, String> {

    /**
     * The label provider to use for displaying elements name.
     */
    private ICommonLabelProvider labelProvider;

    /**
     * Default constructor.
     */
    public ErrorMessageProvider() {
    }

    @Override
    public String apply(Throwable exception) {
        // Returns the error message corresponding to the given exception.
        String errorMessage = exception.getMessage();
        if (exception instanceof LockedInstanceException) {
            EObject lockedElement = ((LockedInstanceException) exception).getLockedElement();
            if (lockedElement != null) {
                errorMessage = MessageFormat.format(LockedInstanceException.PERMISSION_ISSUE_MESSAGE, getLabelProvider().getText(lockedElement));
            }
        }
        return errorMessage;
    }

    /**
     * Returns the label provider to use for displaying locked elements.
     *
     * @return the label provider to use for displaying locked elements.
     */
    protected ICommonLabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new SiriusCommonLabelProvider();
        }
        return labelProvider;
    }

}
