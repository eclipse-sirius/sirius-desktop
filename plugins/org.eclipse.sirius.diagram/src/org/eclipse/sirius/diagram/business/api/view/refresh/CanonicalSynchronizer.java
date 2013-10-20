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
package org.eclipse.sirius.diagram.business.api.view.refresh;

/**
 * A canonical synchronizer is in charge of applying changes made on
 * {@link org.eclipse.sirius.viewpoint.DDiagramElement} or
 * {@link org.eclipse.sirius.viewpoint.DSemanticDiagram} to the GMF annotation model.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 * @since 4.0.0
 * @noimplement This interface is not intended to be implemented by clients,
 *              only used for internal implementation.
 */
public interface CanonicalSynchronizer {

    /**
     * Refreshes the GMF Notations according to the changes made on the element
     * to synchronize.
     */
    void synchronize();

    /**
     * Tells if while the {@link CanonicalSynchronizer#synchronize()} we store
     * in SiriusLayoutDataManager singleton through
     * SiriusLayoutDataManager
     * #addCreatedViewsToLayout(org.eclipse.gmf.runtime.notation.Diagram,
     * java.util.LinkedHashSet) .
     * 
     * NOTE : true by default to have created Views to be arrange when a editor
     * is opened on the Diagram, set to false when used when no editor is opened
     * on Diagram to avoid memory leak on SiriusLayoutDataManager singleton
     * 
     * @param storeViewsToArrange
     *            true to store in SiriusLayoutDataManager, false else
     */
    void storeViewsToArrange(boolean storeViewsToArrange);
}
