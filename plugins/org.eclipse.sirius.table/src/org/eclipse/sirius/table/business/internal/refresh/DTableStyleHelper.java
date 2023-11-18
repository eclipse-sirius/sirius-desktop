/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.business.internal.refresh;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.business.api.query.DTableElementStyleQuery;
import org.eclipse.sirius.table.business.api.query.StyleUpdaterQuery;
import org.eclipse.sirius.table.business.internal.color.TableStyleColorUpdater;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;

/**
 * Synchronizer for the table elements style.
 * 
 * @author cbrun
 */
public class DTableStyleHelper {

    private final RuntimeLoggerInterpreter interpreter;
    
    /**
     * Synchronizer for table elements.
     * 
     * @param interpreter
     *            current interpreter.
     */
    public DTableStyleHelper(final RuntimeLoggerInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    /**
     * Refresh the cell styles.
     * 
     * @param cell
     *            cell to refresh.
     */
    public void refresh(final DCell cell) {
        DCellStyle style = cell.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDCellStyle();
        }
        doUpdateStyle(cell, style);
        if (cell.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            cell.setCurrentStyle(style);
        }
    }

    private void reset(EObject target, EStructuralFeature feature) {
        if (target.eIsSet(feature)) { // applicable for simple or multi-value
            target.eUnset(feature);
        }
    }

    /**
     * The columnUpdater takes priority over the lineUpdater.
     * 
     * @param cell
     * 
     * @param style
     */
    private void doUpdateStyle(final DCell cell, final DCellStyle style) {
        // Get the style updater for the column
        ColumnMapping columnMapping = null;
        StyleUpdater columnStyleUpdater = null;
        DColumn column = cell.getColumn();
        if (column != null) { // Suspicious
            columnMapping = column.getOriginMapping();
            if (columnMapping instanceof FeatureColumnMapping || columnMapping instanceof ElementColumnMapping) {
                columnStyleUpdater = (StyleUpdater) columnMapping;
            }
        }

        doUpdateFgStyle(cell, style, columnMapping, columnStyleUpdater);
        doUpdateBgStyle(cell, style, columnMapping, columnStyleUpdater);
    }

    private void doUpdateFgStyle(final DCell cell, final DCellStyle style, ColumnMapping columnMapping,
            StyleUpdater columnStyleUpdater) {
        IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
        final StyleWithDefaultStatus bestForegroundStyle = getBestForegroundStyle(cell, intersectionMapping, columnStyleUpdater);
        if (bestForegroundStyle != null) {
            ForegroundStyleDescription bestForegroundStyleDesc = (ForegroundStyleDescription) bestForegroundStyle.getStyle();
            
            new TableStyleColorUpdater().updateForegroundColor(style, 
                    ((ForegroundStyleDescription) bestForegroundStyle.getStyle()).getForeGroundColor(), 
                    bestForegroundStyle.isDefaultStyle(), cell.getTarget());
            
            setStyleOrigin(cell, style, bestForegroundStyle, false);
            
            if (!Objects.equals(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat());
            }
            if (style.getLabelSize() != bestForegroundStyleDesc.getLabelSize()) {
                style.setLabelSize(bestForegroundStyleDesc.getLabelSize());
            }
        } else {
            resetStyle(style, true);
        }
    }

    private void doUpdateBgStyle(final DCell cell, final DCellStyle style, ColumnMapping columnMapping,
            StyleUpdater columnStyleUpdater) {
        IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
        final StyleWithDefaultStatus bestBackgroundStyle = getBestBackgroundColor(cell, intersectionMapping, columnStyleUpdater);
        if (bestBackgroundStyle != null) {
            new TableStyleColorUpdater().updateBackgroundColor(style, 
                    ((BackgroundStyleDescription) bestBackgroundStyle.getStyle()).getBackgroundColor(), 
                    bestBackgroundStyle.isDefaultStyle(), cell.getTarget());
            
            setStyleOrigin(cell, style, bestBackgroundStyle, false);
        } else {
            resetStyle(style, false);
        }
    }

    private void setStyleOrigin(DCell cell, DCellStyle style, StyleWithDefaultStatus bestStyle, boolean foreground) {
        EObject styleDescription = bestStyle.getStyle();
        TableMapping styleOrigin = cell.getColumn().getOriginMapping();
        if (cell.getIntersectionMapping() != null 
                && new DCellQuery(cell).isStyleDescriptionInIntersectionMapping(styleDescription)) {
            styleOrigin = cell.getIntersectionMapping();
        }
        if (foreground) {
            if (style.getForegroundStyleOrigin() != styleOrigin) {
                style.setForegroundStyleOrigin(styleOrigin);
            }
        } else { // background
            if (style.getBackgroundStyleOrigin() != styleOrigin) {
                style.setBackgroundStyleOrigin(styleOrigin);
            }            
        }
    }
    
    /**
     * Refresh the line styles.
     * 
     * @param line
     *            line to refresh.
     */
    public void refresh(final DLine line) {
        DTableElementStyle style = line.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDTableElementStyle();
        }
        doUpdateStyle(line, style);
        if (line.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            line.setCurrentStyle(style);
        }
    }

    /**
     * Compute the best styles for this line. This rules are applied :
     * <UL>
     * <LI>the first conditional foreground style (with predicate expression that returns true), otherwise the default
     * foreground style</LI>
     * <LI>the first conditional background style (with predicate expression that returns true), otherwise the default
     * background style</LI>
     * <UL>
     * 
     * @param line
     *            The line to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DLine line, final DTableElementStyle style) {
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final LineMapping originMapping = line.getOriginMapping();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(line, originMapping);

        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, 
                    new StyleUpdaterQuery(originMapping).isDefaultBackgroundColor(bestBackgroundColor), 
                    line.getTarget());
        } else {
            resetStyle(style, false);
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(line, originMapping);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(originMapping).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, line.getTarget());
            if (!Objects.equals(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            resetStyle(style, true);
        }
    }

    private void resetStyle(final DTableElementStyle style, boolean foreground) {
        if (foreground) {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat());
        } else {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
        }
    }

    
    /**
     * Refresh the column styles.
     * 
     * @param column
     *            column to refresh.
     */
    public void refresh(final DColumn column) {
        final ColumnMapping mapping = column.getOriginMapping();
        if (mapping.getInitialWidth() != 0 && column.getWidth() == 0) {
            column.setWidth(mapping.getInitialWidth());
        }
        
        DTableElementStyle style = column.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDTableElementStyle();
        }
        doUpdateStyle(column, style);
        if (column.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            column.setCurrentStyle(style);
        }
    }

    /**
     * Compute the best styles for this column. This rules are applied :
     * <UL>
     * <LI>the default foreground style (and only if the color use for it is not with variable parts: ComputedColor or
     * InterpolatedColor)</LI>
     * <LI>the default background style (and only if the color use for it is not with variable parts: ComputedColor or
     * InterpolatedColor)</LI>
     * <UL>
     * 
     * @param column
     *            The column to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DColumn column, final DTableElementStyle style) {
        StyleUpdater styleUpdater = null;
        ColumnMapping originMapping = column.getOriginMapping();
        if (originMapping instanceof FeatureColumnMapping || originMapping instanceof ElementColumnMapping) {
            styleUpdater = (StyleUpdater) originMapping;
        }
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(column, styleUpdater);
        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, new StyleUpdaterQuery(styleUpdater).isDefaultBackgroundColor(bestBackgroundColor), column.getTarget());
        } else {
            resetStyle(style, false);
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(column, styleUpdater);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(styleUpdater).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, column.getTarget());
            if (!Objects.equals(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            resetStyle(style, true);
        }
    }

    /**
     * Gets the applicable background color of a cell.
     * <p>
     * The first conditional background style of the cell (with predicate expression that returns true). In this case
     * the backgroundStyleOrigin references the intersection mapping and the defaultStyle is equal false. <BR />
     * Otherwise the first conditional background style of the column (with predicate expression that returns true). In
     * this case the backgroundStyleOrigin references the column mapping and the defaultStyle is equal false. <BR />
     * Otherwise, if it exists, the default background style of the cell. In this case the backgroundStyleOrigin
     * references the intersection mapping and the defaultStyle is equal true. <BR />
     * Otherwise, if the default background style of the column uses variable color, the default background style of the
     * column. In this case the backgroundStyleOrigin references the column mapping and the defaultStyle is equal true.
     * </p>
     * 
     * @param cell
     *            The current Cell
     * @param cellStyleUpdater
     *            The associate {@link StyleUpdater cell updater}
     * @param columnStyleUpdater
     *            The associate {@link StyleUpdater column updater}
     * @return The best colorMapping for this cell, or null otherwise
     */
    private StyleWithDefaultStatus getBestBackgroundColor(final DCell cell, final StyleUpdater cellStyleUpdater, final StyleUpdater columnStyleUpdater) {
        return getBestColorStyle(cell, cellStyleUpdater, columnStyleUpdater, 
                styleUpdater -> getApplicableBgConditionalStyle(cell, styleUpdater),
                styleUpdater -> getDefaulBgStyle(styleUpdater));
    }

    /**
     * Gets the applicable style of a cell.
     * <p>
     * The priority of the StyleUpdater is (the highest priority to lowest priority).
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * </p>
     * 
     * @param <S> Style description
     * @param cell to get style for
     * @param cellStyleUpdater of the cell
     * @param columnStyleUpdater of the column of the cell
     * @param conditionalStyleGetter provider from StyleUpdater
     * @param defaultStyleGetter provider from StyleUpdater
     * @return Best style
     */
    private <S extends EObject> StyleWithDefaultStatus getBestColorStyle(final DCell cell, 
            final StyleUpdater cellStyleUpdater, 
            final StyleUpdater columnStyleUpdater,
            Function<StyleUpdater, S> conditionalStyleGetter, 
            Function<StyleUpdater, S> defaultStyleGetter) {

        // Preferred style
        S descr = conditionalStyleGetter.apply(cellStyleUpdater);

        if (descr == null) { // then conditional of column
            descr = conditionalStyleGetter.apply(columnStyleUpdater);            
        }
        boolean conditional = descr != null;

        // Use the default style
        if (descr == null) { // then default cell style
            descr = defaultStyleGetter.apply(cellStyleUpdater);
        }
        if (descr == null) { // the default column
            descr = defaultStyleGetter.apply(columnStyleUpdater);
            if (descr instanceof FixedColor) {
                descr = null;    
            }
        }
        
        if (descr != null) {
            return new StyleWithDefaultStatus(descr, !conditional);
        } else {
            return null;
        }
    }


    private BackgroundStyleDescription getDefaulBgStyle(final StyleUpdater styleUpdater) {
        if (styleUpdater != null 
                && styleUpdater.getDefaultBackground() != null 
                && styleUpdater.getDefaultBackground().getBackgroundColor() != null) {
            return styleUpdater.getDefaultBackground();
        }
        return null;
    }
    
    private BackgroundStyleDescription getApplicableBgConditionalStyle(final DSemanticDecorator element, final StyleUpdater styleUpdater) {
        return getApplicableConditionalStyle(element, styleUpdater, 
                StyleUpdater::getBackgroundConditionalStyle,
                BackgroundConditionalStyle::getStyle,
                DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression());
    }
    
    private ForegroundStyleDescription getDefaulFgStyle(final StyleUpdater styleUpdater) {
        if (styleUpdater != null 
                && styleUpdater.getDefaultForeground() != null 
                && styleUpdater.getDefaultForeground().getForeGroundColor() != null) {
            return styleUpdater.getDefaultForeground();
        }
        return null;
    }
    
    private ForegroundStyleDescription getApplicableFgConditionalStyle(final DTableElement element, final StyleUpdater styleUpdater) {
        return getApplicableConditionalStyle(element, styleUpdater, 
                StyleUpdater::getForegroundConditionalStyle,
                ForegroundConditionalStyle::getStyle,
                DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression());
    }
    
    private <S extends EObject, C extends EObject> S getApplicableConditionalStyle(
            final DSemanticDecorator element, final StyleUpdater styleUpdater, 
            Function<StyleUpdater, List<C>> conditionalsProvider, Function<C, S> styleProvider,
            EStructuralFeature feat) {
        EObject self = element.getTarget();
        if (self == null || styleUpdater == null) {
            return null;
        }
        
        S result = null;
        for (C condStyle : conditionalsProvider.apply(styleUpdater)) {
            if (styleProvider.apply(condStyle) != null // incomplete style are ignored
                    && interpreter.evaluateBoolean(self, condStyle, feat)) {
                result = styleProvider.apply(condStyle);
                break;
            }
        }
        return result;
    }


    /**
     * Return a value only if the current background color is the default background color or one of the conditional
     * background colors.
     * 
     * @param line
     *            The current DLine
     * @param styleUpdater
     *            style update of the line
     * @return The best background color for this line, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DLine line, final StyleUpdater styleUpdater) {
        BackgroundStyleDescription descr = getApplicableBgConditionalStyle(line, styleUpdater);
        if (descr == null) { // then default cell style
            descr = getDefaulBgStyle(styleUpdater);
        }
        if (descr != null && descr.getBackgroundColor() != null) {
            return descr.getBackgroundColor();
        }
        return null;
    }

    /**
     * The best background color is the default background color if it uses a FixedColor.
     * 
     * @param column
     *            The current DLine
     * @param styleUpdater
     *            style update of the column
     * @return The default background color if it uses a FixedColor for this column, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DColumn column, final StyleUpdater styleUpdater) {
        BackgroundStyleDescription descr = getDefaulBgStyle(styleUpdater);
        if (descr != null && styleUpdater.getDefaultBackground().getBackgroundColor() instanceof FixedColor) {
            return styleUpdater.getDefaultBackground().getBackgroundColor();
        }
        return null;
    }

    /**
     * Gets the applicable style of a cell.
     * <p>
     * The priority of the StyleUpdater is (the highest priority to lowest priority).
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * 
     * @param cell
     *            The current Cell
     * @param cellStyleUpdater
     *            The associate {@link StyleUpdater cell updater}
     * @param columnStyleUpdater
     *            The associate {@link StyleUpdater column updater}
     * @return The best ForegroundStyleDescription for this cell, or null otherwise
     */
    private StyleWithDefaultStatus getBestForegroundStyle(final DCell cell, final StyleUpdater cellStyleUpdater, final StyleUpdater columnStyleUpdater) {
        return getBestColorStyle(cell, cellStyleUpdater, columnStyleUpdater, 
                styleUpdater -> getApplicableFgConditionalStyle(cell, styleUpdater),
                styleUpdater -> getDefaulFgStyle(styleUpdater));
    }

    /**
     * Gets the applicable style of a line.
     * <p>
     * The priority of the StyleUpdater is (the highest priority to lowest priority).
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * 
     * @param line
     *            The current DLine
     * @param styleUpdater
     *            The associate {@link StyleUpdater updater}
     * @return The best ForegroundStyleDescription for this cell, or null otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DLine line, final StyleUpdater styleUpdater) {
        ForegroundStyleDescription result = getApplicableFgConditionalStyle(line, styleUpdater);
        if (result == null) {
            result = styleUpdater.getDefaultForeground();
        }
        return result;
    }

    /**
     * The best foreground style is the default foreground style if it uses a FixedColor.
     * 
     * @param column
     *            The current DLine
     * @param styleUpdater
     *            The associate {@link StyleUpdater updater}
     * @return The default foreground style if it uses a FixedColor for this column, or null otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DColumn column, final StyleUpdater styleUpdater) {
        ForegroundStyleDescription bestForegroundStyleDescription = null;
        if (styleUpdater.getDefaultForeground() != null && styleUpdater.getDefaultForeground().getForeGroundColor() instanceof FixedColor) {
            bestForegroundStyleDescription = styleUpdater.getDefaultForeground();
        }
        return bestForegroundStyleDescription;
    }


}
