/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.editing;

import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.sirius.common.tools.DslCommonPlugin;

/**
 * Transactional editing domain factory.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public interface IEditingDomainFactory extends Factory {

    /** . */
    String EXTENSION_POINT_ID = DslCommonPlugin.PLUGIN_ID + ".editingDomainFactory"; //$NON-NLS-1$

    /** . */
    String EXTENSION_POINT_ELEMENT = "editingDomainFactory"; //$NON-NLS-1$

    /** . */
    String EXTENSION_POINT_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$
}
