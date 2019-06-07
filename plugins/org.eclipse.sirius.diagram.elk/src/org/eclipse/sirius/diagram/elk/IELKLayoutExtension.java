/*******************************************************************************
 * Copyright (c) 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.elk;

import org.eclipse.elk.core.service.LayoutMapping;

/**
 * <p>
 * This interface is intended to be implemented to make it possible to perform specific treatments before and / or after
 * the ELK Layout.
 * </p>
 * <p>
 * A specific {@link IELKLayoutExtension} will be instantiated only once per layout. That means a
 * {@link IELKLayoutExtension} can be stateful and reuse its state between the before and after layout operations.
 * </p>
 * <p>
 * <code>beforeELKLayout(LayoutMapping)</code> and <code>afterELKLayout(LayoutMapping)</code> methods will give you
 * access to the ELK Graph and, for each ELK element, the corresponding EditPart. ELK elements coordinates are relative
 * to their parents. ELK edge coordinates (source, target and bendpoints) are relative to the containing node. For more
 * details about ELK coordinates system see
 * <a href="https://www.eclipse.org/elk/documentation/tooldevelopers/graphdatastructure/coordinatesystem.html">the
 * relative documentation<a/>.
 * </p>
 * <p>
 * <code>afterGMFCommandApplied</code> makes it possible to modify the GMF layout once the ELK layout result as been
 * applied by the {@link GmfLayoutCommand}.
 * </p>
 * 
 * @author fbarbin
 *
 */
public interface IELKLayoutExtension {

    /**
     * Extension point ID.
     */
    public final static String EXTENSION_ID = "org.eclipse.sirius.diagram.elk.layout.extension";

    /**
     * called before the ELK Graph is layouted by the ELK algorithm.
     * 
     * @param layoutMapping
     *            the ELK {@link LayoutMapping}.
     */
    void beforeELKLayout(LayoutMapping layoutMapping);

    /**
     * Called once the ELK Graph has been layouted by the ELK algorithm.
     * 
     * @param layoutMapping
     *            the ELK {@link LayoutMapping}.
     */
    void afterELKLayout(LayoutMapping layoutMapping);

    /**
     * <p>
     * Called after the GMF Command has been applied. The GMF Command applies the new layout coordinates to the GMF
     * model. This method is called after the GMF Command but still in the same execution context. You can modify the
     * applied layout in the GMF model in this method. The modified shape and edge layout can be reached through the
     * {@link GmfLayoutCommand}. The parent diagram edit part (that let you access to each sub editPart and the GMF
     * model) can be retrieve like this:
     * </p>
     * <code>
     * // You can retrieve the root diagram editPart like this:
     * DiagramEditPart diagramEditPart = layoutMapping.getProperty(ElkDiagramLayoutConnector.DIAGRAM_EDIT_PART);
     * // You can retrieve the GMF diagram like this:
     *   diagramEditPart.getModel();
     * </code>
     * 
     * @param gmfLayoutCommand
     *            the {@link GmfLayoutCommand} ran before calling this method.
     * @param layoutMapping
     *            the ELK {@link LayoutMapping}.
     */
    void afterGMFCommandApplied(GmfLayoutCommand gmfLayoutCommand, LayoutMapping layoutMapping);
}
