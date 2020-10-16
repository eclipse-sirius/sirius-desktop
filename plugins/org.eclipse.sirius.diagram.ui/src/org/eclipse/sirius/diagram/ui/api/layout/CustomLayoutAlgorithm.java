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
package org.eclipse.sirius.diagram.ui.api.layout;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeSelectionLayoutProvider;

/**
 * A component providing all needed information to provide a custom layout algorithm that can be used to layout Sirius
 * diagrams.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class CustomLayoutAlgorithm {

    /**
     * The algorithm's id.
     */
    private String id;

    /**
     * The algorithm's label.
     */
    private String label;

    /**
     * The algorithm's description.
     */
    private String description;

    /**
     * A supplier of {@link DefaultLayoutProvider} that contains the layout algorithm.
     */
    private Supplier<DefaultLayoutProvider> layoutSupplier;

    /**
     * The options allowing to configure the layout algorithm behavior indexed by their name. Use the factory
     * {@link LayoutOptionFactory} to create it.
     */
    private Map<String, LayoutOption> layoutOptions;

    /**
     * In Sirius, if the snap to options are enabled (snap to grid and snap to shape), the figures are moved to be
     * snapped after the arrange. This field allows to ignore this behavior if the {@link CustomLayoutAlgorithm}
     * considers that the figure should not be moved after its layout result.
     */
    private boolean launchSnapAfter = true;

    /**
     * In Sirius, the arrange selection is handled by using the arrange all and specific "pinned" elements. This is the
     * scope of {@link ArrangeSelectionLayoutProvider}. For some layout algorithms, it can be useful to disable this
     * specific behavior. This is the case for ELK, for example.
     * 
     */
    private boolean useStandardArrangeSelectionMechanism = true;

    /**
     * In Sirius, when several levels of container are used in arrange (see
     * {@link org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler#getCreatedViewsToLayoutMap(DiagramEditPart)],
     * one arrange command is called for each level. They are sort from the highest level container to the lowest level
     * container. For some layout, it can be useful to reverse this order.
     */
    private boolean shouldReverseLayoutsOrder;

    private CustomLayoutAlgorithm() {

    }

    /**
     * Returns the algorithm's id.
     * 
     * @return the algorithm's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the algorithm's label.
     * 
     * @return the algorithm's label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the algorithm's description.
     * 
     * @return the algorithm's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the options allowing to configure the layout algorithm behavior indexed by their names.
     * 
     * @return the options allowing to configure the layout algorithm behavior indexed by their names.
     */
    public Map<String, LayoutOption> getLayoutOptions() {
        return layoutOptions;
    }

    /**
     * Returns an instance of {@link DefaultLayoutProvider} that contains the layout algorithm.
     * 
     * @return an instance of {@link DefaultLayoutProvider} that contains the layout algorithm.
     */
    public DefaultLayoutProvider getLayoutAlgorithmInstance() {
        return layoutSupplier.get();
    }

    /**
     * Whether the current algorithm authorize the Snap to features (snap to grid and snap to shape).
     * 
     * @return true if it authorizes, false otherwise.
     */
    public boolean isLaunchSnapAfter() {
        return launchSnapAfter;
    }

    /**
     * Whether the current algorithm relies on the standard ArrangeSelection mechanism see
     * {@link ArrangeSelectionLayoutProvider} for more details.
     * 
     * @return true if it relies on the standard ArrangeSelection, false otherwise.
     */
    public boolean useStandardArrangeSelectionMechanism() {
        return useStandardArrangeSelectionMechanism;
    }

    /**
     * Whether the current algorithm should reverse the layouts order in
     * {@link org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler#launchArrangeCommand(DiagramEditPart)}
     * (from the lowest level container to the highest).
     * 
     * @return true if the order of layouts should be reversed, false otherwise.
     */
    public boolean shouldReverseLayoutsOrder() {
        return shouldReverseLayoutsOrder;
    }

    @Override
    public String toString() {
        return this.id;
    }

    /**
     * Create a new builder to construct this {@link CustomLayoutAlgorithm}.
     * 
     * @param algorithmId
     *            the algorithm unique Id.
     * @return a new {@link CustomLayoutAlgorithmBuilder} used to build the {@link CustomLayoutAlgorithm}.
     */
    public static CustomLayoutAlgorithmBuilder newCustomLayoutAlgorithm(String algorithmId) {
        return new CustomLayoutAlgorithmBuilder(algorithmId);
    }

    /**
     * A Builder to build a new CustomLayoutAlgorithm.
     * 
     * @author fbarbin
     *
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class CustomLayoutAlgorithmBuilder {
        private String id;

        private String label;

        private String description;

        private Supplier<DefaultLayoutProvider> layoutSupplier;

        private Map<String, LayoutOption> layoutOptions;

        private boolean launchSnapAfter;

        private boolean useStandardArrangeSelectionMechanism;

        private boolean shouldReverseLayoutsOrder;

        CustomLayoutAlgorithmBuilder(String id) {
            this.id = Objects.requireNonNull(id);
        }

        /**
         * Set the algorithm's label.
         * 
         * @param label
         *            the label that will be displayed.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setLabel(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        /**
         * Set the algorithm's description.
         * 
         * @param description
         *            the description.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setDescription(String description) {
            this.description = Objects.requireNonNull(description);
            return this;
        }

        /**
         * 
         * Set a supplier of {@link DefaultLayoutProvider} that contains the layout algorithm.
         * 
         * @param layoutSupplier
         *            the supplier function.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setLayoutSupplier(Supplier<DefaultLayoutProvider> layoutSupplier) {
            this.layoutSupplier = Objects.requireNonNull(layoutSupplier);
            return this;
        }

        /**
         * Set the options allowing to configure the layout algorithm behavior. Use the factory
         * {@link LayoutOptionFactory} to create it.
         * 
         * @param layoutOptions
         *            the layout options map.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setLayoutOptions(Map<String, LayoutOption> layoutOptions) {
            this.layoutOptions = Objects.requireNonNull(layoutOptions);
            return this;
        }

        /**
         * Set whether the algorithm authorize the snap to features after the layout.
         * 
         * @param launchSnapAfter
         *            true if the Snap command should be launched after this algorithm, false otherwise.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setLaunchSnapAfter(boolean launchSnapAfter) {
            this.launchSnapAfter = launchSnapAfter;
            return this;
        }

        /**
         * Set whether the algorithm relies on the standard ArrangeSelection mechanism see
         * {@link ArrangeSelectionLayoutProvider} for more details.
         * 
         * @param useStandardArrangeSelectionMechanism
         *            true if the standard ArrangeSelection mechanism should be used with this algorithm, false otherwise.
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder setStandardArrangeSelectionMechanism(boolean useStandardArrangeSelectionMechanism) {
            this.useStandardArrangeSelectionMechanism = useStandardArrangeSelectionMechanism;
            return this;
        }

        /**
         * Set whether the current algorithm should reverse the layouts order in
         * {@link org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler#launchArrangeCommand(DiagramEditPart)}
         * (from the lowest level container to the highest).
         * 
         * @param shouldReverseLayoutsOrder
         *            true if the order of layouts should be reversed, false otherwise. .
         * 
         * @return the current builder for convenience.
         */
        public CustomLayoutAlgorithmBuilder reverseLayoutsOrder(boolean shouldReverseLayoutsOrder) {
            this.shouldReverseLayoutsOrder = shouldReverseLayoutsOrder;
            return this;
        }

        /**
         * Build the new CustomLayoutAlgorithm according to the builder parameters.
         * 
         * @return the new CustomLayoutAlgorithm.
         */
        public CustomLayoutAlgorithm build() {
            CustomLayoutAlgorithm customLayoutAlgorithm = new CustomLayoutAlgorithm();
            customLayoutAlgorithm.id = Objects.requireNonNull(this.id);
            customLayoutAlgorithm.label = Objects.requireNonNull(this.label);
            customLayoutAlgorithm.description = Objects.requireNonNull(this.description);
            customLayoutAlgorithm.layoutSupplier = Objects.requireNonNull(this.layoutSupplier);
            customLayoutAlgorithm.layoutOptions = Objects.requireNonNull(this.layoutOptions);
            customLayoutAlgorithm.launchSnapAfter = this.launchSnapAfter;
            customLayoutAlgorithm.useStandardArrangeSelectionMechanism = this.useStandardArrangeSelectionMechanism;
            customLayoutAlgorithm.shouldReverseLayoutsOrder = this.shouldReverseLayoutsOrder;
            return customLayoutAlgorithm;
        }

    }

}
