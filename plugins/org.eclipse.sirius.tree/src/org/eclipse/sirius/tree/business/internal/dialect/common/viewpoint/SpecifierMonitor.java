/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tree.tools.internal.TreePlugin;

public class SpecifierMonitor {

    /**
     * Warns user about the given exception.
     * 
     * @param string
     *            the message to use for warning
     * @param e
     *            the exception that occurred
     * @param specificationAttachment
     *            the specification attachment
     */
    public void warning(String string, Exception e, Option<? extends EObject> specificationAttachment) {
        IStatus status = new Status(IStatus.WARNING, TreePlugin.getPlugin().getBundle().getSymbolicName(), string, e);
        TreePlugin.INSTANCE.log(status);
    }

}
