/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.refresh;

/**
 * A canonical synchronizer is in charge of applying changes made on
 * {@link org.eclipse.sirius.diagram.DDiagramElement} or
 * {@link org.eclipse.sirius.diagram.DSemanticDiagram} to the GMF annotation
 * model.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 * @since 0.9.0
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
     * in SiriusLayoutDataManager singleton through SiriusLayoutDataManager
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

    /**
     * Called only once, after the diagram got created and a first synchronize
     * call has been done. This hook gives the chance to a canonical
     * synchronizer to adapt model information based on the current model
     * content when the diagram got created. once created.
     * 
     * @since 2.0.0
     */
    void postCreation();
}
