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

    /**
     * An internal class to store the {@link DefaultLayoutProvider} and some associated data.
     * 
     * @author lredor
     */
    private class LayoutProviderData {
        DefaultLayoutProvider defaultLayoutProvider;

        @SuppressWarnings("unused")
        boolean shouldLaunchSnapTo;

        @SuppressWarnings("unused")
        boolean useStandardArrangeSelectionMechanism;

        public LayoutProviderData(DefaultLayoutProvider defaultLayoutProvider, boolean shouldLaunchSnapTo, boolean useStandardArrangeSelectionMechanism) {
            this.defaultLayoutProvider = defaultLayoutProvider;
            this.shouldLaunchSnapTo = shouldLaunchSnapTo;
            this.useStandardArrangeSelectionMechanism = useStandardArrangeSelectionMechanism;
        }
    }

    private WeakHashMap<IGraphicalEditPart, LayoutProviderData> editPartToLayoutProviderCache = new WeakHashMap<>();

    @Override
    public AbstractLayoutEditPartProvider getLayoutNodeProvider(final IGraphicalEditPart partToLayout) {
        AbstractLayoutEditPartProvider result = null;
        LayoutProviderData layoutProviderData = Optional.ofNullable(editPartToLayoutProviderCache.get(partToLayout)).orElseGet(() -> getLayoutProviderData(partToLayout).orElseGet(null));
        if (layoutProviderData != null) {
            final CompoundLayoutProvider clp = new CompoundLayoutProvider();
            clp.addProvider(layoutProviderData.defaultLayoutProvider);
            if (layoutProviderData.defaultLayoutProvider instanceof ExtendableLayoutProvider) {
                ExtendableLayoutProvider layoutProvider = (ExtendableLayoutProvider) layoutProviderData.defaultLayoutProvider;
                clp.addProvider(new PinnedElementsLayoutProvider(layoutProvider));
            }
            if (layoutProviderData.useStandardArrangeSelectionMechanism) {
                result = new ArrangeSelectionLayoutProvider(clp);
            } else {
                result = clp;
            }
        }
        return result;
    }

    /**
     * Return the layout configuration that should be used by the layout algorithm.
     * 
     * @param partToLayout
     *            the part that will be layouted.
     * @return the layout configuration that should be used by the layout algorithm. Empty {@link Optional} if no such
     *         element exist.
     */
    public Optional<CustomLayoutConfiguration> getLayoutConfiguration(final IGraphicalEditPart partToLayout) {
        // we retrieve the layout configuration from the VSM.
        EditPartQuery editPartQuery = new EditPartQuery(partToLayout);
        DiagramDescription diagramDescription = editPartQuery.getDiagramDescription();
        CustomLayoutConfiguration layoutConfiguration = null;
        if (diagramDescription != null && diagramDescription.getLayout() instanceof CustomLayoutConfiguration) {
            layoutConfiguration = (CustomLayoutConfiguration) diagramDescription.getLayout();
        }
        return Optional.ofNullable(layoutConfiguration);
    }

    /**
     * Whether the current layout provider authorize the "Snap to" features (snap to grid and snap to shape).
     * 
     * @param partToLayout
     *            A part to layout (to find associated configuration)
     * 
     * @return true if it authorizes, false otherwise.
     */
    public boolean shouldLaunchSnapTo(final IGraphicalEditPart partToLayout) {
        Optional<CustomLayoutConfiguration> customLayoutConfiguration = getLayoutConfiguration(partToLayout);
        if (customLayoutConfiguration.isPresent()) {
            Optional<CustomLayoutAlgorithm> customLayoutAlgorithm = getCustomLayoutAlgorithm(customLayoutConfiguration.get());
            if (customLayoutAlgorithm.isPresent()) {
                return customLayoutAlgorithm.get().isLaunchSnapAfter();
            }
        }
        return false;
    }

    private Optional<CustomLayoutAlgorithm> getCustomLayoutAlgorithm(CustomLayoutConfiguration configuration) {
        return Optional.ofNullable(DiagramUIPlugin.getPlugin().getLayoutAlgorithms().get(configuration.getId()));
    }

    @Override
    public boolean provides(final IGraphicalEditPart container) {
        // To avoid to compute the getGenericLayoutProvider twice (at the time we are testing that this provider
        // provides a layout for the given edit part and the second time when calling getLayoutNodeProvider), we keep in
        // cache the result.
        Optional<LayoutProviderData> layoutProviderData = getLayoutProviderData(container);
        if (layoutProviderData.isPresent()) {
            editPartToLayoutProviderCache.put(container, layoutProviderData.get());
            return true;
        }
        // In case of a provider was available but it could be not the case anymore.
        editPartToLayoutProviderCache.remove(container);
        return false;
    }

    private Optional<LayoutProviderData> getLayoutProviderData(IGraphicalEditPart container) {
        LayoutProviderData layoutProviderData = null;
        Optional<CustomLayoutConfiguration> customLayoutConfiguration = getLayoutConfiguration(container);
        if (customLayoutConfiguration.isPresent()) {
            Optional<CustomLayoutAlgorithm> customLayoutAlgorithm = getCustomLayoutAlgorithm(customLayoutConfiguration.get());
            if (customLayoutAlgorithm.isPresent()) {
                DefaultLayoutProvider defaultLayoutProvider = getGenericLayoutProvider(customLayoutConfiguration.get(), customLayoutAlgorithm.get());
                layoutProviderData = new LayoutProviderData(defaultLayoutProvider, customLayoutAlgorithm.get().isLaunchSnapAfter(), customLayoutAlgorithm.get().useStandardArrangeSelectionMechanism());
            }
        }

        return Optional.ofNullable(layoutProviderData);
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
    private Optional<DefaultLayoutProvider> getGenericLayoutProvider(final IGraphicalEditPart partToLayout) {
        Optional<CustomLayoutConfiguration> customLayoutConfiguration = getLayoutConfiguration(partToLayout);
        if (customLayoutConfiguration.isPresent()) {
            return getGenericLayoutProvider(customLayoutConfiguration.get());
        }
        return Optional.empty();
    }

    /**
     * Returns the generic layout provider associated to the given layout configuration.
     * 
     * @param customLayoutConfiguration
     *            the layout configuration to get the generic layout provider.
     * @return the generic layout provider associated to the given layout configuration..
     */
    private Optional<DefaultLayoutProvider> getGenericLayoutProvider(final CustomLayoutConfiguration customLayoutConfiguration) {
        DefaultLayoutProvider defaultLayoutProvider = null;
        Optional<CustomLayoutAlgorithm> customLayoutAlgorithm = getCustomLayoutAlgorithm(customLayoutConfiguration);
        if (customLayoutAlgorithm.isPresent()) {
            defaultLayoutProvider = getGenericLayoutProvider(customLayoutConfiguration, customLayoutAlgorithm.get());
        }
        return Optional.ofNullable(defaultLayoutProvider);
    }

    private DefaultLayoutProvider getGenericLayoutProvider(final CustomLayoutConfiguration customLayoutConfiguration, final CustomLayoutAlgorithm customLayoutAlgorithm) {
        DefaultLayoutProvider layoutAlgorithmInstance = customLayoutAlgorithm.getLayoutAlgorithmInstance();
        layoutAlgorithmInstance.setLayoutConfiguration(customLayoutConfiguration);
        return layoutAlgorithmInstance;
    }
}
