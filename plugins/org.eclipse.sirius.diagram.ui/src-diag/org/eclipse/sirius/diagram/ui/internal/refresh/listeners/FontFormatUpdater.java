/*******************************************************************************
 * Copyright (c) 2012, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.ui.internal.refresh.SynchronizeDDiagramElementStylePropertiesCommand;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;

/**
 * This class update the notation and viewpoint models font style attributes
 * when it receives a notification about a Node font style changes.
 * 
 * Make sure to attach and detach the updater to the editing domain.
 * 
 * @author mporhel
 */
public class FontFormatUpdater extends ResourceSetListenerImpl {

    // Command to add custom feature in label style
    private static class AddCustomFeatureCommand extends RecordingCommand {
        private BasicLabelStyle style;

        private String customFeature;

        public AddCustomFeatureCommand(TransactionalEditingDomain domain, BasicLabelStyle style, String customFeature) {
            super(domain);
            this.style = style;
            this.customFeature = customFeature;
        }

        @Override
        protected void doExecute() {
            this.style.getCustomFeatures().add(this.customFeature);
        }
    }

    // Command to remove custom feature in label style
    private static class RemoveCustomFeatureCommand extends RecordingCommand {
        private BasicLabelStyle style;

        private String customFeature;

        public RemoveCustomFeatureCommand(TransactionalEditingDomain domain, BasicLabelStyle style, String customFeature) {
            super(domain);
            this.style = style;
            this.customFeature = customFeature;
        }

        @Override
        protected void doExecute() {
            this.style.getCustomFeatures().remove(this.customFeature);
        }
    }

    private static final NotificationFilter FEATURES_TO_REFACTOR_FILTER = NotificationFilter.NOT_TOUCH
            .and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Bold()).or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Italic()))
                    .or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Underline()))
                    .or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_StrikeThrough())))
            .or((NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Bold())).and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Italic()))
                    .and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Underline()))
                    .and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_StrikeThrough())))
            .or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_FontHeight()))
            .or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_FontColor()));

    /**
     * Default constructor.
     * 
     * @param domain
     *            the current editing domain.
     */
    public FontFormatUpdater(final TransactionalEditingDomain domain) {
        super(FEATURES_TO_REFACTOR_FILTER);
        domain.addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * Return the list of changed feature on FontStyle during the transaction grouped by FontStyle.
     * 
     * @param event
     *            the event of the transaction, containing the list of notification
     * @return a map with the FontStyle object as key and a set of its modified features as value
     */
    private Map<FontStyle, Set<EStructuralFeature>> getFontStyleFeatureChanges(final ResourceSetChangeEvent event) {
        Map<FontStyle, Set<EStructuralFeature>> featureChanges = new HashMap<>();
        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof FontStyle fontStyle && notification.getFeature() instanceof EStructuralFeature feature) {
                featureChanges.computeIfAbsent(fontStyle, newFontStyle -> new HashSet<>()).add(feature);
            }
        }
        return featureChanges;
    }

    /**
     * Update the custom feature of the given edge label according the GMF Style, the Sirius Style and the Description.
     * 
     * @param cc
     *            The compound command to execute
     * @param featureNames
     *            The set of the changed features in GMF
     * @param gmfStyle
     *            The font style of the GMF edge
     * @param labelStyle
     *            The label style of the Sirius edge
     * @param description
     *            The description of the label style of the Sirius edge
     */
    private void updateEdgeLabelStyle(CompoundCommand cc, Set<String> featureNames, FontStyle gmfStyle, BasicLabelStyle labelStyle, BasicLabelStyleDescription description) {
        String labelFormat = ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT.getName();
        String labelSize = ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE.getName();
        // Here we only process the custom feature 'LabelFormat' and 'LabelSize' because
        // the other custom features are processed by a different mechanism.
        // However, this code is wrong when the three label have different
        // LabelFormat or LabelSize in the description, the reset style button make
        // the same LabelFormat and LabelSize on three label.
        // So, this implementation is temporary pending implementation with the same
        // mechanism as the others custom features.
        if (featureNames.contains(labelFormat)) {
            // if format is different between default (Sirius Description/odesign) and GMF
            if (!isSameLabelFormat(gmfStyle, description.getLabelFormat())) {
                cc.append(new AddCustomFeatureCommand(getTarget(), labelStyle, labelFormat));
            } else {
                cc.append(new RemoveCustomFeatureCommand(getTarget(), labelStyle, labelFormat));
            }
        }
        if (featureNames.contains(labelSize)) {
            // if height is different between default (Sirius Description/odesign) and GMF
            if (gmfStyle.getFontHeight() != description.getLabelSize()) {
                cc.append(new AddCustomFeatureCommand(getTarget(), labelStyle, labelSize));
            } else {
                cc.append(new RemoveCustomFeatureCommand(getTarget(), labelStyle, labelSize));
            }
        }
    }

    /**
     * Update the custom feature of the labels of the given edge according the GMF Style, the Sirius Style and the
     * Description.
     * 
     * @param cc
     *            The compound command to execute
     * @param featureNames
     *            The set of the changed features in GMF
     * @param gmfStyle
     *            The font style of the GMF edge
     * @param siriusStyle
     *            The style of the Sirius edge
     */
    private void updateEdgeLabelStyles(CompoundCommand cc, Set<String> featureNames, FontStyle gmfStyle, EdgeStyle siriusStyle) {
        BeginLabelStyle beginLabelStyle = siriusStyle.getBeginLabelStyle();
        CenterLabelStyle centerLabelStyle = siriusStyle.getCenterLabelStyle();
        EndLabelStyle endLabelStyle = siriusStyle.getEndLabelStyle();
        if (beginLabelStyle != null) {
            updateEdgeLabelStyle(cc, featureNames, gmfStyle, beginLabelStyle, beginLabelStyle.getDescription());
        }
        if (centerLabelStyle != null) {
            updateEdgeLabelStyle(cc, featureNames, gmfStyle, centerLabelStyle, centerLabelStyle.getDescription());
        }
        if (endLabelStyle != null) {
            updateEdgeLabelStyle(cc, featureNames, gmfStyle, endLabelStyle, endLabelStyle.getDescription());
        }
    }

    /**
     * Update the style of the label (or labels if it's an edge with several labels) according the GMF Style, the Sirius
     * Style and the Description.
     * 
     * @param cc
     *            The compound command to execute
     * @param view
     *            The GMF view of the element
     * @param changedFeatures
     *            The set of the changed features in GMF
     * @param gmfStyle
     *            The font style of the GMF edge
     * @param siriusStyle
     *            The Sirius style of the element
     */
    private void updateLabelStyle(CompoundCommand cc, View view, Set<EStructuralFeature> changedFeatures, FontStyle gmfStyle, Style siriusStyle) {
        Set<String> featureNames = getFeatureNames(changedFeatures);
        if (siriusStyle instanceof EdgeStyle edgeStyle) {
            // Special case for edge: the edge can have 3 labels (begin, center and end)
            updateEdgeLabelStyles(cc, featureNames, gmfStyle, edgeStyle);
        } else {
            featureNames.addAll(siriusStyle.getCustomFeatures());
            Command addCustomFeaturesCmd = SetCommand.create(getTarget(), siriusStyle, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, featureNames);
            cc.append(addCustomFeaturesCmd);
        }
        cc.append(new SynchronizeDDiagramElementStylePropertiesCommand(getTarget(), view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand cc = new CompoundCommand();
        Map<FontStyle, Set<EStructuralFeature>> fontStyleFeatureChanges = getFontStyleFeatureChanges(event);
        for (Entry<FontStyle, Set<EStructuralFeature>> entry : fontStyleFeatureChanges.entrySet()) {
            FontStyle fontStyle = entry.getKey();
            Set<EStructuralFeature> features = entry.getValue();
            View view = (View) fontStyle.eContainer();
            if (view.getElement() instanceof DDiagramElement dDiagramElement && !features.isEmpty()) {
                Style style = dDiagramElement.getStyle();
                if (isViewFontStylePropertiesDifferentOfGMFOne(style, fontStyle, features)) {
                    updateLabelStyle(cc, view, features, fontStyle, style);
                }
            }
        }
        if (cc.isEmpty()) {
            return null;
        } else {
            return cc;
        }

    }

    private boolean isSameLabelFormat(FontStyle gmfStyle, EList<FontFormat> siriusStyle) {
        boolean sameBold = (gmfStyle.isBold() == siriusStyle.contains(FontFormat.BOLD_LITERAL));
        boolean sameItalic = (gmfStyle.isItalic() == siriusStyle.contains(FontFormat.ITALIC_LITERAL));
        boolean sameUnderline = (gmfStyle.isUnderline() == siriusStyle.contains(FontFormat.UNDERLINE_LITERAL));
        boolean sameStrike = (gmfStyle.isStrikeThrough() == siriusStyle.contains(FontFormat.STRIKE_THROUGH_LITERAL));
        return sameBold && sameItalic && sameUnderline && sameStrike;
    }

    private boolean isViewFontStylePropertiesDifferentOfGMFOne(Customizable viewpointStyle, FontStyle gmfFontStyle, Set<EStructuralFeature> gmfStyleFeatures) {
        boolean isViewFontStylePropertiesDifferentOfGMFOne = false;
        if (viewpointStyle instanceof EdgeStyle edgeStyle) {
            if (edgeStyle.getBeginLabelStyle() != null) {
                isViewFontStylePropertiesDifferentOfGMFOne = isViewFontStylePropertiesDifferentOfGMFOne(edgeStyle.getBeginLabelStyle(), gmfFontStyle, gmfStyleFeatures);
            }
            if (!isViewFontStylePropertiesDifferentOfGMFOne) {
                if (edgeStyle.getCenterLabelStyle() != null) {
                    isViewFontStylePropertiesDifferentOfGMFOne = isViewFontStylePropertiesDifferentOfGMFOne(edgeStyle.getCenterLabelStyle(), gmfFontStyle, gmfStyleFeatures);
                }
            }
            if (!isViewFontStylePropertiesDifferentOfGMFOne) {
                if (edgeStyle.getEndLabelStyle() != null) {
                    isViewFontStylePropertiesDifferentOfGMFOne = isViewFontStylePropertiesDifferentOfGMFOne(edgeStyle.getEndLabelStyle(), gmfFontStyle, gmfStyleFeatures);
                }
            }
        } else {
            for (EStructuralFeature gmfStyleFeature : gmfStyleFeatures) {
                EStructuralFeature viewpointStyleFeature = ViewPropertiesSynchronizer.GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.get(gmfStyleFeature);
                if (viewpointStyleFeature instanceof EAttribute && viewpointStyle.eClass().getEAllStructuralFeatures().contains(viewpointStyleFeature)) {
                    Object viewpointStyleProperty = viewpointStyle.eGet(viewpointStyleFeature);
                    Object gmfStyleProperty = convertToSiriusPropertyValue(gmfFontStyle, gmfStyleFeature);
                    if (viewpointStyleProperty != null && !viewpointStyleProperty.equals(gmfStyleProperty)) {
                        isViewFontStylePropertiesDifferentOfGMFOne = true;
                        break;
                    }
                }
            }
        }
        return isViewFontStylePropertiesDifferentOfGMFOne;
    }

    @SuppressWarnings("unchecked")
    private Object convertToSiriusPropertyValue(org.eclipse.gmf.runtime.notation.Style gmfStyle, EStructuralFeature gmfStyleAttribute) {
        Object gmfStylePropertyValue = gmfStyle.eGet(gmfStyleAttribute);
        if (gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__BOLD || gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__ITALIC
                || gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__UNDERLINE || gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__STRIKE_THROUGH) {
            FontStyle gmfFontStyle = (FontStyle) gmfStyle;
            gmfStylePropertyValue = new ArrayList<FontFormat>();
            if (gmfFontStyle.isBold()) {
                FontFormatHelper.setFontFormat(((List<FontFormat>) gmfStylePropertyValue), FontFormat.BOLD_LITERAL);
            }
            if (gmfFontStyle.isItalic()) {
                FontFormatHelper.setFontFormat(((List<FontFormat>) gmfStylePropertyValue), FontFormat.ITALIC_LITERAL);
            }
            if (gmfFontStyle.isUnderline()) {
                FontFormatHelper.setFontFormat(((List<FontFormat>) gmfStylePropertyValue), FontFormat.UNDERLINE_LITERAL);
            }
            if (gmfFontStyle.isStrikeThrough()) {
                FontFormatHelper.setFontFormat(((List<FontFormat>) gmfStylePropertyValue), FontFormat.STRIKE_THROUGH_LITERAL);
            }
            if (!gmfFontStyle.isBold() && !gmfFontStyle.isItalic() && !gmfFontStyle.isUnderline() && !gmfFontStyle.isStrikeThrough()) {
                ((List<FontFormat>) gmfStylePropertyValue).clear();
            }
        } else if (gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__FONT_COLOR) {
            FontStyle gmfFontStyle = (FontStyle) gmfStyle;
            gmfStylePropertyValue = RGBValues.integerToRGBValues(gmfFontStyle.getFontColor());

        }
        return gmfStylePropertyValue;
    }

    private Set<String> getFeatureNames(Set<EStructuralFeature> features) {
        Set<String> featureNames = new LinkedHashSet<String>();
        for (EStructuralFeature feature : features) {
            String featureName = feature.getName();
            if (isFeatureOfNotationPackage(feature)) {
                EStructuralFeature eStructuralFeature = ViewPropertiesSynchronizer.GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.get(feature);
                if (eStructuralFeature != null) {
                    featureName = eStructuralFeature.getName();
                }
            }
            featureNames.add(featureName);
        }
        return featureNames;
    }

    private boolean isFeatureOfNotationPackage(EStructuralFeature feature) {
        boolean isFeatureOfNotationPackage = false;
        isFeatureOfNotationPackage = feature.getEContainingClass().getEPackage() == NotationPackage.eINSTANCE;
        return isFeatureOfNotationPackage;
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }
}
