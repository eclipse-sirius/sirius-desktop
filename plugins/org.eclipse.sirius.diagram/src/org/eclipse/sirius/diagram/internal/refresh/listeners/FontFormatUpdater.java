/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.refresh.listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.internal.refresh.SynchronizeDDiagramElementStylePropertiesCommand;
import org.eclipse.sirius.diagram.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This class update the notation and viewpoint models font style attributes
 * when it receives a notification about a Node font style changes.
 * 
 * Make sure to attach and detach the updater to the editing domain.
 * 
 * @author mporhel
 */
public class FontFormatUpdater extends ResourceSetListenerImpl {

    private static final NotificationFilter FEATURES_TO_REFACTOR_FILTER = NotificationFilter.NOT_TOUCH
            .and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Bold()).or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Italic())))
            .or((NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Bold())).and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getFontStyle_Italic())))
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
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand cc = new CompoundCommand();
        Map<FontStyle, Set<EStructuralFeature>> fontStyleFeatureChanges = new HashMap<FontStyle, Set<EStructuralFeature>>();
        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof FontStyle && notification.getFeature() instanceof EStructuralFeature) {
                FontStyle fontStyle = (FontStyle) notification.getNotifier();
                EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
                Set<EStructuralFeature> featureChanges = fontStyleFeatureChanges.get(fontStyle);
                if (featureChanges == null) {
                    featureChanges = new HashSet<EStructuralFeature>();
                    fontStyleFeatureChanges.put(fontStyle, featureChanges);
                }
                featureChanges.add(feature);
            }
        }
        for (Entry<FontStyle, Set<EStructuralFeature>> entry : fontStyleFeatureChanges.entrySet()) {
            FontStyle fontStyle = entry.getKey();
            Set<EStructuralFeature> features = entry.getValue();
            View view = (View) fontStyle.eContainer();
            if (view.getElement() instanceof DDiagramElement && !features.isEmpty()) {
                DDiagramElement dDiagramElement = (DDiagramElement) view.getElement();
                Style style = dDiagramElement.getStyle();
                if (isViewFontStylePropertiesDifferentOfGMFOne(style, fontStyle, features)) {
                    Set<String> featureNames = getFeatureNames(features);
                    if (style instanceof EdgeStyle) {
                        EdgeStyle edgeStyle = (EdgeStyle) style;
                        if (edgeStyle.getBeginLabelStyle() != null) {
                            featureNames.addAll(edgeStyle.getBeginLabelStyle().getCustomFeatures());
                            Command addCustomFeaturesCmd = SetCommand.create(getTarget(), edgeStyle.getBeginLabelStyle(), ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, featureNames);
                            cc.append(addCustomFeaturesCmd);
                        }
                        if (edgeStyle.getCenterLabelStyle() != null) {
                            featureNames.addAll(edgeStyle.getCenterLabelStyle().getCustomFeatures());
                            Command addCustomFeaturesCmd = SetCommand.create(getTarget(), edgeStyle.getCenterLabelStyle(), ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, featureNames);
                            cc.append(addCustomFeaturesCmd);
                        }
                        if (edgeStyle.getEndLabelStyle() != null) {
                            featureNames.addAll(edgeStyle.getEndLabelStyle().getCustomFeatures());
                            Command addCustomFeaturesCmd = SetCommand.create(getTarget(), edgeStyle.getEndLabelStyle(), ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, featureNames);
                            cc.append(addCustomFeaturesCmd);
                        }
                    } else {
                        featureNames.addAll(style.getCustomFeatures());
                        Command addCustomFeaturesCmd = SetCommand.create(getTarget(), style, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, featureNames);
                        cc.append(addCustomFeaturesCmd);
                    }
                    cc.append(new SynchronizeDDiagramElementStylePropertiesCommand(getTarget(), view));
                }
            }
        }
        if (cc.isEmpty()) {
            return null;
        } else {

            return cc;
        }

    }

    private boolean isViewFontStylePropertiesDifferentOfGMFOne(Customizable viewpointStyle, FontStyle gmfFontStyle, Set<EStructuralFeature> gmfStyleFeatures) {
        boolean isViewFontStylePropertiesDifferentOfGMFOne = false;
        if (viewpointStyle instanceof EdgeStyle) {
            EdgeStyle edgeStyle = (EdgeStyle) viewpointStyle;
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

    private Object convertToSiriusPropertyValue(org.eclipse.gmf.runtime.notation.Style gmfStyle, EStructuralFeature gmfStyleAttribute) {
        Object gmfStylePropertyValue = gmfStyle.eGet(gmfStyleAttribute);
        if (gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__BOLD || gmfStyleAttribute == NotationPackage.Literals.FONT_STYLE__ITALIC) {
            FontStyle gmfFontStyle = (FontStyle) gmfStyle;
            if (gmfFontStyle.isBold() && gmfFontStyle.isItalic()) {
                gmfStylePropertyValue = FontFormat.BOLD_ITALIC_LITERAL;
            } else if (gmfFontStyle.isBold()) {
                gmfStylePropertyValue = FontFormat.BOLD_LITERAL;
            } else if (gmfFontStyle.isItalic()) {
                gmfStylePropertyValue = FontFormat.ITALIC_LITERAL;
            } else {
                gmfStylePropertyValue = FontFormat.NORMAL_LITERAL;
            }
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
