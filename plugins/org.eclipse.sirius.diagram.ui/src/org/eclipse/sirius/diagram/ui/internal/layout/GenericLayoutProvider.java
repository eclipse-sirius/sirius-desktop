/*******************************************************************************
 * Copyright (c) 2018, 2020 Obeo
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
package org.eclipse.sirius.diagram.ui.internal.layout;

import java.util.Optional;
import java.util.WeakHashMap;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.CompoundLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.ExtendableLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeSelectionLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.PinnedElementsLayoutProvider;

/**
 * A generic layout provider that will allow to use a layout algorithm specified in a Sirius VSM.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class GenericLayoutProvider implements LayoutProvider {

    private WeakHashMap<IGraphicalEditPart, DefaultLayoutProvider> editPartToLayoutProviderCache = new WeakHashMap<>();

    @Override
    public AbstractLayoutEditPartProvider getLayoutNodeProvider(final IGraphicalEditPart partToLayout) {
        DefaultLayoutProvider defaultLayoutProvider = Optional.ofNullable(editPartToLayoutProviderCache.get(partToLayout)).orElseGet(() -> getGenericLayoutProvider(partToLayout));
        if (defaultLayoutProvider != null) {
            final CompoundLayoutProvider clp = new CompoundLayoutProvider();
            clp.addProvider(defaultLayoutProvider);
            if (defaultLayoutProvider instanceof ExtendableLayoutProvider) {
                ExtendableLayoutProvider layoutProvider = (ExtendableLayoutProvider) defaultLayoutProvider;
                clp.addProvider(new PinnedElementsLayoutProvider(layoutProvider));
            }

            return new ArrangeSelectionLayoutProvider(clp);
        }
        return null;
    }

    /**
     * Return the layout configuration that should be used by the layout algorithm.
     * 
     * @param partToLayout
     *            the part that will be layouted.
     * @return the layout configuration that should be used by the layout algorithm. Null if no such element exist.
     */
    public CustomLayoutConfiguration getLayoutConfiguration(final IGraphicalEditPart partToLayout) {
        // we retrieve the layout configuration from the VSM.
        EditPartQuery editPartQuery = new EditPartQuery(partToLayout);
        DiagramDescription diagramDescription = editPartQuery.getDiagramDescription();
        CustomLayoutConfiguration layoutConfiguration = null;
        if (diagramDescription != null && diagramDescription.getLayout() instanceof CustomLayoutConfiguration) {
            layoutConfiguration = (CustomLayoutConfiguration) diagramDescription.getLayout();
        }
        return layoutConfiguration;
    }

    @Override
    public boolean provides(final IGraphicalEditPart container) {
        // To avoid to compute the getGenericLayoutProvider twice (at the time we are testing that this provider
        // provides a layout for the given edit part and the second time when calling getLayoutNodeProvider), we keep in
        // cache the result.
        Optional<DefaultLayoutProvider> optionalLayoutProvider = Optional.ofNullable(getGenericLayoutProvider(container));
        if (optionalLayoutProvider.isPresent()) {
            editPartToLayoutProviderCache.put(container, optionalLayoutProvider.get());
            return true;
        }
        // In case of a provider was available but it could be not the case anymore.
        editPartToLayoutProviderCache.remove(container);
        return false;
    }

    @Override
    public boolean isDiagramLayoutProvider() {
        return true;
    }

    /**
     * Returns the generic layout provider associated to the description of the {@link DDiagram} related to the given
     * part.
     * 
     * @param partToLayout
     *            the part to be layouted.
     * @return the generic layout provider associated to the description of the {@link DDiagram} related to the given
     *         part. Null if no such element exists.
     */
    private DefaultLayoutProvider getGenericLayoutProvider(final IGraphicalEditPart partToLayout) {
        CustomLayoutConfiguration customLayoutConfiguration = getLayoutConfiguration(partToLayout);
        if (customLayoutConfiguration != null) {
            CustomLayoutAlgorithm customLayoutAlgorithm = DiagramUIPlugin.getPlugin().getLayoutAlgorithms().get(customLayoutConfiguration.getId());
            if (customLayoutAlgorithm != null) {
                DefaultLayoutProvider layoutAlgorithmInstance = customLayoutAlgorithm.getLayoutAlgorithmInstance();
                layoutAlgorithmInstance.setLayoutConfiguration(customLayoutConfiguration);
                return layoutAlgorithmInstance;
            }
        }
        return null;
    }

}
