/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.osgi.framework.Version;

/**
 * The migration code of Sirius 7.0.0.
 * <UL>
 * <LI>Change the {@code Diagram} type</LI>
 * </UL>
 * 
 * @author lredor
 */
public class DiagramRepresentationsFileMigrationParticipantV700 {

    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("7.0.0"); //$NON-NLS-1$

    /**
     * Return "Sirius" instead of "Viewpoint" for the type of
     * {@link org.eclipse.gmf.runtime.notation.Diagram}.
     * 
     * @param object
     *            the object containing the feature.
     * @param feature
     *            the feature to set value.
     * @param value
     *            the initial serialized value.
     * @return a new value if has to be changed otherwise null.
     */
    public Object getValue(EObject object, EStructuralFeature feature, Object value) {
        if (NotationPackage.eINSTANCE.getView_Type().equals(feature) && NotationPackage.eINSTANCE.getDiagram().isInstance(object) && "Viewpoint".equals(value)) { //$NON-NLS-1$
            return DDiagramEditPart.MODEL_ID;
        }
        return null;
    }
}
