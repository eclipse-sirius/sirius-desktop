/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Felix Dorner <felix.dorner@gmail.com> - Bug 540056
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.query;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

/**
 * A class aggregating all the queries (read-only!) having a {@link View} as a starting point.
 * 
 * @author lredor
 */
public class ViewQuery {

    /**
     * custom data key for note shapes specific styles. This key should be used combined {@link #VERTICAL_ALIGNMENT}
     * key.
     */
    public static final String SPECIFIC_STYLES = "specificStyles"; //$NON-NLS-1$

    /**
     * custom data key for note shapes vertical alignment. This key should be used combined {@link #SPECIFIC_STYLES}
     * key.
     */
    public static final String VERTICAL_ALIGNMENT = "verticalAlignment"; //$NON-NLS-1$

    /**
     * custom data key for link notes, used in the {@link #SPECIFIC_STYLES} annotation.
     */
    public static final String REPRESENTATION_LINK_NOTE = "representationlink"; //$NON-NLS-1$

    /**
     * The set of GMF style attributes customizable for which not corresponding Sirius style property exists.
     */
    public static final Set<EAttribute> CUSTOMIZABLE_GMF_EXCLUSIVE_STYLE_ATTRIBUTES = new LinkedHashSet<EAttribute>();

    /** The set of GMF style attributes customizable. */
    public static final Set<EAttribute> CUSTOMIZABLE_GMF_STYLE_ATTRIBUTES = new LinkedHashSet<EAttribute>();

    static {
        for (EClassifier notationEClassifier : NotationPackage.eINSTANCE.getEClassifiers()) {
            if (notationEClassifier instanceof EClass) {
                EClass notationEClass = (EClass) notationEClassifier;
                if (NotationPackage.Literals.STYLE.isSuperTypeOf(notationEClass)) {
                    for (EAttribute eAttribute : notationEClass.getEAttributes()) {
                        // Does not consider Note/Text label in the
                        // customizations
                        if (eAttribute != NotationPackage.Literals.DESCRIPTION_STYLE__DESCRIPTION) {
                            if (!isStylePropertyExistingInSiriusStyleModel(eAttribute)) {
                                CUSTOMIZABLE_GMF_EXCLUSIVE_STYLE_ATTRIBUTES.add(eAttribute);
                            }
                            CUSTOMIZABLE_GMF_STYLE_ATTRIBUTES.add(eAttribute);
                        }
                    }

                }
            }
        }
    }

    /** The starting point. */
    protected View view;

    /**
     * Create a new query.
     * 
     * @param view
     *            the starting point.
     */
    public ViewQuery(View view) {
        this.view = view;
    }

    private static boolean isStylePropertyExistingInSiriusStyleModel(EAttribute eAttribute) {
        // CHECKSTYLE:OFF
        boolean isStylePropertyExistingInSiriusStyleModel = eAttribute == NotationPackage.Literals.FONT_STYLE__BOLD || eAttribute == NotationPackage.Literals.FONT_STYLE__ITALIC
                || eAttribute == NotationPackage.Literals.FONT_STYLE__UNDERLINE || eAttribute == NotationPackage.Literals.FONT_STYLE__STRIKE_THROUGH
                || eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_COLOR || eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT
                || eAttribute == NotationPackage.Literals.FILL_STYLE__FILL_COLOR || eAttribute == NotationPackage.Literals.ROUTING_STYLE__ROUTING
                || eAttribute == NotationPackage.Literals.LINE_STYLE__LINE_COLOR;
        // CHECKSTYLE:ON

        return isStylePropertyExistingInSiriusStyleModel;
    }

    /**
     * Tells if at least one of styles of this {@link View} has some customizations.
     * 
     * @return true if at least one of styles of this {@link View} has some customizations, false else
     */
    public boolean isCustomized() {
        boolean isCustomized = false;
        for (Object obj : view.getStyles()) {
            if (obj instanceof Style) {
                Style style = (Style) obj;
                for (EAttribute eAttribute : style.eClass().getEAllAttributes()) {
                    if (ViewQuery.CUSTOMIZABLE_GMF_EXCLUSIVE_STYLE_ATTRIBUTES.contains(eAttribute)) {
                        Object currentValue = style.eGet(eAttribute);
                        Object defaultValue = getDefaultValue(eAttribute);
                        if (currentValue != null && !currentValue.equals(defaultValue) || currentValue == null && defaultValue != null) {
                            isCustomized = true;
                            break;
                        }
                    }
                }
                if (isCustomized) {
                    break;
                }
            }
        }
        if (!isCustomized && (view instanceof Shape || view instanceof Connector)) {
            // manage Note and Text especially because style properties are
            // directly stored on Shape
            // manage NoteAttachment especially because style properties are
            // directly stored on Connector
            for (EAttribute eAttribute : view.eClass().getEAllAttributes()) {
                if (NotationPackage.Literals.STYLE.isSuperTypeOf(eAttribute.getEContainingClass()) && ViewQuery.CUSTOMIZABLE_GMF_STYLE_ATTRIBUTES.contains(eAttribute)) {
                    Object currentValue = view.eGet(eAttribute);
                    Object defaultValue = getDefaultValue(eAttribute);
                    if (currentValue != null && !currentValue.equals(defaultValue) || currentValue == null && defaultValue != null) {
                        isCustomized = true;
                        break;
                    }
                }
            }
        }
        return isCustomized;
    }

    /**
     * Get the default value of the specified {@link EAttribute} of the specified {@link View}.
     * 
     * @param eAttribute
     *            the specified {@link EAttribute} of the {@link View#getStyles()}
     * @return the default value of the specified {@link EAttribute} of the {@link View#getStyles()}
     */
    public Object getDefaultValue(EAttribute eAttribute) {
        Object defaultValue = null;
        IPreferenceStore preferenceStore = (IPreferenceStore) DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT.getPreferenceStore();
        if (eAttribute == NotationPackage.Literals.FONT_STYLE__BOLD) {
            FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);
            defaultValue = Boolean.valueOf((fontData.getStyle() & SWT.BOLD) != 0);
        } else if (eAttribute == NotationPackage.Literals.FONT_STYLE__ITALIC) {
            FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);
            defaultValue = Boolean.valueOf((fontData.getStyle() & SWT.ITALIC) != 0);
        } else if (eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_COLOR) {
            RGB fontRGB = PreferenceConverter.getColor(preferenceStore, IPreferenceConstants.PREF_FONT_COLOR);
            defaultValue = FigureUtilities.RGBToInteger(fontRGB).intValue();
        } else if (eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_NAME) {
            FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);
            defaultValue = fontData.getName();
        } else if (eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_NAME) {
            FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);
            defaultValue = fontData.getName();
        } else if (eAttribute == NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT) {
            FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);
            defaultValue = fontData.getHeight();
        } else if (eAttribute == NotationPackage.Literals.LINE_STYLE__LINE_COLOR) {
            RGB lineRGB = PreferenceConverter.getColor(preferenceStore, IPreferenceConstants.PREF_LINE_COLOR);
            if (ViewType.NOTE.equals(view.getType())) {
                lineRGB = PreferenceConverter.getColor(preferenceStore, IPreferenceConstants.PREF_NOTE_LINE_COLOR);
            }
            defaultValue = FigureUtilities.RGBToInteger(lineRGB).intValue();
        } else if (eAttribute == NotationPackage.Literals.FILL_STYLE__FILL_COLOR) {
            RGB fillRGB = PreferenceConverter.getColor(preferenceStore, IPreferenceConstants.PREF_FILL_COLOR);
            if (ViewType.NOTE.equals(view.getType())) {
                fillRGB = PreferenceConverter.getColor(preferenceStore, IPreferenceConstants.PREF_NOTE_FILL_COLOR);
            }
            defaultValue = FigureUtilities.RGBToInteger(fillRGB).intValue();
        } else if (eAttribute == NotationPackage.Literals.FILL_STYLE__TRANSPARENCY) {
            defaultValue = eAttribute.getDefaultValue();
            if (ViewType.NOTE.equals(view.getType())) {
                defaultValue = 0;
            }
        } else if (eAttribute == NotationPackage.Literals.LINE_STYLE__LINE_WIDTH) {
            defaultValue = eAttribute.getDefaultValue();
            if (ViewType.NOTE.equals(view.getType())) {
                defaultValue = 1;
            }
        } else if (eAttribute == NotationPackage.Literals.ROUTING_STYLE__JUMP_LINK_STATUS) {
            final IPreferencesService service = Platform.getPreferencesService();
            boolean isJumpLinkOverideEnabled = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE,
                    SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE_DEFAULT_VALUE, null);
            if (isJumpLinkOverideEnabled) {
                Option<JumpLinkStatus> overrideJumpLinkStatus = Options.newNone();
                int jumpLinkStats = service.getInt(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS, SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS_DEFAULT_VALUE, null);
                overrideJumpLinkStatus = Options.newSome(JumpLinkStatus.get(jumpLinkStats));
                if (overrideJumpLinkStatus.some()) {
                    defaultValue = overrideJumpLinkStatus.get();
                }
            }
            if (defaultValue == null) {
                defaultValue = eAttribute.getDefaultValue();
            }
        } else if (eAttribute == NotationPackage.Literals.ROUTING_STYLE__JUMP_LINK_TYPE) {
            final IPreferencesService service = Platform.getPreferencesService();
            boolean isJumpLinkOverideEnabled = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE,
                    SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE_DEFAULT_VALUE, null);
            if (isJumpLinkOverideEnabled) {
                Option<JumpLinkType> overrideJumpLinkType = Options.newNone();
                int jumpLinkType = service.getInt(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE, SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE_DEFAULT_VALUE, null);
                overrideJumpLinkType = Options.newSome(JumpLinkType.get(jumpLinkType));
                if (overrideJumpLinkType.some()) {
                    defaultValue = overrideJumpLinkType.get();
                }
            }
            if (defaultValue == null) {
                defaultValue = eAttribute.getDefaultValue();
            }
        } else if (eAttribute == NotationPackage.Literals.ROUTING_STYLE__JUMP_LINKS_REVERSE) {
            final IPreferencesService service = Platform.getPreferencesService();
            boolean isJumpLinkOverideEnabled = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE,
                    SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE_DEFAULT_VALUE, null);
            if (isJumpLinkOverideEnabled) {
                defaultValue = service.getBoolean(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK, SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK_DEFAULT_VALUE, null);
            }
            if (defaultValue == null) {
                defaultValue = eAttribute.getDefaultValue();
            }
        } else {
            defaultValue = eAttribute.getDefaultValue();
        }
        return defaultValue;
    }

    /**
     * Tests whether the queried View corresponds to a NameEditPart.
     * 
     * @return <code>true</code> if the queried View corresponds to a NameEditPart.
     */
    public boolean isForNameEditPart() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        boolean result = type == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID || type == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID || type == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID;
        result = result || type == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID;
        result = result || type == DNodeContainerNameEditPart.VISUAL_ID || type == DNodeContainerName2EditPart.VISUAL_ID;
        result = result || type == DNodeListNameEditPart.VISUAL_ID || type == DNodeListName2EditPart.VISUAL_ID;
        result = result || type == DEdgeNameEditPart.VISUAL_ID || type == DEdgeEndNameEditPart.VISUAL_ID;
        result = result || type == DEdgeBeginNameEditPart.VISUAL_ID;
        return result;
    }

    /**
     * Tests whether the queried View corresponds to an edge name edit part.
     * 
     * @return <code>true</code> if the queried View corresponds to an edge name edit part.
     */
    public boolean isForEdgeNameEditPart() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        boolean result = type == DEdgeNameEditPart.VISUAL_ID;
        result = result || type == DEdgeEndNameEditPart.VISUAL_ID;
        result = result || type == DEdgeBeginNameEditPart.VISUAL_ID;
        return result;
    }

    /**
     * Tests whether the queried View corresponds to a NameEditPart that is located on the border of its node.
     * 
     * @return <code>true</code> if the queried View corresponds to a NameEditPart.
     */
    public boolean isForNameEditPartOnBorder() {
        boolean result = false;
        if (isForNameEditPart()) {
            if (view.getElement() instanceof DStylizable) {
                DStylizable dStylizable = (DStylizable) view.getElement();
                if (dStylizable.getStyle() instanceof NodeStyle) {
                    if (LabelPosition.BORDER_LITERAL.equals(((NodeStyle) dStylizable.getStyle()).getLabelPosition())) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Tests whether the queried Node corresponds to a bordered node.
     * 
     * @return <code>true</code> if the queried View corresponds to a bordered node.
     */
    public boolean isBorderedNode() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
        return result;
    }

    /**
     * Tests whether the queried Node corresponds to a container (list or not).
     * 
     * @return <code>true</code> if the queried View corresponds to a container node.
     */
    public boolean isContainer() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        boolean result = type == DNodeContainer2EditPart.VISUAL_ID || type == DNodeContainerEditPart.VISUAL_ID || type == DNodeList2EditPart.VISUAL_ID || type == DNodeListEditPart.VISUAL_ID;
        return result;
    }

    /**
     * Return if this GMF node is associated to DNodeContainer Sirius diagram element with free form layout.
     */
    public boolean isFreeFormContainer() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        return type == DNodeContainerEditPart.VISUAL_ID || type == DNodeContainer2EditPart.VISUAL_ID;
    }

    /**
     * Return if this GMF node is compartment of node corresponding to a Sirius container free form.
     */
    public boolean isFreeFormCompartment() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        return type == DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID //
                || type == DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID;
    }

    /**
     * Return if this GMF node have vertical/horizontal stack layout.
     */
    public boolean isRegionContainer() {
        return this.view.getElement() instanceof DDiagramElement element //
                && element.getDiagramElementMapping() instanceof ContainerMapping mapping //
                && new ContainerMappingQuery(mapping).isRegionContainer();
    }

    /**
     * Return if this GMF node is associated to DNode Sirius diagram element.
     */
    public boolean isNode() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        return type == DNodeEditPart.VISUAL_ID //
                || type == DNode2EditPart.VISUAL_ID //
                || type == DNode3EditPart.VISUAL_ID //
                || type == DNode4EditPart.VISUAL_ID;
    }

    /**
     * Return if this GMF node is associated to label of DNode Sirius diagram element.
     */
    public boolean isNodeLabel() {
        int type = SiriusVisualIDRegistry.getVisualID(this.view.getType());
        return type == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID //
                || type == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID //
                || type == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID //
                || type == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID;
    }

    /**
     * Return the compartment of the GMF node container with "free form" layout.
     * 
     * @return the compartment or Optional.empty if view is not container or compartment not found
     */
    public Optional<View> getFreeFormContainerCompartment() {
        if (this.isFreeFormContainer()) {
            List<View> children = this.view.getChildren();
            return children.stream() //
                    .filter(child -> new ViewQuery(child).isFreeFormCompartment()) //
                    .findAny();
        } else {
            return Optional.empty();
        }
    }

    /**
     * Get the first ancestor, or itself, that has at least one of the <code>visualID</code>.
     * 
     * @param visualID
     *            List of visual ID that the ancestor must be.
     * 
     * @return An optional {@link View}
     */
    public Option<View> getAncestor(int... visualID) {
        Option<View> result = Options.newNone();
        int type = SiriusVisualIDRegistry.getVisualID(view.getType());
        for (int i = 0; i < visualID.length; i++) {
            if (type == visualID[i]) {
                result = Options.newSome(view);
                break;
            }
        }
        if (!result.some() && view.eContainer() instanceof View) {
            result = new ViewQuery((View) view.eContainer()).getAncestor(visualID);
        }
        return result;
    }

    /**
     * Return the {@link DDiagram} of the {@link Diagram} that is either the given view or a parent of the given view if
     * such element exists.
     * 
     * @return the {@link DDiagram} of the {@link Diagram} that is either the given view or a parent of the given view
     *         if such element exists.
     */
    public Optional<DDiagram> getDDiagram() {
        return getDDiagram(view);

    }

    /**
     * Return true if the view belong to a {@link DDiagram} with showing mode activated. False otherwise.
     * 
     * @return true if the view belong to a {@link DDiagram} with showing mode activated. False otherwise.
     */
    public boolean isInShowingMode() {
        Optional<DDiagram> dDiagram = getDDiagram(view);
        return dDiagram.isPresent() && dDiagram.get().isIsInShowingMode();

    }

    /**
     * Is the view a representation link note?
     * 
     * @return true if this is a representation link note, false otherwise
     */
    public boolean isRepresentationLink() {
        EAnnotation specificStyles = view.getEAnnotation(ViewQuery.SPECIFIC_STYLES);
        return specificStyles != null && specificStyles.getDetails().containsKey(REPRESENTATION_LINK_NOTE) && ViewType.NOTE.equals(view.getType());
    }

    /**
     * For a representation link, check if it refers to a deleted representation descriptor. Invocations should be
     * guarded by {@link #isRepresentationLink()}.
     * 
     * @return true if the representation link note view has a dangling target, false otherwise.
     */
    public boolean isRepresentationLinkBroken() {
        EObject element = view.getElement();
        return !(element instanceof DRepresentationDescriptor) || element.eIsProxy() || element.eResource() == null;
    }

    /**
     * Return the {@link DDiagram} of the {@link Diagram} that is either the given view or a parent of the given view if
     * such element exists.
     * 
     * @param tempView
     *            the {@link View} from which we try to get the {@link DDiagram}.
     * @return the {@link DDiagram} of the {@link Diagram} that is either the given view or a parent of the given view
     *         if such element exists.
     */
    private Optional<DDiagram> getDDiagram(View tempView) {
        Optional<DDiagram> result = Optional.empty();
        if (tempView instanceof Diagram && ((Diagram) tempView).getElement() instanceof DDiagram) {
            result = Optional.of((DDiagram) ((Diagram) tempView).getElement());
        } else if (tempView.eContainer() instanceof View) {
            result = getDDiagram((View) tempView.eContainer());
        }
        return result;
    }
}
