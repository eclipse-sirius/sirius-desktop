/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.api.query;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

import com.google.common.base.Preconditions;

/**
 * A class aggregating all the queries (read-only!) having a {@link DCell} as a starting point.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class DCellQuery {

    /** Default DTableElement style. */
    public static final DTableElementStyle DEFAULT_STYLE = TableFactory.eINSTANCE.createDTableElementStyle();

    private final DCell cell;

    /**
     * Creates a new query.
     *
     * @param cell
     *            the cell to query.
     */
    public DCellQuery(DCell cell) {
        Preconditions.checkNotNull(cell);
        this.cell = cell;
    }

    /**
     * We use the first conditional cell style (with predicate expression that returns true) <BR>
     * Otherwise, we use the first conditional line style (with predicate expression that returns true) <BR>
     * Otherwise, we use the first conditional column style (with predicate expression that returns true) <BR>
     * Otherwise, we use the default cell style if it exists Otherwise, we use the default line style if it exists <BR>
     * Otherwise, we use the default column style if it exists.
     *
     * @return an optional DTableElementStyle to use for the foreground of this cell (one of cell, line or column).
     */
    public Option<DTableElementStyle> getForegroundStyleToApply() {
        DTableElementStyle styleToApply = null;
        DCellStyle currentCellStyle = cell.getCurrentStyle();
        boolean cellStyleWithDefaultValue = false;
        if (currentCellStyle != null && !currentCellStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())
                && !currentCellStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat())
                && !currentCellStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
            // Ignore the current cell style because it's just for background
            // style
            cellStyleWithDefaultValue = true;
            currentCellStyle = null;
        }

        DTableElementStyle currentLineStyle = null;
        if (cell.getLine() != null) {
            currentLineStyle = cell.getLine().getCurrentStyle();
            if (currentLineStyle != null && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())
                    && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat())
                    && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                // Ignore the current cell style because it's just for
                // background style
                currentLineStyle = null;
            }
        }

        DTableElementStyle currentColumnStyle = null;
        if (cell.getColumn() != null) {
            currentColumnStyle = cell.getColumn().getCurrentStyle();
            if (currentColumnStyle != null && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())
                    && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat())
                    && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                // Ignore the current cell style because it's just for
                // background style
                currentColumnStyle = null;
            }
        }

        if (currentCellStyle != null) {
            if (!(currentCellStyle.isDefaultForegroundStyle())) {
                if (currentCellStyle.getForegroundStyleOrigin() instanceof IntersectionMapping) {
                    // We use the first conditional cell style
                    styleToApply = currentCellStyle;
                } else if (currentLineStyle != null && !currentLineStyle.isDefaultForegroundStyle()) {
                    // We use the first conditional line style
                    styleToApply = currentLineStyle;
                } else {
                    // We use the first conditional column style
                    styleToApply = currentCellStyle;
                }
            } else {
                if (currentLineStyle != null && !currentLineStyle.isDefaultForegroundStyle()) {
                    // We use the first conditional line style
                    styleToApply = currentLineStyle;
                } else if (currentCellStyle.getForegroundStyleOrigin() instanceof IntersectionMapping) {
                    // We use the default cell style
                    styleToApply = currentCellStyle;
                } else if (currentLineStyle != null && currentLineStyle.isDefaultForegroundStyle()) {
                    // We use the default line style
                    styleToApply = currentLineStyle;
                } else if (currentCellStyle.getForegroundStyleOrigin() instanceof ColumnMapping) {
                    // We use the default column style (with variable color)
                    styleToApply = currentCellStyle;
                } else if (currentColumnStyle != null && currentColumnStyle.isDefaultForegroundStyle()) {
                    // We use the default column style (with fixed color)
                    styleToApply = currentColumnStyle;
                }
            }
        } else if (currentLineStyle != null) {
            // We use the line style (first conditional or default)
            styleToApply = currentLineStyle;
        } else if (currentColumnStyle != null) {
            // We use the default column style (with fixed color)
            styleToApply = currentColumnStyle;
        }

        return getStyleToApply(styleToApply, cellStyleWithDefaultValue);
    }

    private Option<DTableElementStyle> getStyleToApply(DTableElementStyle styleToApply, boolean cellStyleWithDefaultValue) {
        Option<DTableElementStyle> result;
        if (styleToApply == null) {
            if (cellStyleWithDefaultValue) {
                // The default style of a Cell have only default value
                // (attributes not set) but we send it anyway for the default of
                // font size and font type (normal).
                result = Options.newSome((DTableElementStyle) cell.getCurrentStyle());
            } else {
                // This should happens if the style used only default value
                // (unset value) so in this case we don't create a style.
                result = Options.newSome(DEFAULT_STYLE);
            }
        } else {
            result = Options.newSome(styleToApply);
        }
        return result;
    }

    /**
     * We use the first conditional cell style (with predicate expression that returns true) <BR>
     * Otherwise, we use the first conditional line style (with predicate expression that returns true) <BR>
     * Otherwise, we use the first conditional column style (with predicate expression that returns true) <BR>
     * Otherwise, we use the default cell style if it exists Otherwise, we use the default line style if it exists <BR>
     * Otherwise, we use the default column style if it exists.
     *
     * @return an optional DTableElementStyle to use for the foreground of this cell (one of cell, line or column).
     */
    public Option<DTableElementStyle> getBackgroundStyleToApply() {
        DTableElementStyle styleToApply = null;
        DCellStyle currentCellStyle = cell.getCurrentStyle();
        boolean cellStyleWithDefaultValue = false;
        if (currentCellStyle != null && !currentCellStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
            // Ignore the current cell style because it's just for foreground
            // style
            cellStyleWithDefaultValue = true;
            currentCellStyle = null;
        }

        DTableElementStyle currentLineStyle = null;
        if (cell.getLine() != null) {
            currentLineStyle = cell.getLine().getCurrentStyle();
            if (currentLineStyle != null && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                // Ignore the current cell style because it's just for
                // foreground style
                currentLineStyle = null;
            }
        }

        DTableElementStyle currentColumnStyle = null;
        if (cell.getColumn() != null) {
            currentColumnStyle = cell.getColumn().getCurrentStyle();
            if (currentColumnStyle != null && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                // Ignore the current cell style because it's just for
                // foreground style
                currentColumnStyle = null;
            }
        }

        if (currentCellStyle != null) {
            if (!(currentCellStyle.isDefaultBackgroundStyle())) {
                if (currentCellStyle.getBackgroundStyleOrigin() instanceof IntersectionMapping) {
                    // We use the first conditional cell style
                    styleToApply = currentCellStyle;
                } else if (currentLineStyle != null && !currentLineStyle.isDefaultBackgroundStyle()) {
                    // We use the first conditional line style
                    styleToApply = currentLineStyle;
                } else {
                    // We use the first conditional column style
                    styleToApply = currentCellStyle;
                }
            } else {
                if (currentLineStyle != null && !currentLineStyle.isDefaultBackgroundStyle()) {
                    // We use the first conditional line style
                    styleToApply = currentLineStyle;
                } else if (currentCellStyle.getBackgroundStyleOrigin() instanceof IntersectionMapping) {
                    // We use the default cell style
                    styleToApply = currentCellStyle;
                } else if (currentLineStyle != null && currentLineStyle.isDefaultBackgroundStyle()) {
                    // We use the default line style
                    styleToApply = currentLineStyle;
                } else if (currentCellStyle.getBackgroundStyleOrigin() instanceof ColumnMapping) {
                    // We use the default column style (with variable color)
                    styleToApply = currentCellStyle;
                } else if (currentColumnStyle != null && currentColumnStyle.isDefaultBackgroundStyle()) {
                    // We use the default column style (with fixed color)
                    styleToApply = currentColumnStyle;
                }
            }
        } else if (currentLineStyle != null) {
            // We use the line style (first conditional or default)
            styleToApply = currentLineStyle;
        } else if (currentColumnStyle != null) {
            // We use the default column style (with fixed color)
            styleToApply = currentColumnStyle;
        }

        return getStyleToApply(styleToApply, cellStyleWithDefaultValue);
    }

    /**
     * Check if the <code>style</code> ({@link ForegroundStyleDescription} or {@link BackgroundStyleDescription}) is the
     * style of the intersectionMapping of the current <code>cell</code>.
     *
     * @param style
     *            The style to check ({@link ForegroundStyleDescription} or {@link BackgroundStyleDescription})
     * @return true if the style is the style of the intersectionMapping of the current <code>cell</code>, false
     *         otherwise
     */
    public boolean isStyleDescriptionInIntersectionMapping(EObject style) {
        if (!(style instanceof ForegroundStyleDescription || style instanceof BackgroundStyleDescription)) {
            throw new IllegalArgumentException(Messages.Table_WrongStyleAttribute);
        }
        IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
        if (intersectionMapping != null) {
            EObject styleContainer = style.eContainer();
            if (intersectionMapping.equals(styleContainer) || (styleContainer != null && intersectionMapping.equals(styleContainer.eContainer()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the column index of this cell in the table.
     *
     * @return the column index of this cell in the table.
     */
    public int getColumnIndex() {
        return new DColumnQuery(cell.getColumn()).getColumnIndex();
    }

    /**
     * When a cell belongs to a column defined by a FeatureColumnMapping with an empty label computation expression, the
     * item property descriptor is used to compute the DCell label to display. For non Boolean and non EEnum data types,
     * org.eclipse.emf.edit.provider.ItemPropertyDescriptor.ItemDelegator .convert(EDataType, Object) crops the obtained
     * value at the first found char on which Character.isISOControl return true, the "..." is added to the label.
     *
     * This method return the label value before the crop.
     *
     * @return the complete label of the cell.
     */
    public String getExportableLabel() {
        String label = cell.getLabel();
        CellUpdater updater = cell.getUpdater();
        if (updater instanceof FeatureColumnMapping && cell.getTarget() != null) {
            String featureName = ((FeatureColumnMapping) updater).getFeatureName();
            if (!StringUtil.isEmpty(featureName) && StringUtil.isEmpty(updater.getLabelComputationExpression())) {
                ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(cell);
                EObject target = cell.getTarget();
                EStructuralFeature eStructuralFeature = null;
                Object value = null;
                try {
                    value = modelAccessor.eGet(cell.getTarget(), featureName);
                    eStructuralFeature = target.eClass().getEStructuralFeature(featureName);
                } catch (FeatureNotFoundException e) {
                    // Do nothing
                }

                if (value instanceof String) {
                    label = (String) value;
                } else if (eStructuralFeature instanceof EAttribute && !(value instanceof Boolean || value instanceof EEnum)) {
                    // See
                    // org.eclipse.emf.edit.provider.ItemPropertyDescriptor.ItemDelegator.getText(Object)
                    // and
                    // org.eclipse.emf.edit.provider.ItemPropertyDescriptor.ItemDelegator.convert(EDataType,
                    // Object)
                    EDataType eDataType = ((EAttribute) eStructuralFeature).getEAttributeType();
                    label = EcoreUtil.convertToString(eDataType, value);
                }
            }
        }
        return label;
    }
}
