/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.delete;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Extension of delete hook extension point should implements this interface.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface IDeleteHook {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.deleteHook"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /** The class attribute. */
    String ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * This method will be called before the deletion command execution. It is
     * useful if you need to display a confirmation dialog, or log a deletion.
     * As you are not in a recording command you should not modify the model in
     * this method. If you have such a need see
     * {@link org.eclipse.emf.transaction.TransactionalEditingDomain#addResourceSetListener(org.eclipse.emf.transaction.ResourceSetListener)}
     * 
     * @param selections
     *            a collection of {@link DSemanticDecorator}. If you need the
     *            semantic element, simply call
     *            {@link DSemanticDecorator#getTarget()}.
     * @param parameters
     *            map of parameters define in modeler description file, as
     *            DeleteHook children
     * @return {@link IStatus#CANCEL} if the delete command should not be
     *         executed, {@link IStatus#OK} if it should. Others status are
     *         currently not supported.
     */
    IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections, Map<String, Object> parameters);

}
