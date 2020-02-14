/**
 * Copyright (c) 2017, 2020 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.StyledString.Style;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Utility class used to store common operations for all the item providers.
 * 
 * @author sbegaudeau
 */
public final class Utils {

    /**
     * The style used by keywords.
     */
    public static final Style KEYWORD_STYLE = Style.newBuilder().setForegroundColor(URI.createURI("color://rgb/125/9/82")).setFont(IItemFontProvider.BOLD_FONT).toStyle(); //$NON-NLS-1$

    /**
     * The constructor.
     */
    private Utils() {
        // prevent instantiation
    }

    /**
     * Computes the label of the given object.
     * 
     * @param itemProviderAdapter
     *            The item provider adapter of the object
     * @param object
     *            The object
     * @param defaultLabelKey
     *            The key of the default label
     * @return The label or <code>null</code> if it is undefined
     */
    public static StyledString computeLabel(ItemProviderAdapter itemProviderAdapter, Object object, String defaultLabelKey) {
        StyledString styledString = new StyledString();
        if (object instanceof IdentifiedElement) {
            IdentifiedElement identifiedElement = (IdentifiedElement) object;
            styledString = Utils.computeIdentifiedElementLabel(itemProviderAdapter, identifiedElement, defaultLabelKey);
        } else if (object instanceof WidgetConditionalStyle) {
            WidgetConditionalStyle widgetConditionalStyle = (WidgetConditionalStyle) object;
            styledString = Utils.computeWidgetConditionalStyleLabel(itemProviderAdapter, widgetConditionalStyle, defaultLabelKey);
        } else if (object instanceof WidgetStyle) {
            WidgetStyle widgetStyle = (WidgetStyle) object;
            styledString = Utils.computeWidgetStyleLabel(itemProviderAdapter, widgetStyle, defaultLabelKey);
        }
        return styledString;
    }

    /**
     * Computes the label of the given identified element.
     * 
     * @param itemProviderAdapter
     *            The {@link ItemProviderAdapter}
     * @param identifiedElement
     *            The Identified Element
     * @param defaultLabelKey
     *            The default label key
     * @return The label of the element
     */
    private static StyledString computeIdentifiedElementLabel(ItemProviderAdapter itemProviderAdapter, IdentifiedElement identifiedElement, String defaultLabelKey) {
        String label = Optional.ofNullable(identifiedElement.getLabel()).orElse(""); //$NON-NLS-1$
        if (label.isEmpty()) {
            label = Optional.ofNullable(identifiedElement.getName()).filter(id -> !id.isEmpty()).orElseGet(() -> itemProviderAdapter.getString(defaultLabelKey));
        }
        StyledString styledString = new StyledString(label);

        EStructuralFeature eStructuralFeature = identifiedElement.eClass().getEStructuralFeature("extends"); //$NON-NLS-1$
        if (eStructuralFeature instanceof EReference && identifiedElement.eIsSet(eStructuralFeature)) {
            Object extendsValue = identifiedElement.eGet(eStructuralFeature);
            styledString.append(" extends ", KEYWORD_STYLE); //$NON-NLS-1$
            styledString.append(Utils.computeSimpleLabel(itemProviderAdapter, extendsValue));
        }

        return styledString;
    }

    /**
     * Computes the label of the given identified element.
     * 
     * @param itemProviderAdapter
     *            The {@link ItemProviderAdapter}
     * @param widgetConditionalStyle
     *            The widget conditional style
     * @param defaultLabelKey
     *            The default label key
     * @return The label of the element
     */
    private static StyledString computeWidgetConditionalStyleLabel(ItemProviderAdapter itemProviderAdapter, WidgetConditionalStyle widgetConditionalStyle, String defaultLabelKey) {
        String label = Optional.ofNullable(widgetConditionalStyle.getPreconditionExpression()).orElse(""); //$NON-NLS-1$
        if (label.isEmpty()) {
            label = itemProviderAdapter.getString(defaultLabelKey);
        }
        return new StyledString(label);
    }

    /**
     * Computes the label of the given identified element.
     * 
     * @param itemProviderAdapter
     *            The {@link ItemProviderAdapter}
     * @param widgetStyle
     *            The widget style
     * @param defaultLabelKey
     *            The default label key
     * @return The label of the element
     */
    private static StyledString computeWidgetStyleLabel(ItemProviderAdapter itemProviderAdapter, WidgetStyle widgetStyle, String defaultLabelKey) {
        String label = Optional.ofNullable(widgetStyle.getLabelFontNameExpression()).orElse(""); //$NON-NLS-1$
        if (label.isEmpty()) {
            label = itemProviderAdapter.getString(defaultLabelKey);
        }
        return new StyledString(label);
    }

    /**
     * Returns the label of the given object.
     * 
     * @param itemProviderAdapter
     *            The Item Provider Adapter
     * @param object
     *            The object
     * @return Its Label
     */
    private static String computeSimpleLabel(ItemProviderAdapter itemProviderAdapter, Object object) {
        if (object instanceof IdentifiedElement) {
            IdentifiedElement identifiedElement = (IdentifiedElement) object;
            String label = Optional.ofNullable(identifiedElement.getLabel()).orElse(""); //$NON-NLS-1$
            if (label.isEmpty()) {
                label = Optional.ofNullable(identifiedElement.getName()).filter(id -> !id.isEmpty())
                        .orElseGet(() -> itemProviderAdapter.getString("_UI_" + identifiedElement.eClass().getName() + "_type")); //$NON-NLS-1$ //$NON-NLS-2$
            }
            return label;
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Add default "Begin" operations with a no-op navigation to the specific element.
     *
     * @param child
     *            a newly created child.
     */
    public static void addNoopNavigationOperations(Object child) {
        if (child instanceof EObject) {
            EObject obj = (EObject) child;
            for (EReference ref : obj.eClass().getEAllReferences()) {
                if (ref.isContainment() && ref.getEReferenceType() == ToolPackage.Literals.INITIAL_OPERATION) {
                    InitialOperation begin = ToolFactory.eINSTANCE.createInitialOperation();
                    ChangeContext noop = ToolFactory.eINSTANCE.createChangeContext();
                    noop.setBrowseExpression("var:self"); //$NON-NLS-1$
                    begin.setFirstModelOperations(noop);
                    obj.eSet(ref, begin);
                }
            }
        }
    }
}
