/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.elk.migration.description;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.elk.Messages;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * Migrate options no longer supported in ELK 0.9.0.<BR/>
 * These options have been detected by a comparison of the MELK files:
 * <code>git diff v0.7.1.. -- '**\*.melk'</code><BR/>
 * and then a regexp search on "<code>- *supports<code>".
 */
public class ELK090MigrationParticipant extends AbstractVSMMigrationParticipant {
    /** Old ids of Layered options */
    public static final String OLD_OPTION_ID_CONSIDER_MODEL_ORDER = "org.eclipse.elk.layered.considerModelOrder"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_LABEL_PORT = "org.eclipse.elk.spacing.labelPort"; //$NON-NLS-1$

    /** Old ids of Rectpacking options */
    public static final String OLD_OPTION_ID_EXPAND_TO_ASPECT_RATIO = "org.eclipse.elk.rectpacking.expandToAspectRatio"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_EXPAND_NODES = "org.eclipse.elk.expandNodes"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_LAST_PLACE_SHIFT = "org.eclipse.elk.rectpacking.lastPlaceShift"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_ONLY_FIRST_ITERATION = "org.eclipse.elk.rectpacking.onlyFirstIteration"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_OPTIMIZATION_GOAL = "org.eclipse.elk.rectpacking.optimizationGoal"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_ROW_COMPACTION = "org.eclipse.elk.rectpacking.rowCompaction"; //$NON-NLS-1$

    public static final String OLD_OPTION_ID_TARGET_WIDTH = "org.eclipse.elk.rectpacking.targetWidth"; //$NON-NLS-1$

    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("15.4.0.202401051836"); //$NON-NLS-1$

    boolean migrationOccured = false;

    StringBuilder migrationMessage = new StringBuilder(Messages.ELK090MigrationParticipant_title);

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        Object newValue = null;
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0 && DescriptionPackage.eINSTANCE.getLayoutOption_Id().equals(feature)) {
            // The "notification migration" is not done here because the <code>object</code> is not yet in its container
            // (its diagram description). The notification will be done in postLoad.
            if (object instanceof EnumLayoutOption) {
                if (OLD_OPTION_ID_CONSIDER_MODEL_ORDER.equals(value)) {
                    newValue = LayeredOptions.CONSIDER_MODEL_ORDER_STRATEGY.getId();
                } else if (OLD_OPTION_ID_OPTIMIZATION_GOAL.equals(value)) {
                    newValue = RectPackingOptions.WIDTH_APPROXIMATION_OPTIMIZATION_GOAL.getId();
                }
            } else if (object instanceof BooleanLayoutOption) {
                if (OLD_OPTION_ID_LAST_PLACE_SHIFT.equals(value)) {
                    newValue = RectPackingOptions.WIDTH_APPROXIMATION_LAST_PLACE_SHIFT.getId();
                }
            } else if (object instanceof DoubleLayoutOption) {
                if (OLD_OPTION_ID_LABEL_PORT.equals(value)) {
                    newValue = LayeredOptions.SPACING_LABEL_PORT_HORIZONTAL.getId();
                    // The LayeredOptions.SPACING_LABEL_PORT_VERTICAL option will be set in "postLoad" method
                } else if (OLD_OPTION_ID_TARGET_WIDTH.equals(value)) {
                    newValue = RectPackingOptions.WIDTH_APPROXIMATION_TARGET_WIDTH.getId();
                }
            }
        }
        if (newValue != null) {
            return newValue;
        } else {
            return super.getValue(object, feature, value, loadedVersion);
        }
    }

    @Override
    protected void postLoad(Group group, Version loadedVersion) {
        // Remove no longer supported options or log message for options renamed in getValue method.
        group.getOwnedViewpoints().stream().forEach(v -> v.getOwnedRepresentations().stream().filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast).forEach(d -> {
            if (d.getLayout() instanceof CustomLayoutConfiguration) {
                CustomLayoutConfiguration customLayoutConfiguration = (CustomLayoutConfiguration) d.getLayout();
                if (LayeredOptions.ALGORITHM_ID.equals(customLayoutConfiguration.getId())) {
                    List<LayoutOption> newOptionsToAdd = new ArrayList<>();
                    StringBuilder detailMessages = new StringBuilder();
                    for (Iterator<LayoutOption> iterator = customLayoutConfiguration.getLayoutOptions().iterator(); iterator.hasNext();) {
                        LayoutOption o = iterator.next();
                        if (LayeredOptions.SPACING_LABEL_PORT_HORIZONTAL.getId().equals(o.getId())) {
                            DoubleLayoutOption vertical = DescriptionFactory.eINSTANCE.createDoubleLayoutOption();
                            vertical.setId(LayeredOptions.SPACING_LABEL_PORT_VERTICAL.getId());
                            vertical.setValue(((DoubleLayoutOption) o).getValue());
                            newOptionsToAdd.add(vertical);
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_layered_rename_label_port);
                        } else if (LayeredOptions.CONSIDER_MODEL_ORDER_STRATEGY.getId().equals(o.getId())) {
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_layered_rename_consider_model_order);
                        }
                    }
                    customLayoutConfiguration.getLayoutOptions().addAll(newOptionsToAdd);
                    if (!detailMessages.isEmpty()) {
                        notifyMigration(migrationMessage, d, Messages.ELK090MigrationParticipant_layered_title, detailMessages);
                    }
                } else if (RectPackingOptions.ALGORITHM_ID.equals(customLayoutConfiguration.getId())) {
                    StringBuilder detailMessages = new StringBuilder();
                    for (Iterator<LayoutOption> iterator = customLayoutConfiguration.getLayoutOptions().iterator(); iterator.hasNext();) {
                        LayoutOption o = iterator.next();
                        if (OLD_OPTION_ID_EXPAND_NODES.equals(o.getId())) {
                            iterator.remove();
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_remove_expandNodes);
                        } else if (OLD_OPTION_ID_EXPAND_TO_ASPECT_RATIO.equals(o.getId())) {
                            iterator.remove();
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_remove_expandToAspectRatio);
                        } else if (OLD_OPTION_ID_ONLY_FIRST_ITERATION.equals(o.getId())) {
                            // In theory, this option could not be added in VSM, but it is nevertheless handled here as
                            // it has been removed.
                            iterator.remove();
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_remove_onlyFirstIteration);
                        } else if (OLD_OPTION_ID_ROW_COMPACTION.equals(o.getId())) { // $NON-NLS-1$
                            // In theory, this option could not be added in VSM, but it is nevertheless handled here as
                            // it has been removed.
                            iterator.remove();
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_remove_rowCompaction);
                        } else if (RectPackingOptions.WIDTH_APPROXIMATION_OPTIMIZATION_GOAL.getId().equals(o.getId())) {
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_rename_optimization_goal);
                        } else if (RectPackingOptions.WIDTH_APPROXIMATION_LAST_PLACE_SHIFT.getId().equals(o.getId())) {
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_rename_last_place_shift);
                        } else if (RectPackingOptions.WIDTH_APPROXIMATION_TARGET_WIDTH.getId().equals(o.getId())) {
                            notifyMigration(detailMessages, Messages.ELK090MigrationParticipant_rectPacking_rename_target_width);
                        }
                    }
                    if (!detailMessages.isEmpty()) {
                        notifyMigration(migrationMessage, d, Messages.ELK090MigrationParticipant_rectPacking_title, detailMessages);
                    }
                }
            }
        }));
        if (migrationOccured) {
            DiagramPlugin.getDefault().logInfo(migrationMessage.toString());
            migrationOccured = false;
            migrationMessage = new StringBuilder(Messages.ELK090MigrationParticipant_title);
        }
    }

    private void notifyMigration(StringBuilder detailMessages, String messageTemplate) {
        migrationOccured = true;
        detailMessages.append("\n"); //$NON-NLS-1$
        detailMessages.append(messageTemplate);
    }

    private void notifyMigration(StringBuilder mainMessage, EObject diagramDescription, String messageTemplate, StringBuilder detailMessages) {
        migrationOccured = true;
        String diagramDescriptionLabel = "unknown"; //$NON-NLS-1$
        if (diagramDescription instanceof DiagramDescription) {
            diagramDescriptionLabel = new IdentifiedElementQuery((DiagramDescription) diagramDescription).getLabel();
        }
        mainMessage.append("\n"); //$NON-NLS-1$
        mainMessage.append(MessageFormat.format(messageTemplate, diagramDescriptionLabel));
        mainMessage.append(detailMessages);
    }
}
